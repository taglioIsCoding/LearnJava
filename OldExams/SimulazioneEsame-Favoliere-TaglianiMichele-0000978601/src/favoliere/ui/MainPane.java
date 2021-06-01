package favoliere.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import favoliere.controller.*;
import favoliere.model.FasciaEta;
import favoliere.model.Favola;
import favoliere.model.Impressionabilita;
public class MainPane extends BorderPane{

	private Controller controller;
	private TextArea output = new TextArea();
	private Button tellFavola;
	private ComboBox livelliImpress, livelliCom;
	private Button print;
	
	public MainPane(Controller controller) {
		this.controller = controller;
		initPane();
		}
	
	private void initPane() {
		VBox vb = new VBox(5);
		Label fascoaEta = new Label("Fascia eta del bambino");
		Label gradoImpress = new Label("Grado impressionabilita del bambino");
		print = new Button("Stampa Favola");
		tellFavola = new Button("Genera Favola");
		ObservableList<Impressionabilita>  dataImpress = FXCollections.observableArrayList(this.controller.getLivelliImpressionabilita());
		ObservableList<FasciaEta>  fasciaEta = FXCollections.observableArrayList(this.controller.getFasceEta());
		livelliImpress = new ComboBox<Impressionabilita>(dataImpress);
		livelliCom = new ComboBox<FasciaEta>(fasciaEta);
		
		
		livelliImpress.setEditable(false);
		livelliCom.setEditable(false);
		livelliImpress.setValue(dataImpress.get(0));
		livelliCom.setValue(fasciaEta.get(0));
		output.setEditable(false);
		print.setDisable(true);
		
		print.setOnAction(arg0 -> {
			try {
				myHandle(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		tellFavola.setOnAction(arg0 -> {
			try {
				myHandle(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
		vb.getChildren().addAll(fascoaEta,livelliCom,gradoImpress, livelliImpress,output,tellFavola, print);
		this.setCenter(vb);
	}
	
	private void myHandle(ActionEvent event) throws IOException {
		
		if(event.getSource() == tellFavola) {
			output.clear();
			System.out.println(livelliImpress.getValue());
			
			Impressionabilita imp;
			
			
			
			
			Optional favOpt = Optional.of(this.controller.generaFavola(FasciaEta.valueOf(livelliCom.getValue().toString()),
					Impressionabilita.fromString(livelliImpress.getValue().toString())));
			
			if(favOpt.isEmpty()) {
				output.setText("impossibile generare una favola coi vincoli richiesti");
				print.setDisable(true);
			}else {
				output.setText(favOpt.get().toString().substring(9, favOpt.get().toString().length()-1));
				print.setDisable(false);
			}
			
			
			
		}else {
			String fileName = this.controller.getOutputFileName();
			try (Writer writer = new FileWriter(fileName)) {
				PrintWriter wrt = new PrintWriter(writer);
				StringBuilder stb = new StringBuilder();
				
				if(output.getText() != null && !output.getText().isEmpty()) {
					wrt.println(output.getText());
				}
				else {
					Controller.alert("Errore", "Qualcosa e' andato diritto", "Addio");
				}
				
			}
		}
		
		
		return;
		
	}
}

