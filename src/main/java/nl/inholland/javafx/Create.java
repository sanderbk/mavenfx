package nl.inholland.javafx;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.inholland.javafx.Users.*;

import java.time.LocalDate;
import java.util.List;

public class Create {
    public BorderPane createPane() {
        BorderPane createPane = new BorderPane();
        createPane.setPadding(new Insets(25));
        //add title
        Text t = new Text("Add Student");
        t.getStyleClass().add("headertext");
        t.setStyle("-fx-font-size: 20");

        createPane.setTop(t);

        //leftvboxform with textfields
        VBox vboxleft = new VBox();
        vboxleft.setPadding(new Insets(10));
        vboxleft.setSpacing(8);


        //username
        TextField usrNameTextfield = new TextField();
        usrNameTextfield.setPromptText("Username");
        usrNameTextfield.getStyleClass().add("input-login-add");
        //password
        TextField pwPassField = new TextField();
        pwPassField.setPromptText("Password");
        pwPassField.getStyleClass().add("input-login-add");
        //firstname
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        firstNameField.getStyleClass().add("input-login-add");
        //lastname
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        lastNameField.getStyleClass().add("input-login-add");
        //combobox groups
        ComboBox<Group> cbxGroup = new ComboBox<>();
        cbxGroup.setItems( FXCollections.observableArrayList( Group.values()));
        cbxGroup.getStyleClass().add("input-login-add");
        cbxGroup.setPromptText("Groep");

        vboxleft.getChildren().addAll(usrNameTextfield, pwPassField, firstNameField, lastNameField, cbxGroup);

        //set left borderpane
        createPane.setLeft(vboxleft);

        //rightvboxform with textfields
        VBox vboxRight = new VBox();
        vboxRight.setPadding(new Insets(10));
        vboxRight.setSpacing(8);
        DatePicker datePicker = new DatePicker();
        datePicker.getStyleClass().add("input-login-add");
        vboxRight.getChildren().add(datePicker);

        //hbox
        HBox hboxBtns = new HBox();
        hboxBtns.setPadding(new Insets(15, 12, 15, 12));
        hboxBtns.setSpacing(10);

        Button buttonAdd = new Button("Add Student");
        buttonAdd.setPrefSize(100, 20);
        buttonAdd.getStyleClass().add("login-btn");
        buttonAdd.setVisible(false);

        StringProperty passwordFieldProperty = pwPassField.textProperty();
        passwordFieldProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                System.out.println("textfield changed from" + oldValue + " to " + newValue);
                if (newValue.length() >= 8 && newValue.matches(".*[-\\d.].*") && newValue.matches(".*[a-z].*") && !newValue.matches(".*[\\s.].*") && newValue.matches(".*[$&+,:;=?@#|'<>.^*()%!-].*")) {
                    buttonAdd.setVisible(true);
                }
                else {
                    buttonAdd.setVisible(false);
                }
            }
        });



        buttonAdd.setOnAction((ActionEvent event) -> {
            if(studentCorrect(usrNameTextfield.getText(), pwPassField.getText(), firstNameField.getText(),lastNameField.getText(), cbxGroup.getValue(), datePicker.getValue())) {
                        addStudentWithForm(usrNameTextfield.getText(),
                        pwPassField.getText(), firstNameField.getText(),
                        lastNameField.getText(), cbxGroup.getValue(),
                        datePicker.getValue());
            }
            else {
                //initialize alert
                Alert a = new Alert(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Zorg ervoor dat je alles goed invult.");
                // show the dialog
                a.show();
            }

        });



        Button buttonEdit = new Button("Cancel");
        buttonEdit.setPrefSize(100, 20);
        buttonEdit.getStyleClass().add("login-btn");
        buttonEdit.setStyle("-fx-background-color: grey");


        hboxBtns.getChildren().addAll(buttonAdd, buttonEdit);

        //set right borderpane
        createPane.setRight(vboxRight);
        createPane.setBottom(hboxBtns);
        return createPane;
    }
    public boolean studentCorrect(String uName, String pW, String firstName, String lastName, Group group, LocalDate bd) {
        boolean check = !uName.isEmpty() && pW.length() >= 8 && !firstName.isEmpty() && !lastName.isEmpty() && group != null && bd != null;
        return check;
    }

    public void addStudentWithForm(String uName, String pW, String firstName, String lastName, Group group, LocalDate bd) {
        Student sNew = new Student(
                Person.MaxID(), uName, pW, firstName, lastName, Role.Basic, bd, group
        );
        DbMock.students.add(sNew);
       System.out.println(sNew.BirthDate.toString());
    }

    public  BorderPane addBorderPaneStudent(HBox hbox) {
        BorderPane TablePane = new BorderPane();
        TablePane.setPadding(new Insets(25));
        TableView table = addTableViewStudent();

        TablePane.setCenter(table);
                if (!Dashboard.personInput.role.equals(Role.Basic)) {
                    TablePane.setBottom(hbox);
                    System.out.println(Dashboard.personInput.role.toString());
                }
        Text text = new Text("Students");
        text.getStyleClass().add("welcome-text");
        TablePane.setTop(text);
        return TablePane;
    }


    public  TableView<Student> addTableViewStudent() {
        TableView<Student> table = new TableView();
        table.setEditable(false);
        List<Student> studentList = DbMock.students;

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        TableColumn idCol = new TableColumn("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn birthdateCol = new TableColumn("Birthdate");
        birthdateCol.setCellValueFactory(new PropertyValueFactory<>("BirthDate"));
        TableColumn ageCol = new TableColumn("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("Age"));



            TableColumn groupCol = new TableColumn("Group");
            groupCol.setCellValueFactory(new PropertyValueFactory<>("Group"));

            for (Student s: studentList
            ) {
                table.getItems().add(s);
            }

        table.getColumns().addAll( idCol, firstNameCol, lastNameCol, birthdateCol, ageCol, groupCol);




            Student sInput = studentList.get(1);

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
            if (table.getSelectionModel().getSelectedItem() != null)
            studentCast(table.getSelectionModel().getSelectedItem(), sInput);
            }
        });






        return table;
    }

    public void studentCast(Student s, Student input) {
        Student temp = s;
        s = input;
        input = temp;
        System.out.println(s.firstName + " SELECTED " + input.firstName + " OLD DATA");
    }



    public HBox addHBoxStudent(Button btn[]) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);


        hbox.getChildren().addAll(btn[0], btn[1], btn[2]);

        return hbox;
    }
    public HBox addHBoxTeacher(Button btn[]) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);


        hbox.getChildren().addAll(btn[3], btn[4], btn[5]);

        return hbox;
    }

    public TableView addTableViewTeacher() {
        TableView table = new TableView();
        table.setEditable(false);
        List<Teacher> teacherList = DbMock.teachers;

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        TableColumn idCol = new TableColumn("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn birthdateCol = new TableColumn("Birthdate");
        birthdateCol.setCellValueFactory(new PropertyValueFactory<>("BirthDate"));
        TableColumn ageCol = new TableColumn("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("Age"));

            if (Dashboard.personInput.role.equals(Role.Admin)) {
                TableColumn salaryCol = new TableColumn("Salary");
                salaryCol.setCellValueFactory(new PropertyValueFactory<>("Salary"));
                table.getColumns().add(salaryCol);
            }
            for (Teacher t: teacherList
            ) {
                table.getItems().add(t);
            }



        table.getColumns().addAll( idCol, firstNameCol, lastNameCol, birthdateCol, ageCol);

        return table;
    }

    public  BorderPane addBorderPaneTeacher(HBox hbox) {
        BorderPane TablePane = new BorderPane();
        TablePane.setPadding(new Insets(25));
        TableView table = addTableViewTeacher();

        TablePane.setCenter(table);
        if (!Dashboard.personInput.role.equals(Role.Basic)) {
            TablePane.setBottom(hbox);
            System.out.println(Dashboard.personInput.role.toString());
        }
        Text text = new Text("Teachers");
        text.getStyleClass().add("welcome-text");
        TablePane.setTop(text);
        return TablePane;
    }
    public GridPane addGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        grid.getChildren().clear();
        Text text = new Text("Welcome " + Dashboard.personInput.firstName);
        text.getStyleClass().add("welcome-text");
        grid.add(text, 1, 0);
        return grid;
    }

}
