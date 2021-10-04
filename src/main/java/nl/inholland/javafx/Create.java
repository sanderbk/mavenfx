package nl.inholland.javafx;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.inholland.javafx.Users.*;

import java.time.LocalDate;
import java.util.List;

public class Create {
    private Student selectedStudent;

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public BorderPane createPane(Student input) {
        boolean createStudent = false;
        if (input == null) createStudent = true;

        BorderPane createPane = new BorderPane();
        createPane.setPadding(new Insets(25));
        //add title

        Text t = new Text("Add Student");
        if (!createStudent) t.setText("Edit Student");
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
        if (!createStudent) usrNameTextfield.setText(selectedStudent.userName);
        usrNameTextfield.getStyleClass().add("input-login-add");
        //password
        TextField pwPassField = new TextField();
        pwPassField.setPromptText("Password");
        if (!createStudent) pwPassField.setText(selectedStudent.userPassword);
        pwPassField.getStyleClass().add("input-login-add");
        //firstname
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        if (!createStudent) firstNameField.setText(selectedStudent.getFirstName());
        firstNameField.getStyleClass().add("input-login-add");
        //lastname
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        if (!createStudent) lastNameField.setText(selectedStudent.getLastName());
        lastNameField.getStyleClass().add("input-login-add");
        //combobox groups
        ComboBox<SchoolGroup> cbxGroup = new ComboBox<>();
        cbxGroup.setItems( FXCollections.observableArrayList( SchoolGroup.values()));
        cbxGroup.getStyleClass().add("input-login-add");
        cbxGroup.setPromptText("Groep");
        if (!createStudent) cbxGroup.setValue(selectedStudent.getGroup());

        vboxleft.getChildren().addAll(usrNameTextfield, pwPassField, firstNameField, lastNameField, cbxGroup);

        //set left borderpane
        createPane.setLeft(vboxleft);

        //rightvboxform with textfields
        VBox vboxRight = new VBox();
        vboxRight.setPadding(new Insets(10));
        vboxRight.setSpacing(8);
        DatePicker datePicker = new DatePicker();
        datePicker.getStyleClass().add("input-login-add");
        if (!createStudent) datePicker.setValue(selectedStudent.getBirthDate());

        vboxRight.getChildren().add(datePicker);

        //hbox
        HBox hboxBtns = new HBox();
        hboxBtns.setPadding(new Insets(15, 12, 15, 12));
        hboxBtns.setSpacing(10);

        Button buttonAdd = new Button("Add Student");
        if (!createStudent) buttonAdd.setText("Edit Student");
        buttonAdd.setPrefSize(100, 20);
        buttonAdd.getStyleClass().add("login-btn");
        buttonAdd.setVisible(false);

        if (createStudent) {
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
        }
        else {
            buttonAdd.setVisible(true);
        }


        boolean finalCreateStudent = createStudent;
        buttonAdd.setOnAction((ActionEvent event) -> {
            if(studentCorrect(usrNameTextfield.getText(), pwPassField.getText(), firstNameField.getText(),lastNameField.getText(), cbxGroup.getValue(), datePicker.getValue())) {

                if (finalCreateStudent) {
                           addStudentWithForm(usrNameTextfield.getText(),
                                   pwPassField.getText(), firstNameField.getText(),
                                   lastNameField.getText(), cbxGroup.getValue(),
                                   datePicker.getValue());

                       }
                       else {
                           selectedStudent.setUserName(usrNameTextfield.getText());
                           selectedStudent.setUserPassword(pwPassField.getText());
                           selectedStudent.setFirstName(firstNameField.getText());
                           selectedStudent.setLastName(lastNameField.getText());
                           selectedStudent.setGroup(cbxGroup.getValue());
                           selectedStudent.setBirthDate(datePicker.getValue());
                       }
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

    public boolean studentCorrect(String uName, String pW, String firstName, String lastName, SchoolGroup schoolGroup, LocalDate bd) {
        boolean check = !uName.isEmpty() && pW.length() >= 8 && !firstName.isEmpty() && !lastName.isEmpty() && schoolGroup != null && bd != null;
        return check;
    }

    public void addStudentWithForm(String uName, String pW, String firstName, String lastName, SchoolGroup schoolGroup, LocalDate bd) {
        Student sNew = new Student(
                Person.MaxID(), uName, pW, firstName, lastName, Role.Basic, bd, schoolGroup
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
        List<Student> studentList = DbMock.getStudents();

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
            //get student from table
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
            if (table.getSelectionModel().getSelectedItem() != null)
                studentSelecter(table.getSelectionModel().getSelectedItem());
            }
        });
        return table;
    }
    public void studentSelecter(Student s) {
        if (s != null)  selectedStudent = s;
        System.out.println(selectedStudent.firstName + selectedStudent.lastName);

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
        List<Teacher> teacherList = DbMock.getTeachers();

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
        TableView<Teacher> table = addTableViewTeacher();

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
