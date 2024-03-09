package isen.quiz.service;

import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceFactory {

    private static SQLiteDataSource dataSource;
    public static Connection connection;

    public DataSourceFactory() {
        throw new IllegalStateException("This class should not be instantiated.");
    }

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new SQLiteDataSource();
            dataSource.setUrl("jdbc:sqlite:sqlite.db");
        }
        return dataSource;
    }

    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null) {
            String databaseUrl = "jdbc:sqlite:sqlite.db";
            connection = DriverManager.getConnection(databaseUrl);
        }
        return connection;
    }
}