package isen.quiz;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import isen.quiz.service.DataSourceFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

	private static Scene scene;

	private static BorderPane mainlayout;
	@Override
	public void init() throws Exception {
		try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
			try (Statement statement = connection.createStatement()) {

				// Create `person` table
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS person"
						+ "idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
						+ "lastname VARCHAR(45) NOT NULL,"
						+ "firstname VARCHAR(45) NOT NULL,"
						+ "nickname VARCHAR(45) NOT NULL,"
						+ "phone_number VARCHAR(15) NULL,"
						+ "address VARCHAR(200) NULL,"
						+ "email_address VARCHAR(150) NULL,"
						+ "birth_date DATE NULL);");

				// Delete previously inserted data
				statement.executeUpdate("DELETE FROM person");

				// Insert 3 Persons into table. We only insert the non-null values for now.
				statement.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname) VALUES (1, 'Aungkurboribhun','Methika','Fent','0990015588','1 rue','me@g.com','20/01/2000')");
				statement.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname) VALUES (2, 'Yawuth','Araya','Jaja','0990015555','4 rue','jj@g.com','02/01/2001')");
				statement.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname) VALUES (3, 'A','Alice','Alice','0990018888','5 rue','aa@g.com','20/01/2002')");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
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
