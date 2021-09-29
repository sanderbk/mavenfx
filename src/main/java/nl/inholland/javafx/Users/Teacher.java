package nl.inholland.javafx.Users;

import java.time.LocalDate;


public class Teacher extends Person {

    public LocalDate BirthDate;
    public Double Salary;

    public Teacher(int id, String uN, String uP, String firstName, String lastName, Role role, LocalDate bD, Double salary){
        super(id, uN, uP, firstName, lastName, role);
        this.BirthDate = bD;
        this.Salary = salary;
    }
}
