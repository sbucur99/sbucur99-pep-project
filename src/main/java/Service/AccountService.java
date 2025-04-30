package Service;

import Model.Account;
import DAO.AccountDAO;

import java.sql.SQLException;

public class AccountService {
    public AccountDAO accountDAO;

    /**
     * Constructor that creates AccountDAO object
     */
    public AccountService (){
        accountDAO = new AccountDAO();
    }

    /**
     * Constructor that uses a AccountDAO object 
     * @param accountDAO
     */
    public AccountService (AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }
    
    /**
     * Registers a user account in the db
     * @param account
     * @return the persisted account object
     * @throws SQLException
     */
    public Account register(Account account) throws SQLException{
        Account persistedAccount = accountDAO.register(account);
        return persistedAccount;
    }

    /**
     * Logs in a user through post
     * @param account
     * @return the account object that logged in or null 
     * @throws SQLException
     */
    public Account login(Account account) throws SQLException{
        return accountDAO.login(account);
    }
}
