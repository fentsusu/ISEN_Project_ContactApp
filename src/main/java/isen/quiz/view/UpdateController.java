package isen.quiz.view;

import isen.quiz.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
    private TextField birthDateField;
    @FXML
    private void goBack(){
        App.showView("HomeScreen");
    }

    public void updatePerson() {
        String url = "jdbc:sqlite:sqlite.db";
    try (Connection connection = DriverManager.getConnection(url)) {
        String sql = "UPDATE person SET lastname = ?, firstname = ?, nickname = ?, phone_number = ?, address = ?, email_address = ?, birth_date = ? WHERE idperson = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Set parameters for the prepared statement from your person object
            pstmt.setString(1, lastNameField.getText());
            pstmt.setString(2, firstNameField.getText());
            pstmt.setString(3, nickNameField.getText());
            pstmt.setString(4, phoneNumberField.getText());
            pstmt.setString(5, addressField.getText());
            pstmt.setString(6, emailAddressField.getText());
            pstmt.setString(7, birthDateField.getText());
            pstmt.setInt(8, Integer.parseInt(idField.getText()));

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                showSuccessAlert("Person has been updated!");
            } else {
                showErrorAlert("No person found with the given ID.");
            }
        }
    } catch (Exception e) {
        showErrorAlert("Error updating person: " + e.getMessage());
    }
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
