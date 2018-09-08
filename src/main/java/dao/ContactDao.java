package dao;

import model.entity.ContactEntity;
import java.sql.*;

public class ContactDao {
    private String drURL = "jdbc:derby://localhost:1527/c:/derbyDB;create=true";
    private Connection cn = null;
    private Statement st = null;


    private void open() throws Exception {
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        cn = DriverManager.getConnection(drURL);
        st = cn.createStatement();
    }

    public boolean insertContact(ContactEntity contactEntity) {
        boolean result=false;
              try {
            open();
            String sql = "INSERT INTO CONTACT (FIRSTNAME,LASTNAME,PHONEBOOK_ID) VALUES (?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, contactEntity.getFirstname());
            ps.setString(2, contactEntity.getLastname());
            ps.setInt(3,contactEntity.getPhoneBookId());
//            ResultSet results = st.executeQuery("select PHONEBOOK_ID from  PHONEBOOK WHERE PHONEBOOKNAME='" + phoneBookName + "'");
//            while (results.next()) {
//                int id = results.getInt("PHONEBOOK_ID");
//                ps.setInt(3, id);
//            }
            ps.executeUpdate();
            ps.close();
            close();
            result=true;
        } catch (Exception sqlExcept) {
            if (sqlExcept.getMessage().equals("Table/View 'CONTACT' does not exist.")) {
                try {
                    st.execute("CREATE TABLE CONTACT(CONTACT_ID INTEGER NOT NULL primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1 )" + ",FIRSTNAME VARCHAR(50), LASTNAME VARCHAR (50), PHONEBOOK_ID INTEGER references PHONEBOOK (PHONEBOOK_ID))");
                    insertContact(contactEntity);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                sqlExcept.printStackTrace();
                result=false;            }
        }
        return result;
    }
    public boolean searchContact(ContactEntity contactEntity) {
        boolean result=false;
        try {
            open();
            String sql = "select * from CONTACT INNER JOIN PHONEBOOK ON CONTACT.PHONEBOOK_ID=PHONEBOOK.PHONEBOOK_ID";
            ResultSet contactresult = st.executeQuery(sql);
            while (contactresult.next()) {
//             phoneBookEntity.setPhonebookName(contactresult.getString(1));
                if ((contactresult.getString("FIRSTNAME").equals(contactEntity.getFirstname()))&& (contactresult.getString("LASTNAME").equals(contactEntity.getLastname())))
                    result=true;
                else
                    result=false;
            }
            contactresult.close();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int selectContact(ContactEntity contactEntity) {
        int contactId = 0;
            try {
                open();
                String query = "select CONTACT_ID from CONTACT WHERE FIRSTNAME='" + contactEntity.getFirstname() + "' , LASTNAME='" + contactEntity.getLastname() + "'";
                ResultSet contactresult = st.executeQuery(query);
                while (contactresult.next()) {
                    contactId = contactresult.getInt("CONTACT_ID");
                }
                contactresult.close();
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return contactId;
    }

    private void close() throws Exception {
        st.close();
        cn.close();
    }
}
