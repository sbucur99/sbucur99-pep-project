package Service;

import Model.Account;
import DAO.AccountDAO;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    public AccountDAO accountDAO;

    AccountService (){
        accountDAO = new AccountDAO();
    }

    AccountService (AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    
}
