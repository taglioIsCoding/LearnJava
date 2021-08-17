package minirail.ui.javafx;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import minirail.model.Gauge;
import minirail.model.Line;
import minirail.model.LineStatus;
import minirail.model.MyLineStatus;
import minirail.model.Section;
import minirail.model.Train;
import minirail.persistence.BadFileFormatException;
import minirail.persistence.ConfigReader;
import minirail.persistence.LineReader;
import minirail.persistence.MyConfigReader;
import minirail.persistence.MyLineReader;
import minirail.ui.controller.Controller;
import minirail.ui.controller.MyController;

public class MiniRailApp extends Application {

	private Controller controller;
	private double[] trainPositions = {85, 160, 260};

	public void start(Stage stage) {
		// defaults
		Section[] sections = new Section[] {
				new Section("A-B", 90), 
				new Section("B-C", 90),
				new Section("C-D", 70),
				new Section("D-A", 70)
		};
		Line defaultLine = new Line("Infernale", Arrays.asList(sections));
		List<Train> trains = List.of( 
				new Train ("IC 583", 65, Gauge.N.kmhToCms(160)), 
				new Train ("R 2961", 68, Gauge.N.kmhToCms(140)) );
		// actual start
		try {
			ConfigReader configReader = new MyConfigReader(new FileReader("config.txt"));
			LineReader lineReader = new MyLineReader(new FileReader("lines.txt"));
			LineStatus dir = new MyLineStatus(lineReader.getLine());
			System.out.println("Configuration files read correctly.");
			trains = configReader.getTrains();
			controller = new MyController(dir, trains);
		} catch (FileNotFoundException | BadFileFormatException e) {
			LineStatus dir = new MyLineStatus(defaultLine);
			System.out.println("Error reading one of the configuration files, using defaults.");
			controller = new MyController(dir, trains);
		} catch (IOException e) {
			System.out.println("I/O Error reading one of the configuration files, exiting.");
			System.exit(1);
		}
		if (controller == null) {
			Controller.alert("Errore irrecuperabile", "Errore nella creazione del controller", "Addio");
			System.exit(2);
		}
		
		stage.setTitle("MiniRail Commander");
		MiniRailPane root = new MiniRailPane(controller, trainPositions);
		
		Scene scene = new Scene(root, 650, 400, Color.ALICEBLUE);
		//scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
