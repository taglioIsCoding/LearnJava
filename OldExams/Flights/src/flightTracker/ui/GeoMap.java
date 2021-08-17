package flightTracker.ui;

import javafx.scene.image.Image;

public class GeoMap {

	private Image image;	
	private float minLongitude, maxLongitude, minLatitude, maxLatitude, fattoreCorrettivoMercatore;

	public GeoMap(Image image, float minLongitude, float maxLongitude, float minLatitude, float maxLatitude, float fattoreCorrettivoMercatore){
		this.image = image;
		this.minLongitude=minLongitude;
		this.maxLongitude=maxLongitude;
		this.minLatitude=minLatitude;
		this.maxLatitude=maxLatitude;
		this.fattoreCorrettivoMercatore=fattoreCorrettivoMercatore;
	}

	public Image getImage() {
		return image;
	}

	public float getMinLongitude() {
		return minLongitude;
	}

	public float getMaxLongitude() {
		return maxLongitude;
	}

	public float getMinLatitude() {
		return minLatitude;
	}

	public float getMaxLatitude() {
		return maxLatitude;
	}
	
	public float getFattoreCorrettivoMercatore(){
		return fattoreCorrettivoMercatore;
	}
	
}
