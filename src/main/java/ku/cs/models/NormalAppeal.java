package ku.cs.models;
import java.time.LocalDate;

public class NormalAppeal {
    private String subject; // เรื่อง
    private String request; // มีความประสงค์คือ
    private LocalDate date; // วัน/เดือน/ปี
    private String studentSignature; // ลงนามนิสิต/ผู้ดำเนินการแทน

    public NormalAppeal(String subject, String request, LocalDate date, String studentSignature) {
        this.subject = subject;
        this.request = request;
        this.date = date;
        this.studentSignature = studentSignature;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStudentSignature() {
        return studentSignature;
    }

    public void setStudentSignature(String studentSignature) {
        this.studentSignature = studentSignature;
    }

    @Override
    public String toString() {
        return "NormalAppeal{" +
                "subject='" + subject + '\'' +
                ", request='" + request + '\'' +
                ", date=" + date +
                ", studentSignature='" + studentSignature + '\'' +
                '}';
    }
}
