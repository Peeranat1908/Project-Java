package ku.cs.models;

import java.util.ArrayList;

public class MajorList{
    private ArrayList<Major> majors;

    public MajorList(){majors = new ArrayList<>();}

    public void addNewMajor(String facultyId, String majorId, String majorName){
        facultyId = facultyId.trim();
        majorId = majorId.trim();
        majorName = majorName.trim();

        for (Major major : majors){
            if (major.getMajorName().equalsIgnoreCase(majorName)){
                return;
            }
        }
        majors.add(new Major(facultyId.trim(), majorId.trim(), majorName.trim()));
    }


    public ArrayList<Major> getMajors() {
        return majors;
    }
}
