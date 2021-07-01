package unident.ui;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import unident.controller.Controller;
import unident.model.AttivitaFormativa;
import unident.model.Esame;
import unident.model.Voto;

public class MainPane extends BorderPane {
	
	private TextArea outputArea; 
	private ComboBox<AttivitaFormativa> afCombo;
	private Controller controller;
	private DatePicker picker;
	private Button button;
	private ComboBox<Voto> votoCombo;

	public MainPane(Controller controller) {
		this.controller=controller;
		//
		HBox topBox = new HBox();
		topBox.setPrefHeight(40);
		// populate combos
		afCombo = new ComboBox<>(); populateCombo(afCombo);
		topBox.getChildren().addAll(new Label(" Attivit� formative  "), afCombo);
		this.setTop(topBox);
		//
		HBox centerBox = new HBox();
		centerBox.setPrefHeight(80);
		centerBox.setAlignment(Pos.CENTER);
		votoCombo = new ComboBox<>(FXCollections.observableArrayList(Voto.values()));
		picker = new DatePicker();
		picker.setValue(LocalDate.now());
		button = new Button("Aggiungi esame");
		button.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		centerBox.getChildren().addAll(new Label("Data sostenimento: "), picker, new Label("   Voto: "), votoCombo, 
										new Label("      "), button);
		this.setCenter(centerBox);
		//
		button.setOnAction(e -> addExam()); 
		//
		outputArea = new TextArea();
		outputArea.setPrefSize(500,400);
		outputArea.setFont(Font.font("Courier New", FontWeight.NORMAL, 14));
		this.setBottom(outputArea);
	}

	private void populateCombo(ComboBox<AttivitaFormativa> combo) {
		TreeSet<AttivitaFormativa> dati = new TreeSet<AttivitaFormativa>(Comparator.comparing(AttivitaFormativa:: getNome));
		this.controller.pianoDidattico();
		for(AttivitaFormativa af: this.controller.pianoDidattico()) {
			dati.add(af);
		}
		
		System.out.println(dati);
		combo.getItems().addAll((FXCollections.observableArrayList(dati)));
	}
	
	private void addExam() {
		try {
			this.controller.aggiornaCarriera(new Esame(afCombo.getValue(), votoCombo.getValue(), picker.getValue()));
		} catch (Exception e) {
			UniDentApp.alert("errore", e.toString(), "Ricontrolla");
		}
		this.outputArea.setText( this.controller.stampaCarriera());
	}
	
}
