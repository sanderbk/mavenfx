package nl.inholland.javafx.Users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DbMock {
public static List<Student> students = new ArrayList<>();
static {
    Student s1 = new Student(1, "Markie", "Welkom123@", "Mark", "DeHaan", Role.Basic, LocalDate.of(1997, 4, 12), "IT-02-A");
    Student s2 = new Student(2, "Ali1", "Welkom123@", "Ali", "Usmani",Role.Basic, LocalDate.of(1997, 4, 12), "IT-02-A");
    Student s3 = new Student(3, "Frenk", "Welkom123@", "Frenk", "Dersjant", Role.Basic, LocalDate.of(1997, 4, 12), "IT-02-A");
    students.add(s1);
    students.add(s2);
    students.add(s3);
}
}
