package ku.cs.models;

public class MajorStaff {
    private String name;
    private String username;
    private String password;
    private String major;
    private String staffType;

    public MajorStaff(String name, String username, String password, String major, String staffType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.major = major;
        this.staffType = "หัวหน้าภาควิชา" + major;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMajor() {
        return major;
    }

    public String getStaffType() {
        return staffType;
    }

//    public void printMajorStaff() {
//        System.out.println("Name: " + name);
//        System.out.println("Username: " + username);
//        System.out.println("Major: " + major);
//        System.out.println("Staff Type: " + staffType + " " +name);
//    }

    @Override
    public String toString() {
        return "FacultyStaff{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", major='" + major + '\'' +
                ", staffType='" + staffType + '\'' +
                '}';
    }
}
