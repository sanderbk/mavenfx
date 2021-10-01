package nl.inholland.javafx.Users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DbMock {
public static List<Student> students = new ArrayList<>();
static {
    Student s1 = new Student(1, "Markie", "Welkom123@", "Mark", "DeHaan", Role.Basic, LocalDate.of(1997, 4, 12), Group.INF02A);
    Student s2 = new Student(2, "Ali1", "Welkom123@", "Ali", "Usmani",Role.Basic, LocalDate.of(1999, 4, 12), Group.INF02B);
    Student s3 = new Student(3, "Frenk", "Welkom123@", "Frenk", "Dersjant", Role.Basic, LocalDate.of(1991, 4, 12), Group.INF02C);
    students.add(s1);
    students.add(s2);
    students.add(s3);
}
    public static List<Teacher> teachers = new ArrayList<>();
    static {
        Teacher t1 = new Teacher(4, "Sander", "Welkom123@", "Sander", "Baak", Role.Editor, LocalDate.of(1997, 4, 12), 2400.00);
        Teacher t2 = new Teacher(5, "Joppe", "Welkom123@", "Joppe", "Skum", Role.Editor, LocalDate.of(1991, 4, 12), 2800.00);
        Teacher t3 = new Teacher(6, "Leeroy", "Welkom123@", "Leeroy", "Janssen", Role.Basic, LocalDate.of(1957, 4, 12), 3400.00);
        teachers.add(t1);
        teachers.add(t2);
        teachers.add(t3);
    }
    public static List<Manager> managers = new ArrayList<>();
    static {
        Manager m1 = new Manager(7, "Admin", "Welkom123@", "Admin", "Admin", Role.Admin);

        managers.add(m1);
    }
}
