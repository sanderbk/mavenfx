package nl.inholland.javafx.Users;

public class Person {
    public int id;
    public String userName;
    public String userPassword;
    public String firstName;
    public String lastName;
    public Role role;

    public Person(int ID, String uName, String pW, String fN, String lN, Role role){
        this.id = ID;
        this.userName = uName;
        this.userPassword = pW;
        this.firstName = fN;
        this.lastName = lN;
        this.role = role;
    }
}
