package tris.ui.javafx;


import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import tris.model.BoardValue;
import tris.ui.controller.Controller;

public class TrisGrid extends GridPane{
	
	private Button printButton;
	private Controller controller;
	private TextArea outpuTextArea;
	private Label playerLabel;
	private Button[][] gridButtons = new Button[3][3];
	
	
	public TrisGrid(Controller controller, Label label,TextArea area, Button button) {
		this.controller = controller;
		this.playerLabel = label;
		this.outpuTextArea = area;
		this.printButton = button;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				Button toInsertButton = new Button(BoardValue.EMPTY.get());
				toInsertButton.setFont(Font.font("Courier New",20));
				toInsertButton.setStyle(getAccessibleHelp());
				toInsertButton.setOnAction((event) -> {
						this.handle(toInsertButton);
				});
				this.gridButtons[i][j] = toInsertButton;
				this.add(toInsertButton, j, i);
			}
		}
		
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				sb.append(this.gridButtons[i][j].getText());
			}
		}
		
		return sb.toString();
	}
	
	private void handle(Button button) {
		button.setText(this.controller.getCurrentPlayer());
		button.setDisable(true);
		
		if(this.controller.storeStatus(this.toString())) {
			this.outpuTextArea.appendText(this.toString() + "Vittoria" + this.controller.getCurrentPlayer()+"\n");
			this.endGame();
		} else if (this.toString().indexOf(" ") == -1) {
			this.endGame();
		}
		else {
			this.outpuTextArea.appendText(this.toString() + "Continua" + "\n");
		}
		
		this.controller.getNextPlayer();
		this.playerLabel.setText(this.controller.getCurrentPlayer());
	}
	
	private void endGame() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				this.gridButtons[i][j].setDisable(true);
			}
		}
		
		this.printButton.setDisable(false);
		this.printButton.setOnAction(this::print);
	}
	
	private void print(ActionEvent ev) {
		this.controller.print();
		this.printButton.setDisable(true);
	}

}
