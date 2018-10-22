package dao;

import model.entity.ContactEntity;
import model.entity.PhoneBookEntity;
import model.service.PhoneBookService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookDao {
    private final static PhoneBookDao instance=new PhoneBookDao();
    private String dbURL = "jdbc:derby://localhost:1527/c:/derbyDB;create=true";
    private Connection cn = null;
    private Statement st = null;

    private PhoneBookDao(){}

    public static PhoneBookDao getInstance(){
        return instance;
    }

    private void open() throws Exception {
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        cn = DriverManager.getConnection(dbURL);
        st = cn.createStatement();
    }

    public boolean insertPhoneBook(PhoneBookEntity phoneBookEntity) {
        boolean result = false;
        try {
            open();
            String sql = "INSERT INTO PHONEBOOK (PHONEBOOKNAME ) VALUES (?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, phoneBookEntity.getPhonebookName());
            ps.executeUpdate();
            ps.close();
            result = true;
        } catch (Exception sqlExcept) {
            if (sqlExcept.getMessage().equals("Table/View 'PHONEBOOK' does not exist.")) {
                try {
                    st.execute("CREATE TABLE PHONEBOOK (PHONEBOOK_ID INTEGER NOT NULL primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)" + ",PHONEBOOKNAME VARCHAR(50))");
                    insertPhoneBook(phoneBookEntity);
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

    public boolean searchPhoneBook(PhoneBookEntity phoneBookEntity) {
        boolean result = false;
        try {
            open();
            String sql = "select PHONEBOOKNAME from PHONEBOOK WHERE PHONEBOOKNAME='" + phoneBookEntity.getPhonebookName() + "'";
            ResultSet phoneBookresult = st.executeQuery(sql);
            while (phoneBookresult.next()) {
                if (phoneBookresult.getString(1).equals(phoneBookEntity.getPhonebookName())) result = true;
                else result = false;
            }
            phoneBookresult.close();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int selectPhoneBook(PhoneBookEntity phoneBookEntity) {
        int phoneBookId = 0;
        try {
            open();
            String sql = "select PHONEBOOK_ID from PHONEBOOK WHERE PHONEBOOKNAME='" + phoneBookEntity.getPhonebookName() + "'";
            ResultSet selectresult = st.executeQuery(sql);
            while (selectresult.next()) {
                phoneBookId = selectresult.getInt("PHONEBOOK_ID");
            }
            selectresult.close();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phoneBookId;
    }

    public void displayPhoneBook(PhoneBookEntity phoneBookEntity) {
//        PhoneBookService pbService=new PhoneBookService();
//        List<PhoneBookEntity> pbEntityList = new ArrayList<PhoneBookEntity>();
//        List<ContactEntity> contactEntities = new ArrayList<ContactEntity>();
        try {
            open();
            String query = "SELECT PHONEBOOK.PHONEBOOKNAME , CONTACT.FIRSTNAME, CONTACT.LASTNAME FROM PHONEBOOK INNER JOIN CONTACT ON PHONEBOOK.PHONEBOOK_ID =CONTACT.PHONEBOOK_ID WHERE (PHONEBOOKNAME='" + phoneBookEntity.getPhonebookName() + "') ";
            ResultSet resultSet = st.executeQuery(query);
            System.out.println(" phonebookname\tfirstname\tlastname");
            while (resultSet.next()) {
                System.out.print("\t" + resultSet.getString("PHONEBOOKNAME")+"\t");
                System.out.print("\t" + resultSet.getString("FIRSTNAME")+"\t");
                System.out.println("\t" + resultSet.getString("LASTNAME"));

//                PhoneBookEntity phBEntity = new PhoneBookEntity();
//                ContactEntity contactEntity = new ContactEntity();
//                phBEntity.setPhonebookName(resultSet.getString("PHONEBOOKNAME"));
//                contactEntity.setFirstname(resultSet.getString("FIRSTNAME"));
//                contactEntity.setLastname(resultSet.getString("LASTNAME"));
//                contactEntities.add(contactEntity);
//                phBEntity.setContactList(contactEntities);

            }
            resultSet.close();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean editPhoneBook(PhoneBookEntity phoneBookEntity){
        boolean result;
        try {
            open();
            st.executeUpdate("UPDATE PHONEBOOK SET PHONEBOOKNAME='"+phoneBookEntity.getPhonebookName()+"' WHERE PHONEBOOK_ID="+phoneBookEntity.getPhoneBookId()+"");
             result=true;
            } catch (Exception e) {
            e.printStackTrace();
            result=false;
        }
        return result;
    }


    public boolean removePhoneBook(PhoneBookEntity phoneBookEntity){
        boolean result=false;
        try{
            open();
            st.executeUpdate("DELETE FROM PHONEBOOK WHERE PHONEBOOK_ID= "+phoneBookEntity.getPhoneBookId()+" ");
            result=true;
        } catch (Exception e) {
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    private void close() throws Exception {
        st.close();
        cn.close();
    }
}




