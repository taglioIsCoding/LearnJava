package oroscopo.ui;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import oroscopo.controller.AbstractController;
import oroscopo.model.Oroscopo;
import oroscopo.model.SegnoZodiacale;

public class MainPane extends BorderPane{
	
	private AbstractController controller = null;
	private int fortunaMin;
	private TextArea output = new TextArea();
	private ComboBox<SegnoZodiacale> segniZodiacali;
	
	public MainPane(AbstractController controller, int fortunaMin ) {
		this.controller = controller;
		this.fortunaMin = fortunaMin;
		initPane();
	}
	
	private void initPane() {
		VBox vb = new VBox(5);
		Label segnozod = new Label("Segno Zodiacale");
		Label OroscopoMensile = new Label("Oroscopo mensile del segno");
		Button print = new Button("Stampa Annuale");
		ObservableList<SegnoZodiacale>  data = FXCollections.observableArrayList(this.controller.getSegni());
		
		segniZodiacali = new ComboBox<SegnoZodiacale>(data);
		segniZodiacali.setEditable(false);
		output.setEditable(false);
		
		print.setOnAction(arg0 -> {
			try {
				myHandle(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		segniZodiacali.setOnAction(arg0 -> {
			try {
				myHandle(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
		vb.getChildren().addAll(segnozod,segniZodiacali,OroscopoMensile, output, print);
		this.setCenter(vb);
	}
	
	
	private void myHandle(ActionEvent event) throws IOException {
		
		if(event.getSource() == segniZodiacali) {
			output.clear();
			Oroscopo toPrint = this.controller.generaOroscopoCasuale(segniZodiacali.getValue());
			output.setText("Amore: "+ toPrint.getPrevisioneAmore().getPrevisione()+"\n"+
							"Lavoro: "+ toPrint.getPrevisioneLavoro().getPrevisione()+"\n"+
							"Salute: "+ toPrint.getPrevisioneSalute().getPrevisione()+"\n");
			
		}else {
			String fileName = "anno.txt";
			try (Writer writer = new FileWriter(fileName)) {
				PrintWriter wrt = new PrintWriter(writer);
				StringBuilder stb = new StringBuilder();
				int totalAvg = 0;
				List<Oroscopo> oroscopiAnnuali =  Arrays.asList(this.controller.generaOroscopoAnnuale(segniZodiacali.getValue(), fortunaMin));
				wrt.println(segniZodiacali.getValue());
				wrt.println("---------");
				for(int i = 0; i <= 11; i++) {
					stb.append(Month.of(i+1).getDisplayName( TextStyle.FULL_STANDALONE, Locale.ITALIAN).toUpperCase() + "\n");
					stb.append("Amore: "+ oroscopiAnnuali.get(i).getPrevisioneAmore().getPrevisione()+"\n"+
							"Lavoro: "+ oroscopiAnnuali.get(i).getPrevisioneLavoro().getPrevisione()+"\n"+
							"Salute: "+ oroscopiAnnuali.get(i).getPrevisioneSalute().getPrevisione()+"\n\n");
					totalAvg +=  oroscopiAnnuali.get(i).getFortuna();
					
				}
				
				wrt.print(stb);
				wrt.println("Fortuna Media:" + totalAvg/12);
			}
		}
		
		
		return;
		
	}
}
