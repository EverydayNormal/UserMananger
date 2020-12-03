package my.java.test.accountmanager.dao;

import my.java.test.accountmanager.mapper.UserAccountMapper;
import my.java.test.accountmanager.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(UserAccount userAccount) {
        String sql = "INSERT INTO user_manager.user_account (" +
                "id, user_name, password, first_name, last_name," +
                " role, status, created_at) VALUES (" +
                "(SELECT MAX(id) FROM user_manager.user_account) + 1" +
                ", ?, ?, ?, ?, ?, ?, NOW())";
        jdbcTemplate.update(sql,
                notNullCheck(userAccount.getUserName()),
                notNullCheck(userAccount.getPassword()),
                notNullCheck(userAccount.getFirstName()),
                notNullCheck(userAccount.getLastName()),
                notNullCheck(userAccount.getRole()),
                true);
    }

    private String notNullCheck(String value) {
        return value != null ? value : "";
    }

    @Override
    public UserAccount getById(int id) {
        String sql = "SELECT * FROM user_manager.user_account WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new UserAccountMapper(), id);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM user_manager.user_account WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(UserAccount userAccount) {
        String sql = "UPDATE user_manager.user_account SET user_name = ?, password  = ?, first_name  = ?," +
                " last_name  = ?, role  = ?, status  = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                userAccount.getUserName(),
                userAccount.getPassword(),
                userAccount.getFirstName(),
                userAccount.getLastName(),
                userAccount.getRole(),
                userAccount.isStatus(),
                userAccount.getId());
    }

    @Override
    public List<UserAccount> findAll() {
        String sql = "SELECT * FROM user_manager.user_account";
        return jdbcTemplate.query(sql, new UserAccountMapper());
    }

    @Override
    public UserAccount getByUserName(String userName) {
        String sql = "SELECT * FROM user_manager.user_account WHERE user_name = ?";
        try {
            UserAccount account =  jdbcTemplate.queryForObject(sql, new UserAccountMapper(), userName);
            return account;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }
}
