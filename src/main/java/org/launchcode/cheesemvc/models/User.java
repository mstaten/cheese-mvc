package org.launchcode.cheesemvc.models;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    @NotNull
    @Size(min=5, max=15, message = "Username must be between 5 and 15 characters")
    private String username;

    @Email
    @Size(min=5, max=15, message = "Email must be between 5 and 15 characters")
    private String email;

    @NotNull
    @Size(min=4, max=12, message = "Password must be between 4 and 12 characters")
    private String password;

    @NotNull(message = "Passwords do not match")
    private String verifyPassword;

    private int userId;
    private static int nextUserId;

    /** Constructors **/
    public User() {
        userId = nextUserId;
        nextUserId++;
    }

    public User(String username, String email, String password) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /** Getters and Setters **/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword();
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPassword();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** other methods **/
    private void checkPassword() {
        if (!password.equals(verifyPassword) && verifyPassword!=null) {
            // don't match and neither is null
            setVerifyPassword(null);
        }
    }

    @Override
    public String toString() {
        return "User{"+ username + " : " + email + ", userId=" + userId + "}";
    }
}
