package nl.inholland.javafx.Users;


import java.util.*;

public class UserAuth {
    public static Student Auth( String userName, String Password) {
        boolean Auth = false;
        Student studentTrue = null;
        for (Student s: DbMock.students
             ) {
            System.out.println(s.userName + " testing with " +  userName);
            System.out.println(s.userPassword + " testing with " +  Password);
            if (s.userName.equals(userName) && s.userPassword.equals(Password))
            {
                studentTrue = s;
                Auth = true;
                break;
            }
            else {
                Auth = false;
            }
        }
       if (Auth) {
           System.out.println("Login succesful");
       }
       else {
           System.out.println("Login UNSUCCESFUL");
       }
        return studentTrue;
        }


}