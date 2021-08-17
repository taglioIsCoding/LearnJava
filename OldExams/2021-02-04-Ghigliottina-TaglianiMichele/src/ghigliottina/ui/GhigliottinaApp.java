package ghigliottina.ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import ghigliottina.model.Esatta;
import ghigliottina.model.Ghigliottina;
import ghigliottina.model.Terna;
import ghigliottina.persistence.BadFileFormatException;
import ghigliottina.persistence.GhigliottineReader;
import ghigliottina.persistence.MyGhigliottineReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class GhigliottinaApp extends Application {
 
	int montepremi = 100000;
	OuterGhigliottinaPanel parent;
	Controller controller;
	List<Ghigliottina> list;
	
	public void start(Stage stage) throws InterruptedException {
		stage.setTitle("Gioco della Ghigliottina!");
		
		GhigliottineReader ghReader = new MyGhigliottineReader();
		try {
			list = ghReader.readAll(new BufferedReader(new FileReader("Ghigliottine.txt")));
		}
		catch(IOException | BadFileFormatException e) {
			System.err.println("Lettura file fallita - uso il mock");
			list = List.of(new Ghigliottina(
					List.of(
					new Terna("Lordo",    "Lardo",   Esatta.SECOND),
					new Terna("Zampino",  "Zampone", Esatta.FIRST),
					new Terna("Tetto",    "Veranda", Esatta.FIRST),
					new Terna("Lenta", "Frettolosa", Esatta.SECOND),
					new Terna("Gino Paoli", "Mina",  Esatta.FIRST)
					), "Gatta"));
		}
		controller = new MyController(list);
		//
		parent = new OuterGhigliottinaPanel(montepremi, controller);
		stage.setResizable(false);
		stage.setScene(new Scene(parent, 350, 450, Color.ALICEBLUE));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}