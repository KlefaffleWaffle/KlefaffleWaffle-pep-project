package DAO;

import Model.Account;
import Model.Message;

import Util.ConnectionUtil;

import java.sql.*;


public class AccountDAO {
    //DAOs are classes used to get information to and from the SQL database.
   
    public boolean existsWithinDatabase(String strParameter) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
       

        //======WAS TOLD NOT TO USE TRY/CATCH in DAO.
        //try {
            String sql = "Select * FROM account Where Username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, strParameter);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("AccDAO return value = " + (rs.absolute(1)));
            System.out.println("(Meaning it does/does not exist in database)");
            return (rs.absolute(1));
        //} 
        /*
        catch (SQLException e) {
            System.out.println("Exception has been caught in \"existsWithinDatabase\"");
            System.out.println("=================");
            e.getMessage();
            System.out.println("=================");
            System.out.println("AJD ERROR:  SQL exception in DAO");
            return false;
        }
        */

    }

    public Account addToDatabase(Account a) throws SQLException{
        String p = a.getPassword();
        String u = a.getUsername();
        Connection connection = ConnectionUtil.getConnection();

        String sql = "INSERT INTO account (username, password) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, p);
            preparedStatement.setString(2, u);
            
            //ResultSet rs = preparedStatement.executeQuery();
            
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            //preparedStatement.execute();
            if(pkeyResultSet.next()){
                int generated_Account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_Account_id, a.getUsername(), a.getPassword());
            }

            //DEBUGGING
            /*
            String sql2 = "SELECT * FROM account";
            preparedStatement = connection.prepareStatement(sql2);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                for(int i = 1 ; i <= 3; i++){

                    System.out.print(rs.getString(i) + " "); //Print one element of a row
              
                }
              
                System.out.println();//Move to the next line to print the next row. 

            }
            */
            return null;
        //return addToDatabase(u, p);
    }

    //=========================================Definitely gonna need something here parameters;
    public Account addToDatabase(String userN, String passW) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
       
        //try {
            String sql = "INSERT INTO account (username, password) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userN);
            preparedStatement.setString(2, passW);
            
            //ResultSet rs = preparedStatement.executeQuery();
            
            preparedStatement.executeUpdate();
            //preparedStatement.execute();


            //DEBUGGING
            
            String sql2 = "SELECT * FROM account";
            preparedStatement = connection.prepareStatement(sql2);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                for(int i = 1 ; i <= 3; i++){

                    System.out.print(rs.getString(i) + " "); //Print one element of a row
              
                }
              
                System.out.println();//Move to the next line to print the next row. 

            }
            Account a = new Account(-1,"Lala", "Password");
            return a;
        
        /*
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println("AJD ERROR:  SQL exception in DAO: \"addToDatabase\"");
            System.out.println(e.getMessage());
            return false;
        }
        */


    }
}
