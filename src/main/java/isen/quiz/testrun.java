package isen.quiz;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class testrun {

    @Test
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:sqlite.db";
        Connection connection = DriverManager.getConnection(url);
        //func connect

        //This printout to check the database connection
        System.out.println("Connected to Database Successfully");
        Statement stmt = connection.createStatement();

        String query = "CREATE TABLE IF NOT EXISTS person (\n" +
                "    idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "    lastname VARCHAR(45) NOT NULL,\n" +
                "    firstname VARCHAR(45) NOT NULL,\n" +
                "    nickname VARCHAR(45) NOT NULL,\n" +
                "    phone_number VARCHAR(15) NULL,\n" +
                "    address VARCHAR(200) NULL,\n" +
                "    email_address VARCHAR(150) NULL,\n" +
                "    birth_date DATE NULL);";
        stmt.executeUpdate(query);

        stmt.executeUpdate("DELETE FROM person");
        //insert data
        stmt.executeUpdate("Insert into person values (1,'Aungkurboribhun','Methika','Fent','0999999999','2 Rue D','fent1@g.com','20/10/2001')");
        stmt.executeUpdate("INSERT INTO person VALUES (2, 'Yawuth','Araya','Jaja','0990015555','4 rue','jj@g.com','02/01/2001')");
        stmt.executeUpdate("INSERT INTO person VALUES (3, 'A','Alice','Alice','0990018888','5 rue','aa@g.com','07/01/2002')");

        String select = "Select * from person";
        ResultSet rs = stmt.executeQuery(select);
        //to check that the information is already inserted
        System.out.println("Print Firstname");
        while (rs.next()){
            //print out firstname to check
            System.out.println(rs.getString("firstname"));
        }
    }

}