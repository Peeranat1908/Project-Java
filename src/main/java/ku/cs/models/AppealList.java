package ku.cs.models;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppealList {
    private ArrayList<Appeal> appeals;

    public AppealList(){
        this.appeals = new ArrayList<>();
    }

    public void addAppeal(Appeal appeal){
        appeals.add(appeal);
    }

    public void removeAppeal(Appeal appeal){
        appeals.remove(appeal);
    }

    public List<Appeal> getsAppeals(){
        return new ArrayList<>(appeals);
    }

    public Appeal findAppealBySubject(String subject) {
        for (Appeal appeal : appeals) {
            if (appeal.getSubject().equalsIgnoreCase(subject)) {
                return appeal;
            }
        }
        return null;
    }

    public Appeal findAppealById(String id) {
        for (Appeal appeal : appeals) {
            if (appeal.getSubject().equalsIgnoreCase(id)) {
                return appeal;
            }
        }
        return null;
    }
    public Appeal findByStudentIDTypeSubjectRequestStatus(String studentID, String type, String subject, String request, String status) {
        for (Appeal appeal : appeals) {
            if (appeal.getStudentID().equals(studentID) &&
                    appeal.getType().equals(type) &&
                    appeal.getSubject().equals(subject) &&
                    appeal.getRequest().equals(request) &&
                    appeal.getStatus().equals(status))
                return appeal;
        }

        return null;
    }
    public AppealList findAppealByStudentID(StudentList studentlist) {
        AppealList appealList1 = new AppealList();
        for (Appeal appeal : appeals) {
            for (Student student : studentlist.getStudents()) {
                if(appeal.getStudentID().equalsIgnoreCase(student.getId())) {
                    appealList1.addAppeal(appeal);
                }
            }
        }
        return appealList1;
    }


    public int getAppealCount() {
        return appeals.size();
    }


}