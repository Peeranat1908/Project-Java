package ku.cs.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Student extends User {
    private String id;
    private String email;
    private String faculty;
    private String department;
    private String advisorID;

    public Student(String name, String surname, String username, String password, String id, String email, String faculty, String major, LocalDate lastLoginDate, LocalTime lastLoginTime, String profilePicturePath) {
        super(name, surname, username, password, lastLoginDate, lastLoginTime, "student", profilePicturePath,false,null,null);
        this.id = id;
        this.email = email;
        this.advisorID = null;
    }
    public Student(String name, String surname, String username,String id,String email) {
        this(name, surname, username, null, id, email, null, null , null,null,null);
        advisorID = null;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getFaculty() {
        return faculty;
    }

    public String getMajor() {
        return department;
    }

}
