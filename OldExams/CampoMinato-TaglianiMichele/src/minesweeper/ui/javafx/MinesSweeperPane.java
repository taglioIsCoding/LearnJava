package minesweeper.ui.javafx;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import minesweeper.ui.controller.Controller;

public class MinesSweeperPane extends BorderPane{
	
	private Controller controller;
	private MinesSweeperGrid grid;
	private TextArea outPuTextArea;
	private TextArea outPuTextArea2;
	private Button restarButton;
	private Button saveButton;
	
	public MinesSweeperPane(Controller controller, Stage stage) {
		this.controller = controller;
		this.init();
	}
	
	private void init() {
		Label mineLabel = new Label("Numero Mine: " + this.controller.getMinesNumber());
		this.outPuTextArea = new TextArea();
		this.outPuTextArea.setEditable(false);
		this.outPuTextArea2 = new TextArea();
		this.outPuTextArea2.setEditable(false);
		this.grid = new MinesSweeperGrid(controller, outPuTextArea);
		
		HBox hBox = new HBox();
		HBox vBox = new HBox();
		vBox.getChildren().addAll(grid,outPuTextArea );
		this.restarButton = new Button("Restart");
		this.saveButton = new Button("SaveButton");
		hBox.getChildren().addAll(restarButton, saveButton);
		hBox.setPadding(new Insets(15, 12, 15, 12));
		
		this.restarButton.setOnAction(arg0 -> {
			try {
				myHandleCalc(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		this.saveButton.setOnAction(arg0 -> {
			try {
				myHandlePrint(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		this.setTop(mineLabel);
		this.setCenter(vBox);
		this.setBottom(hBox);
		
	}
	
	private void myHandlePrint(ActionEvent event) throws IOException {
		System.out.println("Stampa");
		this.controller.save();
		this.controller.close();
	}
	private void myHandleCalc(ActionEvent event) throws IOException {
		this.init();
		this.controller.restart();
	}
}
