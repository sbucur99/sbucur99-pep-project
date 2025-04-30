package Service;

import Model.Message;
import Model.Account;
import DAO.AccountDAO;
import DAO.MessageDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageService {
    public MessageDAO messageDAO;
    public AccountDAO accountDAO;

    public MessageService (){
        messageDAO = new MessageDAO();
    }

    public MessageService (MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public Message createMessage(Message message) throws SQLException{
   
        Message persistedMessage = messageDAO.createMessage(message);
        return persistedMessage;
        
    }
 
    public Message deleteMessageById(int id) throws SQLException{
        return messageDAO.deleteMessageById(id);
    }

    public Message updateMessage(Message message) throws SQLException{
        return messageDAO.updateMessage(message);
    }

    public List<Message> getAllMessages() throws SQLException{
        List<Message> messages = new ArrayList<>(); 
        messages = messageDAO.getAllMessages();
        return messages;
    }

    public Message getMessageById(Message message) throws SQLException{
        return messageDAO.getMessageById(message);
    }

   public List<Message> getAllMessagesByUserId(int user_id) throws SQLException{
        List<Message> messages = new ArrayList<>();
    
        messages = messageDAO.getAllMessagesByUserId(user_id);
        return messages;
    }
}
