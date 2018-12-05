package dao;
import model.entity.NumberEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NumberDao {
    private final static NumberDao instance=new NumberDao();
    private String dbURL = "jdbc:derby://localhost:1527/c:/derbyDB;create=true";
    private Connection cn=null;
    private Statement st= null;

    private NumberDao(){}

    public static NumberDao getInstance(){return instance;}

    public void open() throws Exception{
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        cn= DriverManager.getConnection(dbURL);
        st=cn.createStatement();
    }
    public boolean insertNumber(NumberEntity numberEntity){
        boolean result=false;
              try {
             open();
             String sql="INSERT INTO PHONENUMBER (NUMBERS, TYPES,CONTACT_ID) VALUES (?,?,?)" ;
             PreparedStatement ps= cn.prepareStatement(sql);
             ps.setString(1, numberEntity.getNumber());
             ps.setString(2, String.valueOf((numberEntity.getNumberType())));
             ps.setInt(3,numberEntity.getContactId());
//             ResultSet results = st.executeQuery("select CONTACT_ID from  CONTACT");
//             while (results.next()) {
//                 int id = results.getInt("CONTACT_ID");
//                 ps.setInt(3,id);
//             }
             ps.executeUpdate();
//                  results.close();
             ps.close();
             result=true;
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
                 result=false;
             }
         }
         return  result;
    }

    public boolean searchNumberById(NumberEntity numberEntity){
        boolean result=false;
        try {
            open();
            String query="SELECT * FROM  PHONENUMBER INNER JOIN CONTACT ON PHONENUMBER.CONTACT_ID = CONTACT.CONTACT_ID";
            ResultSet resultSet=st.executeQuery(query);
            while (resultSet.next()){
                if((resultSet.getString("NUMBERS").equals(numberEntity.getNumber())) && resultSet.getString("TYPES").equals(numberEntity.getNumberType()))
                     result=true;
                else
                    result=false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return result;
    }

    public boolean searchNumberByNum(NumberEntity numberEntity){
        boolean result=false;
        try {
            open();
            String query="SELECT * FROM  PHONENUMBER WHERE NUMBERS='"+numberEntity.getNumber()+"'";
            ResultSet numresult=st.executeQuery(query);
            while (numresult.next()){
//                if((numresult.getString("NUMBERS").equals(numberEntity.getNumber())) && numresult.getString("TYPES").equals(numberEntity.getNumberType()))
                result=true;
            }
            numresult.close();
            close();
        } catch (Exception e) {
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    public boolean searchbyContactId(NumberEntity numberEntity){
        boolean result=false;
        try {
            open();
            String query="SELECT * FROM PHONENUMBER WHERE CONTACT_ID="+numberEntity.getContactId()+"";
            ResultSet resultSet=st.executeQuery(query);
            while (resultSet.next()){
                result=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    public List<NumberEntity> selectNumberlist(NumberEntity numberEntity){
        List<NumberEntity> numberList=new ArrayList<NumberEntity>();
//        int numberId=0;
        try {
            open();
            String query="SELECT NUMBER_ID FROM PHONENUMBER WHERE NUMBERS='"+numberEntity.getNumber()+"'";
            ResultSet resultSet=st.executeQuery(query);
            while (resultSet.next()){
                NumberEntity numEntity=new NumberEntity();
                numEntity.setNumberId(resultSet.getInt("NUMBER_ID"));
                numberList.add(numEntity);
            }
            resultSet.close();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberList;
    }


    public int selectNumberbyNumber(NumberEntity numberEntity){
        int numberId=0;
        try {
            open();
            String query="SELECT NUMBER_ID FROM PHONENUMBER WHERE NUMBERS='"+numberEntity.getNumber()+"'";
            ResultSet resultSet=st.executeQuery(query);
            while (resultSet.next()){
                numberId=resultSet.getInt("NUMBER_ID");
            }
            resultSet.close();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberId;

    }

    public List<NumberEntity> selectByContactId(NumberEntity numberEntity){
        List<NumberEntity> numberEntities=new ArrayList<NumberEntity>();
        try {
            open();
            String query="SELECT NUMBER_ID FROM PHONENUMBER WHERE CONTACT_ID="+numberEntity.getContactId()+"";
            ResultSet resultSet=st.executeQuery(query);
            while (resultSet.next()){
                NumberEntity numEntity=new NumberEntity();
                numEntity.setNumberId(resultSet.getInt("NUMBER_ID"));
                numberEntities.add(numEntity);
            }
            resultSet.close();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberEntities;
    }


    public void editNumber(NumberEntity numberEntity){
        try {
            open();
            st.executeUpdate("UPDATE PHONENUMBER SET NUMBERS = '"+numberEntity.getNewnumber()+"' , TYPES= '"+numberEntity.getNumberType()+"'  WHERE NUMBER_ID="+numberEntity.getNumberId()+"");
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void removeNumber(NumberEntity numberEntity){
        try {
            open();
            st.executeUpdate("DELETE FROM PHONENUMBER WHERE NUMBER_ID= "+numberEntity.getNumberId()+"");
            close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void close(){
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
