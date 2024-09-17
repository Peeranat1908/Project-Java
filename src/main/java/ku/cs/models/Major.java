package ku.cs.models;

public class Major extends Faculty{
    private String majorName;
    private String majorId;

    public Major(int facultyNumber, String facultyId, String facultyName, String majorName, String majorId) {
        super(facultyNumber, facultyId, facultyName);
        this.majorName = majorName;
        this.majorId = majorId;
    }

    @Override
    public String toString() {
        return "Major{" +
                "majorName='" + majorName + '\'' +
                ", majorId='" + majorId + '\'' +
                '}';
    }
}
