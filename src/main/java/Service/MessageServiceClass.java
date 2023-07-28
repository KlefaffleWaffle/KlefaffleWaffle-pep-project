package Service;
import Model.Message;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.MessageDAO;
import io.javalin.http.Context;

public class MessageServiceClass {
    MessageDAO messageDAOObject;

    public MessageServiceClass(){
        messageDAOObject = new MessageDAO();
    }

    public Message createMessage(Message m) throws SQLException{
        return messageDAOObject.createMessage(m);
    }

    public ArrayList<Message> getAllMessages()throws SQLException{
        return messageDAOObject.getAllMessagesDAO();
    }

    public Message getSpecificMessage(int intParam)throws SQLException{
        return messageDAOObject.getSpecificMessage(intParam);
    }

    public Message deleteSpecificMessage(int intParam)throws SQLException{
        return messageDAOObject.deleteSpecificMessageDAO(intParam);
    }

    public Message patchText(int IDParamPatch, Context context)throws SQLException{
        
        return messageDAOObject.PatchTextDAO(IDParamPatch, context);
    }

    public ArrayList<Message> getAllMessagesUser()throws SQLException{
        return messageDAOObject.getAllMessagesUserDAO();
        //message1 = MSC.patchText(Integer.parseInt(context.pathParam("message_id")), context);
    }
}
