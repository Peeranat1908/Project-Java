package ku.cs.models;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppealList {
    private ArrayList<Appeal> appeals;

    public AppealList(){
        this.appeals = new ArrayList<>();
    }

    public void addAppeal(Appeal appeal){
        appeals.add(appeal);
    }

    public void removeAppeal(Appeal appeal){
        appeals.remove(appeal);
    }

    public List<Appeal> getsAppeals(){
        return new ArrayList<>(appeals);
    }

    public Appeal findAppealBySubject(String subject) {
        for (Appeal appeal : appeals) {
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
