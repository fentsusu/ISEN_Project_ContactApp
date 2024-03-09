package isen.quiz.view;

import isen.quiz.model.Database;
import isen.quiz.model.Person;
import isen.quiz.service.DataSourceFactory;
import isen.quiz.util.PersonDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, Integer> idColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> nickNameColumn;
    @FXML
    private TableColumn<Person, String> phoneNumberColumn;
    @FXML
    private TableColumn<Person, String> addressColumn;
    @FXML
    private TableColumn<Person, String> emailAddressColumn;
    @FXML
    private TableColumn<Person, String> birthDateColumn;

    private PersonDAO personDAO;
    public Database database;

    @FXML
    public void initialize() throws SQLException {

        String url = "jdbc:sqlite:sqlite.db";
        Connection connection = DriverManager.getConnection(url);

        listPersons();
//        personDAO = new PersonDAO(); // Initialize the DAO
//
//        // Bind table columns to Person properties
//        idColumn.setCellValueFactory(new PropertyValueFactory<>("idperson"));
//        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//        nickNameColumn.setCellValueFactory(new PropertyValueFactory<>("nickName"));
//        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
//        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
//        emailAddressColumn.setCellValueFactory(new PropertyValueFactory<>("email_address"));
//        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birth_date"));
//
//        refreshTable();
    }

    public List<Person> listPersons() {
        String sql = "SELECT * FROM person";
        List<Person> persons = new ArrayList<>();
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            try (Statement stmt  = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        Person person = new Person(
                                rs.getInt("idperson"),
                                rs.getString("lastname"),
                                rs.getString("firstname"),
                                rs.getString("nickname"),
                                rs.getString("phone_number"),
                                rs.getString("address"),
                                rs.getString("email_address"),
                                rs.getString("birth_date")
                        );
                        persons.add(person);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    public void refreshTable() throws SQLException {
        ObservableList<Person> personList = personDAO.getPersons();
        personTable.setItems(personList);
    }
}
