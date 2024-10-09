package ku.cs.models;

import java.util.ArrayList;
import java.util.List;

public class StudentList extends UserList{
    private List<Student> students;

    public StudentList() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(String username) {
        students.removeIf(student -> student.getUsername().equals(username));
    }


    public Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }


    public boolean isExists(String username, String id) {
        for (Student student : students) {
            if (student.getUsername().equals(username) || student.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addNewStudent(String name, String username, String id, String email) {
        id = id.trim();
        name = name.trim();
        username = username.trim();
        email = email.trim();
        if (!id.equals("") && !name.equals("")) {
            Student exist = findStudentById(id);
            if (exist == null) {
                students.add(new Student(name, username, id, email));
            }
        }
    }
    public Student findStudentByName(String name){
        for (Student student : students){
            if (student.getName().equalsIgnoreCase(name.trim())){
                return student;
            }
        }
        return null;
    }

    public StudentList getStudentsListBYFaculty(String faculty) {
        StudentList studentsList = new StudentList();
        for (Student student : students) {
            if (student.getFaculty().equalsIgnoreCase(faculty)) {
                studentsList.addStudent(student);
            }
        }
        return studentsList;  // คืนค่า StudentList
    }

    public StudentList getStudentsListBYMajor(String major){
        StudentList studentsList = new StudentList();
        for(Student student: students){
            if(student.getMajor().equalsIgnoreCase(major)){
                studentsList.addStudent(student);
            }
        }
        return studentsList;
    }



}