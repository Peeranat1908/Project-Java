package ku.cs.models;

import java.util.ArrayList;

public class MajorEndorserList {
    private ArrayList<MajorEndorser> majorEndorsers;

    public MajorEndorserList() {majorEndorsers = new ArrayList<>();}

    public void addNewMajorEndorser(String name, String position, String faculty, String major) {
        name = name.trim();
        position = position.trim();
        faculty = faculty.trim();
        major = major.trim();

        for (MajorEndorser majorEndorser : majorEndorsers) {
            if (majorEndorser.getName().equalsIgnoreCase(name)){
                return;
            }
        }
        majorEndorsers.add(new MajorEndorser(name, position, faculty, major));
    }

    public ArrayList<MajorEndorser> getMajorEndorsers() {
        return majorEndorsers;
    }
}
