package flightTracker.ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.io.*;

import flightTracker.ui.controller.Controller;
import flightTracker.ui.controller.MyController;
import flightTracker.ui.MainPane;

public class FlightTrackApp extends Application {

	public void start(Stage stage){
		GeoMap geoMap = null;
		try {
			geoMap = new GeoMap(new Image(new FileInputStream("EmisferoNord.png")), -125, 28, 26, 61, 0.89F);
		} catch (FileNotFoundException e1) {
			System.out.println("argh");
			System.exit(1);		
		}
		// --- retrieve available file trackings
		String[] voli = listing(new File("."), ".csv");
		// --- setting up controller and main panel
		Controller controller = new MyController(voli);
		MainPane panel = new MainPane(geoMap,controller);
		// --- set up scene
		stage.setTitle("Flight tracker");
		Scene scene = new Scene(panel, 1100, 500,Color.WHITE);
		stage.setScene(scene);
		stage.show(); 
	}
	
	public static String[] listing(File dir, String extension) {
		if (dir.isDirectory()) { 
			return dir.list((f,name) -> name.endsWith(extension));
		}
		throw new IllegalArgumentException(dir + " is not a directory");
	} 

	public static void main(String[] args) {
		launch(args);
	}
}
