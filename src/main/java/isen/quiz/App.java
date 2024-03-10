package isen.quiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App extends Application {

	private static Scene scene;

	private static BorderPane mainlayout;

	//initialize the application
	@Override
	public void init() throws Exception {
		//connect to SQLite database
		String url = "jdbc:sqlite:sqlite.db";
		try (Connection connection = DriverManager.getConnection(url)) {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DROP TABLE person;");
			// Create 'person' table if it doesn't exist
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS person (\n" +
					"    idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
					"    lastname VARCHAR(45) NOT NULL,\n" +
					"    firstname VARCHAR(45) NOT NULL,\n" +
					"    nickname VARCHAR(45) NOT NULL,\n" +
					"    phone_number VARCHAR(15) NULL,\n" +
					"    address VARCHAR(200) NULL,\n" +
					"    email_address VARCHAR(150) NULL,\n" +
					"    birth_date DATE NULL);");

//			// Delete any previously inserted data from the 'person' table
//			statement.executeUpdate("DELETE FROM person");
//
//			// Insert 3 Persons into table. Insert the non-null
//			statement.executeUpdate("INSERT INTO person VALUES (1, 'Aungkurboribhun','Methika','Fent','0990015588','1 rue','me@g.com','02/01/2000')");
//			statement.executeUpdate("INSERT INTO person VALUES (2, 'Yawuth','Araya','Jaja','0990015555','4 rue','jj@g.com','02/01/2001')");
//			statement.executeUpdate("INSERT INTO person VALUES (3, 'A','Alice','Alice','0990018888','5 rue','aa@g.com','07/01/2002')");

			// Handle database connection errors
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("Connection failed at App");
		}
	}

	// start the application
	@Override
	public void start(Stage stage) throws IOException {
		// Set up the main application window
		stage.setTitle("The Contact form");
		mainlayout = loadFXML("MainLayout");
		scene = new Scene(mainlayout, 640, 540);
		stage.setScene(scene);
		stage.show();
		// Display the 'HomeScreen' view
		App.showView("HomeScreen");
	}

	// loads an FXML file
	private static <T> T loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/quiz/view/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	//the entry point of the Java application
	public static void main(String[] args) {
		launch();
	}

	/**
	 * @param rootElement updates the center of our layout with the @rootElement
	 *                    passed in parameter
	 */

	//updates the main layout with a specified view
	public static void showView(String rootElement) {
		try {
			mainlayout.setCenter(loadFXML(rootElement));
		} catch (IOException e) {
			// Handle errors while loading views
			throw new IllegalArgumentException(e);
		}
	}
}
