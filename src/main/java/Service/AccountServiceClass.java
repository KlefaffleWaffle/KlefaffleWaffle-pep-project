package Service;

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
    public boolean addAccount(Account account){
        return accountDAOObject.addToDatabase(account);
    }


}

