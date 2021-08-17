package crosswords.ui;


import java.io.FileReader;
import java.io.IOException;

import crosswords.controller.Controller;
import crosswords.controller.MyController;
import crosswords.persistence.BadFileFormatException;
import crosswords.persistence.ConfigReader;
import crosswords.persistence.MyConfigReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CrossWordsApp extends Application {

	private Controller controller;

	public void start(Stage stage) {
		try {
			ConfigReader configReader = new MyConfigReader(new FileReader("config.txt"));
			//controller = new MyController(configReader.getSize());
			controller = new MyController(configReader.getScheme());
			//System.out.println(configReader.getSGame());
			System.out.println("Configuration read");
		} catch ( BadFileFormatException  | IOException e) {
			alert("File non trovato", "Errore nella creazione del controller", "Addio");
			System.exit(1);
		} 
	
		stage.setTitle("CrittoCruciverba");
		MainPane root = new MainPane(controller, stage);
		Scene scene = new Scene(root, 750, 300, Color.ALICEBLUE);
		stage.setScene(scene);
		stage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}

	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}


}
