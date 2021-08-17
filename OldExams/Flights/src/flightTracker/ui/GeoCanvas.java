package flightTracker.ui;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import java.util.*;

import flightTracker.model.Point;

public class GeoCanvas extends Canvas {

	private Image background;
	private int xAxisMin=-125, xAxisMax=28, yAxisMin=26, yAxisMax=61;
    private int larghezza, altezza;
	private float fattoreDiScalaX, fattoreDiScalaY;
	private GraphicsContext g;
	private GeoMap geoMap;
	private Color color = Color.BLUE;

	public GeoCanvas(GeoMap geoMap){
		super();
		this.geoMap=geoMap;
		background = geoMap.getImage();
		larghezza = (int) Math.round(background.getWidth());
		altezza = (int) Math.round(background.getHeight());
	    this.setWidth(larghezza);
		this.setHeight(altezza);
		
		fattoreDiScalaX = larghezza/((float)xAxisMax-xAxisMin);
		fattoreDiScalaY = altezza/((float)yAxisMax-yAxisMin);
		redrawMap();
	}
	
	public void drawPoint(Point point){
		float x = point.getX(), y = point.getY();
        if (x<xAxisMin || x>xAxisMax || y<yAxisMin || y>yAxisMax ) return;
        Pixel pixel = toPixel(point);
        g.strokeLine(pixel.getX(),pixel.getY(),pixel.getX(),pixel.getY());
    }

	public void drawPoints(List<Point> punti){
        g.setStroke(color);
		Point pPrev = punti.get(0);
        drawPoint(pPrev);
		for (Point p : punti) {
            drawLine(pPrev, p); 
			pPrev=p;
		}
    }

	public void drawParallels(List<Integer> parallels){
        g.setStroke(Color.RED);
		for (int parallel : parallels) {
			drawParallel(parallel);
		}
    }

	public void drawLine(Point p0, Point p1){
		float x1 = p1.getX(), y1 = p1.getY();
		float x0 = p0.getX(), y0 = p0.getY();
        if (x1<xAxisMin || x1>xAxisMax || y1<yAxisMin || y1>yAxisMax || x0<xAxisMin || x0>xAxisMax || y0<yAxisMin || y0>yAxisMax ) return;
	    Pixel pixel0 = toPixel(p0);
	    Pixel pixel1 = toPixel(p1);
        g.strokeLine(pixel0.getX(),pixel0.getY(),pixel1.getX(),pixel1.getY());
    }

	public void drawParallel(int latitude){
        if (latitude<yAxisMin || latitude>yAxisMax) return;
        int y  = (altezza-Math.round((latitude-yAxisMin)*fattoreDiScalaY*geoMap.getFattoreCorrettivoMercatore()));
        g.strokeLine(0,y, larghezza,y);
        g.fillText("" + latitude + "°N", larghezza/2+5, y-5);
    }

	private Pixel toPixel(Point p){
	    int ix = Math.round((p.getX()-xAxisMin)*fattoreDiScalaX);
        int iy = altezza-Math.round((p.getY()-yAxisMin)*fattoreDiScalaY*geoMap.getFattoreCorrettivoMercatore());
		return new Pixel(ix, iy);
	}
	
	public void redrawMap() {
		g = this.getGraphicsContext2D();
		g.drawImage(background,0,0);
		// etichette coordinate estreme
        g.setStroke(Color.BLACK);
        g.fillText(""+xAxisMin + "°W", 10,altezza-5);
        g.fillText(""+xAxisMax + "°E", larghezza-30,altezza-5);
        g.fillText(""+yAxisMax + "°N", larghezza/2+5,15);
        g.fillText(""+yAxisMin + "°N", larghezza/2+5,altezza-5);
        color = Color.BLUE;
	}
	
	public void setDrawingColor(Color color) {
		this.color=color;
	}
	 
}
