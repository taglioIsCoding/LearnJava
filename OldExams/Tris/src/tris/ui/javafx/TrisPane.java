package tris.ui.javafx;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import tris.ui.controller.Controller;

public class TrisPane extends BorderPane {

	private Label giocatore;
	private TextArea output;
	private Button save;
	
	public TrisPane(Controller controller, Stage stage) {
		this.giocatore=new Label(controller.getCurrentPlayer());
		//
		HBox topPane = new HBox();
		topPane.getChildren().addAll(new Label("Gioca: "), giocatore);
		this.setTop(topPane);
		//
		output = new TextArea();
		output.setPrefColumnCount(30);
		output.setPrefRowCount(10);
		output.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
		//
		this.setRight(output);
		//
		save = new Button("Salva");
		save.setDisable(true);
		this.setBottom(save);
		//
		this.setCenter(new TrisGrid(controller, giocatore, output, save));
	}

}
