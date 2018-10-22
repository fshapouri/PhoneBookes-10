package dao;

import model.entity.ContactEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {
    private final static ContactDao instance= new ContactDao();
    private String drURL = "jdbc:derby://localhost:1527/c:/derbyDB;create=true";
    private Connection cn = null;
    private Statement st = null;

    private ContactDao(){}

    public static ContactDao getInstance(){return instance;}

    private void open() throws Exception {
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        cn = DriverManager.getConnection(drURL);
        st = cn.createStatement();
    }

    public boolean insertContact(ContactEntity contactEntity) {
        boolean result = false;
        try {
            open();
            String sql = "INSERT INTO CONTACT (FIRSTNAME,LASTNAME,PHONEBOOK_ID) VALUES (?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, contactEntity.getFirstname());
            ps.setString(2, contactEntity.getLastname());
            ps.setInt(3, contactEntity.getPhoneBookId());
//            ResultSet results = st.executeQuery("select PHONEBOOK_ID from  PHONEBOOK WHERE PHONEBOOKNAME='" + phoneBookName + "'");
//            while (results.next()) {
//                int id = results.getInt("PHONEBOOK_ID");
//                ps.setInt(3, id);
//            }
            ps.executeUpdate();
            ps.close();
            close();
            result = true;
        } catch (Exception sqlExcept) {
            if (sqlExcept.getMessage().equals("Table/View 'CONTACT' does not exist.")) {
                try {
                    st.execute("CREATE TABLE CONTACT(CONTACT_ID INTEGER NOT NULL primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1 )" + "" + ",FIRSTNAME VARCHAR(50), LASTNAME VARCHAR (50), PHONEBOOK_ID INTEGER references PHONEBOOK (PHONEBOOK_ID))");
                    insertContact(contactEntity);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                sqlExcept.printStackTrace();
                result = false;
            }
        }
        return result;
    }

    public boolean searchContactById(ContactEntity contactEntity) {
        boolean result = false;
        try {
            open();
            String sql = "select * from CONTACT INNER JOIN PHONEBOOK ON CONTACT.PHONEBOOK_ID=PHONEBOOK.PHONEBOOK_ID";
            ResultSet resultset = st.executeQuery(sql);
            while (resultset.next()) {
//             phoneBookEntity.setPhonebookName(resultset.getString(1));
                if ((resultset.getString("FIRSTNAME").equals(contactEntity.getFirstname())) && (resultset.getString("LASTNAME").equals(contactEntity.getLastname())))
                    result = true;
                else result = false;
            }
            resultset.close();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean searchByPhoneId(ContactEntity contactEntity){
        boolean result=false;
        try {
            open();
            String query="select * from CONTACT WHERE PHONEBOOK_ID="+contactEntity.getPhoneBookId()+"";
            ResultSet resultSet=st.executeQuery(query);
            while (resultSet.next()){
                result=true;
            }
        }catch (Exception e){
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    public boolean searchContactByName(ContactEntity contactEntity) {
        boolean result=false;
        try {
            open();
            String sql = "select * from CONTACT WHERE FIRSTNAME= '"+contactEntity.getFirstname()+"' AND LASTNAME='"+contactEntity.getLastname()+"' ";
            ResultSet resultset = st.executeQuery(sql);
            while (resultset.next()) {
//                if ((resultset.getString("FIRSTNAME").equals(contactEntity.getFirstname())) && (resultset.getString("LASTNAME").equals(contactEntity.getLastname())))
                result=true;
//                else  result=false;
            }
            resultset.close();
            close();
        } catch (Exception e) {
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    public int selectContact(ContactEntity contactEntity) {
        int contactId = 0;
        try {
            open();
            String query = "select CONTACT_ID from CONTACT WHERE (FIRSTNAME='" + contactEntity.getFirstname() + "' AND LASTNAME= '" + contactEntity.getLastname() + "')";
            ResultSet resultset = st.executeQuery(query);
            while (resultset.next()) {
                contactId = resultset.getInt("CONTACT_ID");
            }
            resultset.close();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactId;
    }

    public List<ContactEntity> selectbyPhoneBookId(ContactEntity contactEntity){
        List<ContactEntity> contactEntityList=new ArrayList<ContactEntity>();
        try {
            open();
            String query="SELECT CONTACT_ID FROM CONTACT WHERE CONTACT.PHONEBOOK_ID= "+contactEntity.getPhoneBookId()+"";
            ResultSet resultSet=st.executeQuery(query);
            while (resultSet.next()){
                ContactEntity conEntity=new ContactEntity();
                conEntity.setContactId(resultSet.getInt("CONTACT_ID"));
                contactEntityList.add(conEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactEntityList;
    }

    public void displayContact(ContactEntity contactEntity) {
        try {
            open();
            String query = "SELECT PHONENUMBER.NUMBERS , PHONENUMBER.TYPES FROM CONTACT INNER JOIN PHONENUMBER on CONTACT.CONTACT_ID = PHONENUMBER.CONTACT_ID WHERE (FIRSTNAME='" + contactEntity.getFirstname() + "' AND LASTNAME= '" + contactEntity.getLastname() + "')";
            ResultSet resultSet = st.executeQuery(query);
            System.out.println("NUMBER \t" + "\tTYPE");
            while (resultSet.next()) {
                System.out.print(resultSet.getString("NUMBERS") + "\t");
                System.out.println(resultSet.getString("TYPES") + "\t");
            }
            resultSet.close();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean editContact(ContactEntity contactEntity) {
        boolean result;
        try {
            open();
            st.executeUpdate("UPDATE CONTACT SET FIRSTNAME= '"+contactEntity.getFirstname()+"' , LASTNAME='"+contactEntity.getLastname()+"' WHERE CONTACT_ID="+contactEntity.getContactId()+" ");
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean removeContact(ContactEntity contactEntity) {
        boolean result=false;
        try {
            open();
            st.executeUpdate("DELETE FROM CONTACT WHERE CONTACT_ID="+contactEntity.getContactId()+"");
            result=true;

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    private void close() throws Exception {
        st.close();
        cn.close();
    }
}
