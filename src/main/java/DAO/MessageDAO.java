
package DAO;

import Model.Message;


import Util.ConnectionUtil;

import java.sql.*;


public class MessageDAO {


    public Message createMessage(Message m)throws SQLException{
        Connection connection = ConnectionUtil.getConnection();

        String messageBodyVar = m.getMessage_text(); 
        int userID = m.getPosted_by();
        long time = m.getTime_posted_epoch();
        
        if(failsBlankMessage(messageBodyVar) == true || failsLengthTest(messageBodyVar)==true || failsExistingUser(userID) == true){
            return null;
        }
        System.out.println("Passed all message qualifications");

        String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, userID);
        preparedStatement.setString(2, messageBodyVar);
        preparedStatement.setLong(3, time);
            
            //ResultSet rs = preparedStatement.executeQuery();
        
            preparedStatement.executeUpdate();
            //formerly execute update
            
            String sql2 = "SELECT MAX(message_id) FROM message";
            preparedStatement = connection.prepareStatement(sql2);
            
            ResultSet rs = preparedStatement.executeQuery();
            int id = -1;
            //System.out.println("int id has been collected. Value is: " + id);
            //System.out.println("id is = " + id);
    
            while(rs.next()){
                int i = 1;
                    id = rs.getInt(i);
                    
                    System.out.print("id = " + id + " "); //Print one element of a row
              
                i++;
              
                System.out.println();//Move to the next line to print the next row. 

            }
    
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            System.out.println("Line 60: " +pkeyResultSet.first());
            //preparedStatement.execute();
        
            System.out.println(pkeyResultSet);
            pkeyResultSet.first();





        return new Message(id,userID,messageBodyVar,time);
    }

    private boolean failsBlankMessage(String s){
        if(s.length() == 0){
            return true;
        }else{
            return false;
        }
    }

    private boolean failsLengthTest(String s){
        if(s.length() < 255){
            return false;
        }else{
            return true;
        }
    }

    private boolean failsExistingUser(int ID) throws SQLException{
        //if never found then it fails. meaning true
        
        Connection connection = ConnectionUtil.getConnection();
       

       
        System.out.println("Starting duplicate check");
        //String sql = "SELECT * FROM account WHERE username = ?";
        String sql = "SELECT * FROM message";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        Statement s = connection.createStatement();
        //preparedStatement.setString(1, strParameter);
        ResultSet rs = s.executeQuery(sql);
        System.out.println("Before big test");
        while(rs.next() == true){
        int temp = rs.getInt(2);
        System.out.println("In While Loop. temp = "+temp);
        System.out.println("Comparing against: "+ID);
            if(temp == ID ){
                return false;
            }
        }
        System.out.println("After big test");
        return true;
    }


}