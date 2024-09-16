package ku.cs.models;

public class Major extends Faculty{
    private String majorName;
    private String majorId;
    public Major(String facultyName, String facultyId, String majorName, String majorId) {
        super(facultyName, facultyId);
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
