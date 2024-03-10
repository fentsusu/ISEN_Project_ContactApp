module isen.quiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.junit.jupiter.api;
    requires org.xerial.sqlitejdbc;

    opens isen.quiz.model to javafx.base;
    opens isen.quiz to javafx.fxml;
    opens isen.quiz.view to javafx.fxml;

    exports isen.quiz;
}