package ku.cs.models;

public class ApproveFacultyStaff {
    String name;
    String position;
    String faculty;

    public ApproveFacultyStaff(String name, String position, String faculty) {
        this.name = name;
        this.position = position;
        this.faculty = faculty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setFaculty(String faculty) {this.faculty = faculty;}

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getFaculty() {return faculty;}

    @Override
    public String toString() {
        return "ApproveFacultyStaff{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", faculty='" + faculty + '\'' +
                '}';
    }
}
