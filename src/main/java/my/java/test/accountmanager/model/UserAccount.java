package my.java.test.accountmanager.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserAccount {

    private int id;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only latin letters")
    @Size(min = 3, max = 16)
    private String userName;

    @Pattern(regexp = "^(?=.*?[a-zA-Z])(?=.*?[0-9]).{3,}$", message = "Only latin letters and numbers," +
            " one letter and one number at least")
    @Size(min = 3, max = 16)
    private String password;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only latin letters")
    @Size(min = 1, max = 16)
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only latin letters")
    @Size(min = 1, max = 16)
    private String lastName;

    private String role;

    private boolean status;

    private Date dateCreated;

    public UserAccount() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}