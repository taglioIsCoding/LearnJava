package rfd.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import rfd.controller.Controller;
import rfd.model.PointOfInterest;
import rfd.model.Route;


public class MainPane extends BorderPane {
	
	private TextArea outputArea; 
	private ComboBox<String> comboFrom, comboTo;
	private Controller controller;
	private CheckBox senzaCambi, unCambio;

	public MainPane(Controller controller) {
		this.controller=controller;
		HBox topBox = new HBox();
		topBox.setPrefHeight(40);
		// populate combos
		comboFrom = new ComboBox<>(); populateCombo(comboFrom);
		comboTo   = new ComboBox<>(); populateCombo(comboTo);
		comboFrom.setOnAction(this::reset);
		comboTo.setOnAction(this::reset);
		topBox.getChildren().addAll(new Label("Partenza "), comboFrom, new Label("  Arrivo "), comboTo);
		this.setTop(topBox);
		//
		HBox centerBox = new HBox();
		centerBox.setPrefHeight(80);
		centerBox.setAlignment(Pos.CENTER);
		senzaCambi = new CheckBox("senza cambi");
		unCambio = new CheckBox("con un cambio");
		centerBox.getChildren().addAll(new Label("Mostra soluzioni:    "), senzaCambi, new Label("   "), unCambio);
		this.setCenter(centerBox);
		senzaCambi.setOnAction(this::myHandle); 
		unCambio.setOnAction(this::myHandle); 
		outputArea = new TextArea();
		outputArea.setPrefSize(500,300);
		outputArea.setFont(Font.font("Courier New", 12));
		this.setBottom(outputArea);
	}

	private void populateCombo(ComboBox<String> combo) {
		ArrayList<String> sorteArrayList = new ArrayList<>(this.controller.getStationNames());
		Collections.sort(sorteArrayList, String::compareTo);
		combo.getItems().addAll((FXCollections.observableArrayList(sorteArrayList)));
	}
	
	private void reset(ActionEvent event) {
		outputArea.setText("");
		senzaCambi.setSelected(false);
		unCambio.setSelected(false);
	}
	
	protected void myHandle(ActionEvent event) {
		if(this.comboFrom.getValue() == null || this.comboTo.getValue() == null) {
			RailwayApp.alert("Attenzione","seleziona stazione", "Riprova");
			return;
		}
		
		this.outputArea.clear();
		if (senzaCambi.isSelected() && unCambio.isSelected()) {
			this.findDirectRoutes(this.comboFrom.getValue(), this.comboTo.getValue());
			if(!this.outputArea.getText().trim().isEmpty()) {
				outputArea.appendText("\n\n");
			}
			this.findIndirectRoutes(this.comboFrom.getValue(), this.comboTo.getValue());
		} else if(senzaCambi.isSelected()) {
			 System.out.println("senza");
			 this.findDirectRoutes(this.comboFrom.getValue(), this.comboTo.getValue());
		 }else if (unCambio.isSelected()) {
			 System.out.println("un");
			 this.findIndirectRoutes(this.comboFrom.getValue(), this.comboTo.getValue());
		 }
		
	}
	
	protected void findDirectRoutes(String from, String to) {
		if(!this.controller.getDirectRoutes(from, to).isEmpty()) {
			String primaRigaString = "Percorsi diretti senza cambi:\n";
			outputArea.setText(primaRigaString.toUpperCase());
	
			for(Route route : this.controller.getDirectRoutes(from, to)) {
				outputArea.appendText(route.toString()+"\n\n");
			}
		}
		
		
	}
	protected void findIndirectRoutes(String from, String to) {
		if(!this.controller.getIndirectRoutes(from, to).isEmpty()) {
			String primaRigaString = "Percorsi con cambi:\n";
			if(this.outputArea.getText().trim().isEmpty()) {
				outputArea.setText(primaRigaString.toUpperCase());
			}else {
				outputArea.appendText(primaRigaString.toUpperCase());
			}
		
			for(Route route : this.controller.getIndirectRoutes(from, to)) {
				outputArea.appendText(route.toString()+"\n\n");
			}
		} 
	}

}
