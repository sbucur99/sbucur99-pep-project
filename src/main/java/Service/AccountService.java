package Service;

import Model.Account;
import Model.Message;
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

    public Account register(String username, String password){
        if (accountDAO.register(username, password) != null){
            return accountDAO.register(username, password);
        } return null;
        return accountDAO;
        
    }

    public Account login(Account account){

    }
}
