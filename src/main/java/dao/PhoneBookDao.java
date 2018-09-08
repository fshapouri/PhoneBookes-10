package dao;

import model.entity.PhoneBookEntity;

import java.sql.*;

public class PhoneBookDao {

    private String dbURL = "jdbc:derby://localhost:1527/c:/derbyDB;create=true";
    private Connection cn = null;
    private Statement st = null;

    private void open() throws Exception {
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        cn = DriverManager.getConnection(dbURL);
        st = cn.createStatement();
    }

    public boolean insertPhoneBook(PhoneBookEntity phoneBookEntity){
        boolean result=false;
        try {
            open();
            String sql= "INSERT INTO PHONEBOOK (PHONEBOOKNAME ) VALUES (?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, phoneBookEntity.getPhonebookName());
            ps.executeUpdate();
            ps.close();
            result=true;
        }catch (Exception sqlExcept) {
            if (sqlExcept.getMessage().equals("Table/View 'PHONEBOOK' does not exist.")) {
                try {
                    st.execute("CREATE TABLE PHONEBOOK (PHONEBOOK_ID INTEGER NOT NULL primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)"+
                            ",PHONEBOOKNAME VARCHAR(50))");
                    insertPhoneBook(phoneBookEntity);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                sqlExcept.printStackTrace();
                result=false;
            }
        }
        return  result;
    }

    public boolean searchPhoneBook(PhoneBookEntity phoneBookEntity) {
        boolean result=false;
        try {
            open();
            String sql = "select PHONEBOOKNAME from PHONEBOOK WHERE PHONEBOOKNAME='" + phoneBookEntity.getPhonebookName() + "'";
            ResultSet phoneBookresult = st.executeQuery(sql);
            while (phoneBookresult.next()){
             if(phoneBookresult.getString(1).equals(phoneBookEntity.getPhonebookName()))
                 result=true;
             else
                 result=false;
            }
            phoneBookresult.close();
            close();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int selectPhoneBook(PhoneBookEntity phoneBookEntity) {
        int phoneBookId=0;
        try {
            open();
            String sql = "select PHONEBOOK_ID from PHONEBOOK WHERE PHONEBOOKNAME='" + phoneBookEntity.getPhonebookName() + "'";
            ResultSet selectresult = st.executeQuery(sql);
            while (selectresult.next()) {
                phoneBookId = selectresult.getInt("PHONEBOOK_ID");
            }
            selectresult.close();
            close();
        }catch (Exception e) {
            e.printStackTrace();
            }
            return phoneBookId;
           }

    private void close() throws Exception{
        st.close();
        cn.close();
    }
}




