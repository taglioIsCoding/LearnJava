package electriclife.ui.javafx;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import electriclife.model.Bolletta;
import electriclife.model.Tariffa;
import electriclife.persistence.BollettaWriter;
import electriclife.persistence.MyBollettaWriter;
import electriclife.ui.controller.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ElectricLifePane extends BorderPane{
	
	private Bolletta bolletta;
	private Button calcolaButton;
	private Button stampaButton;
	private TextField campoConsumoField;
	private ComboBox<Tariffa> tariffeBox;
	private Controller controller;
	private VBox leftPaneBox;
	private DatePicker datePicker;
	private TextArea outpuTextArea;
	
	public ElectricLifePane(Controller controller) {
		this.controller = controller;
		this.init();
	}

	private void init() {
		Label tariffeDispLabel = new Label("Tariffe disponibili");
		Label consumoLabel = new Label("Consumo in KW/h");
		Label dataEmiiLabel = new Label("Data emisione");
		this.calcolaButton = new Button("Calcola");
		this.stampaButton = new Button("Stampa");
		this.campoConsumoField = new TextField();
		this.tariffeBox = new ComboBox<>(this.controller.getTariffe());
		this.tariffeBox.setValue(this.controller.getTariffe().get(0));
		this.datePicker = new DatePicker();
		this.leftPaneBox = new VBox(5);
		this.outpuTextArea = new TextArea();
		this.outpuTextArea.setEditable(false);
		this.tariffeBox.setEditable(false);
		this.datePicker.setEditable(false);
		this.datePicker.setValue(LocalDate.now());
		this.stampaButton.setDisable(true);
		
		this.calcolaButton.setOnAction(arg0 -> {
			try {
				myHandleCalc(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		this.stampaButton.setOnAction(arg0 -> {
			try {
				myHandlePrint(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		HBox hButtonBox = new HBox(5);
		hButtonBox.getChildren().addAll(calcolaButton, stampaButton);
		hButtonBox.setAlignment(Pos.CENTER);
		hButtonBox.setPadding(new Insets(15, 12, 15, 12));
		
		leftPaneBox.getChildren().addAll(tariffeDispLabel, tariffeBox,
				consumoLabel, campoConsumoField,
				dataEmiiLabel, datePicker);

		leftPaneBox.setPadding(new Insets(15, 12, 15, 12));
		
		this.setLeft(leftPaneBox);
		this.setCenter(outpuTextArea);
		this.setBottom(hButtonBox);
		
	}
	
	private void myHandlePrint(ActionEvent event) throws IOException {
		BollettaWriter bollettaWriter = new MyBollettaWriter(new PrintWriter(new File("Bolletta.txt")));
		this.controller.stampaBolletta(bolletta, bollettaWriter);
		
	}
	private void myHandleCalc(ActionEvent event) throws IOException {
		this.outpuTextArea.clear();
		try {
			int KWInt = Integer.parseInt(this.campoConsumoField.getText());
			if(KWInt < 0) {
				throw new IOException();
			}
			this.bolletta = this.controller.creaBolletta(this.datePicker.getValue(),
			this.tariffeBox.getValue().getNome(), Integer.parseInt(this.campoConsumoField.getText()));
			 outpuTextArea.appendText(bolletta.toString());
			 this.stampaButton.setDisable(false);
		
		} catch (Exception e) {
			Controller.alert("Dati non corretti","Impossibile Calcolare","Ok");
		}
		
		
	}
	
	
}
