package ku.cs.models;

import java.util.ArrayList;

public class StudentAdvisorList {
    private ArrayList<StudentAdvisor> studentAdvisor;

    public StudentAdvisorList() {studentAdvisor = new ArrayList<>();}

    public void addNewStudent(String id, String name, String faculty, String major){
        id = id.trim();
        name = name.trim();
        faculty = faculty.trim();
        major = major.trim();
    }

    public StudentAdvisor findStudentById(String id){
        for (StudentAdvisor studentAdvisor1 : studentAdvisor){
            if (studentAdvisor1.isId(id)){
                return studentAdvisor1;
            }
        }
        return null;
    }

    public ArrayList<StudentAdvisor> getStudentAdvisor() {
        return studentAdvisor;
    }
}
