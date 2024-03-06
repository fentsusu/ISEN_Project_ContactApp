package isen.quiz.test;

import isen.quiz.model.Database;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    @Test
    public void testConnection() {
        Database database = new Database("jdbc:sqlite:sqlitedb");
        assertNotNull(database);
    }
}
