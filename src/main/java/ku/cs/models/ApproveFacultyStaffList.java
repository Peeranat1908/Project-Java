package ku.cs.models;

import java.util.ArrayList;

public class ApproveFacultyStaffList {
    private ArrayList<ApproveFacultyStaff> approveFacultyStaffList;

    public ApproveFacultyStaffList() {approveFacultyStaffList = new ArrayList<>();}

    public void addNewApproveFacultyStaff(String name, String position, String faculty) {
        name = name.trim();
        position = position.trim();
        faculty = faculty.trim();

        for (ApproveFacultyStaff approveFacultyStaff : approveFacultyStaffList) {
            if (approveFacultyStaff.getName().equalsIgnoreCase(name)){
                return;
            }
        }
        approveFacultyStaffList.add(new ApproveFacultyStaff(name, position, faculty));
    }

    public ArrayList<ApproveFacultyStaff> getApproveFacultyStaffList() {
        return approveFacultyStaffList;
    }
}
