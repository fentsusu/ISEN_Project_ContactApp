package target.test;

//import isen.quiz.model.Person;
//import isen.quiz.service.DataSourceFactory;
//import isen.quiz.util.PersonDAO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.Assert.assertThat;
//
//public class FunctionTest {
//    private final PersonDAO personDao = new PersonDAO();
//    @BeforeEach
//    public void init() throws Exception {
//        Connection connection = DataSourceFactory.getConnection();
//        Statement statement = connection.createStatement();
//        // Create person table
//        statement.executeUpdate("CREATE TABLE IF NOT EXISTS person "
//                + "idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
//                + "lastname VARCHAR(45) NOT NULL,"
//                + "firstname VARCHAR(45) NOT NULL,"
//                + "nickname VARCHAR(45) NOT NULL,"
//                + "phone_number VARCHAR(15) NULL,"
//                + "address VARCHAR(200) NULL,"
//                + "email_address VARCHAR(150) NULL,"
//                + "birth_date DATE NULL);");
//
//        // Clear the table
//        statement.executeUpdate("DELETE FROM person");
//
//        // Insert 3 Persons into table
//        statement.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname,phone_number,address,emailAddress,birthDate) VALUES (1, 'Aungkurboribhun','Methika','Fent','0990015588','1 rue','me@g.com','09/01/2000')");
//        statement.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname,phone_number,address,emailAddress,birthDate) VALUES (2, 'Yawuth','Araya','Jaja','0990015555','4 rue','jj@g.com','02/01/2001')");
//        statement.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname,phone_number,address,emailAddress,birthDate) VALUES (3, 'A','Alice','Alice','0990018888','5 rue','aa@g.com','02/06/2002')");
//
//        // Close connection
//        statement.close();
//    }
//
//    @Test
//    public void shouldListAllPersons() {
//        // WHEN
//        List<Person> personsList = personDao.listPersons();
//        // THEN
//        assertThat(personsList).hasSize(3);
//        assertThat(personsList).extracting(Person::getId, Person::getLastName, Person::getFirstName, Person::getNickName, Person::getPhoneNumber, Person::getAddress, Person::getEmailAddress,Person::getBirthDate).containsOnly(
//                tuple(1, "Aungkurboribhun","Methika","Fent","0990015588","1 rue","me@g.com","09/01/2000"),
//                tuple(2, "Yawuth","Araya","Jaja","0990015555","4 rue","jj@g.com","02/01/2001"),
//                tuple(3, "A","Alice","Alice","0990018888","5 rue","aa@g.com","02/06/2002"));
//    }
//
//    @Test
//    public void shouldAddPerson() throws Exception {
//        // WHEN
//        Person person = new Person();
//        person.setLastName("Dee");
//        person.setFirstName("Avril");
//        person.setNickName("Avril");
//        person.setPhoneNumber("0990015599");
//        person.setAddress("9 rue A");
//        person.setEmailAddress("a@g.com");
//
//        Person addedPerson = personDao.addPerson(person);
//        // THEN
//        Connection connection = DataSourceFactory.getDataSource().getConnection();
//        String sqlQuery = "SELECT * FROM person WHERE idperson=?";
//        PreparedStatement statement = connection.prepareStatement(sqlQuery);
//        statement.setInt(1, person.getId());
//        ResultSet result = statement.executeQuery();
//        assertThat(result.next()).isTrue(); // Assert that the update has been successful.
//        assertThat(result.getInt("idperson")).isNotNull();
//        assertThat(result.getInt("idperson")).isEqualTo(person.getId());
//        assertThat(addedPerson.getId()).isEqualTo(person.getId());
//        assertThat(addedPerson.getId()).isNotNull();    // id is not null;
//        assertThat(result.next()).isFalse();    // no next element in the result set.
//        result.close();
//        statement.close();
//        connection.close();
//    }
//}


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FunctionTest {

    @BeforeAll
    public static void setUp() throws SQLException {
        // Initialize the database and insert test data
        initializeDatabase();
    }

    @Test
    public void runApplication() {
    }

    public static void initializeDatabase() throws SQLException {
        String url = "jdbc:sqlite:sqlite.db";
        try (Connection connection = DriverManager.getConnection(url)) {
            Statement statement = connection.createStatement();

            statement.executeUpdate("DROP TABLE person;");
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
        }
    }
}

