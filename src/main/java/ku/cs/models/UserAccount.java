package ku.cs.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class UserAccount {
    private String username;
    private String password;
    private final String role;
    private LocalDate lastLoginDate;
    private LocalTime lastLoginTime;

    public UserAccount(String username, String password, String role, LocalDate lastLoginDate, LocalTime lastLoginTime) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.lastLoginDate = lastLoginDate;
        this.lastLoginTime = lastLoginTime;
    }

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        throw new UnsupportedOperationException("User role cannot be changed.");
    }

    // Getter and Setter for lastLoginDate
    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    // Getter and Setter for lastLoginTime
    public LocalTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "UserCredential{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", lastLoginDate=" + lastLoginDate +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}

