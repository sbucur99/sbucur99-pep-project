package Service;

import Model.Account;
import DAO.AccountDAO;

import java.sql.SQLException;

public class AccountService {
    public AccountDAO accountDAO;

    public AccountService (){
        accountDAO = new AccountDAO();
    }

    public AccountService (AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }
    

    public Account register(Account account) throws SQLException{
        Account persistedAccount = register(account);
        return persistedAccount;
     
    }

    public Account login(Account account) throws SQLException{
        return accountDAO.login(account);
    }
}
