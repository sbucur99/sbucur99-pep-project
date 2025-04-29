package Service;

import Model.Message;
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
 
    public Message deleteMessageById(Message message) throws SQLException{
        Message persistedMessage = messageDAO.deleteMessageById(message);
        return persistedMessage;
    }

    public Message updateMessage(Message message) throws SQLException{
        Message persistedMessage = messageDAO.updateMessage(message);
        return persistedMessage;
    }

    public List<Message> getAllMessages() throws SQLException{
        List<Message> messages = new ArrayList<>(); 
        messages = messageDAO.getAllMessages();
        return messages;
    }

    public Message getMessageById(Message message) throws SQLException{
        return messageDAO.getMessageById(message);
    }

   /*public List<Message> getAllMessagesByUserId(Account account) throws SQLException{
        List<Message> messages = new ArrayList<>();
    
        messages = messageDAO.getAllMessagesByUserId(account);
        return messages;
    }*/
}
