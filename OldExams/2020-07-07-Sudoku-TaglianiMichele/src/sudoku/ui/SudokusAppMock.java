package sudoku.ui;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sudoku.controller.Controller;
import sudoku.controller.MyController;
import sudoku.model.SudokuBoard;

public class SudokusAppMock extends Application {

	private Controller controller;

	public void start(Stage stage) {
		
		SudokuBoard sudoku = new SudokuBoard();
		sudoku.setCellRow(0, new int[] {0,0,0,0,9,8,0,3,0});
		sudoku.setCellRow(1, new int[] {3,0,0,4,0,5,9,0,0});
		sudoku.setCellRow(2, new int[] {0,9,0,0,0,0,0,4,6});
		sudoku.setCellRow(3, new int[] {1,6,0,0,0,0,0,7,5});
		sudoku.setCellRow(4, new int[] {0,0,0,3,8,0,0,0,9});
		sudoku.setCellRow(5, new int[] {2,3,9,0,0,0,4,0,0});
		sudoku.setCellRow(6, new int[] {0,4,7,0,0,1,0,5,0});
		sudoku.setCellRow(7, new int[] {0,1,0,8,0,0,0,0,0});
		sudoku.setCellRow(8, new int[] {0,0,0,0,3,0,0,9,7});
		

		controller = new MyController(sudoku);
		if (controller == null) {
			alert("Errore irrecuperabile", "Errore nella creazione del controller", "Addio");
			System.exit(1);
		}
		stage.setTitle("Sudoku");
		MainPane root = new MainPane(controller, stage);
		
		Scene scene = new Scene(root, 750, 350, Color.ALICEBLUE);
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
