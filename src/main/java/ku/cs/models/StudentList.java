package ku.cs.models;

import ku.cs.services.exceptions.AuthenticationFailedException;

import java.util.ArrayList;

public class StudentList {
    private ArrayList<Student> students;

    public StudentList() {
        students = new ArrayList<>();
    }

    public void addNewStudent(String name, String surname, String id, String username, String email, String password) {
        name = name.trim();
        surname = surname.trim();
        id = id.trim();
        username = username.trim();
        email = email.trim();
        if (!id.equals("") && !name.equals("")) {
            Student exist = findStudentById(id);
            if (exist == null) {
                students.add(new Student(name, surname, id, username, email, password));
            }
        }
    }

    public void addNewStudent(String id, String name, String email) {
        id = id.trim();
        name = name.trim();
        email = email.trim();
        if (!id.equals("") && !name.equals("")) {
            Student exist = findStudentById(id);
            if (exist == null) {
                students.add(new Student(id, name, email));
            }
        }
    }

    public boolean isExists(String username, String id) {
        for (Student student : students) {
            if (student.getName().equals(username) || student.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Student authen(String username, String password) {
        for (Student student : students) {
            if (student.check(username, password)) {
                return student;
            }
        }
        throw new AuthenticationFailedException("Invalid username or password!");
    }

    public Student findStudentById(String id) {
        for (Student student : students) {
            if (student.isId(id)) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
