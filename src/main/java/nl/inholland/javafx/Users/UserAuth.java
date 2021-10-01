package nl.inholland.javafx.Users;


import java.util.*;

public class UserAuth {
    public static Person Auth( String userName, String Password) {
        boolean Auth = false;
        Person personTrue = null;
        for (Student s: DbMock.students
             ) {
            System.out.println(s.userName + " testing with " +  userName);
            System.out.println(s.userPassword + " testing with " +  Password);
            if (s.userName.equals(userName) && s.userPassword.equals(Password))
            {
                personTrue = s;
                Auth = true;
                break;
            }
        }
        for (Teacher t: DbMock.teachers
        ) {
            if (t.userName.equals(userName) && t.userPassword.equals(Password))
            {
                personTrue = t;
                Auth = true;
                break;
            }
        }
        for (Manager m: DbMock.managers
        ) {
            if (m.userName.equals(userName) && m.userPassword.equals(Password))
            {
                personTrue = m;
                Auth = true;
                break;
            }
        }
       if (Auth) {
           System.out.println("Login succesful");
       }
       else {
           System.out.println("Login UNSUCCESFUL");
       }
        return personTrue;
        }


}