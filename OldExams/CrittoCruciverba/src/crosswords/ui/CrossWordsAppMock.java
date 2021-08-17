package crosswords.ui;

import crosswords.controller.Controller;
import crosswords.controller.MyController;
import crosswords.model.MyGame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CrossWordsAppMock extends Application {

	private Controller controller;

	public void start(Stage stage) {
		
		MyGame game = new MyGame(5);
		game.setCellRow(0, new int[] {1,2,3,4,5});
		game.setCellRow(1, new int[] {9,10,0,6,3});
		game.setCellRow(2, new int[] {10,4,12,0,13});
		game.setCellRow(3, new int[] {0,12,10,15,0});
		game.setCellRow(4, new int[] {16,10,1,1,10});
		

		controller = new MyController(game);
		if (controller == null) {
			alert("Errore irrecuperabile", "Errore nella creazione del controller", "Addio");
			System.exit(1);
		}
		stage.setTitle("CrittoCruciverba");
		MainPane root = new MainPane(controller, stage);
		
		Scene scene = new Scene(root, 450, 300, Color.ALICEBLUE);
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
