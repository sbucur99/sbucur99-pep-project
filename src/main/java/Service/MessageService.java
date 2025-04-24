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
}
