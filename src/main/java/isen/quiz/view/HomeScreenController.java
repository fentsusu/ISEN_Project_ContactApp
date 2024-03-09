package isen.quiz.view;

import isen.quiz.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeScreenController {

	@FXML
	public void addButton() throws IOException {
		App.showView("AddPerson");
	}
	@FXML
	public void listButton() throws IOException {
		App.showView("ListPerson");
	}
}
