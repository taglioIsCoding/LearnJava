package flightTracker.ui;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Random;

import flightTracker.model.Flight;
import flightTracker.persistence.BadFileFormatException;
import flightTracker.ui.controller.Controller;

public class MainPane extends BorderPane{
	private GeoCanvas geoCanvas;
	private CheckBox checkBox;
	private Controller controller;
	private ComboBox<String> comboBox;
	
	
	public MainPane(GeoMap geoMap, Controller controller) {
		geoCanvas = new GeoCanvas(geoMap);
		this.controller = controller;
		
		HBox topBox = new HBox(5);
		Label svegliVoliLabel = new Label("scegli voli:");
		
		this.comboBox = new ComboBox<>();
		comboBox.getItems().addAll(this.controller.getFlights());
		comboBox.setTooltip(new Tooltip("Scegli il volo da disegnare"));
		comboBox.setOnAction(this::myHandler);
		
		this.checkBox = new CheckBox("voli multipli");
		
		
		topBox.getChildren().addAll(svegliVoliLabel, comboBox, checkBox);
		
		this.setTop(topBox);
		this.setCenter(geoCanvas);
	}
	
	private void myHandler(ActionEvent ev) {
		Reader rdr;
		
		if(!this.checkBox.isSelected()) {
			this.geoCanvas.redrawMap();
		}
		
		try {
			rdr = new FileReader(this.comboBox.getValue());
		} catch (FileNotFoundException e) {
			Controller.alert("errore di apertura", "", "");
			e.printStackTrace();
			return;
		}
		
		Flight flight;
		try {
			flight = this.controller.load(this.comboBox.getValue().substring(0, this.comboBox.getValue().indexOf('.')), rdr);
		} catch (IOException e) {
			Controller.alert("errore di io", "", "");
			e.printStackTrace();
			return;
		} catch (BadFileFormatException e) {
			Controller.alert("errore di file", "", "");
			e.printStackTrace();
			return;
		}
		Random random = new Random();
		this.geoCanvas.setDrawingColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
		this.geoCanvas.drawPoints(this.controller.getPoints(flight));
		
	}
	
	
	
	
	
	
	
}
