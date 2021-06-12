package dentinia.governor.ui.javafx;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import dentinia.governor.model.Elezioni;
import dentinia.governor.model.Partito;
import dentinia.governor.model.RisultatoElezioni;
import dentinia.governor.ui.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ElectionPane extends BorderPane{
	private Button calcolaButton;
	private Button salvaButton;
	private Controller controller;
	private VBox venterPaneBox;
	private FlowPane rightPaneBox;
	private FlowPane leftPaneBox;
	private HBox bottomBox;
	private HBox TopBox;
	private TextArea outpuTextArea;
	private TextArea outpuTextAreaConPerc;
	private Slider slider;
	private PieChart pieChart;
	private Elezioni ElezioniConSbarramento;
	
	public ElectionPane(Controller controller) {
		this.controller = controller;
		this.init();
	}
	
	private void init() {
		TopBox = new HBox(5);
		Label percSbarLabel = new Label("Sbarramento %");
		slider = new Slider(0, 10, 0);
		slider.setMaxWidth(Double.MAX_VALUE);
		slider.setBlockIncrement(1);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(1);
		
		TopBox.getChildren().addAll(percSbarLabel, slider);
		TopBox.setPadding(new Insets(15, 12, 15, 12));
		
		rightPaneBox = new FlowPane();
		this.outpuTextArea = new TextArea();
		outpuTextArea.setEditable(false);
		outpuTextArea.setText(this.controller.getElezioni().toString());
		this.outpuTextAreaConPerc = new TextArea();
		outpuTextAreaConPerc.setText(this.calcolaPercentuali(this.controller.getElezioni()));
		outpuTextAreaConPerc.setEditable(false);
		rightPaneBox.getChildren().addAll(outpuTextArea);
		rightPaneBox.getChildren().add(outpuTextAreaConPerc);
		rightPaneBox.setPadding(new Insets(15, 12, 15, 12));
		
		this.calcolaButton = new Button("Calcola");
		this.salvaButton = new Button("Salva");
		salvaButton.setDisable(true);
		this.bottomBox = new HBox(5);
		bottomBox.getChildren().addAll(calcolaButton, salvaButton);
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.setPadding(new Insets(15, 12, 15, 12));
		
		this.disegnaGrafico(this.controller.getElezioni());
		
		this.calcolaButton.setOnAction(arg0 -> {
			try {
				myHandleCalc(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		this.salvaButton.setOnAction(arg0 -> {
			try {
				myHandlePrint(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		this.setTop(TopBox);
		this.setLeft(rightPaneBox);
		this.setBottom(bottomBox);
		this.setRight(leftPaneBox);
		
	}
	
	private void myHandlePrint(ActionEvent event) throws IOException {
		this.controller.salvaSuFile("Elezioni calcolate con sbarramento all' " + this.slider.getValue()+ " %");
		this.salvaButton.setDisable(true);
		
	}
	private void myHandleCalc(ActionEvent event) throws IOException {
		ElezioniConSbarramento = this.controller.ricalcola(this.slider.getValue()/100);
		System.out.println(this.slider.getValue());
		this.disegnaGrafico(ElezioniConSbarramento);
		this.outpuTextArea.clear();
		this.outpuTextAreaConPerc.clear();
		this.outpuTextArea.setText(ElezioniConSbarramento.toString());
		this.outpuTextAreaConPerc.setText(this.calcolaPercentuali(ElezioniConSbarramento));
		this.setLeft(rightPaneBox);
		this.setBottom(bottomBox);
		this.setRight(leftPaneBox);
		this.salvaButton.setDisable(false);
	}
	
	private void disegnaGrafico(Elezioni elezioni) {
		ArrayList<PieChart.Data> dataNoObse = new ArrayList<>();
		for(Partito p: this.controller.getElezioni().getPartiti()) {
			dataNoObse.add(new PieChart.Data(p.getNome(), elezioni.getVoti(p)));
		}
		
		ObservableList<Data> dati =
				FXCollections.observableArrayList(dataNoObse);
		pieChart = new PieChart(dati);
		leftPaneBox = new FlowPane();
		leftPaneBox.getChildren().addAll(pieChart);
	}
	
	private String calcolaPercentuali(Elezioni elezioni) {
		RisultatoElezioni risultato = elezioni.getRisultato();
		NumberFormat formatter = NumberFormat.getNumberInstance(Locale.ITALY);
		StringBuilder sb = new StringBuilder();
		if (!risultato.seggiSettati()) sb.append("*** ATTENZIONE: seggi non ancora calcolati ***" + System.lineSeparator());
		for (Partito p : elezioni.getPartiti()) {
			sb.append(String.format("%-35.35s %5s %11s %8s %4d%n", p.toString(), "Voti%:",
					formatter.format((elezioni.getVoti(p)*100)/elezioni.getVotiTotali()), "Seggi%:", (risultato.getSeggi(p)*100)/elezioni.getSeggiDaAssegnare()));
		}
		sb.append(String.format("%-35.35s %5s %11s %8s %4d%n", "TOTALE", "Voti:",
				formatter.format(elezioni.getVotiTotali()), "Seggi:", risultato.getSeggiTotali()));
		return sb.toString();
	}
	
	
}
