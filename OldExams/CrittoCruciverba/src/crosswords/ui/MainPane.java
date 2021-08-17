package crosswords.ui;



import java.util.ArrayList;

import crosswords.controller.Controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainPane extends BorderPane {

	private TextArea output;
	private Controller controller;
	private ComboBox<Integer> numbers ;
	private ComboBox<String> letters;
	private Button button;
	
	
	
	public MainPane(Controller controller, Stage stage) {
		this.controller=controller;
		HBox topPane = new HBox();
		this.setTop(topPane);

		output = new TextArea();
		output.setPrefColumnCount(12);
		output.setPrefRowCount(12);
		output.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));

		output.setEditable(false);
		
		updateGrid();
		
		this.setCenter(output);

		HBox bottomPane = new HBox();
		numbers= new ComboBox<Integer>();
		populateComboNumbers(numbers);
		letters= new ComboBox<String>();
		populateComboLetters(letters);
		
		
		
		button = new Button("Imposta");
		this.setBottom(bottomPane);
		
		
		bottomPane.getChildren().add(numbers);
		bottomPane.getChildren().add(letters);
		bottomPane.getChildren().add(button);
		button.setOnAction(this::updateMapping);

}
	
	
	
	private void populateComboNumbers(ComboBox<Integer> combo) {
		ArrayList<Integer> listInt= new ArrayList<>();
		for(int i = 1; i<27; i ++ ) {
			listInt.add(Integer.valueOf(i));
		}
		combo.getItems().addAll((FXCollections.observableArrayList(listInt)));
		combo.setValue(1);
	}
	
	private void populateComboLetters(ComboBox<String> combo) {
		String[] alpha = new String[26];
		for(int i = 0; i < 26; i++){
			alpha[i] = (char)(97 + i)+"";
		}
		combo.getItems().addAll((FXCollections.observableArrayList(alpha)));
		combo.setValue("a");
	}
	
	private void updateGrid()
	{
		output.setText(controller.getGame().toString());
	}
	
	
	private void updateMapping(ActionEvent event)
	{
		int number = this.numbers.getValue();
		String letterString = this.letters.getValue();
		
		this.controller.getGame().setMapping(number, letterString);
		this.updateGrid();
		
	}
}
