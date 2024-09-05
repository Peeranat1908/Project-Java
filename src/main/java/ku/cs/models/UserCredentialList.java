package ku.cs.models;

import java.util.ArrayList;

public class UserCredentialList {
    private ArrayList<UserCredential> users;

    public UserCredentialList() {
        users = new ArrayList<>();
    }

    public void addNewUser(String username, String password,String role) {
        username = username.trim();
        password = password.trim();
        if (!password.equals("") && !username.equals("")) {
            UserCredential exist = findAdminByUsername(username);
            if (exist == null) {
                users.add(new UserCredential(username,password,role));
            }
        }
    }

    public UserCredential findAdminByUsername(String username) {
        for (UserCredential user: users) {
            if (user.getUsernameName().equals(username)) {
                return user;
            }
        }
        return null;
    }



    public ArrayList<UserCredential> getUsers() {
        return users;
    }
}
