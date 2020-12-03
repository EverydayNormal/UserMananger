package my.java.test.accountmanager.dao;

import my.java.test.accountmanager.model.UserAccount;

import java.util.List;

public interface UserDao {
    void save(UserAccount userAccount);

    UserAccount getById(int id);

    void delete(int id);

    void update(UserAccount userAccount);

    List<UserAccount> findAll();

    UserAccount getByUserName(String userName);
}