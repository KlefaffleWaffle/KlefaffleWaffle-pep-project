package Service;

import DAO.AccountDAO;

public class AccountServiceClass {
    AccountDAO accountDAOObject;
    //accountDAOObject = new AccountDAO();

    public AccountServiceClass(){
        accountDAOObject = new AccountDAO();
    }

    public AccountServiceClass(AccountDAO ad){
        this.accountDAOObject = ad;
    }


}

