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


    public void addToDatabase(){
        Connection connection = ConnectionUtil.getConnection();
       
        try {
            String sql = "Select * FROM account Where Username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(      );
            
            ResultSet rs = preparedStatement.executeQuery();
            return;
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println("AJD ERROR:  SQL exception in DAO");
            return;
        }
        


    }
}
