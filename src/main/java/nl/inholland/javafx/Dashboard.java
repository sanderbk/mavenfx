package nl.inholland.javafx;


import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.*;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;
import nl.inholland.javafx.Users.Student;

import java.util.concurrent.atomic.AtomicInteger;

public class Dashboard {


    public Dashboard(Student sAuth)
    {
        //stage settings
        Stage window = new Stage();
        window.setHeight(420);
        window.setWidth(800);
        window.setTitle("University Project | Dashboard");
        window.setResizable(false);

        Button dashBtn = new Button("Dashboard");
        dashBtn.getStyleClass().add("login-btn");

        Button studentBtn = new Button("Students");
        studentBtn.getStyleClass().add("login-btn");

        Button teacherBtn = new Button("Teachers");
        teacherBtn.getStyleClass().add("login-btn");


        //button array for vbox
        Button[] buttons = new Button[] {
                dashBtn,
                studentBtn,
                teacherBtn,
        };
        AtomicInteger finalResult = new AtomicInteger();
        for (int i = 0; i < buttons.length ; i++) {
            int finalI = i;

            buttons[i].setOnAction((ActionEvent event) -> {
                System.out.println( buttons[finalI].toString() + " clicked");
                finalResult.set(finalI);
                System.out.println(finalResult);
            });

        }





        //initialize window settings
        BorderPane border = new BorderPane();
      // border.setTop();

        border.setLeft(addVBox(buttons));
      
          // Add stack to HBox in top region

       // border.setCenter(addGridPane());




        Scene scene=new Scene(border);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.show();


    }
    public void gridPaneChanger(GridPane insert, GridPane output) {

        System.out.println("old gp" + insert.getChildren().toString());
        System.out.println("new gp" + output.getChildren().toString());
        //test cause retarded java
        Text text = new Text("Welcome NEWNEWNEW");
        text.getStyleClass().add("welcome-text");
        insert.add(text, 1, 1);


        GridPane temp = output;
        System.out.println("old gp after swap" + insert.getChildren().toString());
        System.out.println("new gp after swap" + output.getChildren().toString());

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
    public GridPane addGridPane(Button b, Student student) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        String input = b.getText().toString();
        switch (input) {
            case "Dashboard":
                grid.getChildren().clear();
                Text text = new Text("Welcome " + student.firstName);
                text.getStyleClass().add("welcome-text");
                grid.add(text, 1, 0);
                break;
            case "Students":
                grid.getChildren().clear();
                text = new Text("Students");
                text.getStyleClass().add("welcome-text");
                grid.add(text, 1, 0);
                break;

        }
        return grid;
    }
}
