package my.java.test.accountmanager.security;

import my.java.test.accountmanager.dao.UserDaoImpl;
import my.java.test.accountmanager.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecurityBean {

    @Autowired
    public UserDaoImpl userDao;

    private UserAccount currentUserAccount;

    public void search(UserAccount userAccount) {
        currentUserAccount = userDao.getByUserName(userAccount.getUserName());
    }

    public UserAccount getCurrentUserAccount() {
        return currentUserAccount;
    }

    public boolean isUserValid() {
        return currentUserAccount != null && isUserActive();
    }

    public boolean isUserActive() {
        if (currentUserAccount != null) {
            return currentUserAccount.isStatus();
        }
        return true;
    }

    public boolean isUserAdmin() {
        if (currentUserAccount != null) {
            return currentUserAccount.getRole().equals("ADMIN");
        }
        return false;
    }

    public boolean isCredentialsIsCorrect() {
        if (currentUserAccount != null) {
            return currentUserAccount.getRole().equals("ADMIN");
        }
        return true;
    }

    public void logOut(){
        this.currentUserAccount = null;
    }


}
