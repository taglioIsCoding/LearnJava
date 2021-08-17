package ghigliottina.ui;

import ghigliottina.model.Ghigliottina;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class OuterGhigliottinaPanel extends BorderPane {
 
	private GhigliottinaPanel gPanel;
	private TextField txtRispostaUtente, txtRispostaEsatta;
	private Label rightLabel, leftLabel;
	private Button svela;
	private String rispostaEsatta;
	private Controller controller;
	private Ghigliottina gh;
	private int montepremi;
	
	public OuterGhigliottinaPanel(int montepremi, Controller controller) {
		this.controller=controller;
		this.montepremi=montepremi;
		setupGhigliottinaPanel();
		//
		HBox revealBox = new HBox();
		revealBox.setAlignment(Pos.CENTER);
		revealBox.setStyle("-fx-background-color: blue;");
		
		//
		VBox parolaSegretaBox = new VBox();
		rightLabel = new Label("La parola segreta:");
		rightLabel.setTextFill(Color.WHITE);
		txtRispostaEsatta = new TextField();
		txtRispostaEsatta.setEditable(false);
		parolaSegretaBox.getChildren().addAll(rightLabel, txtRispostaEsatta);
		
		VBox rispostaBox = new VBox();
		leftLabel = new Label("La tua risposta:");
		leftLabel.setTextFill(Color.WHITE);
		txtRispostaUtente = new TextField();
		rispostaBox.getChildren().addAll(leftLabel, txtRispostaUtente);
		
		svela= new Button("Svela");
		svela.setOnAction(this::svela);
		svela.setTextFill(Color.RED);
		svela.setStyle("fx-background-color: #000000");
		revealBox.getChildren().addAll(parolaSegretaBox, rispostaBox, svela);
		
		//
		this.setTop(revealBox);
	}
	
	private void setupGhigliottinaPanel() {
		// initial setup
		gh = controller.sorteggiaGhigliottina();
		this.rispostaEsatta=gh.getRispostaEsatta();
		gPanel = new GhigliottinaPanel(montepremi, gh.getTerne());
		this.setBottom(gPanel);
	}
	
	private void reset() {
		setupGhigliottinaPanel();
		txtRispostaUtente.setText("");
		txtRispostaEsatta.setText("");
	}
	

	// COMPLETARE: gestione evento pressione pulsante SVELA
	
	private void svela(ActionEvent e) {
		if(this.txtRispostaUtente.getText().isEmpty()) {
			OuterGhigliottinaPanel.alert("Devi inserire una risposta", "Altrimenti non vinci", "Errore");
			return;
		}
		
		this.txtRispostaEsatta.setText(rispostaEsatta);
		if(this.txtRispostaUtente.getText().equalsIgnoreCase(rispostaEsatta)) {
			OuterGhigliottinaPanel.info("Hai vinto", this.gPanel.montepremiAsString(), rispostaEsatta);
			this.reset();
		}else {
			OuterGhigliottinaPanel.info("Hai perso", this.gPanel.montepremiAsString(), rispostaEsatta);
			this.reset();
		}
	}


	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	public static void info(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

}
