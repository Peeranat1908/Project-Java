package ku.cs.models;

public class Student {
//ชื่อ-นามสกุล รหัสนิสิต username email password confirmed-Password
    private String name;
    private String surname;
    private String id;
    private String username;
    private String email;
    private String password;

    private String faculty;
    private String major;

    public Student(String name, String surname, String id, String username, String email, String password){
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public boolean check(String username, String password){

        return this.username.equals(username) && this.password.equals(password) ;
    }

    public boolean isId(String id) {
        return this.id.equals(id);
    }

    public boolean isUsername(String username){ return this.username.equals(username);}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getMajor() {
        return major;
    }

    public String toCsv(){
        return name+ "," + surname + "," + username + "," + id + "," + email + "," + password;
    }
}
