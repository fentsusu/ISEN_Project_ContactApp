package isen.quiz.view;

import isen.quiz.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeScreenController {

	//method for the add button, navigate the user to the add person page
	@FXML
	public void addButton() throws IOException {
		App.showView("AddPerson");
	}

	//method for the List button, navigate the user to the list of people page
	@FXML
	public void listButton() throws IOException {
		App.showView("ListPerson");
	}
}
