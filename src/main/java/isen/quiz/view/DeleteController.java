package isen.quiz.view;

import isen.quiz.App;
import isen.quiz.model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class DeleteController {

    @FXML
    private TextField idField;

    @FXML
    private void goBack(){
        App.showView("HomeScreen");
    }
    private Database database;
    public void PersonDAO() {
        database = new Database("jdbc:sqlite:sqlite.db");
    }

    public void deletePerson() {
        try {
            int id = Integer.parseInt(idField.getText());
            showSuccessAlert("Person has been deleted!");
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

    public void goBack(ActionEvent actionEvent) { App.showView("HomeScreen");
    }
}
