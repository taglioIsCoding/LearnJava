package sudoku.ui;

import java.util.Arrays;




import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sudoku.controller.Controller;

public class MainPane extends BorderPane {

	private ComboBox<String> sudokuNumbers;
	private SudokuGrid grid;
	private TextArea output;
	
	
	
	public MainPane(Controller controller, Stage stage) {
		HBox topPane = new HBox();
		this.setTop(topPane);
		Insets insets = new Insets(10);

		VBox leftPane = new VBox();
		sudokuNumbers= new ComboBox<String>();
		sudokuNumbers.setItems(FXCollections.observableArrayList(Arrays.asList("123456789 ".split(""))));
		
		leftPane.getChildren().add(new Label("Scegli il numero dalla combo"));
		
		leftPane.getChildren().add(new Label("e premi il pulsante nella griglia"));
    	leftPane.getChildren().add(sudokuNumbers);
    	VBox.setMargin(sudokuNumbers, insets);
    	
    	output = new TextArea();
    	output.setPrefColumnCount(5);
		output.setPrefRowCount(5);
		output.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
		VBox.setMargin(output, insets);
		leftPane.getChildren().add(output);
    	
    	setLeft(leftPane);
    	
    	insets = new Insets(20);
    	setMargin(leftPane, insets);
    	grid = new SudokuGrid(controller, sudokuNumbers, output);
    	this.setCenter(grid);
    	setMargin(grid, insets);

}
	
	
	
}
