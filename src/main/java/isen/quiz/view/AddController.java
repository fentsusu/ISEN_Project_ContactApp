package isen.quiz.view;

import isen.quiz.model.Database;
import isen.quiz.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
public class AddController {
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
    private DatePicker birthDatePicker; // Use DatePicker for birth date input

    private final Database database;

    public void initialize() {
        // Set the default value to today's date
        birthDatePicker.setValue(LocalDate.now());
    }


    public AddController() {
        database = new Database("jdbc:sqlite:sqlitedb");
    }

    public void addPerson() {
        try{
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String nickName = nickNameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String address = addressField.getText();
        String emailAddress = emailAddressField.getText();
        Date birthDate;
        birthDate = Date.valueOf(birthDatePicker.getValue());

        Person person = new Person(lastName, firstName, nickName, phoneNumber, address, emailAddress, birthDate);
        database.addPerson(person);

        showSuccessAlert("Person has been added!");
        } catch (Exception e) {
            showErrorAlert("Error adding person: " + e.getMessage());
        }
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String errorMessage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
