package battleship.ui;

import battleship.controller.Controller;
import battleship.model.ShipItem;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainPane extends BorderPane{
	private ComboBox<ShipItem> shipsBox;
	private Controller controller;
	private BattleshipGrid grid;
	private Button verificaButton;
	
	public MainPane(Controller controller, Stage stage) {
		this.controller = controller;
		
		//creo pannello di sinstra
		VBox lefBox = new VBox(5);
		Label topLabel = new Label("Scegli un elemnto della combo e posizionalo nella griglia");
		shipsBox = new ComboBox<>(FXCollections.observableArrayList(ShipItem.values()));
		shipsBox.setValue(ShipItem.SEA);
		Label centerLabel = new Label(this.controller.getShipList());
		this.verificaButton = new Button("VERIFICA");
		this.verificaButton.setOnAction(this::myHandle);
		
		//creo griglia
		this.grid = new BattleshipGrid(controller, shipsBox);
		
		//posiziono gli elemneti nella vertifal box
		lefBox.setPadding(new Insets(15, 12, 15, 12));
		lefBox.getChildren().addAll(topLabel, shipsBox, centerLabel, verificaButton);
		lefBox.setAlignment(Pos.CENTER);
		
		//aggiungo gli elementi al pannello
		this.setLeft(lefBox);
		this.setCenter(grid);
	}
	
	private void myHandle(ActionEvent ev) {
		int errate = this.controller.verify();
		
		if(errate != 0) {
			Controller.alert("Esito della verifica", "Trovate " + errate+ " celle Errate", "");
			
			this.controller.setCurrentCellItem(errate, errate, null);
			
			//cerifivo le celle errate 
			for(int i = 0; i < this.controller.getSize()-1; i++) {
				for(int j = 0; j < this.controller.getSize()-1; j++) {
					if(this.controller.getCurrentCellItem(i, j)!= ShipItem.EMPTY && this.controller.getCurrentCellItem(i, j) != this.controller.getSolutionCellItem(i, j)) {
						
						this.controller.setCurrentCellItem(i, j, ShipItem.EMPTY);
						this.grid.updateGridStatus();
					}	
				}	
			}
			
			
			
		} else if(this.controller.isGameOver()){
			Controller.alert("Esito della verifica", "Game over", "hai vinto");
			return;
		} else {
			Controller.alert("Esito della verifica", "Nessuna cella errata", "continua");
		}
	}
}
