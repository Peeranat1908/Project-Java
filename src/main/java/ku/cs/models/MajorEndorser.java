package ku.cs.models;

public class MajorEndorser {
    String name;
    String position;
    String faculty;
    String major;

    public MajorEndorser(String name, String position, String faculty, String major) {
        this.name = name;
        this.position = position;
        this.faculty = faculty;
        this.major = major;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setFaculty(String faculty) {this.faculty = faculty;}

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getFaculty() {return faculty;}

    public String getMajor() {return major;}

    @Override
    public String toString() {
        return "ApproveFacultyStaff{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", faculty='" + faculty + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
