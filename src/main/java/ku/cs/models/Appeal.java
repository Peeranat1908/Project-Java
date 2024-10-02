package ku.cs.models;
import java.time.LocalDate;
import java.time.LocalTime;


public class Appeal {
    private String studentID;
    private String type; //ประเภท
    private String subject; // เรื่อง
    private String request; // มีความประสงค์คือ
    private LocalDate date; // วัน/เดือน/ปี
    private String studentSignature; // ลงนามนิสิต/ผู้ดำเนินการแทน
    private String majorEndorserSignature; // ลายเซ็นเจ้าหน้าที่ผู้อนุมัติ
    private long second; //เก็บเวลาที่ส่ง
    private String status; //สถานะ
    private LocalTime sendtime; // เวลาที่ส่ง
    private String DeclineReason;

    public Appeal(String studentID, String type , String subject, String request, LocalDate date, String studentSignature, long second, String status, LocalTime sendtime, String DeclineReason, String majorEndorserSignature) {
        this.studentID = studentID;
        this.type = type;
        this.subject = subject;
        this.request = request;
        this.date = date;
        this.studentSignature = studentSignature;
        this.second = second;
        this.status = status;
        this.sendtime = sendtime;
        this.DeclineReason = DeclineReason;
        this.majorEndorserSignature = majorEndorserSignature;
    }

    public String getStudentID(){
        return studentID;
    }

    public String getType(){ return type; }

    public void setType(String type){this.type = type; }

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

    public long getSecond() {
        return second;
    }

    public String getStatus(){ return status;}

    public void setStatus(String status){
        this.status = status;
    }

    public LocalTime getSendtime(){
        return sendtime;
    }

    public String getDeclineReason(){
        return DeclineReason;
    }
    public void setDeclineReason(String DeclineReason){
        this.DeclineReason = DeclineReason;
    }

    public String getMajorEndorserSignature() {
        return majorEndorserSignature;
    }

    public void setMajorEndorserSignature(String majorEndorserSignature){
        this.majorEndorserSignature = majorEndorserSignature;
    }

    @Override
    public String toString() {
        return "NormalAppeal{" +
                "type='" + type + '\'' +
                ", subject='" + subject + '\'' +
                ", request='" + request + '\'' +
                ", date=" + date + '\''+
                ", studentSignature='" + studentSignature + '\'' +
                ", second =" + second + '\'' + ",status =" + status +
                '}';
    }

}
