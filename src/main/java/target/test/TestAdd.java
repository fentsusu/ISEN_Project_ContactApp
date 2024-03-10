
import static org.junit.jupiter.api.Assertions.assertEquals; // Add this import statement

import isen.quiz.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestAdd {

    @BeforeAll
    public static void setUp() throws SQLException {
        // Initialize the database and insert test data
        initializeDatabase();
    }

    @Test
    public void runApplication() {
        // Here you can test any functionality of your application
        // For simplicity, this test method does not contain any assertions
    }

    @Test
    public void shouldListAllPersons() {
        // Test listing all persons from the database
        List<Person> expectedPersons = new ArrayList<>();
        expectedPersons.add(new Person("Aungkurboribhun", "Methika", "Fent", "0987654321", "1 Rue Andre", "methika@studen.com","2000-01-02"));
        expectedPersons.add(new Person("Yawuth", "Araya", "Jaja", "0983456789", "2 Rue Andre", "araya@studen.com","2001-01-02"));
        expectedPersons.add(new Person("Soodla", "Napat", "Mimi", "0945678921", "3 Rue Andre", "methika@studen.com","2002-01-07"));

        List<Person> actualPersons = fetchAllPersonsFromDatabase();

        assertEquals(expectedPersons.size(), actualPersons.size());
        for (int i = 0; i < expectedPersons.size(); i++) {
            assertEquals(expectedPersons.get(i), actualPersons.get(i));
        }
    }

    @Test
    public void shouldAddPerson() {
        // Test adding a person to the database
        Person personToAdd = new Person( "lastName", "firstName", "nickName", "0987654321", "address", "email@Address.com", "birthDate");

        Person addedPerson = addPersonToDatabase(personToAdd);

        assertEquals(personToAdd.getLastName(), addedPerson.getLastName());
        assertEquals(personToAdd.getFirstName(), addedPerson.getFirstName());
        assertEquals(personToAdd.getNickName(), addedPerson.getNickName());
        assertEquals(personToAdd.getBirthDate(), addedPerson.getBirthDate());
    }

    @Test
    public void shouldDeletePerson() {
        // Test deleting a person from the database
        Person personToDelete = new Person(int id, String lastName, String firstName, String nickName,
                String phoneNumber, String address, String emailAddress, String birthDate);


        int rowsDeleted = deletePersonFromDatabase(personToDelete);

        assertEquals(1, rowsDeleted);
    }

    @Test
    public void shouldUpdateLastName() {
        // Test updating the last name of a person in the database
        Person personToUpdate = new Person(1, "Aungkurboribhun", "Methika", "Fent", "", "", "", "2000-01-02");

        Person updatedPerson = updateLastNameInDatabase(personToUpdate, "NewLastName");

        assertEquals("NewLastName", updatedPerson.getLastName());
    }

    private static void initializeDatabase() throws SQLException {
        String url = "jdbc:sqlite:sqlite.db";
        try (Connection connection = DriverManager.getConnection(url)) {
            Statement statement = connection.createStatement();

            statement.executeUpdate("DROP TABLE IF EXISTS person;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS person (\n" +
                    "    idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "    lastname VARCHAR(45) NOT NULL,\n" +
                    "    firstname VARCHAR(45) NOT NULL,\n" +
                    "    nickname VARCHAR(45) NOT NULL,\n" +
                    "    phone_number VARCHAR(15) NULL,\n" +
                    "    address VARCHAR(200) NULL,\n" +
                    "    email_address VARCHAR(150) NULL,\n" +
                    "    birth_date VARCHAR(10) NULL);");

            // Delete previously inserted data
            statement.executeUpdate("DELETE FROM person");

            // Insert test data
            statement.executeUpdate("INSERT INTO person (lastname, firstname, nickname, birth_date) VALUES ('Aungkurboribhun', 'Methika', 'Fent', '2000-01-02')");
            statement.executeUpdate("INSERT INTO person (lastname, firstname, nickname, birth_date) VALUES ('Yawuth', 'Araya', 'Jaja', '2001-01-02')");
            statement.executeUpdate("INSERT INTO person (lastname, firstname, nickname, birth_date) VALUES ('Soodla', 'Napat', 'Mimi', '2002-01-07')");
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

    public Person addPersonToDatabase(Person person) {
        String url = "jdbc:sqlite:sqlite.db";
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO person (lastname, firstname, nickname, birth_date) VALUES (?, ?, ?, ?)");
            statement.setString(1, person.getLastName());
            statement.setString(2, person.getFirstName());
            statement.setString(3, person.getNicName());
            statement.setString(4, person.getBirthDate());
            statement.executeUpdate();

            // Retrieve the generated id
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                person.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public int deletePersonFromDatabase(Person person) {
        int rowsDeleted = 0;
        String url = "jdbc:sqlite:sqlite.db";
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM person WHERE idperson = ?");
            statement.setInt(1, person.getId());
            rowsDeleted = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsDeleted;
    }

    public Person updateLastNameInDatabase(Person person, String newLastName) {
        String url = "jdbc:sqlite:sqlite.db";
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement statement = connection.prepareStatement("UPDATE person SET lastname = ? WHERE idperson = ?");
            statement.setString(1, newLastName);
            statement.setInt(2, person.getId());
            statement.executeUpdate();
            person.setLastName(newLastName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public Person getPersonByIdFromDatabase(int id) {
        Person person = null;
        String url = "jdbc:sqlite:sqlite.db";
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM person WHERE idperson = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String lastName = resultSet.getString("lastname");
                String firstName = resultSet.getString("firstname");
                String nickName = resultSet.getString("nickname");
                String phoneNumber = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                String emailAddress = resultSet.getString("email_address");
                String birthDate = resultSet.getString("birth_date");

                person = new Person(id, lastName, firstName, nickName, phoneNumber, address, emailAddress, birthDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

}
