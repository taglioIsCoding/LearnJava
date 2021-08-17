package saferepo.ui.javafx;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.sun.tools.javac.util.List;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import saferepo.model.VersionedDocument;
import saferepo.persistence.SadPersisterException;
import saferepo.ui.controller.Controller;

public class SefeRepoPane extends BorderPane {
	
	private Button aggiungiButton;
	private Button eliminaButton;
	private Controller controller;
	private ListView<String> listadocumenti;
	private ListView<VersionedDocument> listaVersioni;
	private String selectedDocumentId;
	private VersionedDocument version;
	private Stage stage;
	private VBox v1;
	private VBox v2;
	
	public SefeRepoPane(Controller controller, Stage stage){
		this.controller = controller;
		this.stage = stage;
		
		Label selezionaLabel = new Label("Seleziona documenti: ");
		Label versioni = new Label("Elenco versioni: ");
		listadocumenti = new ListView<>();
		listadocumenti.setOnMouseClicked(e -> documentSelectedHandler(e));
		listaVersioni = new ListView<>();
		listaVersioni.setOnMouseClicked(e -> downloadFile(e));
		aggiungiButton = new Button("Aggiungi documenti");
		aggiungiButton.setOnAction(e -> AddDocument(e));
		eliminaButton = new Button("Elimina documenti");
		eliminaButton.setDisable(true);
		
		v1 = new VBox(5);
		v1.getChildren().addAll(selezionaLabel, listadocumenti);
		v1.setPadding(new Insets(5, 10, 5, 10));
		
		v2 = new VBox(5);
		v2.getChildren().addAll(versioni, listaVersioni);
		v2.setPadding(new Insets(5, 10, 5, 10));
		
		HBox hButton = new HBox(5);
		hButton.getChildren().addAll(aggiungiButton, eliminaButton);
		hButton.setPadding(new Insets(5, 10, 5, 10));
		hButton.setAlignment(Pos.CENTER);
		
		this.setLeft(v1);
		this.setRight(v2);
		this.setBottom(hButton);
	}
	
	private void AddDocument(ActionEvent e) {
		FileChooser fChooser = new FileChooser();
		fChooser.setInitialDirectory(Paths.get(System.getProperty("user.dir")).toFile());
		
		File toAdd = fChooser.showOpenDialog(stage);
		try {
			this.controller.store(toAdd.getName(), Path.of(toAdd.getPath()));
		} catch (SadPersisterException e1) {
			Controller.alert("Attenzione", "Caricamento non riuscoto", "Riprova piu tardi");
			return;
		}
		
		listadocumenti.getItems().clear();
		//listadocumenti.getItems().addAll(this.controller.getDocumentIDs().sort(Comparator.comparing(VersionedDocument::getID)));
		listadocumenti.getItems().addAll(this.controller.getDocumentIDs().stream().sorted().collect(Collectors.toList()));
	}
	
	private void documentSelectedHandler(MouseEvent e) {
		if(this.listadocumenti.getSelectionModel().getSelectedItem() == null) {
			return;
		}
		
		this.listadocumenti.getSelectionModel().getSelectedItem();
		this.listaVersioni.getItems().clear();
		//Collections.sort(this.listaVersioni.getItems(), Comparator.comparing(VersionedDocument::getID));
		this.listaVersioni.getItems().addAll(this.controller.getVersions(this.listadocumenti.getSelectionModel().getSelectedItem()));
	}
	
	private void downloadFile(MouseEvent e) {
		
	}
	
	private void delDocument() {
		boolean confirmed = Controller.askConfirm("Richiesta eliminazione", "Eliminare davvero il documento " + selectedDocumentId + "?", "Pensaci bene..");
		if (confirmed) {
			try {
				controller.delete(selectedDocumentId);
				listadocumenti.setItems(controller.getDocumentIDs());
			} catch (SadPersisterException e) {
				Controller.alert("Errore irrecuperabile", "Errore nell'eliminazione di " + selectedDocumentId, "Situazione inconsistente");
			}
		}
}
	
}
