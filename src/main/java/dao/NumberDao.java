package dao;
import model.entity.NumberEntity;
import java.sql.*;

public class NumberDao {
    private String dbURL = "jdbc:derby://localhost:1527/c:/derbyDB;create=true";
    private Connection cn=null;
    private Statement st= null;

    public void open() throws Exception{
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        cn= DriverManager.getConnection(dbURL);
        st=cn.createStatement();
    }
    public void insertNumber(NumberEntity numberEntity){
              try {
             open();
             String sql="INSERT INTO PHONENUMBER (NUMBERS, TYPES,CONTACT_ID) VALUES (?,?,?)" ;
             PreparedStatement ps= cn.prepareStatement(sql);
             ps.setString(1, numberEntity.getNumber());
             ps.setString(2, String.valueOf((numberEntity.getNumberType())));
             ResultSet results = st.executeQuery("select CONTACT_ID from  CONTACT");
             while (results.next()) {
                 int id = results.getInt("CONTACT_ID");
                 ps.setInt(3,id);
             }
             ps.executeUpdate();
             results.close();
             ps.close();
         }catch (Exception sqlExcept){
             if(sqlExcept.getMessage().equals("Table/View 'PHONENUMBER' does not exist.")){
                 try {
                     st.execute("CREATE TABLE PHONENUMBER (NUMBER_ID INTEGER NOT NULL primary key GENERATED ALWAYS AS IDENTITY (START WITH 1 , INCREMENT BY 1) " +
                     " , NUMBERS VARCHAR(50) NOT NULL,TYPES VARCHAR(50), CONTACT_ID INTEGER NOT NULL, FOREIGN KEY(CONTACT_ID) REFERENCES CONTACT (CONTACT_ID))");
                     insertNumber(numberEntity);
                 }catch (SQLException e){
                     e.printStackTrace();
                 }
             }else {
                 sqlExcept.printStackTrace();
             }
         }
    }

    public void close(){
        try {
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
