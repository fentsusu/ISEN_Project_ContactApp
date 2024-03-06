package isen.quiz.view;

import isen.quiz.model.Database;
import isen.quiz.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class ListController {
    @FXML
    private ListView<Person> listView;

    private Database database;

    public ListController() {
        database = new Database("jdbc:sqlite:sqlite.db");
    }

    public void initialize() {
        // Populate the list view with persons from the database
        List<Person> persons = database.listPersons();
        listView.getItems().addAll(persons);
    }
}
