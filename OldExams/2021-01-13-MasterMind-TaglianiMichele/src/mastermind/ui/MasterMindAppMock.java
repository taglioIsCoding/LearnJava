package mastermind.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MasterMindAppMock extends Application {

	private Controller controller;

	public void start(Stage stage) {
		controller = new MyController(10,4,"gameover.txt");
		if (controller == null) {
			Controller.alert("Errore irrecuperabile", "Errore nella creazione del controller", "Addio");
			System.exit(1);
		}
		stage.setTitle("Master Mind MOCK");
		MasterMindPane root = new MasterMindPane(controller, stage);
		
		Scene scene = new Scene(root, 650, 300, Color.ALICEBLUE);
		stage.setScene(scene);
		stage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}

}
