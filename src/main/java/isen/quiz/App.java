package isen.quiz;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

	private static Scene scene;

	private static BorderPane mainlayout;

	@Override
	public void start(Stage stage) throws IOException {
		stage.setTitle("The Contact form");
		mainlayout = loadFXML("MainLayout");
		scene = new Scene(mainlayout, 640, 480);
		stage.setScene(scene);
		stage.show();
		App.showView("HomeScreen");
	}

	private static <T> T loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/quiz/view/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}




	/**
	 * @param rootElement updates the center of our layout with the @rootElement
	 *                    passed in parameter
	 */
	public static void showView(String rootElement) {
		try {
			mainlayout.setCenter(loadFXML(rootElement));
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
