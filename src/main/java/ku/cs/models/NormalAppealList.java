package ku.cs.models;

import java.util.ArrayList;
import java.util.List;

public class NormalAppealList {
    private ArrayList<NormalAppeal> appeals;

    public NormalAppealList(){
        this.appeals = new ArrayList<>();
    }

    public void addAppeal(NormalAppeal appeal){
        appeals.add(appeal);
    }

    public void removeAppeal(NormalAppeal appeal){
        appeals.remove(appeal);
    }

    public List<NormalAppeal> getsAppeals(){
        return new ArrayList<>(appeals);
    }

    public NormalAppeal findAppealBySubject(String subject) {
        for (NormalAppeal appeal : appeals) {
            if (appeal.getSubject().equalsIgnoreCase(subject)) {
                return appeal;
            }
        }
        return null;
    }

    public int getAppealCount() {
        return appeals.size();
    }


}
