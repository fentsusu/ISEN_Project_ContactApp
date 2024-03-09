package isen.quiz.util;

import isen.quiz.App;
import isen.quiz.model.Database;
import isen.quiz.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.time.LocalDate;

public class PersonDAO {

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
    private static final String databaseUrl = "jdbc:sqlite:sqlite.db";
    public void initialize() throws SQLException {
        // Set the default value to today's date
        birthDatePicker.setValue(LocalDate.now());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        nickNameColumn.setCellValueFactory(new PropertyValueFactory<>("nickName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailAddressColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        personTable.setItems(getPersons());

    }

    public ObservableList<Person> getPersons() throws SQLException {
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
                System.out.println(rs.getString("lastName"));

                person.setFirstName(rs.getString("firstName"));
                person.setNickName(rs.getString("nickName"));
                person.setPhoneNumber(rs.getString("phone_number"));
                person.setAddress(rs.getString("address"));
                person.setEmailAddress(rs.getString("email_address"));

                //SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
                String rs1 = rs.getString("birth_date");
                //java.util.Date date1 = dateFormat1.parse(rs1);

                System.out.println(rs.getString("birth_date"));
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


//    this one is not use
//    public Person addPerson(Person person) {
//        String url = "jdbc:sqlite:sqlite.db";
//        try (Connection connection = DriverManager.getConnection(url);) {
//            String sqlQuery = "INSERT INTO person(lastname, firstname, nickname, phone_number, address, email_address, birth_date) VALUES (?,?,?,?,?,?,?)";
//            try (PreparedStatement stmt = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
//                stmt.setString(1, person.getLastName());
//                stmt.setString(2, person.getFirstName());
//                stmt.setString(3, person.getNickName());
//                stmt.setString(4, person.getPhoneNumber());
//                stmt.setObject(5, person.getAddress());
//                stmt.setString(6, person.getEmailAddress());
//                stmt.setObject(7, person.getBirthDate());
//
//                stmt.executeUpdate();
//
//                try(ResultSet ids = stmt.getGeneratedKeys()) {
//                    if (ids.next()) {
//                        Integer generatedId = ids.getInt(1);
//                        // We set id of person object passed to the method equal to the generated ID.
//                        person.setId(generatedId);
//                        showSuccessAlert("Person has been added!");
//                    } else {
//                        throw new SQLException("Failed to add new person to contacts.");
//                    }
//                }
//
//            }
//        } catch (SQLException e) {
//            // Print stack trace to help us debug
//            e.printStackTrace();
//        }
//        return person;
//    }


//    public void addPerson() {
//        try{
//            String lastName = lastNameField.getText();
//            String firstName = firstNameField.getText();
//            String nickName = nickNameField.getText();
//            String phoneNumber = phoneNumberField.getText();
//            String address = addressField.getText();
//            String emailAddress = emailAddressField.getText();
//            String birthDate = String.valueOf(birthDatePicker.getValue());
//
//
//            Person person = new Person(lastName, firstName, nickName, phoneNumber, address, emailAddress, birthDate);
//            database.addPerson(person);
//
//            showSuccessAlert("Person has been added!");
//        } catch (Exception e) {
//            showErrorAlert("Error adding person: " + e.getMessage());
//        }
//    }

    public void deletePerson() {
        try {
            int id = Integer.parseInt(idField.getText());
            database.deletePerson(id);
            showSuccessAlert("Person has been deleted!");
        } catch (Exception e) {
            showErrorAlert("Error deleting person: " + e.getMessage());
        }
    }



//    public List<Person> listPersons() {
//        String sql = "SELECT * FROM person";
//        List<Person> persons = new ArrayList<>();
//        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
//            try (Statement stmt  = connection.createStatement()) {
//                try (ResultSet rs = stmt.executeQuery(sql)) {
//                    while (rs.next()) {
//                        Person person = new Person(
//                                rs.getInt("idperson"),
//                                rs.getString("lastname"),
//                                rs.getString("firstname"),
//                                rs.getString("nickname"),
//                                rs.getString("phone_number"),
//                                rs.getString("address"),
//                                rs.getString("email_address"),
//                                rs.getString("birth_date")
//                        );
//                        persons.add(person);
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return persons;
//    }

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
