package Service;

import Model.Account;
import Model.Message;
import DAO.AccountDAO;

import java.sql.SQLException;
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

    public Account register(String username, String password) throws SQLException{
        if (accountDAO.register(username, password) != null){
            return accountDAO.register(username, password);
        } 
        return null;        
    }

    public Account login(String username, String password) throws SQLException{
        return accountDAO.login(username, password);
    }
}
