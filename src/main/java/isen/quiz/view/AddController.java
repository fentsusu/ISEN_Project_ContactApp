package isen.quiz.view;

import isen.quiz.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.sql.*;

public class AddController {

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
    private TextField birthDateField;

    public void initialize() {}
    public void addPerson() {
        String url = "jdbc:sqlite:sqlite.db";
        try (Connection connection = DriverManager.getConnection(url)) {
            String lastName = lastNameField.getText();
            String firstName = firstNameField.getText();
            String nickName = nickNameField.getText();
            String phoneNumber = phoneNumberField.getText();
            String address = addressField.getText();
            String emailAddress = emailAddressField.getText();
            String birthDate = birthDateField.getText();

            String addquery = "INSERT INTO person(lastname, firstname, nickname, " +
                    "phone_number, address, email_address, birth_date) VALUES (?,?,?,?,?,?,?)";
            try (PreparedStatement stmt = connection.prepareStatement(addquery, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, lastName);
                stmt.setString(2, firstName);
                stmt.setString(3, nickName);
                stmt.setString(4, phoneNumber);
                stmt.setString(5, address);
                stmt.setString(6, emailAddress);
                stmt.setString(7, birthDate);

                stmt.executeUpdate();
            }
            catch (SQLException e) {

            }
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

    public void goBack(ActionEvent actionEvent) { App.showView("HomeScreen");
    }
}