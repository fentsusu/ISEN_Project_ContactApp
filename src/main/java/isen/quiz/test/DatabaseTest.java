package isen.quiz.test;

import isen.quiz.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This DatabaseTest was error, so we decided to test manually in the testrun class
 */

public class DatabaseTest {

    @BeforeEach
    public void init() throws Exception {
        String url = "jdbc:sqlite:sqlite.db";
        try (Connection connection = DriverManager.getConnection(url)) {
            Statement statement = connection.createStatement();
            // Initialize the database schema
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS person ("
                    + "idperson INTEGER PRIMARY KEY AUTOINCREMENT, /n"
                    + "lastname VARCHAR(45) NOT NULL, /n"
                    + "firstname VARCHAR(45) NOT NULL, /n"
                    + "nickname VARCHAR(45), /n"
                    + "phone_number VARCHAR(15), /n"
                    + "address VARCHAR(200), /n"
                    + "email_address VARCHAR(150), /n"
                    + "birth_date DATE);");
            // Clear the table before each test
            statement.executeUpdate("DELETE FROM person");
            statement.close();
            connection.close();
        }
    }

    @Test
    public void shouldListAllPersons() {
        // Test listing all persons from the database
        List<Person> expectedPersons = new ArrayList<>();
        expectedPersons.add(new Person("Aungkurboribhun", "Methika", "Fent", "0987654321", "1 Rue Andre", "methika@studen.com","20/10/2001"));
        expectedPersons.add(new Person("Yawuth", "Araya", "Jaja", "0983456789", "2 Rue Andre", "araya@studen.com","01/02/2000"));
        expectedPersons.add(new Person("Soodla", "Napat", "Mimi", "0945678921", "3 Rue Andre", "methika@studen.com","07/01/2002"));

        List<Person> actualPersons = fetchAllPersonsFromDatabase();

        assertEquals(expectedPersons.size(), actualPersons.size());
        for (int i = 0; i < expectedPersons.size(); i++) {
            assertEquals(expectedPersons.get(i), actualPersons.get(i));
        }
    }
    public List<Person> fetchAllPersonsFromDatabase() {
        List<Person> persons = new ArrayList<>();
        String url = "jdbc:sqlite:sqlite.db";
        try (Connection connection = DriverManager.getConnection(url)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM person");
            while (resultSet.next()) {
                int id = resultSet.getInt("idperson");
                String lastName = resultSet.getString("lastname");
                String firstName = resultSet.getString("firstname");
                String nickname = resultSet.getString("nickname");
                String phoneNumber = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                String emailAddress = resultSet.getString("email_address");
                String birthDate = resultSet.getString("birth_date");

                Person person = new Person(id, lastName, firstName, nickname, phoneNumber, address, emailAddress, birthDate);
                persons.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }
}