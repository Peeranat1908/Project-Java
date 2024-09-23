package ku.cs.models;

import java.util.ArrayList;

public class MajorStaffList {
    private ArrayList<MajorStaff> majorStaffs;
    public MajorStaffList() {
        this.majorStaffs = new ArrayList<>();
    }

    public void addMajorStaffs(MajorStaff staff) {
        majorStaffs.add(staff);
    }

    public void removeMajorStaffByUsername(String username) {
        majorStaffs.removeIf(staff -> staff.getUsername().equals(username));
    }

    public MajorStaff findMajorStaffByUsername(String username) {
        for (MajorStaff staff : majorStaffs) {
            if (staff.getUsername().equals(username)) {
                return staff;
            }
        }
        return null;
    }

    public ArrayList<MajorStaff> getAllMajorStaff() {
        return majorStaffs;
    }
}
