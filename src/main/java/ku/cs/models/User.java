package ku.cs.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class User {
    protected String name;
    protected String surname;
    protected String username;
    protected String password;
    protected LocalDate lastLoginDate;
    protected LocalTime lastLoginTime;
    protected String role;
    protected String profilePicturePath;  // เพิ่ม field สำหรับเก็บรูปภาพ
    protected boolean banned;

    public User(String name, String surname, String username, String password, LocalDate lastLoginDate, LocalTime lastLoginTime, String role, String profilePicturePath, boolean banned ) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.lastLoginDate = lastLoginDate;
        this.lastLoginTime = lastLoginTime;
        this.role = role;
        this.profilePicturePath = profilePicturePath;
        this.banned = banned;
    }

    public User(String name, String surname, String username, String password, String role, String profilePicturePath) {
        this(name, surname, username, password, null, null, role, profilePicturePath   , false);
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    public LocalTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setLastLoginTime(LocalTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public boolean check(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // เปลี่ยน toCsv ให้ไม่ใช่ abstract
    public String toCsv() {
        return String.join(",", username, password, role, name, surname, lastLoginDate.toString(), lastLoginTime.toString(), profilePicturePath);
    }
}