package ku.cs.controllers.student;

import ku.cs.models.Appeal;
import ku.cs.models.AppealList;
import ku.cs.models.StudentList;
import ku.cs.models.User;
import ku.cs.services.AppealListDatasource;
import ku.cs.services.Datasource;
import ku.cs.services.StudentListFileDatasource;

public class TestStudentAppeal {
    public static void main(String[] args) {
        // สร้าง User ตัวอย่าง
        User user = new User("John", "john123", "password", "facultyStaff", "", "faculty1", "วิทยาศาสตร์", "คอม");

        Datasource<StudentList> studentDatasource = new StudentListFileDatasource("data", "student-info.csv");
        StudentList studentList = studentDatasource.readData();
        StudentList studentlistInFaculty = studentList.getStudentsListBYFaculty(user.getFaculty());
        AppealListDatasource datasource = new AppealListDatasource("data/appeals.csv");
        AppealList loadedAppeals = datasource.readData();
        AppealList appealListInFaculty = loadedAppeals.findAppealByStudentID(studentlistInFaculty);

        if (appealListInFaculty.getsAppeals().isEmpty()) {
            System.out.println("No appeals found for students in the same faculty.");
        } else {
            System.out.println("Appeals found for students in the same faculty:");
            for (Appeal appeal : appealListInFaculty.getsAppeals()) {
                System.out.println("Appeal ID: " + appeal.getStudentID() + ", Student ID: " + appeal.getStudentID());
            }
        }
    }
}