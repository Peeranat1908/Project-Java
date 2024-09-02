package ku.cs.models;

import java.util.ArrayList;

public class StudentAdivisorList {
    private ArrayList<String> student;

    public StudentAdivisorList() {student = new ArrayList<>();}

    public void addNewStudent(String id, String name, String faculty, String major){
        id = id.trim();
        name = name.trim();
        faculty = faculty.trim();
        major = major.trim();
    }

}
