package nl.inholland.javafx.Users;



public class UserAuth {
    public static boolean Auth(User u, String userName, String Password) {
        boolean Auth = false;
        if (u.userName.equals(userName) && u.userPassword.equals(Password))
        {
            Auth = true;
        }
            else {
                Auth = false;
            }
            return Auth;
    }
}