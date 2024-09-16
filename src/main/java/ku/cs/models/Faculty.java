package ku.cs.models;

public class Faculty {
    private String facultyName;
    private String facultyId;

    public Faculty(String facultyId, String facultyName) {
        this.facultyName = facultyName;
        this.facultyId = facultyId;
    }

    public void setFaculty(String facultyName) {
        this.facultyName = facultyName;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }


    public String getFacultyName() {
        return facultyName;
    }

    public String getFacultyId() {
        return facultyId;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "facultyName='" + facultyName + '\'' +
                ", facultyId='" + facultyId + '\'' +
                '}';
    }
}
