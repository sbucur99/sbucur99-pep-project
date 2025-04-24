package Service;

import Model.Message;
import DAO.MessageDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageService {
    public MessageDAO messageDAO;

    MessageService (){
        messageDAO = new MessageDAO();
    }

    MessageService (MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public Message createMessage(Message message) throws SQLException{
        if (messageDAO.getMessageById(message.getMessage_id()) != null){
            return null;
        } else {
            Message persistedMessage = messageDAO.createMessage(message);
            return persistedMessage;
        }
    }
 
    public Message deleteMessageById(int id) throws SQLException{
        Message persistedMessage = messageDAO.deleteMessageById(id);
        return persistedMessage;
    }

    public Message updateMessage(String message_text, int id) throws SQLException{
        Message persistedMessage = messageDAO.updateMessage(message_text, id);
        return persistedMessage;
    }

    public List<Message> getAllMessages() throws SQLException{
        List<Message> messages = new ArrayList<>(); 
        messages = messageDAO.getAllMessages();
        return messages;
    }

    public Message getMessageById(int id) throws SQLException{
        return messageDAO.getMessageById(id);
    }

    public List<Message> getAllMessagesByUserId(int user_id) throws SQLException{
        List<Message> messages = new ArrayList<>();
        messages = messageDAO.getAllMessagesByUserId(user_id);
        return messages;
    }
}
