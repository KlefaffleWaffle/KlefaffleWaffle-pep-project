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
    
    }


}

