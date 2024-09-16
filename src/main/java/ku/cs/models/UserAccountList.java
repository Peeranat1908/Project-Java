package ku.cs.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserAccountList {
    private ArrayList<UserAccount> users;

    public UserAccountList() {
        users = new ArrayList<>();
    }

    // เพิ่มผู้ใช้ใหม่
    public void addNewUser(String username, String password, String role) {
        username = username.trim();
        password = password.trim();
        if (!password.equals("") && !username.equals("")) {
            UserAccount exist = findUserByUsername(username);
            if (exist == null) {
                users.add(new UserAccount(username, password, role, LocalDate.now(), LocalTime.now()));
            }
        }
    }

    // ค้นหาผู้ใช้โดย username
    public UserAccount findUserByUsername(String username) {
        for (UserAccount user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    // อัปเดตเวลา login ล่าสุด โดยแยกวันที่และเวลา
    public void updateLastLogin(String username) {
        UserAccount user = findUserByUsername(username);
        if (user != null) {
            LocalDate nowDate = LocalDate.now();
            LocalTime nowTime = LocalTime.now();

            user.setLastLoginDate(nowDate);
            user.setLastLoginTime(nowTime);
        }
    }

    // ดึงรายชื่อผู้ใช้ทั้งหมด
    public ArrayList<UserAccount> getUsers() {
        return users;
    }
    public void sortByLastLogin() {
        Collections.sort(users, new LastLoginComparator().reversed());
    }

    private class LastLoginComparator implements Comparator<UserAccount> {
        @Override
        public int compare(UserAccount user1, UserAccount user2) {
            int dateComparison = user1.getLastLoginDate().compareTo(user2.getLastLoginDate());
            return dateComparison != 0 ? dateComparison : user1.getLastLoginTime().compareTo(user2.getLastLoginTime());
        }
    }
}
