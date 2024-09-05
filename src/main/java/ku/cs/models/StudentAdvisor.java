package ku.cs.models;

public class StudentAdvisor {
    private String id;
    private String name;
    private String faculty;
    private String major;

    public StudentAdvisor(String id, String name, String faculty, String major){
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.major = major;
    }
    public boolean isId(String id){
        return this.id.equals(id);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public String toString() {
        return "StudentAdvisor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", faculty='" + faculty + '\'' +
                ", major='" + major + '\'' +
                '}';
    }

    public String toCsv() {
        return id + "," + name + "," + faculty + "," + major;
    }
}
