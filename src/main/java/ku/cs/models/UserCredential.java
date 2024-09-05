package ku.cs.models;

public class UserCredential {
    private String username;
    private String password;
    private String role;

    public UserCredential(String username, String password){
        this.username = username;
        this.password = password;
    }
    public UserCredential(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }



    public String getUsernameName(){
        return username;
    }

    public String getPassword(){
        return password;
    }
    public String getRole(){return role;}
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "{" +
                "name: '" + username + '\'' +
                ", password: " + password +
                ", role: '" + role + '\'' +
                '}';
    }
}
