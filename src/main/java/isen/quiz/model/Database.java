package isen.quiz.model;

import isen.quiz.service.DataSourceFactory;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private Connection connection;

    public Database(String url) {
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addPerson(Person person) {
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

//                try(ResultSet ids = stmt.getGeneratedKeys()) {
//                    if (ids.next()) {
//                        Integer generatedId = ids.getInt(1);
//                        person.setId(generatedId);
//                        showSuccessAlert("Person has been added!");
//                    } else {
//                        throw new SQLException("Failed to add new person to contacts.");
//                    }
//                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    public void addPerson1(Person person) {
//        String sql = "INSERT INTO person(lastname, firstname, nickname, phone_number, address, email_address, birth_date) VALUES(?,?,?,?,?,?,?)";
//
//        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//            pstmt.setString(1, person.getLastName());
//            pstmt.setString(2, person.getFirstName());
//            pstmt.setString(3, person.getNickName());
//            pstmt.setString(4, person.getPhoneNumber());
//            pstmt.setString(5, person.getAddress());
//            pstmt.setString(6, person.getEmailAddress());
//            pstmt.setDate(7, new Date(person.getBirthDate().getTime()));
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public void updatePerson(Person person) {
        String sql = "UPDATE person SET lastname = ?, firstname = ?, nickname = ?, phone_number = ?, address = ?, email_address = ?, birth_date = ? WHERE idperson = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, person.getLastName());
            pstmt.setString(2, person.getFirstName());
            pstmt.setString(3, person.getNickName());
            pstmt.setString(4, person.getPhoneNumber());
            pstmt.setString(5, person.getAddress());
            pstmt.setString(6, person.getEmailAddress());
            pstmt.setDate(7, new Date(person.getBirthDate().getTime()));
            pstmt.setInt(8, person.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePerson(int id) {
        String sql = "DELETE FROM person WHERE idperson = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Person> listPersons() {
        String sql = "SELECT * FROM person";
        List<Person> persons = new ArrayList<>();

        try (Statement statement  = connection.createStatement();
             ResultSet rs    = statement.executeQuery(sql)){

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("idperson"));
                person.setLastName(rs.getString("lastname"));
                person.setFirstName(rs.getString("firstname"));
                person.setNickName(rs.getString("nickname"));
                person.setPhoneNumber(rs.getString("phone_number"));
                person.setAddress(rs.getString("address"));
                person.setEmailAddress(rs.getString("email_address"));
                person.setBirthDate(rs.getDate("birth_date"));
                persons.add(person);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return persons;
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



