package ku.cs.models;

import java.util.ArrayList;

public class ApproveFacultyStaffList {
    private ArrayList<ApproveFacultyStaff> approveFacultyStaffList;

    public ApproveFacultyStaffList() {approveFacultyStaffList = new ArrayList<>();}

    public void addNewApproveFacultyStaff(String name, String position) {
        name = name.trim();
        position = position.trim();

        for (ApproveFacultyStaff approveFacultyStaff : approveFacultyStaffList) {
            if (approveFacultyStaff.getName().equalsIgnoreCase(name)){
                return;
            }
        }
        approveFacultyStaffList.add(new ApproveFacultyStaff(name, position));
    }

    public ArrayList<ApproveFacultyStaff> getApproveFacultyStaffList() {
        return approveFacultyStaffList;
    }
}
