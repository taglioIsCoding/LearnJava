package minirail.ui.javafx;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import minirail.model.Line;
import minirail.model.Section;

public class TrainLinePane extends FlowPane {

	private double len;
	private final int HEIGHT = 50, MARGIN = 10;
	private GraphicsContext g;
	
	public TrainLinePane(Line line) {
		this.len = line.getLength();
		Canvas canvas = new Canvas(len+5*MARGIN,HEIGHT);
		g = canvas.getGraphicsContext2D();
		g.setFill(Color.BLACK);	g.setLineWidth(4); 
		// System.out.println("drawing line from " + (MARGIN) + " to " + (len+MARGIN));
		g.strokeLine(MARGIN+2, HEIGHT/2, len+MARGIN-2,HEIGHT/2);  // NB: the +2 offset compensates the line width 4
		g.setLineWidth(0.5);
		double accumulatedLength = MARGIN;
		for(Section section: line.getSections()) {
			// System.out.println("drawing section tick at " + (accumulatedLength));
			double sectionLength = section.getLength();
			g.strokeLine(accumulatedLength, HEIGHT/2-MARGIN, accumulatedLength, HEIGHT/2+MARGIN); 	// left section boundary
			g.strokeText(""+(accumulatedLength-MARGIN), accumulatedLength-MARGIN, HEIGHT);  		// left boundary label 
			accumulatedLength += sectionLength;
		}
		// System.out.println("drawing section tick at " + (len+MARGIN));
		g.strokeLine(len+MARGIN, HEIGHT/2-MARGIN, len+MARGIN, HEIGHT/2+MARGIN); // rightest boundary
		g.strokeText(""+len, len, HEIGHT);  									// rightest boundary label
		this.getChildren().addAll(canvas);
	}
	
	public void drawTrain(double pos, double trainLen, Color color, String trainID) {
		if (pos < trainLen) {
				double delta = trainLen-pos;
				drawTrain(len, delta, color, trainID);
				drawTrain(pos, pos, color, trainID);
		}
		else {
			// NB: the +/-5 offset compensates the line width 10
			g.setStroke(color); g.setLineWidth(10);  g.strokeLine(pos-trainLen+MARGIN+5,HEIGHT/2, pos+MARGIN-5,HEIGHT/2);
			// System.err.println("drawing " + trainID + " (pos: " + pos + ", len: " + trainLen + ")" + " from " + (pos-trainLen+MARGIN+5) + " to " + (pos+MARGIN-5));
			if (getStrokenStringLength(trainID) < trainLen) {
				// NB: only drawing ID train if there is enough space inside the train segment
				g.setStroke(Color.WHITE); g.setLineWidth(0.8); g.strokeText(trainID, pos-trainLen/2-MARGIN, HEIGHT/2+5);
			}
		}
	}
	
	private double getStrokenStringLength(String s) {
		Text theText = new Text(s);
		return theText.getBoundsInLocal().getWidth();
	}
	
}
