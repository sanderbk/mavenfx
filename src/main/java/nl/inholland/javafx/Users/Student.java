package nl.inholland.javafx.Users;

import java.time.LocalDate;
import java.time.Period;


public class Student extends Person {

    public LocalDate BirthDate;
    public Group Group;
    public int Age;


    public Student(int id, String uN, String uP, String firstName, String lastName,Role role, LocalDate bD, Group group){
        super(id, uN, uP, firstName, lastName, role);
        this.BirthDate = bD;
        this.Group = group;
        this.Age = Period.between(BirthDate, LocalDate.now()).getYears();

    }

    public int getAge() {
        return Age;
    }
    public void setAge(int set) {
       this.Age = set;
    }
    public Group getGroup() {
        return Group;
    }
    public void setGroup(Group set) {
        this.Group = set;
    }
    public LocalDate getBirthDate() {
        return BirthDate;
    }
    public void setBirthDate(LocalDate set) {
        this.BirthDate = set;
    }

}
