package isen.quiz.view;

import isen.quiz.App;
import isen.quiz.model.Database;
import isen.quiz.model.Person;
import isen.quiz.util.DeleteButtonCellFactory;
import isen.quiz.util.EditButtonCellFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

public class ListController {
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, Void> editColumn;
    @FXML
    private TableColumn<Person, Void> deleteColumn;

    private Database database;

    public ListController() {
        database = new Database("jdbc:sqlite:sqlitedb");
    }

    @FXML
    public void initialize() {
        // Populate the table view with persons from the database
        List<Person> persons = database.listPersons();
        tableView.getItems().addAll(persons);

        // Set the cell factories
        editColumn.setCellFactory(new EditButtonCellFactory());
        deleteColumn.setCellFactory(new DeleteButtonCellFactory());
    }

    @FXML
    public void goBack() {
        App.showView("HomeScreen");
    }
}
