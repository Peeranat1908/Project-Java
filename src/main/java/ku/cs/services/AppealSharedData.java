package ku.cs.services;

import ku.cs.models.NormalAppealList;

public class AppealSharedData {
    private static NormalAppealList normalAppealList = new NormalAppealList();

    public static NormalAppealList getNormalAppealList(){
        return normalAppealList;
    }

    public static void setNormalAppealList(NormalAppealList list){
        normalAppealList = list;
    }
}
