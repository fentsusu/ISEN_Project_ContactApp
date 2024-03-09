package isen.quiz;

import java.sql.*;

public class testrun {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:sqlite.db";
        Connection connection = DriverManager.getConnection(url);
        //func connect


        System.out.println("Connected Successfully");
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

//        String insert = "Insert into person values (4,'me','me','me','0999999999','d','d','d')";
//        stmt.executeUpdate(insert);

        String select = "Select * from person";
        ResultSet rs = stmt.executeQuery(select);
        while (rs.next()){
            System.out.println(rs.getString("firstname"));
        }
    }
}
