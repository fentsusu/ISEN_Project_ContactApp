package isen.quiz.util;

import isen.quiz.model.Person;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class DeleteButtonCellFactory implements Callback<TableColumn<Person, Void>, TableCell<Person, Void>> {
    @Override
    public TableCell<Person, Void> call(TableColumn<Person, Void> param) {
        return new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Person person = getTableView().getItems().get(getIndex());
                    // Call the method to delete the person
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        };
    }
}
