/*
package isen.quiz.test;

import isen.quiz.model.Person;
import isen.quiz.view.AddController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AddPersonTest {

    @BeforeEach
    public void init() throws Exception {
        String url = "jdbc:sqlite:sqlite.db";
        try (Connection connection = DriverManager.getConnection(url)) {
            Statement statement = connection.createStatement();
            // Initialize the database schema
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS person ("
                    + "idperson INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "lastname VARCHAR(45) NOT NULL, "
                    + "firstname VARCHAR(45) NOT NULL, "
                    + "nickname VARCHAR(45), "
                    + "phone_number VARCHAR(15), "
                    + "address VARCHAR(200), "
                    + "email_address VARCHAR(150), "
                    + "birth_date DATE);");
            // Clear the table before each test
            statement.executeUpdate("DELETE FROM person");
        }
    }

    @Test
    public void shouldAddPerson() throws Exception {
        // Initialize the controller and manually set TextField values
        AddController controller = new AddController();
        // Assuming controller fields are public or setters are provided for testing
        controller.initializeTextFields(); // This is a hypothetical method you would create to initialize TextFields or mock them.
        controller.lastNameField.setText("Doe");
        controller.firstNameField.setText("John");
        controller.nickNameField.setText("Johnny");
        controller.phoneNumberField.setText("0123456789");
        controller.addressField.setText("123 Main St");
        controller.emailAddressField.setText("john.doe@example.com");
        controller.birthDateField.setText("05/06/1999");

        // Execute the method to add a person
        controller.addPerson();

        // Verification logic remains the same
    }

    // Existing test logic to verify the person was added to the database
}



//package isen.quiz.test;
//
//import isen.quiz.model.Person;
//import isen.quiz.view.AddController;
//import org.junit.jupiter.api.Before;
//import org.junit.jupiter.api.Test;
//
//import java.sql.*;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//public class AddPersonTest {
//
//    @Before
//    public void init() throws Exception {
//        String url = "jdbc:sqlite:sqlite.db";
//        Connection connection = DriverManager.getConnection(url);
//
//        Statement statement = connection.createStatement();
//        // Initialize the database schema
//        String createTableSQL = "CREATE TABLE IF NOT EXISTS person ("
//                + "idperson INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + "lastname VARCHAR(45) NOT NULL, "
//                + "firstname VARCHAR(45) NOT NULL, "
//                + "nickname VARCHAR(45), "
//                + "phone_number VARCHAR(15), "
//                + "address VARCHAR(200), "
//                + "email_address VARCHAR(150), "
//                + "birth_date DATE);";
//        statement.executeUpdate(createTableSQL);
//        // Clear the table before each test
//        statement.executeUpdate("DELETE FROM person");
//        statement.close();
//    }
//
//    @Test
//    public void shouldAddPerson() throws Exception {
//        // Initialize the controller and manually set TextField values
//        AddController controller = new AddController();
//        controller.idField = new TextField();
//        controller.lastNameField = new TextField("Doe");
//        controller.firstNameField = new TextField("John");
//        controller.nickNameField = new TextField("Johnny");
//        controller.phoneNumberField = new TextField("0123456789");
//        controller.addressField = new TextField("123 Main St");
//        controller.emailAddressField = new TextField("john.doe@example.com");
//        controller.birthDateField = new TextField("05/06/1999");
//
//        // Since addPerson doesn't return the added person, you'll have to query the DB afterward to verify
//        controller.addPerson();
//
//        // Assuming you have a method to retrieve the latest added person or you know the ID
//        // For this example, let's assume we're fetching the last inserted person
//        String url = "jdbc:sqlite:sqlite.db";
//
//        Person addedPerson = null; // Placeholder for the person fetched from the database
//
//        // Verify the person was added to the database
//        try (Connection connection = DriverManager.getConnection(url);
//             Statement stmt = connection.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT * FROM person ORDER BY idperson DESC LIMIT 1")) { // Get the last inserted person
//
//            if (rs.next()) {
//                addedPerson = new Person();
//                addedPerson.setId(rs.getInt("idperson"));
//                addedPerson.setLastName(rs.getString("lastname"));
//                addedPerson.setFirstName(rs.getString("firstname"));
//                addedPerson.setNickName(rs.getString("nickname"));
//                addedPerson.setPhoneNumber(rs.getString("phone_number"));
//                addedPerson.setAddress(rs.getString("address"));
//                addedPerson.setEmailAddress(rs.getString("email_address"));
//                addedPerson.setBirthDate(rs.getString("birth_date"));
//            }
//        }
//
//        assertThat(addedPerson).isNotNull();
//        assertThat(addedPerson.getLastName()).isEqualTo("Doe");
//        assertThat(addedPerson.getFirstName()).isEqualTo("John");
//        // Continue asserting other fields
//    }
//
//
//}*/
