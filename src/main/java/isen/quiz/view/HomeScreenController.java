package isen.quiz.view;

import java.io.IOException;
import isen.quiz.App;
import javafx.fxml.FXML;

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
