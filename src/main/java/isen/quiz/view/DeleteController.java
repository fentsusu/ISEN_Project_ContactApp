package isen.quiz.view;

import isen.quiz.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteController {

    @FXML
    private TextField idField;

    @FXML
    private void goBack(){
        App.showView("HomeScreen");
    }

    public void deletePerson() {
        String url = "jdbc:sqlite:sqlite.db";
        try (Connection connection = DriverManager.getConnection(url)) {
            int id = Integer.parseInt(idField.getText());

            String sql = "DELETE FROM person WHERE idperson = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    showSuccessAlert("Person has been deleted!");
                    // The id is unique when delete and add new person id not use the old one
                } else {
                    showErrorAlert("No person found with the given ID.");
                }
            }
        } catch (Exception e) {
            showErrorAlert("Error deleting person: " + e.getMessage());
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


    //for the go back method (navigate to the Homescreen)
    public void goBack(ActionEvent actionEvent) { App.showView("HomeScreen");
    }
}
