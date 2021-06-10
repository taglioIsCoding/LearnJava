package cambiavalute.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;

import cambiavalute.model.CambiaValute;
import cambiavalute.model.TassoDiCambioUfficiale;
import cambiavalute.ui.controller.*;

public class MainPane extends BorderPane{
	private Controller controller;
	private ComboBox<String> valuteBox;
	private TextArea outpuTextArea;
	private TextField impoField;
	private TextField cambioField;
	private ToggleGroup tg;
	
	public MainPane(Controller controller) {
		this.controller = controller;
		initPane();
	}
	
	public void initPane() {
		HBox vh = new HBox(5);
		HBox vh2 = new HBox(5);
		VBox vb = new VBox(5);
		VBox vb2 = new VBox(5);
		
		Label valuta = new Label("Valuta:");
		Label emptyLabel = new Label(" ");
		Label impotroLabel = new Label("Importo");
		Label cambioLabel = new Label("Cambio Ufficiale");
		impoField = new TextField();
		cambioField = new TextField();
		tg = new ToggleGroup();
		outpuTextArea = new TextArea();
		
		ObservableList<String> valuteList = FXCollections.observableArrayList(this.controller.getListaValuteEstere());
		valuteBox = new ComboBox<>(valuteList);
		valuteBox.setValue(valuteList.get(0));
		valuteBox.setEditable(false);
		RadioButton b1 = new RadioButton("Acquisto");
		RadioButton b2 = new RadioButton("Vendita");
		b1.setToggleGroup(tg);
		b2.setToggleGroup(tg);
		tg.selectToggle(b2);
		impoField.setText("100,00");
		cambioField.setEditable(false);
		outpuTextArea.setEditable(false);
		outpuTextArea.setPrefColumnCount(25);
		
		
		b1.setOnAction(arg0 -> {
			try {
				myHandle(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		b2.setOnAction(arg0 -> {
			try {
				myHandle(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		valuteBox.setOnAction(arg0 -> {
			try {
				myHandle(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		impoField.textProperty().addListener((obs, oldV, newV) -> {
			ActionEvent fakEvent = new ActionEvent();
			try {
				myHandle(fakEvent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			});
		
		
		vh.setPadding(new Insets(15, 12, 15, 12));
		vh.getChildren().addAll(valuta, valuteBox,emptyLabel, b1, b2);
		vh.setAlignment(Pos.CENTER);
		
		//vh2.setPadding(new Insets(15, 12, 15, 12));
		vh2.getChildren().addAll(impotroLabel,impoField, cambioLabel, cambioField);
		vh2.setAlignment(Pos.CENTER);
		
		vb.getChildren().addAll(vh, vh2, vb2);
		
		this.setTop(vb);
		this.setCenter(outpuTextArea);
	}
	
	private void myHandle(ActionEvent event) throws IOException {
		this.outpuTextArea.clear();
		this.cambioField.setText("");
		String impotextString = this.impoField.getText();
		String codeString = this.valuteBox.getValue();
		Double valueDouble = 0.0;
		try {
			valueDouble = CambiaValute.convertiInDouble(impotextString);
		} catch (Exception e) {
			System.out.println(e);
			Controller.alert("Attenzione", "Qualcosa e' andato sotrto", "dati errati");
		}
		
		Map<String, TassoDiCambioUfficiale> tassiMap = this.controller.getCambiUfficiali();
		cambioField.setText(CambiaValute.formatta(tassiMap.get(codeString).getTassoDiCambio()*valueDouble));
		
		this.outpuTextArea.setText("Richiesta compravendita di: "+ tassiMap.get(codeString).getNomeValuta()+"\n");
		
		if(((RadioButton)tg.getSelectedToggle()).getText().equals("Vendita")) {
			Map<String, OptionalDouble> cambiavaluteMap = this.controller.changeTo(codeString, valueDouble);
			cambioField.setText(CambiaValute.formatta(tassiMap.get(codeString).getTassoDiCambio()*valueDouble));
			for(String nomeCambia: cambiavaluteMap.keySet()) {
				this.outpuTextArea.appendText(nomeCambia+": ");
				if(cambiavaluteMap.get(nomeCambia).isPresent()) {
					this.outpuTextArea.appendText(valueDouble + "EUR = "+ cambiavaluteMap.get(nomeCambia).getAsDouble()+tassiMap.get(codeString).getNomeValuta()+"\n");
				}else {
					this.outpuTextArea.appendText("Non tratta la valuta estera richesta\n");
				}
			}
		}else {
			Map<String, OptionalDouble> cambiavaluteMap = this.controller.changeFrom(codeString, valueDouble);
			cambioField.setText(CambiaValute.formatta(valueDouble/tassiMap.get(codeString).getTassoDiCambio()));
			for(String nomeCambia: cambiavaluteMap.keySet()) {
				this.outpuTextArea.appendText(nomeCambia+": ");
				if(cambiavaluteMap.get(nomeCambia).isPresent()) {
					this.outpuTextArea.appendText(valueDouble +tassiMap.get(codeString).getNomeValuta()+"= "+ cambiavaluteMap.get(nomeCambia).getAsDouble()+"EUR \n");
				}else {
					this.outpuTextArea.appendText("Non tratta la valuta estera richesta\n");
				}
			}
		}
		
		return;
		
	}
}
