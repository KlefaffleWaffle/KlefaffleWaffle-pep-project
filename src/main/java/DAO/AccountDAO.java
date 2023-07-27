package DAO;

import Model.Account;


import Util.ConnectionUtil;

import java.sql.*;

import org.h2.jdbc.JdbcSQLNonTransientException;


public class AccountDAO {
    //DAOs are classes used to get information to and from the SQL database.
   
    

    public Account addToDatabase(Account a) throws SQLException, org.h2.jdbc.JdbcSQLNonTransientException{
        String p = a.getPassword();
        String u = a.getUsername();
        Connection connection = ConnectionUtil.getConnection();
       
        /*
         * Check for Existence here.
         * 
         * if  username is Blank || existsInDatabase = true || password is tooshort  
        */
        if(usernameIsBlank(u) == true|| existsWithinDatabase(u) == true || passwordIsTooShort(p) == true){
            return null;
        }


        String sql = "INSERT INTO account (username, password) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, u);
        preparedStatement.setString(2, p);
            
            //ResultSet rs = preparedStatement.executeQuery();
        
            preparedStatement.executeUpdate();
            //formerly execute update
            
            String sql2 = "SELECT MAX(account_id) FROM account";
            preparedStatement = connection.prepareStatement(sql2);
            
            ResultSet rs = preparedStatement.executeQuery();
            int id = -1;
            //System.out.println("int id has been collected. Value is: " + id);
            //System.out.println("id is = " + id);
    
            while(rs.next()){
                int i = 1;
                    String temp = rs.getString(i);
                    id = Integer.parseInt(temp);
                    System.out.print(rs.getString(i) + i + " "); //Print one element of a row
              
                i++;
              
                System.out.println();//Move to the next line to print the next row. 

            }
    
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            System.out.println("Line 60: " +pkeyResultSet.first());
            //preparedStatement.execute();
        
            System.out.println(pkeyResultSet);
            pkeyResultSet.first();

            return new Account(id, a.username, a.password);
           
           
        //return addToDatabase(u, p);
    }

    //=========================================Definitely gonna need something here parameters;
    private boolean usernameIsBlank(String usernameParameter){
        if(usernameParameter == ""){
            return true;
        }else{
            return false;
        }
    }

    private boolean passwordIsTooShort(String PasswordParameter){
        if(PasswordParameter.length() >= 4){
            return false;
        }else{
            return true;
        }
    }

    private boolean existsWithinDatabase(String strParameter) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
       

       
            System.out.println("Starting duplicate check");
            //String sql = "SELECT * FROM account WHERE username = ?";
            String sql = "SELECT * FROM account";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            Statement s = connection.createStatement();
            //preparedStatement.setString(1, strParameter);
            ResultSet rs = s.executeQuery(sql);
            System.out.println("Before big test");
            while(rs.next() == true){
            String temp = rs.getString("username");
            System.out.println("In While Loop. temp = "+temp);
            System.out.println("Comparing against: "+strParameter);
                if(temp.equals(strParameter)){
                    return true;
                }
            }
            System.out.println("After big test");
            return false;
            /* 
            if((rs.getString(2) != null)){
                System.out.println("AccDAO return value = " + (rs.getString(2)));
                }
                */
            //System.out.println("(Meaning it does/does not exist in database)");
            //return (rs.absolute(1));
            
            /* 
            if(rs.absolute(1) == true){
                System.out.println("Returned true in test");
            }else if(rs.absolute(1) == false){
                System.out.println("Returned false in test");
            }else{
                System.out.println("Returned other in test");
            }
            */  
        
      

    }



    public Account testLogin(Account a) throws SQLException{
        String p = a.getPassword();
        String u = a.getUsername();
        Connection connection = ConnectionUtil.getConnection();

        String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, u);
        preparedStatement.setString(2, p);

        ResultSet rs = preparedStatement.executeQuery();


        if(rs.next()){
        int id= -1;
        String temp = rs.getString(1);
        id = Integer.parseInt(temp);
        System.out.print(rs.getString(1) + " "); //Print one element of a row        

        return new Account(id, u, p);
        }else{
        return null;
        }
    }
}
