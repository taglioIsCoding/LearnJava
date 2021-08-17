package tris.ui.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import tris.ui.controller.Controller;
import tris.ui.controller.MyController;

public class TrisApp extends Application {

	private Controller controller;

	public void start(Stage stage) {
		controller = new MyController("printout.txt");
		if (controller == null) {
			Controller.alert("Errore irrecuperabile", "Errore nella creazione del controller", "Addio");
			System.exit(1);
		}
		stage.setTitle("Tris!");
		TrisPane root = new TrisPane(controller, stage);
		
		Scene scene = new Scene(root, 500, 200, Color.ALICEBLUE);
		stage.setScene(scene);
		stage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}

}
