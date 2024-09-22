package ku.cs.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;

    public UserList() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    // ค้นหาผู้ใช้โดย username
    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    // อัปเดตเวลา login ล่าสุด
    public void updateLastLogin(String username) {
        User user = findUserByUsername(username);
        if (user != null) {
            LocalDate nowDate = LocalDate.now();
            LocalTime nowTime = LocalTime.now();
            user.setLastLoginDate(nowDate);
            user.setLastLoginTime(nowTime);
        }
    }

    // Method for user authentication
    public User authenticate(String username, String password) {
        // Find the user by username
        User user = findUserByUsername(username);

        // Check if user exists and password matches
        if (user != null && user.getPassword().equals(password)) {
            // Update last login time when authenticated
            updateLastLogin(username);
            return user;
        }

        // Return null if authentication fails
        return null;
    }


    // อัปเดตรหัสผ่านของผู้ใช้
    public boolean updatePassword(String username, String newPassword) {
        User user = findUserByUsername(username);
        if (user != null) {
            user.setPassword(newPassword);
            return true; // Password updated successfully
        }
        return false; // User not found
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
