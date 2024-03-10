package isen.quiz.view;

import isen.quiz.App;
import isen.quiz.model.Database;
import isen.quiz.model.Person;
import isen.quiz.util.PersonDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
    @FXML
    private void goBack(){
        App.showView("HomeScreen");
    }
    private PersonDAO personDAO;
    public Database database;
    @FXML
    public void initialize() {
        PersonDAO personDAO = new PersonDAO(); // Initialize the DAO
        database = new Database("jdbc:sqlite:sqlite.db");
    }
    public void updatePerson() {
        int id = Integer.parseInt(idField.getText());
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String nickName = nickNameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String address = addressField.getText();
        String emailAddress = emailAddressField.getText();

        String birthDate = String.valueOf(Date.valueOf(birthDatePicker.getValue()));

        Person person = new Person(id, lastName, firstName, nickName, phoneNumber, address, emailAddress, birthDate);
        //database.updatePerson(person);

        showSuccessAlert("Person has been updated!");
    }
    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
