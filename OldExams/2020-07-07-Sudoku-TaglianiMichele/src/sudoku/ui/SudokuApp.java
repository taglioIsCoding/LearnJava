package sudoku.ui;


import java.io.FileReader;
import java.io.IOException;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sudoku.controller.Controller;
import sudoku.controller.MyController;
import sudoku.persistence.BadFileFormatException;
import sudoku.persistence.MySudokuReader;
import sudoku.persistence.SudokuReader;

public class SudokuApp extends Application {

	private Controller controller;

	public void start(Stage stage) {
		try {
			SudokuReader sudokuReader = new MySudokuReader(new FileReader("sudokuConfig.txt"));
			controller = new MyController(sudokuReader.getSudoku());
			//System.out.println(configReader.getSGame());
			System.out.println("Configuration read");
		} catch ( BadFileFormatException  | IOException e) {
			alert("File non trovato", "Errore nella creazione del controller", "Addio");
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
