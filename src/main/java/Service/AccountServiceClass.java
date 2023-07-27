package Service;

import java.sql.SQLException;

import DAO.AccountDAO;
import Model.Account;


public class AccountServiceClass {
    AccountDAO accountDAOObject;
    //accountDAOObject = new AccountDAO();

    public AccountServiceClass(){
        accountDAOObject = new AccountDAO();
    }

    public AccountServiceClass(AccountDAO ad){
        this.accountDAOObject = ad;
    }

    //Try account if error
    public Account addAccount(Account account) throws SQLException{
        return accountDAOObject.addToDatabase(account);
        
        /*

        System.out.println("Add Account SERVICE is working");
        

        System.out.println("parameters are:\nUsername =" + account.username + "\nPassword = " + account.password);

        if(accountDAOObject.existsWithinDatabase(account.username) == false){
            if(account.username.length() > 0 && account.password.length() > 4){

                if(accountDAOObject.addToDatabase(account.username, account.password) == true){
                
                    //=========//registerHandler.status(200);
                    return true;

                }
            }else{
                
                return false;
            }
            
        } else{
            return false;
        }

   

        return false;
        */
    }


}

