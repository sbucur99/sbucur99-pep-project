package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    public Account register(String username, String password) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();

        if (password.length() < 4){
            return null;
        }

        String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected == 0){
            return null;
        }
        String sql2 = "SELECT * FROM account WHERE username = ?";
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
        preparedStatement2.setString(1, username);

        ResultSet rs = preparedStatement2.executeQuery();
        
        if (rs.next()){
            Account account = new Account(
                rs.getInt("account_id"),
                rs.getString("username"),
                rs.getString("password")
            );
            return account;
        }
        return null;
    }

    public Account login(String username, String password) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT account WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()){
            Account account = new Account(
                rs.getInt("account_id"),
                rs.getString("username"),
                rs.getString("password")
            );        
            return account;
        }
        return null;
    }
   
}
