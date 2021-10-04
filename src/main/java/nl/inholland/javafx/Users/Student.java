package nl.inholland.javafx.Users;

import java.time.LocalDate;
import java.time.Period;


public class Student extends Person {

    public LocalDate BirthDate;
    public SchoolGroup SchoolGroup;
    public int Age;


    public Student(int id, String uN, String uP, String firstName, String lastName,Role role, LocalDate bD, SchoolGroup schoolGroup){
        super(id, uN, uP, firstName, lastName, role);
        this.BirthDate = bD;
        this.SchoolGroup = schoolGroup;
        this.Age = Period.between(BirthDate, LocalDate.now()).getYears();

    }

    public int getAge() {
        return Age;
    }
    public void setAge(int set) {
       this.Age = set;
    }
    public SchoolGroup getGroup() {
        return SchoolGroup;
    }
    public void setGroup(SchoolGroup set) {
        this.SchoolGroup = set;
    }
    public LocalDate getBirthDate() {
        return BirthDate;
    }
    public void setBirthDate(LocalDate set) {
        this.BirthDate = set;
    }

}
