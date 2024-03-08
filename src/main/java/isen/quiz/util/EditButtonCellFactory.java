package isen.quiz.util;

import isen.quiz.model.Person;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class EditButtonCellFactory implements Callback<TableColumn<Person, Void>, TableCell<Person, Void>> {
    public EditButtonCellFactory(Object goToUpdate) {
    }

    @Override
    public TableCell<Person, Void> call(TableColumn<Person, Void> param) {
        return new TableCell<>() {
            private final Button editButton = new Button("Edit");

            {
                editButton.setOnAction(event -> {
                    Person person = getTableView().getItems().get(getIndex());
                    // Call the method to edit the person
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        };
    }
}
