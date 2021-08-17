package minirail.ui.javafx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import minirail.model.LineStatus;
import minirail.model.Train;
import minirail.ui.controller.Controller;

public class MiniRailPane extends BorderPane{
		
	private Button clockButton;
	private Button moving;
	private Button stopping;
	private Controller controller;
	private Label statusLabel;
	private TextArea outpuTextArea;
	private Map<Train, Color> trainColors;
	private TrainLinePane tPane;
	private final String STOP = "STOPPED";
	private final String MOVE = "MOVING";
	
	public MiniRailPane(Controller controller, double[] positions ) {
		this.controller = controller;
		
		
		HBox topBox = new HBox(5);
		moving = new Button("Move Trains");
		moving.setOnAction(this::moveTrains);
		stopping = new Button("Stop Trains");
		stopping.setOnAction(this::stopTrains);
		statusLabel = new Label("Trains are now " + STOP);
		
		topBox.getChildren().addAll(moving, stopping, statusLabel);
		topBox.setPadding(new Insets(10, 5, 10, 5));
		
		
		HBox centerBox = new HBox(30);
		tPane = new TrainLinePane(this.controller.getLine());
		drawTrainColors();
		setUpTrainPanel(positions);
		clockButton = new Button("clock");
		clockButton.setOnAction(this::advanceClock);
		centerBox.getChildren().addAll(tPane, clockButton);
		
		outpuTextArea = new TextArea();
		
		this.setTop(topBox);
		this.setCenter(centerBox);
		this.setBottom(outpuTextArea);
		
	}
	
	private void advanceClock(ActionEvent ev) {
		
		this.controller.clock(0.5);
		this.outpuTextArea.clear();
		this.outpuTextArea.setText(this.controller.getLog());
		this.updateTrainPanel();
	}
	
	private void updateTrainPanel() {
		List<Train> trains = this.controller.getTrains();
		LineStatus status = this.controller.getLineStatus();
		
		tPane = new TrainLinePane(this.controller.getLine());
		HBox centerBox = new HBox(30);
		centerBox.getChildren().addAll(tPane, clockButton);
		this.setCenter(centerBox);
		
		System.out.println(status);
		
		for(Train t:trains) {
			tPane.drawTrain(status.getTrainLocation(t), t.getLength(), this.trainColors.get(t), t.getName());
		}
		
	}
	
	private void setUpTrainPanel(double[] positions) {
		List<Train> trains = this.controller.getTrains();
		
		for(int i = 0; i < positions.length-1; i++) {
			this.controller.getLineStatus().putTrain(trains.get(i), positions[i]);
			tPane.drawTrain(positions[i], trains.get(i).getLength(), this.trainColors.get(trains.get(i)), trains.get(i).getName());
		}
		
	}
	
	private void drawTrainColors() {
		trainColors = new HashMap<>();
		for(Train t: this.controller.getTrains()) {
			trainColors.put(t, randomColor());
		}
	}
	
	private void moveTrains(ActionEvent ev) {
		this.statusLabel.setText("Trains are now " + MOVE);
		for(Train t: this.controller.getTrains()) {
			this.controller.setMoving(t);
		}
	}
	
	private void stopTrains(ActionEvent ev) {
		this.statusLabel.setText("Trains are now " + STOP);
		for(Train t: this.controller.getTrains()) {
			this.controller.setStopped(t);
		}
	}
	
	private Color randomColor() {
		Random random = new Random();
		return Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}
	
}
