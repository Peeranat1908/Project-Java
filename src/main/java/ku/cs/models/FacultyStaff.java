package ku.cs.models;

public class FacultyStaff {
    private String name;
    private String username;
    private String password;
    private String faculty;
    private String major;
    private String staffType;

    // for faculty
    public FacultyStaff(String name, String username, String password, String faculty) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.faculty = faculty;
        this.major = "";
        this.staffType = "คณะ";
    }

    // for major
    public FacultyStaff(String name, String username, String password, String faculty, String major) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.faculty = faculty;
        this.major = major;
        this.staffType = "ภาควิชา";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }


    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    @Override
    public String toString() {
        return "FacultyStaff{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", faculty='" + faculty + '\'' +
                ", major='" + major + '\'' +
                ", staffType='" + staffType + '\'' +
                '}';
    }

}
