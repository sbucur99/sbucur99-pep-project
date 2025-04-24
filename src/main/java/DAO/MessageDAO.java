package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    public List<Message> getAllMessages() throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
            
        String sql = "SELECT * FROM message;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            Message message = new Message(
                rs.getInt("message_id"), 
                rs.getInt("posted_by"), 
                rs.getString("message_text"), 
                rs.getInt("time_posted_epoch")
            );
            messages.add(message);
        }
        return messages;
    }


    public Message getMessageById(int id) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();

        String sql = "SELECT * FROM message WHERE message_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, id);

        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            Message book = new Message(
                rs.getInt("message_id"), 
                rs.getInt("posted_by"), 
                rs.getString("message_text"), 
                rs.getInt("time_posted_epoch")
            );
            return book;
        }
        return null;
    }


    public Message insertMessage(Message message) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();

        String sql = "INSERT INTO message (message_id, posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?, ?);" ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, message.getMessage_id());
        preparedStatement.setInt(2, message.getPosted_by());
        preparedStatement.setString(3, message.getMessage_text());
        preparedStatement.setLong(4, message.getTime_posted_epoch());

        preparedStatement.executeUpdate();
        return message;
    }


    public Message updateMessage(Message message) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
        String sql = "UPDATE message SET ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
    }

    public Message deleteMessageById(int id) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM message WHERE posted_by == ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
    } 

    public List<Message> getMessagesByUserAccountId(int user_id) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        String sql = "SELECT * FROM message WHERE posted_by == ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, user_id);

        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            Message message = new Message(
                rs.getInt("message_id"), 
                rs.getInt("posted_by"), 
                rs.getString("message_text"), 
                rs.getInt("time_posted_epoch")
            );
            messages.add(message);
        }
     
        return messages;
    }
}