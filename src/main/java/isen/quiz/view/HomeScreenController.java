/**
 * 
 */
package isen.quiz.view;

import java.io.IOException;

import isen.quiz.App;
import javafx.fxml.FXML;

public class HomeScreenController {

	@FXML
	public void addButton() throws IOException {
		// Here we make use of our new method allowing us to change views inside the main Parent
		App.showView("AddPerson"); //QuizOverview
	}
	@FXML
	public void listButton() throws IOException {
		// Here we make use of our new method allowing us to change views inside the main Parent
		App.showView("ListPerson"); //QuizOverview
	}
}
