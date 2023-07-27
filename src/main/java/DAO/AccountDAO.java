package DAO;

import Model.Account;
import Model.Message;

import Util.ConnectionUtil;

import java.sql.*;

import org.h2.jdbc.JdbcSQLNonTransientException;


public class AccountDAO {
    //DAOs are classes used to get information to and from the SQL database.
   
    private boolean existsWithinDatabase(String strParameter) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
       

        //======WAS TOLD NOT TO USE TRY/CATCH in DAO.
        //try {
            String sql = "SELECT * FROM account WHERE Username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, strParameter);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("AccDAO return value = " + (rs.absolute(1)));
            System.out.println("(Meaning it does/does not exist in database)");
            return (rs.absolute(1));
        //} 
      

    }

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

        preparedStatement.setString(1, p);
        preparedStatement.setString(2, u);
            
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
}
