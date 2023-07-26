package DAO;

import Model.Account;
import Model.Message;

import Util.ConnectionUtil;

import java.sql.*;


public class AccountDAO {
    //DAOs are classes used to get information to and from the SQL database.
   
    public boolean existsWithinDatabase(String strParameter){
        Connection connection = ConnectionUtil.getConnection();
       
        try {
            String sql = "Select * FROM account Where Username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, strParameter);
            ResultSet rs = preparedStatement.executeQuery();
            return (rs.absolute(1));
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println("AJD ERROR:  SQL exception in DAO");
            return false;
        }
        

    }

    public boolean addToDatabase(Account a){
        //parse string
        //addToDatabase(u, p);
        return true;
    }

    //=========================================Definitely gonna need something here parameters;
    public boolean addToDatabase(String userN, String passW){
        Connection connection = ConnectionUtil.getConnection();
       
        try {
            String sql = "INSERT INTO account (username, password) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userN);
            preparedStatement.setString(2, passW);
            
            ResultSet rs = preparedStatement.executeQuery();
            return true;
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println("AJD ERROR:  SQL exception in DAO");
            return false;
        }
        


    }
}
