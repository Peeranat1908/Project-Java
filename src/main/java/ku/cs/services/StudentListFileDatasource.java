package ku.cs.services;

import java.io.*;
import java.nio.charset.StandardCharsets;
import ku.cs.models.Student;
import ku.cs.models.StudentList;

public class StudentListFileDatasource implements Datasource<StudentList> {
    private String directoryName;
    private String fileName;

    public StudentListFileDatasource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    // ตรวจสอบว่าไฟล์มีอยู่หรือไม่ ถ้าไม่ให้สร้างไฟล์ใหม่
    private void checkFileIsExisted() {
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public StudentList readData() {
        StudentList studentList = new StudentList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;

            while ((line = buffer.readLine()) != null) {
                if (line.isEmpty()) continue;

                String[] data = line.split(",");

                String username = data[0].trim();
                String name = data[1].trim();
                String surname = data[2].trim();
                String id = data[3].trim();
                String email = data[4].trim();
                String faculty = data[5].trim();
                String major = data[6].trim();
                String advisorID = data.length > 7 ? data[7].trim() : null;
                Student student = new Student(name, surname, username, "", id, email, faculty, major, null, null, "");
                student.setAdvisorID(advisorID);
                studentList.addStudent(student);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return studentList;
    }

    @Override
    public void writeData(StudentList data) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        try (BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) { // ไม่ใช้ append
            for (Student student : data.getStudents()) {
                String line = String.join(",",
                        student.getUsername(),
                        student.getName(),
                        student.getSurname(),
                        student.getId(),
                        student.getEmail(),
                        student.getFaculty(),
                        student.getMajor(),
                        student.getAdvisorID() != null ? student.getAdvisorID() : ""
                );
                buffer.write(line);
                buffer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
