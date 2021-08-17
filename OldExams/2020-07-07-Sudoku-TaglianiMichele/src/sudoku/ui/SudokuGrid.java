package sudoku.ui;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sudoku.controller.Controller;

public class SudokuGrid extends GridPane {

	private int size, tot;
	private Button[][] grid;  
	private Controller controller;
	private ComboBox<String> combo;
	private TextArea output;
	
	public SudokuGrid(Controller controller, ComboBox<String> comb, TextArea ou) {
		this.size=controller.getSize();
		tot = size*size;
		this.controller = controller;
		this.combo=comb;
		this.output = ou;

		grid = new Button[size][size];
		for(int k=0; k<tot; k++) {
			int row=k/size, col=k%size;
			grid[row][col]=new Button();
			grid[row][col].setStyle("-fx-background-color: WHITE");
			grid[row][col].setStyle("-fx-border-width: 1;");
			grid[row][col].setFont(Font.font("Courier New", FontWeight.BOLD, 16));
			grid[row][col].setText(" ");
			grid[row][col].setOnAction(ev -> insertNumber(row,col));
			add(grid[row][col],col,row,1,1);
		}
		
		updateGridStatus();
	}
	
	private void updateGridStatus() {
		for(int k=0; k<tot; k++) {
			int row=k/size, col=k%size;
			grid[row][col].setText(controller.getCellLabel(row,col));
		}
		output.setText(controller.getEmptyCell());
		
		}

	
	private void insertNumber(int row, int col) {
		
		String valueComboString = combo.getValue();
		boolean inserito = this.controller.setCell(row, col, valueComboString);
		if(inserito) {
			this.grid[row][col].setText(valueComboString);
			this.output.clear();
			this.output.setText(this.controller.getEmptyCell());
			if(this.controller.endGame()) {
				this.controller.saveGame();
				SudokuApp.alert("Complimenti", "Hai vinto", "sei proprio bravo");
			}
		}else {
			SudokuApp.alert("Errore", "Mossa non consentita", "Non rispetti le regole");
		}
	}
	

}
