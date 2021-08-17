package ghigliottina.ui;
import java.text.NumberFormat;
import java.util.List;

import ghigliottina.model.Esatta;
import ghigliottina.model.Terna;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.util.Duration;


public class GhigliottinaPanel extends BorderPane {
	
	// IMPORTANTE: Dimensione prevista = 350 x 350 
	// Questo pannello *NON* deve essere ridimensionato, è calcolato per funzionare su una ben precisa dimensione in quanto:
	// a) gli effetti animati nascondono gli elementi portandoli fuori schermo in base alle dimensioni previste,
	// b) gli elementi che devono sparire vengono sovrapposti da altri nella stessa posizione ma su sotto-pannelli diversi, 
	// quindi se si allarga il pannello, l'effetto di sovrapposizione viene meno.
 
	private Button ghigliottina;
	private RadioButton first,second;
	private ToggleGroup tg;
	private Node item1, item2, correctItemDuplicates[], correctItem, wrongItem, selectedItem, itemVincita;
	private int montepremi, k;
	private NumberFormat formatter;
	private List<Terna> terne;
	private Pane rightPane;
	
	public GhigliottinaPanel(int montepremi, List<Terna> terne) {
		if (montepremi<=0 || terne.size()<=0) throw new IllegalArgumentException("wrong args in GhigliottinaPanel constructor");
		this.montepremi=montepremi;
		this.terne=terne;
		this.k=0;
		correctItemDuplicates = new Node[terne.size()];
		formatter = NumberFormat.getCurrencyInstance();
		formatter.setMaximumFractionDigits(0);
		//
		VBox vbox = new VBox();
		ghigliottina = new Button("Ghigliottina!");
		ghigliottina.setStyle("-fx-background-color: red;");
		ghigliottina.setOnAction(this::onGhigliottina);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(new Label(""), ghigliottina, new Label(""));	
		//
		tg= new ToggleGroup();
		first  = new RadioButton("Prima parola   ");
		second = new RadioButton("Seconda parola");
		first.setToggleGroup(tg);
		second.setToggleGroup(tg);
		// 
		HBox innerHBox = new HBox();
		innerHBox .setAlignment(Pos.CENTER);
		innerHBox.getChildren().addAll(first,second);
		vbox.getChildren().add(innerHBox);
		this.setTop(vbox);
		//
		rightPane = new Pane();
		rightPane.setPrefWidth(150);
		rightPane.setStyle("-fx-background-color: white;");
		this.setRight(rightPane);
		//
		reload(this.terne.get(0));
	}

	private void onGhigliottina(ActionEvent e) {
		Toggle culprit = tg.getSelectedToggle();
		if (culprit==null) return;
		selectedItem = (culprit==first) ? item1 : (culprit==second) ? item2 : null;
		int discriminator = selectedItem==correctItem ? 0 : 1;
		//
		SequentialTransition sq = new SequentialTransition();
		sq.setAutoReverse(false);
		sq.setCycleCount(0);
		sq.getChildren().addAll(
				guillotineAnimation(selectedItem,   discriminator),
				correctItemAnimation(correctItem, 1-discriminator),
				wrongItemAnimation(wrongItem)
		);
		sq.setOnFinished(ev -> {
			rightPane.getChildren().addAll(correctItemDuplicates[k]);
			if (k>=terne.size()-1) {
				ghigliottina.setDisable(true);
				ghigliottina.setStyle("-fx-background-color: grey;");
			}
			else {
				k++;
				reload(this.terne.get(k));
			}
		});
		sq.play();
	}
	
	private PathTransition correctItemAnimation(Node item,int correctAnswer) {
		// spostare parola corretta al centro
		Path finalPath = new Path();
		finalPath.getElements().add(new MoveTo(60,65+165*correctAnswer));
		finalPath.getElements().add(new LineTo(260,65+k*40)); // +k*40
		PathTransition finalPathTransition = new PathTransition();
		finalPathTransition.setDuration(Duration.millis(400));
		finalPathTransition.setPath(finalPath);
		finalPathTransition.setNode(item);
		finalPathTransition.setCycleCount(1);
		finalPathTransition.setAutoReverse(false);
		//finalPathTransition.setOnFinished(e2 -> System.out.println("correct item moved"));
		return finalPathTransition;
	}
	
	private PathTransition wrongItemAnimation(Node item) {
		// spostare parola sbagliata fuori campo
		Path goingOutPath = new Path();
		goingOutPath.getElements().add(new MoveTo(100,100));
		goingOutPath.getElements().add(new LineTo(100,100));
		PathTransition moveOutOfScreenTransition = new PathTransition();
		moveOutOfScreenTransition.setDuration(Duration.millis(400));
		moveOutOfScreenTransition.setPath(goingOutPath);
		moveOutOfScreenTransition.setNode(item);
		moveOutOfScreenTransition.setCycleCount(1);
		moveOutOfScreenTransition.setAutoReverse(false);
		//moveOutOfScreenTransition.setOnFinished(e3 -> System.out.println("wrong item moved out of screen"));
		return moveOutOfScreenTransition;
	}

	private PathTransition guillotineAnimation(Node item, int wrongAnswer) {
		Path path = new Path();
		path.getElements().add(new MoveTo(60,65));
		path.getElements().add(new LineTo(60,220+85*wrongAnswer));
		//
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(400));
		pathTransition.setPath(path);
		pathTransition.setNode(item);
		pathTransition.setCycleCount(1);
		pathTransition.setAutoReverse(true);
		pathTransition.setOnFinished(event -> { 
			montepremi = selectedItem==correctItem ? montepremi : montepremi/2;
			itemVincita = createItem(10, 240, montepremiAsString(), Color.LIGHTGREEN);
			((Pane) this.getLeft()).getChildren().add(itemVincita);
		});
		return pathTransition;
	}
	
	private void reload(Terna terna) {
		item1 = createItem(10, 50, terna.getWord1(), Color.YELLOW);
		item2 = createItem(10, 90, terna.getWord2(), Color.YELLOW);
		itemVincita = createItem(10, 240, montepremiAsString(), Color.LIGHTGREEN);
		//
		correctItem = terna.getCorrect().equals(Esatta.FIRST) ? item1 : item2;
		wrongItem   = terna.getCorrect().equals(Esatta.FIRST) ? item2 : item1;
		correctItemDuplicates[k] = createItem(10, 50+40*k, terna.getCorrectWord(), Color.YELLOW);
		Pane leftPane = new Pane();
		leftPane.setPrefWidth(200);
		leftPane.getChildren().addAll(item1,item2,itemVincita);
		this.setLeft(leftPane);
	}
	
	private Node createItem(int a, int b, String text, Color color) {
		Group group = new Group();
		//
		Rectangle boundingRect = new Rectangle(a, b, 100, 30);
		boundingRect.setArcHeight(10);
		boundingRect.setArcWidth(10);
		boundingRect.setFill(color);
		boundingRect.setStroke(Color.BLACK);
		//
		Text innerText = new Text(a+10,b+20,text);
		innerText.setFont(Font.font(null, FontWeight.BOLD, 12));
		//
		group.getChildren().addAll(boundingRect,innerText);
        return group;
    }
	
	public int getMontepremi() {
		return this.montepremi;
	}

	public String montepremiAsString() {
		return formatter.format(montepremi);
	}
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

}
