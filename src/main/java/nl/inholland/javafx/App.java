package nl.inholland.javafx;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import nl.inholland.javafx.Users.User;
import nl.inholland.javafx.Users.UserAuth;

public class App extends Application {
    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(600);
        window.setWidth(800);
        window.setTitle("JavaFX DEMO");

        GridPane gP = new GridPane();
        gP.setPadding(new Insets(10, 10, 10, 10));
        gP.setVgap(10);
        gP.setHgap(8);

        Label usrNameLabel = new Label("Username");

        TextField usrNameTextfield = new TextField();
        usrNameTextfield.setPromptText("Enter your username");



        Label passWordLabel = new Label("Password");

        PasswordField pwPassField = new PasswordField();
        pwPassField.setPromptText("Enter your password");

        Label loginLabel = new Label("Login");
        Button loginButton = new Button("Login");
        loginButton.setVisible(false);

        GridPane.setConstraints(usrNameLabel, 0 ,0);
        GridPane.setConstraints(passWordLabel, 0 ,1);
        GridPane.setConstraints(usrNameTextfield, 1, 0);
        GridPane.setConstraints(pwPassField, 1 , 1);
        GridPane.setConstraints(loginLabel, 0 , 2);
        GridPane.setConstraints(loginButton, 1 , 2);
        //property
        StringProperty passwordFieldProperty = pwPassField.textProperty();
        passwordFieldProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                System.out.println("textfield changed from" + oldValue + " to " + newValue);
                if (newValue.length() >= 8 && newValue.matches(".*[-\\d.].*") && newValue.matches(".*[a-z].*") && !newValue.matches(".*[\\s.].*") && newValue.matches(".*[$&+,:;=?@#|'<>.^*()%!-].*")) {
                    loginButton.setVisible(true);
                }
                else {
                    loginButton.setVisible(false);
                }
            }
        });
        gP.getChildren().addAll(usrNameLabel, usrNameTextfield, passWordLabel,  pwPassField, loginLabel, loginButton);



        User u1 = new User("Mark", "Welcome123@");


        Scene scene = new Scene(gP);
        window.setScene(scene);
        window.show();





        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                boolean Auth = UserAuth.Auth(u1, usrNameTextfield.getText(), pwPassField.getText());
                if (Auth) {
                    System.out.println("Succesful login!");
                }
                else {
                    System.out.println("username or pass incorrect enter again." + usrNameTextfield.getText() + pwPassField.getText());
                }
            }
        });




    }
}
