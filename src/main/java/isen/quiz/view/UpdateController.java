package isen.quiz.view;

import isen.quiz.model.Database;
import isen.quiz.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;

import java.sql.Date;

public class UpdateController {
    @FXML
    private TextField idField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField nickNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField emailAddressField;
    @FXML
    private DatePicker birthDatePicker;
    private Database database;

    public UpdateController() {
        database = new Database("jdbc:sqlite:sqlitedb");
    }

    public void updatePerson() {
        int id = Integer.parseInt(idField.getText());
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String nickName = nickNameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String address = addressField.getText();
        String emailAddress = emailAddressField.getText();
        Date birthDate;
        birthDate = Date.valueOf(birthDatePicker.getValue());

        Person person = new Person(id, lastName, firstName, nickName, phoneNumber, address, emailAddress, birthDate);
        database.updatePerson(person);

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Person has been updated!");

        alert.showAndWait();
    }
}
