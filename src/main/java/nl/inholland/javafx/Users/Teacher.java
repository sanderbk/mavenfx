package nl.inholland.javafx.Users;

import java.time.LocalDate;
import java.time.Period;


public class Teacher extends Person {

    public LocalDate BirthDate;
    public Double Salary;
    public int Age;

    public Teacher(int id, String uN, String uP, String firstName, String lastName, Role role, LocalDate bD, Double salary){
        super(id, uN, uP, firstName, lastName, role);
        this.BirthDate = bD;
        this.Salary = salary;
        this.Age = Period.between(BirthDate, LocalDate.now()).getYears();
    }
    public int getAge() {
        return Age;
    }
    public void setAge(int set) {
        this.Age = set;
    }
    public double getSalary() {
        return Salary;
    }
    public void setSalary(double set) {
        this.Salary = set;
    }
    public LocalDate getBirthDate() {
        return BirthDate;
    }
    public void setBirthDate(LocalDate set) {
        this.BirthDate = set;
    }
}
