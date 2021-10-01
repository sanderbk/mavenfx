package nl.inholland.javafx;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;
import nl.inholland.javafx.Users.*;

import java.time.LocalDate;
import java.util.List;


public class Dashboard {
public static Person personInput;
public static boolean isEditor() {
    boolean isEditor = !personInput.role.equals("Basic");
    return isEditor;
}


    public Dashboard(Person pAuth)
    {
        personInput = pAuth;

        //stage settings
        Stage window = new Stage();
        window.setHeight(420);
        window.setWidth(800);
        window.setTitle("University Project | Dashboard");
        window.setResizable(false);

        //navigation buttons
        Button dashBtn = new Button("Dashboard");
        Button studentBtn = new Button("Students");
        Button teacherBtn = new Button("Teachers");

        //crud buttons
        Button buttonAdd = new Button("Add Student");
        buttonAdd.setPrefSize(100, 20);
        buttonAdd.getStyleClass().add("login-btn");

        Button buttonEdit = new Button("Edit Student");
        buttonEdit.setPrefSize(100, 20);
        buttonEdit.getStyleClass().add("login-btn");
        buttonEdit.setStyle("-fx-background-color: grey");

        Button buttonDelete = new Button("Delete Student");
        buttonDelete.setPrefSize(100, 20);
        buttonDelete.getStyleClass().add("login-btn");
        buttonDelete.setStyle("-fx-background-color: red");

        Button buttonAddTeacher = new Button("Add Teacher");
        buttonAddTeacher.setPrefSize(100, 20);
        buttonAddTeacher.getStyleClass().add("login-btn");

        Button buttonEditTeacher = new Button("Edit Teacher");
        buttonEditTeacher.setPrefSize(100, 20);
        buttonEditTeacher.getStyleClass().add("login-btn");
        buttonEditTeacher.setStyle("-fx-background-color: grey");

        Button buttonDeleteTeacher = new Button("Delete Teacher");
        buttonDeleteTeacher.setPrefSize(100, 20);
        buttonDeleteTeacher.getStyleClass().add("login-btn");
        buttonDeleteTeacher.setStyle("-fx-background-color: red");

        //initialize window settings
        BorderPane border = new BorderPane();

        //create object of create
        Create createUI = new Create();
        //button array for vbox
        Button[] buttons = new Button[] {
                dashBtn,
                studentBtn,
                teacherBtn,
        };
        Button[] crudBtns = new Button[] {
                buttonAdd,
                buttonEdit,
                buttonDelete,
                buttonAddTeacher,
                buttonEditTeacher,
                buttonDeleteTeacher,
        };

        VBox leftNav = addVBox(buttons);
        border.setLeft(leftNav);


        for (int i = 0; i < buttons.length ; i++) {
            int finalI = i;
            buttons[i].getStyleClass().add("nav-btn");
            buttons[i].setStyle("-fx-text-fill: #cfcfcf");
            ImageView imageView = new ImageView("img/icon" + (i + 1) + ".png");

            System.out.println("img/icon" + (i + 1) + ".png");
            buttons[i].setGraphic(imageView);

            buttons[i].setOnAction((ActionEvent event) -> {
                System.out.println( buttons[finalI].getText() + " clicked");
            });
        }

        //initialize hbox with functions and check if role is admin
        HBox hboxStudent = createUI.addHBoxStudent(crudBtns);
        HBox hboxTeacher = createUI.addHBoxTeacher(crudBtns);

        dashBtn.setOnAction((ActionEvent event) -> {
            border.setCenter(createUI.addGridPane());
        });

        studentBtn.setOnAction((ActionEvent event) -> {
            border.setCenter(createUI.addBorderPaneStudent(hboxStudent));
        });
        teacherBtn.setOnAction((ActionEvent event) -> {
            border.setCenter(createUI.addBorderPaneTeacher(hboxTeacher));
        });
        buttonAdd.setOnAction((ActionEvent event) -> {
            border.setCenter(createUI.createPane());
        });

        Scene scene=new Scene(border);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.show();
    }
    public VBox addVBox(Button[] btn) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        vbox.getStyleClass().add("dashboard-vbox");

        Text title = new Text("University \n Project");
        title.getStyleClass().add("headertext");
        title.setStyle("-fx-font-size: 20");

        vbox.getChildren().add(title);

        for (int i=0; i<btn.length; i++) {
            VBox.setMargin(btn[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(btn[i]);
        }

        return vbox;
    }

}
