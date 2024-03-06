module isen.quiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.junit.jupiter.api;

    opens isen.quiz to javafx.fxml;
    // All our views will be in this package, that must be accessible by JavaFX
    opens isen.quiz.view to javafx.fxml;
    opens isen.quiz.test to org.junit.jupiter;
    exports isen.quiz;
}