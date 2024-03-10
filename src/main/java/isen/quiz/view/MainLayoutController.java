package isen.quiz.view;

import isen.quiz.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
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
	public void gotoListPerson() {App.showView("ListPerson");}
	public void gotoUpdatePerson() {
		App.showView("UpdatePerson");
	}
	public void gotoDeletePerson() {
		App.showView("DeletePerson");
	}

}
