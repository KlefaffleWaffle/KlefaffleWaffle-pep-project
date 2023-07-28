
package DAO;

import Model.Message;


import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

import io.javalin.http.Context;

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

    public ArrayList<Message> getAllMessagesDAO() throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM message";
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(sql); System.out.println("Before big test");
        ArrayList<Message> messages = new ArrayList<Message>();
        while(rs.next() == true){
            
                Message m = new Message(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getLong(4));
                messages.add(m);
            
        }
        return messages;
    }

    public Message getSpecificMessage(int intParam2)throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM message";
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(sql); System.out.println("Before big test");
       Message messageR = null;
        while(rs.next() == true){
            
                if(rs.getInt(1) == intParam2){
                    messageR = new Message(rs.getInt(1), rs.getInt(2),rs.getString(3),rs.getLong(4));
                }
            
        }
        return messageR;
    }

    public Message deleteSpecificMessageDAO(int IDParam)throws SQLException{
        Message message2 = getSpecificMessage(IDParam);
        String sql = "DELETE FROM message WHERE message_id = ?";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, IDParam);
        preparedStatement.executeUpdate();
        return message2;
    }

    public Message PatchTextDAO(int IDParamPatch, Context context)throws SQLException{
        Message testMessage = getSpecificMessage(IDParamPatch);
        if( testMessage == null){
           return null;
        }

        int q3 = -1;
        int q4 = -1;
        String bodyText = context.body();
        for(int i = 0, j = 0; i < context.body().length(); i++){
            if(bodyText.charAt(i) == '"'){
                j++;
                if(j == 3){
                    q3 = i;
                }else if(j == 4){
                    q4 = i;
                }
            }
        }
        String messageStr = "";
        messageStr = bodyText.substring(q3+1, q4);
        Message message2 = getSpecificMessage(IDParamPatch);
        message2.setMessage_text(messageStr);

        System.out.println("message is: " + message2.getMessage_text());
        // int userID = m.getPosted_by();
        if(failsBlankMessage(message2.getMessage_text()) == true || failsLengthTest(message2.getMessage_text())==true){
            return null;
        }
        
        

        //message2.setMessage_text(context.body());



        //context.
        System.out.println("Context Body is: " + context.body());

        
        
        System.out.println("line 179");
        String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("line 185");
       
        preparedStatement.setString(1, message2.getMessage_text());
        preparedStatement.setInt(2, IDParamPatch);
        System.out.println("line 185");
        preparedStatement.executeUpdate();
        System.out.println("DAO successful");
        return message2;

    }

    public ArrayList<Message> getAllMessagesUserDAO(){
        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM message WHERE account_id = ?";
        //Statement s = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, message2.getMessage_text());
        
        ResultSet rs = s.executeQuery(sql); System.out.println("Before big test");
        ArrayList<Message> messages = new ArrayList<Message>();
        while(rs.next() == true){
            
                Message m = new Message(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getLong(4));
                messages.add(m);
            
        }
        return messages;
    }


}