package my.java.test.accountmanager.mapper;

import my.java.test.accountmanager.model.UserAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountMapper implements RowMapper<UserAccount> {
    @Override
    public UserAccount mapRow(ResultSet resultSet, int i) throws SQLException {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(resultSet.getInt("id"));
        userAccount.setUserName(resultSet.getString("user_name"));
        userAccount.setPassword(resultSet.getString("password"));
        userAccount.setFirstName(resultSet.getString("first_name"));
        userAccount.setLastName(resultSet.getString("last_name"));
        userAccount.setRole(resultSet.getString("role"));
        userAccount.setStatus(resultSet.getBoolean("status"));
        userAccount.setDateCreated(resultSet.getDate("created_at"));
        return userAccount;
    }
}
