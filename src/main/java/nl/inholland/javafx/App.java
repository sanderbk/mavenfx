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
import javafx.scene.text.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.*;
import nl.inholland.javafx.Users.Role;
import nl.inholland.javafx.Users.Student;
import nl.inholland.javafx.Users.UserAuth;

import java.time.LocalDate;


public class App extends Application {
    private boolean isLoggedIn = false;
    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(350);
        window.setWidth(600);
        window.setTitle("University Project | Login");

        //hbox
        BorderPane border = new BorderPane();;
        HBox hbox = addHBox();
        border.setTop(hbox);
        VBox vbox = addVBox();
        border.setCenter(vbox);

        //scene + style

        Scene scene = new Scene(border);
        scene.getStylesheets().add("style.css");


        window.setScene(scene);
        window.setResizable(false);
        window.show();


        window.close();

        Student sTest = new Student(1, "Markie", "Welkom123@", "Mark", "DeHaan", Role.Basic, LocalDate.of(1997, 4, 12), "IT-02-A");
        Dashboard d = new Dashboard(sTest);








    }

    public HBox addHBox() {
        HBox hbox = new HBox();
        Text t = new Text();
        t.getStyleClass().add("headertext");
        t.setText("University Project");

        hbox.setPadding(new Insets(12, 12, 12, 12));
        hbox.setSpacing(10);
        hbox.getStyleClass().add("hbox-header");
        //uni img
        ImageView imageView = new ImageView("img/logo.png");

        hbox.getChildren().add(imageView);
        hbox.getChildren().add(t);

        return hbox;
    }
   /* public void closeWindow() {
        if (isLoggedIn) {

        }
    }*/

    public VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(40, 60, 40 , 60));
        vbox.setSpacing(20);
        //username
        TextField usrNameTextfield = new TextField();
        usrNameTextfield.setPromptText("Enter your username");
        usrNameTextfield.getStyleClass().add("input-login");
        //password
        PasswordField pwPassField = new PasswordField();
        pwPassField.setPromptText("Enter your password");
        pwPassField.getStyleClass().add("input-login");

        //loginbutton
        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("login-btn");
        loginButton.setVisible(false);
        loginButton.setMaxWidth(Double.MAX_VALUE);
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
        //Auth
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

               Student sAuth = UserAuth.Auth( usrNameTextfield.getText(), pwPassField.getText());
               // boolean Auth = false;
                if (sAuth != null) {
                    isLoggedIn = true;
                    loginButton.getScene().getWindow().hide();
                    Dashboard dash = new Dashboard(sAuth);
                    System.out.println("Succesful login!");
                }
                else {
                    System.out.println("username or pass incorrect enter again." + usrNameTextfield.getText() + pwPassField.getText());
                }
            }
        });

        //add vboxitems
        vbox.getChildren().add(usrNameTextfield);
        vbox.getChildren().add(pwPassField);
        vbox.getChildren().add(loginButton);


        return vbox;
    }


}
