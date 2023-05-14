package bll;

import be.Account;
import be.AccountType;
import bll.interfaces.IAccountManager;
import dal.AccountDAO;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class AccountManager implements IAccountManager {
    List<Account> accounts;
    AccountDAO accountDAO = AccountDAO.getInstance();

    public AccountManager() {
        accounts = accountDAO.getAllAccounts();
    }

    private Account checkForAccount(String username) {
        for (Account a : accounts) {
            if (a.getName().equals(username))
                return a;
        }
        return null;
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private Boolean checkPassword(String inputPassword, String hashedPassword) {
        return BCrypt.checkpw(inputPassword, hashedPassword);
    }

    public Boolean checkCredentials(String username, String password) {
        if (checkForAccount(username) != null)
            return checkPassword(password, checkForAccount(username).getPassword());
        else
            return false;
    }

    @Override
    public Account getAccountByName(String name) {
        for (Account a : accounts) {
            if(a.getName().equals(name))
                return a;
        }
        return null;
    }

    @Override
    public AccountType getAccountTypeByName(String name) {
        for (Account a : accounts) {
            if(a.getName().equals(name))
                return a.getType();
        }
        return null;
    }

    @Override
    public int getMaxID() {
        return accountDAO.getMaxID();
    }

    @Override
    public void deleteAccount(int id) {
        accountDAO.deleteAccount(id);
    }

    @Override
    public void createAccount(Account account) {
        accountDAO.createAccount(account);
    }

}
