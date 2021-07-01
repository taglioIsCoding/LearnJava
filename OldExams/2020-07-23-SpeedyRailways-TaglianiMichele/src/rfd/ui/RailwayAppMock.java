package rfd.ui;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rfd.model.Station;
import rfd.model.RailwayLine;

public class RailwayAppMock extends Application {

	public static void alert(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("MOCK - Rupestri Ferrovie di Dentinia");
	
		RailwayLine lineaBoVr = new RailwayLine(Map.of(
				"Verona Porta Nuova", new Station("Verona Porta Nuova", 114.95, 200),
				"Bologna Centrale", new Station("Bologna Centrale", 0.0, 200)
				),
				new TreeSet<>(Set.of("Bologna Centrale", "Verona Porta Nuova")
				));
		RailwayLine lineaMoVr = new RailwayLine(Map.of(
				"Verona Porta Nuova", new Station("Verona Porta Nuova", 97.86, 110),
				"Modena", new Station("Modena", 0.0, 110)
				),
				new TreeSet<>(Set.of("Modena", "Verona Porta Nuova")
				));
		RailwayLine lineaBoMi = new RailwayLine(Map.of(
				"Modena", new Station("Modena", 36.93, 160),
				"Reggio Emilia", new Station("Reggio Emilia", 61.44, 160),
				"Parma", new Station("Parma", 89.74, 160),
				"Bologna Centrale", new Station("Bologna Centrale", 0.0, 160),
				"Piacenza", new Station("Piacenza", 146.82, 160)
				),
				new TreeSet<>(Set.of("Bologna Centrale", "Parma", "Modena")
				));
		
		Set<RailwayLine> rwylines = Set.of(lineaBoVr, lineaMoVr, lineaBoMi);	

		rfd.controller.Controller controller = new rfd.controller.MyController(rwylines);
				
		MainPane mainPanel = new MainPane(controller);
		Scene scene = new Scene(mainPanel, 600, 400, Color.AQUAMARINE);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	

}
