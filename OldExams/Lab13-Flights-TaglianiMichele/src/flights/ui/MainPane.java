package flights.ui;

import java.io.IOException;
import java.time.LocalDate;

import flights.controller.Controller;
import flights.model.Airport;
import flights.model.FlightSchedule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainPane extends BorderPane{
	
	private Controller controller;
	private ComboBox<Airport> arrivalAirportComboBox;
	private ComboBox<Airport> departureAirportComboBox;
	private DatePicker departureDatePicker;
	private FlightScheduleListPanel flightSchedulePanel;
	public MainPane(Controller controller) {
		this.controller = controller;
		initPane();
	}
	
	private void initPane() {
		VBox vb = new VBox(5);
		Label dep = new Label("Departure");
		Label arr = new Label("Arrival");
		Label dpLab = new Label("Date");
		Button find = new Button("Find");
		flightSchedulePanel = new FlightScheduleListPanel();
		departureDatePicker = new DatePicker();
		ObservableList<Airport>  data = FXCollections.observableArrayList(this.controller.getSortedAirports());
		
		departureAirportComboBox = new ComboBox<Airport>(data);
		arrivalAirportComboBox = new ComboBox<Airport>(data);
		departureAirportComboBox.setEditable(false);
		arrivalAirportComboBox.setEditable(false);
		arrivalAirportComboBox.setValue(data.get(0));
		departureAirportComboBox.setValue(data.get(0));
		find.setOnAction(arg0 -> {
			try {
				myHandle(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		vb.getChildren().addAll(dep,departureAirportComboBox, arr, arrivalAirportComboBox, dpLab, departureDatePicker , find);
		//vb.getChildren().addAll(dep,arr);
		vb.setAlignment(Pos.BASELINE_RIGHT);
		this.setLeft(vb);
		this.setCenter(flightSchedulePanel);
		
		
	}
	
	private void myHandle(ActionEvent event) throws IOException {
		Airport start = departureAirportComboBox.getValue();
		Airport end = arrivalAirportComboBox.getValue();
		LocalDate date = departureDatePicker.getValue();
		
		if(date == null) {
			throw new IOException(); 
		}
		
		this.flightSchedulePanel.setFlightSchedules(this.controller.searchFlights(start, end, date));
		return;
		
	}
}
