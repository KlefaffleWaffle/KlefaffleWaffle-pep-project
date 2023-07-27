package Service;
import Model.Message;

import java.sql.SQLException;

import DAO.MessageDAO;

public class MessageServiceClass {
    MessageDAO messageDAOObject;

    public MessageServiceClass(){
        messageDAOObject = new MessageDAO();
    }

    public Message createMessage(Message m) throws SQLException{
        return messageDAOObject.createMessage(m);
    }
}
