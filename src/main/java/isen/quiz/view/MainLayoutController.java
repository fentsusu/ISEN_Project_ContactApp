package isen.quiz.view;

import isen.quiz.App;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;


public class MainLayoutController {

	@FXML
	private Pane mainContent;

	public void closeApplication() {
		Platform.exit();
	}

	public void gotoHome() {
		App.showView("HomeScreen");
	}
	public void gotoAddPerson() {App.showView("AddPerson");}
	public void gotoUpdatePerson() {
		App.showView("UpdatePerson");
	}
	public void gotoDeletePerson() {
		App.showView("DeletePerson");
	}

	public void showAddPerson() throws Exception {
		Pane addPane = FXMLLoader.load(getClass().getResource("isen/quiz/view/AddPerson.fxml"));
		mainContent.getChildren().setAll(addPane);
	}

	public void showUpdatePerson() throws Exception {
		Pane updatePane = FXMLLoader.load(getClass().getResource("isen/quiz/view/UpdatePerson.fxml"));
		mainContent.getChildren().setAll(updatePane);
	}

	public void showDeletePerson() throws Exception {
		Pane deletePane = FXMLLoader.load(getClass().getResource("isen/quiz/view/DeletePerson.fxml"));
		mainContent.getChildren().setAll(deletePane);
	}

	public void showListPersons() throws Exception {
		Pane listPane = FXMLLoader.load(getClass().getResource("isen/quiz/view/ListPerson.fxml"));
		mainContent.getChildren().setAll(listPane);
	}


}
