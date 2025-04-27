package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import java.sql.SQLException;
import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();        
        app.post("/register", this::postRegisterHandler);
        app.post("/login", this::postAccountHandler);
        app.post("/messages", this::postMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);

        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.get("/accounts/{account_id}/messsages", this::getMessagesByUserIdHandler);
        //app.start(8080);
        return app;
    }
    
    private void postRegisterHandler(Context ctx) throws JsonProcessingException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.register(account);
        if(addedAccount != null){
            ctx.json(mapper.writeValueAsString(addedAccount));
        } else {
            ctx.status(400);
        }
    }

    private void postAccountHandler(Context ctx) throws JsonProcessingException, SQLException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account loggedAccount = accountService.login(account);
        if(loggedAccount != null){
            ctx.json(mapper.writeValueAsString(loggedAccount));
        } else {
            ctx.status(401);
        }
    }
    
    private void postMessageHandler(Context ctx) throws JsonProcessingException, SQLException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        if (message.message_text == null || message.getMessage_text().isEmpty() || message.getMessage_text().length() > 255){
            ctx.status(400);
            return;
        }
        Message addedMessage = messageService.createMessage(message);
        if(addedMessage != null){
            ctx.json(mapper.writeValueAsString(addedMessage));
        } else {
            ctx.status(400);
        }
    }

    private void deleteMessageByIdHandler(Context ctx) throws JsonProcessingException, SQLException{
        int id = Integer.parseInt(ctx.pathParam("message_id"));
       
        Message deletedMessage = messageService.deleteMessageById(id);            
        ctx.status(200);

        if(deletedMessage != null){
            ctx.json(deletedMessage);
        }
    }
    
    private void updateMessageHandler(Context ctx) throws JsonProcessingException, SQLException{
        int id = Integer.parseInt(ctx.pathParam("message_id"));
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        if (message == null || message.getMessage_text().length() == 0 || message.getMessage_text().length() > 255){
            ctx.status(400);
            return;
        }
        Message updatedMessage = messageService.updateMessage(message.getMessage_text(), message.getMessage_id());
        if (updatedMessage != null){
            ctx.json(mapper.writeValueAsString(updatedMessage)); //default 200
        } 
    }

    public void getAllMessagesHandler(Context ctx) throws SQLException{
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

    
    private void getMessageByIdHandler(Context ctx) throws JsonProcessingException, SQLException {
        String id = ctx.pathParam("message_id");
        
        Message message = messageService.getMessageById(Integer.parseInt(id));
        ctx.json(message);
    }

    private void getMessagesByUserIdHandler(Context ctx) throws JsonProcessingException, SQLException {
        String id = ctx.pathParam("user_id");
        
       // if (id != null){            
        List<Message> messages = messageService.getAllMessagesByUserId(Integer.valueOf(id));
        ctx.json(messages);
        //}
        
    }



}