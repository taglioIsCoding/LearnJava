package rfd.ui;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import rfd.controller.Controller;
import rfd.model.MyTripDurationCalculator;
import rfd.model.Route;
import rfd.model.Station;
import rfd.model.TripDurationCalculator;


public class MainPane extends BorderPane {
	
	private TextArea outputArea; 
	private ComboBox<String> comboFrom, comboTo;
	private Controller controller;
	private RadioButton b1, b2;
	private ToggleGroup tg;

	public MainPane(Controller controller) {
		this.controller=controller;
		//
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
		b1 = new RadioButton("per distanza");
		b2 = new RadioButton("per durata");
		tg = new ToggleGroup();
		b1.setToggleGroup(tg);
		b2.setToggleGroup(tg);
		centerBox.getChildren().addAll(new Label("Ordina soluzioni:    "), b1, new Label("   "), b2);
		this.setCenter(centerBox);
		//
		b1.setOnAction(e -> showSorted(OrderType.BY_DISTANCE)); 
		b2.setOnAction(e -> showSorted(OrderType.BY_DURATION)); 
		//
		outputArea = new TextArea();
		outputArea.setPrefSize(500,400);
		this.setBottom(outputArea);
	}

	private void populateCombo(ComboBox<String> combo) {
		combo.getItems().addAll((FXCollections.observableArrayList(this.controller.getStationNames())));
	}
	
	private void reset(ActionEvent event) {
		outputArea.setText("");
		if(tg.getSelectedToggle()!=null) tg.getSelectedToggle().setSelected(false);
		
		System.out.println("ciao");

		
	}
	
	private void showSorted(OrderType orderType) {
		if(this.comboFrom.getValue() == null || this.comboTo.getValue()== null){
			RailwayApp.alert("Qualcosa è andato storto", "dati non completi", "riprova");
			return;
		}
		ArrayList<Route> routes=  new ArrayList<>(this.controller.getRoutes(this.comboFrom.getValue(), this.comboTo.getValue()));
		
		if(orderType.equals(OrderType.BY_DISTANCE)) {
			Collections.sort(routes, Comparator.comparing(Route::getLength));
		}else {
			Comparator<Route>  compareDuration= (Route r1, Route r2) -> {
				return new MyTripDurationCalculator().getDuration(r2).compareTo(new MyTripDurationCalculator().getDuration(r1));
			};
			Collections.sort(routes, compareDuration);
			Collections.reverse(routes);
		}
		StringBuilder stringBuilder = new StringBuilder();
		for(Route r: routes) {
			stringBuilder.append(toRouteString(r));
			stringBuilder.append(System.lineSeparator());
		}
		
		this.outputArea.clear();
		this.outputArea.setText(stringBuilder.toString());
	
	}
	
	private String toRouteString(Route route) {
		return route.toString() + System.lineSeparator() + LocalTime.of(this.controller.getRouteDuration(route).toHoursPart(),this.controller.getRouteDuration(route).toMinutesPart());
	}

}
