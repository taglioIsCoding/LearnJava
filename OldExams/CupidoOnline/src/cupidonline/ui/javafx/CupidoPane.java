package cupidonline.ui.javafx;

import java.util.SortedSet;

import cupidonline.model.Corrispondenza;
import cupidonline.persistence.IscrittiReader;
import cupidonline.ui.controller.Controller;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CupidoPane extends BorderPane{
	
	private Button buttonCerca;
	private ComboBox<String> iscritteBox;
	private Controller controller;
	private VBox leftPane;
	private TextArea outPutTextArea;
	private PreferenzePane preferenzePane;
	
	
	public CupidoPane(Controller controller) {
		this.controller = controller;
		this.leftPane = new VBox(5);
		this.preferenzePane = new PreferenzePane();
		this.buttonCerca = new Button("Cerca");
		buttonCerca.setOnAction(this::cercaCorrispondenze);
		this.outPutTextArea = new TextArea();
		outPutTextArea.setEditable(false);
		
		
		//ComboBox
		this.iscritteBox = new ComboBox<>();
		iscritteBox.getItems().addAll(this.controller.getNomiIscritti());
		iscritteBox.getItems().add("NON ISCRITTO");
		iscritteBox.setValue("NON ISCRITTO");
		iscritteBox.setOnAction(this::aggiornaPrefPane);
		HBox topBox = new HBox(5);
		topBox.getChildren().addAll(iscritteBox, buttonCerca);
		
		leftPane.getChildren().addAll(topBox, preferenzePane);
		this.setLeft(leftPane);
		this.setCenter(outPutTextArea);
		
	}
	
	private void aggiornaPrefPane(ActionEvent ev) {
		if(!this.iscritteBox.getValue().equals("NON ISCRITTO")) {
			this.preferenzePane.setPreferenza(this.controller.getPreferenza(this.iscritteBox.getValue()));
		}
	}
	
	private void cercaCorrispondenze(ActionEvent ev) {
		if(!this.iscritteBox.getValue().equals("NON ISCRITTO")) {
			this.outPutTextArea.clear();
			SortedSet<Corrispondenza> corr = this.controller.trovaCorrispondenze(this.iscritteBox.getValue());
			for(Corrispondenza corrispo: corr) {
				outPutTextArea.appendText(corrispo + "\n");
			}
			
			outPutTextArea.appendText("Non ci sono altre corrispondenze");
		} else {
			
			this.outPutTextArea.clear();
			SortedSet<Corrispondenza> corr = this.controller.trovaCorrispondenze(this.iscritteBox.getValue(), this.preferenzePane.getPreferenza());
			for(Corrispondenza corrispo: corr) {
				outPutTextArea.appendText(corrispo + "\n");
			}
			
			outPutTextArea.appendText("Non ci sono altre corrispondenze");
		}
	}
	
	
}
