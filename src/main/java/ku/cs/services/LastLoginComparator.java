package ku.cs.services;

import ku.cs.models.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

public class LastLoginComparator implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
        LocalDate date1 = user1.getLastLoginDate();
        LocalDate date2 = user2.getLastLoginDate();

        if (date1 == null && date2 == null) return 0; // ทั้งสองเป็น null
        if (date1 == null) return 1; // user1 เป็น null ให้อยู่ท้าย
        if (date2 == null) return -1; // user2 เป็น null ให้อยู่ท้าย

        int dateComparison = date2.compareTo(date1); // เรียงจากล่าสุดไปก่อน
        if (dateComparison != 0) {
            return dateComparison;
        }

        LocalTime time1 = user1.getLastLoginTime();
        LocalTime time2 = user2.getLastLoginTime();

        if (time1 == null && time2 == null) return 0; // ทั้งสองเป็น null
        if (time1 == null) return 1; // user1 เป็น null ให้อยู่ท้าย
        if (time2 == null) return -1; // user2 เป็น null ให้อยู่ท้าย

        return time2.compareTo(time1); // เรียงจากล่าสุดไปก่อน
    }
}

