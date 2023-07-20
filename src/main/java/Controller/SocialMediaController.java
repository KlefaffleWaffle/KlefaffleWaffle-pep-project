package Controller;

import org.h2.util.json.JSONString;

import DAO.AccountDAO;
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
        //app.start?
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::registerHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
        
    }

    private void registerHandler(Context context) {
        String str;
        JSONString jstr;
        Context c;
        c = context.json(context.body());
        str = c.result();

        System.out.println(str);
        if(str.length() == 0){
            System.out.println("AJD DEBUGGING CODE: Str has no value");
        }
     
        int q1 = 3;
        int q2 = 4;
        //findQuotes(str, q1, q2);

        for(int i = 0, q = 0; i <str.length(); i++){
            if(str.charAt(i) == '"'){
                q++;
            }
            if( q == q1){
                q1 = i;
            }
            if( q == q2){
                q2 = i;
                break;
            }

        }




        String Username = str.substring(q1 +1, q2);
        q1 = 7;
        q2 = 8;

        for(int i = 0, q = 0; i <str.length(); i++){
            if(str.charAt(i) == '"'){
                q++;
            }
            if( q == q1){
                q1 = i;
            }
            if( q == q2){
                q2 = i;
                break;
            }

        }
        String Password = str.substring(q1 +1, q2);
        AccountDAO ad = new AccountDAO();

        if(ad.existsWithinDatabase(Username) == false){
            if(Username.length() > 0 && Password.length() > 4){
                //add to database;
            }

        }
        /* 
        System.out.println("AJD DEBUGGING: Username and Passwords");
        System.out.println(Password);
        System.out.println(Username);
        */
        
        /*
         * Parse String: Get Username
         * Check to see if username has characters.
         * Check to see if it exists within database;
         *          Probably going to have to edit DAO
         * Check to see if password is four characters long
        */
        
    }

    /*
     * @Param str: string to be searched
     * @Param q1: the xth quotation mark
     * @Param q2: the yth quotation mark;
     * 
     * @edit: q1 and q2 will have the position of the x and yth place in the string.
    */


}