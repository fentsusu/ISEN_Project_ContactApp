package isen.quiz.view;

import isen.quiz.model.Database;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DeleteController {
    @FXML
    private TextField idField;

    private Database database;

    public DeleteController() {
        database = new Database("jdbc:sqlite:sqlitedb");
    }

    public void deletePerson() {
        int id = Integer.parseInt(idField.getText());
        database.deletePerson(id);

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Person has been deleted!");

        alert.showAndWait();
    }
}
