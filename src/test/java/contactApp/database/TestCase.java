package contactApp.database;

import isen.quiz.model.Person;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

public class TestCase {
    private Person person = new Person();

    @Before
    public void init() throws Exception{
        String url = "jdbc:sqlite:sqlite.db";
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS person (\n" +
                "    idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "    lastname VARCHAR(45) NOT NULL,\n" +
                "    firstname VARCHAR(45) NOT NULL,\n" +
                "    nickname VARCHAR(45) NOT NULL,\n" +
                "    phone_number VARCHAR(15) NULL,\n" +
                "    address VARCHAR(200) NULL,\n" +
                "    email_address VARCHAR(150) NULL,\n" +
                "    birth_date VARCHAR(150) NULL);");

        // Delete previously inserted data
        statement.executeUpdate("DELETE FROM person");

        // Insert test data
        statement.executeUpdate("INSERT INTO person VALUES (1, 'Aungkurboribhun','Methika','Fent','0990015588','1 rue','me@g.com','2000-01-02')");
        statement.executeUpdate("INSERT INTO person VALUES (2, 'Yawuth','Araya','Jaja','0990015555','2 rue','jj@g.com','2001-01-02')");
        statement.executeUpdate("INSERT INTO person VALUES (3, 'Soodla','Napat','Mimi','0990018888','3 rue','mm@g.com','2002-01-07')");
        statement.executeUpdate("INSERT INTO person VALUES (4, 'Suthamma','Sirimata','Mint','0990017777','4 rue','m@g.com','2002-23-02')");

        statement.close();
        connection.close();
    }

    @Test
    public void dbConnection() {
        String url = "jdbc:sqlite:sqlite.db";
        try {
            Connection connection = DriverManager.getConnection(url);
            System.out.println("Connected Successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> getAllPersons() throws SQLException {
        String url = "jdbc:sqlite:sqlite.db";
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

        String sqlQuery = "SELECT * FROM person";
        ResultSet result = statement.executeQuery(sqlQuery);
        return (List<Person>) result;
    }
    /*@Test
    public void shouldListAllPersons() throws SQLException {
        // WHEN
        List<Person> allPersons = Person.getAllPersons();
        // THEN
        assertThat(allPersons).hasSize(4);
        assertThat(allPersons).extracting(Person::getId, Person::getLastName, Person::getFirstName, Person::getNickName).containsOnly(
                tuple(1, "Aungkurboribhun", "Methika", "Fent"),
                tuple(2, "Yawuth", "Araya", "Jaja"),
                tuple(3, "Soodla", "Napat", "Mimi"),
                tuple(4, "Suthamma", "Sirimata", "Mint"));
    }*/
}
