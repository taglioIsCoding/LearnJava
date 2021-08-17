package flightTracker.model;
import java.time.*;

public class FlightPos {
	private ZonedDateTime timestamp;
	double latitude, longitude, altitude, speed;
	int direction;

	public FlightPos(ZonedDateTime timestamp, double latitude, double longitude, double altitude, double speed, int direction){
		this.timestamp = timestamp;
		this.latitude = latitude ; this.longitude = longitude ; this.altitude = altitude ; this.speed = speed ;
		this.direction  = direction  ;
	}

	public ZonedDateTime getTimestamp(){
		return timestamp;
	}

	public Point getPosition(){
		return new Point((float)longitude, (float)latitude);
	}

	public String toString(){
		return 	timestamp.toString() + timestamp.toLocalDateTime().toString() + ", " + latitude + ", " + longitude + ", " + altitude + ", " + speed + ", " + direction;
	}
}
