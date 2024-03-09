package isen.quiz.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public void databaseConnect() throws SQLException {
        String url = "jdbc:sqlite:sqlite.db";
        Connection connection = DriverManager.getConnection(url);
    }
}
