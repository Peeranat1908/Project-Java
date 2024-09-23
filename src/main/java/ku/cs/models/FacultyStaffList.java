package ku.cs.models;

import java.util.ArrayList;

public class FacultyStaffList {
    private ArrayList<FacultyStaff> facultyStaffList;
    public FacultyStaffList() {
        this.facultyStaffList = new ArrayList<>();
    }

    public void addFacultyStaff(FacultyStaff staff) {
        facultyStaffList.add(staff);
    }

    public void removeFacultyStaffByUsername(String username) {
        facultyStaffList.removeIf(staff -> staff.getUsername().equals(username));
    }

    public FacultyStaff findFacultyStaffByUsername(String username) {
        for (FacultyStaff staff : facultyStaffList) {
            if (staff.getUsername().equals(username)) {
                return staff;
            }
        }
        return null;
    }

    public ArrayList<FacultyStaff> getAllFacultyStaff() {
        return facultyStaffList;
    }

}
