package isen.quiz.util;

import isen.quiz.App;
import isen.quiz.model.Database;
import isen.quiz.model.Person;
import isen.quiz.service.DataSourceFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static isen.quiz.service.DataSourceFactory.connection;

public class PersonDAO {
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
    private final Database database;
    public PersonDAO() {
        database = new Database("jdbc:sqlite:sqlite.db");
    }

    public void initialize() {
        // Set the default value to today's date
        birthDatePicker.setValue(LocalDate.now());
    }
    public Person addPerson(Person person) {
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            String sqlQuery = "INSERT INTO person(lastname, firstname, nickname, phone_number, address, email_address, birth_date) VALUES (?,?,?,?,?,?,?)";
            try (PreparedStatement stmt = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, person.getLastName());
                stmt.setString(2, person.getFirstName());
                stmt.setString(3, person.getNickName());
                stmt.setString(4, person.getPhoneNumber());
                stmt.setObject(5, person.getAddress());
                stmt.setString(6, person.getEmailAddress());
                stmt.setObject(7, person.getBirthDate());

                stmt.executeUpdate();

                try(ResultSet ids = stmt.getGeneratedKeys()) {
                    if (ids.next()) {
                        Integer generatedId = ids.getInt(1);
                        // We set id of person object passed to the method equal to the generated ID.
                        person.setId(generatedId);
                        showSuccessAlert("Person has been added!");
                    } else {
                        throw new SQLException("Failed to add new person to contacts.");
                    }
                }

            }
        } catch (SQLException e) {
            // Print stack trace to help us debug
            e.printStackTrace();
        }
        return person;
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

    public void deletePerson() {
        try {
            int id = Integer.parseInt(idField.getText());
            database.deletePerson(id);
            showSuccessAlert("Person has been deleted!");
        } catch (Exception e) {
            showErrorAlert("Error deleting person: " + e.getMessage());
        }
    }

    public void updatePerson() {
        int id = Integer.parseInt(idField.getText());
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String nickName = nickNameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String address = addressField.getText();
        String emailAddress = emailAddressField.getText();
        Date birthDate;
        birthDate = Date.valueOf(birthDatePicker.getValue());

        Person person = new Person(id, lastName, firstName, nickName, phoneNumber, address, emailAddress, birthDate);
        database.updatePerson(person);

        showSuccessAlert("Person has been updated!");
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
                                rs.getDate("birth_date")
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
