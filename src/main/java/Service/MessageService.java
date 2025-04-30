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

    /**
     * Contructor that creates MessageDAO object
     */
    public MessageService (){
        messageDAO = new MessageDAO();
    }

    /**
     * Constructor that uses a MessageDAO object
     * @param messageDAO
     */
    public MessageService (MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    /**
     * Creates a message with message object
     * @param message
     * @return a persisted message or null
     * @throws SQLException
     */
    public Message createMessage(Message message) throws SQLException{
        Message persistedMessage = messageDAO.createMessage(message);
        return persistedMessage;
    }
 
    /**
     * Deletes a message by the id
     * @param id
     * @return a deleted message object or null
     * @throws SQLException
     */
    public Message deleteMessageById(int id) throws SQLException{
        return messageDAO.deleteMessageById(id);
    }

    /**
     * Updates a message by the message id
     * @param message
     * @param id
     * @return a updated message or null
     * @throws SQLException
     */
    public Message updateMessage(Message message, int id) throws SQLException{
        return messageDAO.updateMessage(message, id);
    }

    /**
     * Retrieves a list of message objects
     * @return a list of message objects
     * @throws SQLException
     */
    public List<Message> getAllMessages() throws SQLException{
        List<Message> messages = new ArrayList<>(); 
        messages = messageDAO.getAllMessages();
        return messages;
    }

    /**
     * Retrieves a message by message id
     * @param id
     * @return a message object or null
     * @throws SQLException
     */
    public Message getMessageById(int id) throws SQLException{
        return messageDAO.getMessageById(id);
    }

    /**
     * Retrieves a list of messages by account id
     * @param id
     * @return a list of message objects
     * @throws SQLException
     */
   public List<Message> getAllMessagesByUserId(int id) throws SQLException{
        List<Message> messages = new ArrayList<>();
        messages = messageDAO.getAllMessagesByUserId(id);
        return messages;
    }
}
