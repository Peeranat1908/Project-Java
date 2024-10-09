package ku.cs.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Student extends User {
    private String id;
    private String email;
    private String advisorID;

    public Student(String name, String username, String password, String id, String email, String faculty, String major, LocalDate lastLoginDate, LocalTime lastLoginTime, String profilePicturePath) {
        super(name, username, password, lastLoginDate, lastLoginTime, "student", profilePicturePath, false, faculty, major, false, id);
        this.email = email;
        this.faculty = faculty;
        this.major = major;
        this.advisorID = null;
    }
    public Student(String name, String username, String password, String id, String email, String faculty, String major, LocalDate lastLoginDate, LocalTime lastLoginTime, String profilePicturePath,String advisorID) {
        super(name, username, password, lastLoginDate, lastLoginTime, "student", profilePicturePath, false, faculty, major, false, id);
        this.email = email;
        this.faculty = faculty;
        this.major = major;
        this.advisorID = advisorID;
    }

    public Student(String name, String username, String id, String email) {
        this(name, username, null, id, email, null, null, null, null, null);
    }

    public String getEmail() {
        return email;
    }

    public String getAdvisorID() {
        return advisorID;
    }

    public void setAdvisorID(String advisorID) {
        this.advisorID = advisorID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}