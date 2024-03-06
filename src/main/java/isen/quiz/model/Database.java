package isen.quiz.model;

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
        String sql = "INSERT INTO person(lastname, firstname, nickname, phone_number, address, email_address, birth_date) VALUES(?,?,?,?,?,?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, person.getLastName());
            pstmt.setString(2, person.getFirstName());
            pstmt.setString(3, person.getNickName());
            pstmt.setString(4, person.getPhoneNumber());
            pstmt.setString(5, person.getAddress());
            pstmt.setString(6, person.getEmailAddress());
            pstmt.setDate(7, new Date(person.getBirthDate().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

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
}
