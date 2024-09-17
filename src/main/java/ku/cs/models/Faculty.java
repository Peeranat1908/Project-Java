package ku.cs.models;

public class Faculty {
    private int facultyNumber;
    private String facultyName;
    private String facultyId;

    public Faculty(int facultyNumber, String facultyId, String facultyName) {
        this.facultyNumber = facultyNumber;
        this.facultyName = facultyName;
        this.facultyId = facultyId;
    }

    public void setFaculty(String facultyName) {
        this.facultyName = facultyName;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public void setFacultyNumber(int facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public int getFacultyNumber() {
        return facultyNumber;
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
