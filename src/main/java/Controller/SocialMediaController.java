package Controller;

import java.sql.SQLException;

import org.h2.util.json.JSONString;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.AccountDAO;
import Model.Account;
import Service.AccountServiceClass;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        System.out.println ("Seeing debug line in startApi");
        
    
        
        
        //app.get("/register", ctx ->{System.out.println("Start API is working");this.registerHandler(ctx);});
        app.post("/register", ctx ->{System.out.println("Start API is working");this.registerHandler(ctx);});
        
        
        //app.post("/register", this::registerHandler);
        // app.post("/register", ctx->{ if(this::registerHandler) {ctx.status(200);}else{ctx.status();}});
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
 

    private void registerHandler(Context context) throws SQLException, JsonMappingException, JsonProcessingException{

        ObjectMapper mapper = new ObjectMapper();
        Account account2 = mapper.readValue(context.body(), Account.class);
        System.out.println("Mapper value translated " + account2.username + " " + account2.password);
        AccountServiceClass as = new AccountServiceClass();
        String contents = context.body();

        System.out.println("Controller 1");
        Account accountTest = as.addAccount(account2);
        System.out.println("Controller 2");
        //account2.setAccount_id(accountTest.getAccount_id());
        

        
        if(accountTest != null){
            context.status(200);
            //context.json(account2);
            context.json(accountTest);
        }else{
            context.status(400);

        }
        

        //context
        

   
        
    }




}