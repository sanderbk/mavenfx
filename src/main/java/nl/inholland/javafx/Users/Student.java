package nl.inholland.javafx.Users;

import java.time.LocalDate;


public class Student extends Person {

    public LocalDate BirthDate;
    public String Group;

    public Student(int id, String uN, String uP, String firstName, String lastName,Role role, LocalDate bD, String group){
        super(id, uN, uP, firstName, lastName, role);
        this.BirthDate = bD;
        this.Group = group;
    }
}
