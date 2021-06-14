package bikerent.ui.javafx;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Formatter;
import java.util.Locale;

import bikerent.persistence.BadFileFormatException;
import bikerent.ui.controller.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BikeRentPane extends BorderPane{
	
	private Button ticketButton;
	private VBox centerPaneBox;
	private VBox leftPanePaneBox;
	private ComboBox<String> comboBox;
	private Controller controller;
	private TextField fieldFineField;
	private TextField fieldInizioField;
	private NumberFormat formatter;
	private TextArea outpuTextArea;
	private DatePicker pickerFine;
	private DatePicker pickerInizio;
	
	public BikeRentPane(Controller controller) {
		this.controller = controller;
		this.init();
	}
	
	private void init() {
		Label cityLabel = new Label("Città");
		comboBox = new ComboBox<>(this.controller.getCityNames());
		comboBox.setValue(this.controller.getCityNames().get(0));
		ticketButton = new Button("Calcola costo");
		ticketButton.setOnAction(arg0 -> {
			myHandle(arg0);
		});
		HBox topBox = new HBox(5);
		topBox.getChildren().addAll(cityLabel, comboBox, ticketButton);
		
		Label inizioLabel  = new Label("Inizio Noleggio");
		Label fineLabel  = new Label("FIne Noleggio");
		pickerFine = new DatePicker();
		pickerInizio = new DatePicker();
		fieldFineField = new TextField();
		fieldInizioField = new TextField();
		pickerInizio.setEditable(false);
		pickerFine.setEditable(false);
		comboBox.setEditable(false);
		
		VBox inizioBox = new VBox(5);
		VBox fineBox = new VBox(5);
		HBox hourBox = new HBox();
		inizioBox.getChildren().addAll(inizioLabel, pickerInizio, fieldInizioField);
		fineBox.getChildren().addAll(fineLabel, pickerFine, fieldFineField);
		hourBox.getChildren().addAll(inizioBox, fineBox);
		
		outpuTextArea = new TextArea();
		outpuTextArea.setEditable(false);
		
		topBox.setPadding(new Insets(15, 12, 15, 12));
		inizioBox.setPadding(new Insets(15, 12, 15, 12));
		fineBox.setPadding(new Insets(15, 12, 15, 12));
		leftPanePaneBox = new VBox();
		leftPanePaneBox.getChildren().addAll(topBox, hourBox);
		this.setLeft(leftPanePaneBox);
		this.setCenter(outpuTextArea);
	}
	
	private void myHandle(ActionEvent event) {
		if(this.comboBox.getValue() == null || this.pickerFine.getValue() == null
				|| this.pickerInizio.getValue() == null
				|| this.fieldFineField.getText() == null
				|| this.fieldInizioField.getText() == null) {
			Controller.alert("Errore", "Tutti i campi devono essere compilati", "Controlla");
		}
		LocalTime timeStart = null;
		LocalTime timeEnd = null;
		if(fieldFineField.getText().length() != 5 || fieldInizioField.getText().length() != 5) {
			Controller.alert("Errore", "L'ora deve essere nella forma HH:MM", "Controlla");
		}else {
			try {
				timeStart = LocalTime.of(Integer.parseInt(fieldInizioField.getText().split(":")[0]),Integer.parseInt(fieldInizioField.getText().split(":")[1]));
			} catch (Exception e) {
				Controller.alert("Errore", "L'ora  di inizio deve essere nella forma HH:MM", "Controlla");
				return;
			}
			try {
				timeEnd = LocalTime.of(Integer.parseInt(fieldFineField.getText().split(":")[0]),Integer.parseInt(fieldFineField.getText().split(":")[1]));
			} catch (Exception e) {
				Controller.alert("Errore", "L'ora di fine deve essere nella forma HH:MM", "Controlla");
				return;
			}
			
			LocalDateTime dateTimeStarTime = LocalDateTime.of(this.pickerInizio.getValue(), timeStart);
			LocalDateTime dateTimeEnd = LocalDateTime.of(this.pickerFine.getValue(), timeEnd);
			if(dateTimeStarTime.isAfter(dateTimeEnd)) {
				Controller.alert("Errore", "Non puoi viaggiare nel tempo!", "Controlla");
				return;
			}else {
				double costo = this.controller.getRentCost(this.comboBox.getValue(), this.pickerInizio.getValue(), timeStart,
					this.pickerFine.getValue(), timeEnd);
				
				formatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
				outpuTextArea.clear();
				outpuTextArea.setText("Il noleggio costerà: "+ formatter.format(costo));
			}
			
			
		}
	}
}
