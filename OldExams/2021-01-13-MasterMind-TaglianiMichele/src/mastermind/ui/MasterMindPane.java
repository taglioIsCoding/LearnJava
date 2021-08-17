package mastermind.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import mastermind.model.Combinazione;
import mastermind.model.PioloDiGioco;
import mastermind.model.Status;

public class MasterMindPane extends BorderPane {

	private TextArea output;
	private Button stampaReport, restart, inserisci, svela;
	private Controller controller;
	private Label rimasti, status;
	private List<ComboBox<PioloDiGioco>> bottoniCodice = new ArrayList<>();
	
	public MasterMindPane(Controller controller, Stage stage) {
		this.controller=controller;
		
		HBox boxCombo = new HBox(5);
		VBox topBox = new VBox(5);
		VBox tantiPulsanti = new VBox(5);
		HBox bottomBox = new HBox(5);
		
		for(int i = 0; i < controller.dimensioneCodice(); i++) {
			ComboBox<PioloDiGioco> combopBox = new ComboBox<>(FXCollections.observableArrayList(PioloDiGioco.values()));
			combopBox.setEditable(false);
			combopBox.getItems().remove(PioloDiGioco.VUOTO);
			combopBox.setOnAction(ev -> colorer(ev));
			bottoniCodice.add(combopBox);
		}
		
		boxCombo.getChildren().addAll(bottoniCodice);
		boxCombo.setAlignment(Pos.CENTER);
		boxCombo.setPadding(new Insets(15, 12, 15, 12));
		
		inserisci = new Button();
		inserisci.setText("Inserisci");
		inserisci.setOnAction(EventHandler -> gioca(EventHandler));
		topBox.setAlignment(Pos.CENTER);
		topBox.getChildren().addAll(boxCombo, inserisci);
		
		
		
		stampaReport = new Button();
		stampaReport.setText("Stampa Report");
		stampaReport.setOnAction(EventHandler -> saver(EventHandler));
		restart = new Button();
		restart.setText("Restart");
		restart.setOnAction(EventHandler -> restarter(EventHandler));
		svela = new Button();
		svela.setText("Svela");
		svela.setOnAction(EventHandler -> reveal(EventHandler));
		boxCombo.setPadding(new Insets(5, 10, 5, 10));
		tantiPulsanti.getChildren().addAll(stampaReport, restart, svela);
		tantiPulsanti.setAlignment(Pos.CENTER);
		
		
		output = new TextArea();
		output.setEditable(false);
		
		rimasti = new Label("Tentativi rimasti: "+ controller.tentativiRimasti());
		status = new Label("Status di gioco: "+ controller.status());
		Label maxTentativiLabel = new Label("Tentativi massimi: " +this.controller.maxTentativi());
		bottomBox.getChildren().addAll(maxTentativiLabel, rimasti, status);
		
		
		this.setTop(topBox);
		this.setLeft(tantiPulsanti);
		this.setCenter(output);
		this.setBottom(bottomBox);
		
	
	}
	
	// da agganciare al pulsante "Stampa report"
	private void saver(ActionEvent e) {
		this.controller.save();
	}

	private void gioca(ActionEvent e) {
		Combinazione tentativoCombinazione = new Combinazione(this.controller.dimensioneCodice());
		int i = 0;
		for(ComboBox<PioloDiGioco> combo: bottoniCodice) {
			if(combo.getValue() != null) {
				tentativoCombinazione.setPiolo(i, combo.getValue());
				i++;
			}
			else {
				Controller.alert("Non tutte selezionate", "gioca MEglio", "OKE");
				return;
			}
		}
		
		this.controller.tenta(tentativoCombinazione);
		rimasti.setText("Tentativi rimasti: "+ controller.tentativiRimasti());
		status.setText("Status di gioco: "+ controller.status());
		
		
		
		output.clear();
		output.appendText(this.controller.situazione());
		
		if(controller.status().equals(Status.VITTORIA) || controller.tentativiRimasti() == 0) {
			output.appendText("\n Gioco Terminato");
			inserisci.setDisable(true);
		}
	}

	// da agganciare al pulsante "Svela"
	private void reveal(ActionEvent e) {
		this.output.appendText("\n Combinazione segreta " +this.controller.combinazioneSegreta().toString() + "\n");
	}
	
	// da agganciare al pulsante "Restart"
	private void restarter(ActionEvent e) {
		controller.restart();
		output.setText("");
		inserisci.setDisable(false);
		rimasti.setText("Tentativi rimasti: " + controller.tentativiRimasti() + "  ");
		status.setText("Stato del gioco: " + controller.status());
		for (ComboBox<PioloDiGioco> c : bottoniCodice) {
			c.getSelectionModel().clearSelection();
			c.setStyle("-fx-background-color: #C0C0C0;");
		}
	}

	// da agganciare alle combo per colorarne automaticamente lo sfondo
	private void colorer(ActionEvent e) {
		@SuppressWarnings("unchecked")
		ComboBox<PioloDiGioco> combo = (ComboBox<PioloDiGioco>)(e.getSource());
		if (combo.getValue()!=null) {
			combo.setStyle("-fx-background-color: " + combo.getValue().rgb() + ";");
		};
	}
	
}
