package cupidonline.ui.javafx;

import java.util.Optional;
import java.util.OptionalInt;

import cupidonline.model.Colore;
import cupidonline.model.Preferenza;
import cupidonline.model.SegnoZodiacale;
import cupidonline.model.Sesso;
import javafx.collections.FXCollections;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PreferenzePane extends VBox {

	private RadioButton maschio, femmina;
	private ToggleGroup group;
	private Spinner<Integer> minAgeSpinner, maxAgeSpinner;
	private Spinner<Integer> heightSpinner, weightSpinner;
	private ComboBox<SegnoZodiacale> comboZodiaco;
	private ComboBox<Colore> comboColoreCapelli, comboColoreOcchi;
	private TextField txtCitta, txtProvincia, txtRegione;
	private CheckBox b1,b2,b3,b4,b5;
	
	public PreferenzePane() {
		this.setStyle("-fx-border-color: black; -fx-border-width: 1;");
		//
		group = new ToggleGroup();
		Label labelSesso = new Label("Sesso");
		HBox hbox0 = new HBox();
			maschio = new RadioButton("M"); maschio.setToggleGroup(group);
			femmina = new RadioButton("F"); femmina.setToggleGroup(group);
			hbox0.getChildren().addAll(maschio,femmina);
		Label labelEtaMin = new Label("Età minima");
		Label labelEtaMax = new Label("Età massima");		
		minAgeSpinner = new Spinner<>(18,99,20);
		maxAgeSpinner = new Spinner<>(18,99,25);
		//
		Label labelZodiaco = new Label("Segno zodiacale");
		HBox hbox1 = new HBox();
			b1 = new CheckBox("abilita");
			b1.setOnAction(event -> comboZodiaco.setDisable(!comboZodiaco.isDisabled()));
			comboZodiaco = new ComboBox<>(FXCollections.observableArrayList(SegnoZodiacale.values()));
			comboZodiaco.setValue(comboZodiaco.getItems().get(0));
			b1.setSelected(false);
			comboZodiaco.setDisable(true);
			hbox1.getChildren().addAll(b1,comboZodiaco);
		//
		Label labelCapelli = new Label("Colore capelli");
		HBox hbox2 = new HBox();
			b2 = new CheckBox("abilita");
			b2.setOnAction(event -> comboColoreCapelli.setDisable(!comboColoreCapelli.isDisabled()));
			comboColoreCapelli = new ComboBox<>(FXCollections.observableArrayList(Colore.values()));
			comboColoreCapelli.setValue(comboColoreCapelli.getItems().get(0));
			b2.setSelected(false);
			comboColoreCapelli.setDisable(true);
			hbox2.getChildren().addAll(b2,comboColoreCapelli);
		//
		Label labelOcchi = new Label("Colore occhi");
		HBox hbox3 = new HBox();
			b3 = new CheckBox("abilita");
			b3.setOnAction(event -> comboColoreOcchi.setDisable(!comboColoreOcchi.isDisabled()));
			comboColoreOcchi = new ComboBox<>(FXCollections.observableArrayList(Colore.values()));
			comboColoreOcchi.setValue(comboColoreOcchi.getItems().get(0));
			b3.setSelected(false);
			comboColoreOcchi.setDisable(true);
			hbox3.getChildren().addAll(b3,comboColoreOcchi);
		//
		Label labelAltezza= new Label("Altezza in cm");
		HBox hbox4 = new HBox();
			b4 = new CheckBox("abilita");
			b4.setOnAction(event -> heightSpinner.setDisable(!heightSpinner.isDisabled()));
			heightSpinner = new Spinner<>(150,200,170);
			b4.setSelected(false);
			heightSpinner.setDisable(true);
			hbox4.getChildren().addAll(b4,heightSpinner);
		//
		Label labelPeso= new Label("Peso in kg");
		HBox hbox5 = new HBox();
			b5 = new CheckBox("abilita");
			b5.setOnAction(event -> weightSpinner.setDisable(!weightSpinner.isDisabled()));
			weightSpinner = new Spinner<>(45,100,60);	
			b5.setSelected(false);
			weightSpinner.setDisable(true);
			hbox5.getChildren().addAll(b5,weightSpinner);
		//
		Label labelCitta = new Label("Città");		
		txtCitta = new TextField();
		Label labelProvincia = new Label("Provincia (sigla)");		
		txtProvincia = new TextField();
		Label labelRegione = new Label("Regione");		
		txtRegione = new TextField();
		this.getChildren().addAll(labelSesso, hbox0, labelEtaMin, minAgeSpinner, labelEtaMax, maxAgeSpinner,
				labelZodiaco, b1, comboZodiaco, labelCapelli, b2, comboColoreCapelli, labelOcchi, b3, comboColoreOcchi,
				labelAltezza, b4, heightSpinner, labelPeso, b5, weightSpinner, 
				labelCitta, txtCitta, labelProvincia, txtProvincia, labelRegione, txtRegione);
	}
	
	public Preferenza getPreferenza() {
		return new Preferenza(
				// Sesso sesso, int etaMin, int etaMax, 
				// Optional<SegnoZodiacale> zodiaco, Optional<Colore> capelli2, Optional<Colore> occhi, 
				// Optional<Float> altezza, OptionalInt peso, 
				// String citta, String provincia,	String regione)
				group.getSelectedToggle()==maschio ? Sesso.MASCHIO : Sesso.FEMMINA,
				minAgeSpinner.getValue(),
				maxAgeSpinner.getValue(),
				b1.isSelected() ? Optional.of(comboZodiaco.getValue()) : Optional.empty(), 
				b2.isSelected() ? Optional.of(comboColoreCapelli.getValue()) : Optional.empty(),
				b3.isSelected() ? Optional.of(comboColoreOcchi.getValue()) : Optional.empty(),
				b4.isSelected() ? Optional.of(heightSpinner.getValue()/100.0F) : Optional.empty(),
				b5.isSelected() ? OptionalInt.of(weightSpinner.getValue()) : OptionalInt.empty(),
				txtCitta.getText(),	
				txtProvincia.getText(),
				txtRegione.getText()
				);
	}
	
	public void setPreferenza(Optional<Preferenza> preferenza) {
				if (!preferenza.isPresent()) return;
				Preferenza pref = preferenza.get(); // sicuramente ok
				//
				group.selectToggle(pref.getSesso()==Sesso.MASCHIO ? maschio : femmina);
				minAgeSpinner.getValueFactory().setValue(pref.getEtaMin());
				maxAgeSpinner.getValueFactory().setValue(pref.getEtaMax());
				
				if (pref.getSegnoZodiacale().isPresent()) {
					b1.setSelected(true); comboZodiaco.setValue(pref.getSegnoZodiacale().get());
				} 
				else {
					b1.setSelected(false);
				}
				
				if (pref.getColoreCapelli().isPresent()) {
					b2.setSelected(true); comboColoreCapelli.setValue(pref.getColoreCapelli().get());
				} 
				else {
					b2.setSelected(false);
				}
				
				if (pref.getColoreOcchi().isPresent()) {
					b3.setSelected(true); comboColoreOcchi.setValue(pref.getColoreOcchi().get());
				} 
				else {
					b3.setSelected(false);
				}
				
				if (pref.getAltezza().isPresent()) {
					b4.setSelected(true); heightSpinner.getValueFactory().setValue(Math.round(pref.getAltezza().get())*100);
				} 
				else {
					b4.setSelected(false);
				}
				
				if (pref.getPeso().isPresent()) {
					b5.setSelected(true); weightSpinner.getValueFactory().setValue(pref.getPeso().getAsInt());
				} 
				else {
					b5.setSelected(false);
				}

				txtCitta.setText(pref.getCitta());	
				txtProvincia.setText(pref.getProvincia());
				txtRegione.setText(pref.getRegione());
	}
}
