package isen.quiz.view;

import isen.quiz.model.Database;
import isen.quiz.model.Person;
import isen.quiz.util.PersonDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

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

        // Bind table columns to Person properties
//        idColumn.setCellValueFactory(new PropertyValueFactory<>("idperson"));
//        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//        nickNameColumn.setCellValueFactory(new PropertyValueFactory<>("nickName"));
//        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
//        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
//        emailAddressColumn.setCellValueFactory(new PropertyValueFactory<>("email_address"));
//        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birth_date"));

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        nickNameColumn.setCellValueFactory(new PropertyValueFactory<>("nickName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailAddressColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));


        try {
            personTable.setItems(listPersons()); // Bind the ObservableList to the TableView
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<Person> listPersons() throws SQLException {
        String url = "jdbc:sqlite:sqlite.db";

        ObservableList<Person> persons = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(url)){
            Statement stmt = connection.createStatement();
            String select = "Select * from person";
            ResultSet rs = stmt.executeQuery(select);
            System.out.println("Try1111");


            while (rs.next()) {
                Person person = new Person();

                person.setId(rs.getInt("idperson"));
                person.setLastName(rs.getString("lastName"));
                person.setFirstName(rs.getString("firstName"));
                person.setNickName(rs.getString("nickName"));
                person.setPhoneNumber(rs.getString("phone_number"));
                person.setAddress(rs.getString("address"));
                person.setEmailAddress(rs.getString("email_address"));
                person.setBirthDate(rs.getString("birth_date"));

                persons.add(person);
                System.out.println("Select Successfully");

            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e);
        }
        return persons;
    }

}
