package ku.cs.models;

import java.util.ArrayList;

public class MajorList {
    private ArrayList<Major> majors;

    public MajorList() {
        majors = new ArrayList<>();
    }

    public void addNewMajor(String facultyId, String majorId, String majorName) {
        facultyId = facultyId.trim();
        majorId = majorId.trim();
        majorName = majorName.trim();

        for (Major major : majors) {
            if (major.getMajorName().equalsIgnoreCase(majorName)) {
                return;
            }
        }
        majors.add(new Major(facultyId, majorId, majorName));
    }

    // เพิ่มฟังก์ชันนี้เพื่อตามหาสาขาวิชาตาม facultyId
    public ArrayList<String> getMajorsByFacultyId(String facultyId) {
        ArrayList<String> majorNames = new ArrayList<>();
        for (Major major : majors) {
            if (major.getFacultyId().equals(facultyId)) {
                majorNames.add(major.getMajorName());
            }
        }
        return majorNames;
    }

    public ArrayList<Major> getMajors() {
        return majors;
    }
}

