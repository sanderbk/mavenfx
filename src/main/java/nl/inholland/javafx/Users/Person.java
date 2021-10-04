package nl.inholland.javafx.Users;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.Period;

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
//
    }
    public static int MaxID() {
        return  (DbMock.students.size() + DbMock.teachers.size() + DbMock.managers.size() + 1);
    }

    public int getId() {
        return id;
    }
    public void setId(int set) {
        this.id = set;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String set) {
        this.userName = set;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String set) {
        this.userPassword = set;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String set) {
        this.firstName = set;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String set) {
        this.lastName = set;
    }

}
