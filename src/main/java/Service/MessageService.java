package Service;

import Model.Message;
import DAO.MessageDAO;

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

    public Message createMessage(Message message){
        if (messageDAO.getMessageById(message.getMessage_id()) != null){
            return null;
        } else {
            Message persistedMessage = messageDAO.createMessage(message);
            return persistedMessage;
        }
    }
 

    public getAllMessagesByUserId(){
        messageDAO.getMessagesByUserAccountId(0);
    }
}
