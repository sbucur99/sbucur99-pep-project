package DAO;

import Model.Account;
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


    public Message getMessageById(Message message) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();

        String sql = "SELECT * FROM message WHERE message_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, message.getMessage_id());

        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            if (rs.getString("message_text") == null){
                break;
                //return null;
            }
            Message newMessage = new Message(
                rs.getInt("message_id"), 
                rs.getInt("posted_by"), 
                rs.getString("message_text"), 
                rs.getInt("time_posted_epoch")
            );
            return newMessage;
        }
        return null;
    }


    /**
     * Creates message by inserting the object values
     * @param message
     * @return the Message object that was created or null
     * @throws SQLException
     */
    public Message createMessage(Message message) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();

        String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);" ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, message.getPosted_by());
        preparedStatement.setString(2, message.getMessage_text());
        preparedStatement.setLong(3, message.getTime_posted_epoch());

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            
            String sql2 = "SELECT * FROM message WHERE message_text = ?;";
            
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);

            preparedStatement2.setString(1, message.getMessage_text());
       
            ResultSet rs = preparedStatement2.executeQuery();
            if (rs.next()){
                Message message2 = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getInt("time_posted_epoch")
                );
                return message2;
            }
        }
        return null;
    }

    /**
     * Updates the message text by a message id
     * @param message_text
     * @param id
     * @return the Message object that is updated or null
     * @throws SQLException
     */
    public Message updateMessage(Message message) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
        String sql = "UPDATE message SET message_text = ? WHERE message_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, message.getMessage_text());
        preparedStatement.setInt(2, message.getMessage_id());
        
        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0){
            String sql2 = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);

            preparedStatement.setInt(1, message.getMessage_id());

            ResultSet rs = preparedStatement2.executeQuery();
            if (rs.next()){
                Message newMessage = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getInt("time_posted_epoch")
                );
                return newMessage;
            }
        }
        return null;

    }

    /**
     * Deletes a message by a message id
     * @param id
     * @return the Message object that was deleted or null
     * @throws SQLException
     */
    public Message deleteMessageById(int id) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();

        String sql = "SELECT * FROM message WHERE message_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, id);

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()){
            Message message = new Message(
                rs.getInt("message_id"), 
                rs.getInt("posted_by"), 
                rs.getString("message_text"), 
                rs.getInt("time_posted_epoch")
            );

            String sql2 = "DELETE FROM message WHERE message_id = ?;";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, id);

            preparedStatement2.executeUpdate();
            return message;
        }
        
        return null;
    } 

    public List<Message> getAllMessagesByUserId(Account account) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        if (account.getAccount_id() <= 0){
            return null;
        }
        String sql = "SELECT * FROM message WHERE posted_by == ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, account.getAccount_id());

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
        if (messages.isEmpty()){
            return null;
        }
     
        return messages;
    }
}