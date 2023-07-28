package Service;
import Model.Message;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.MessageDAO;

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
}
