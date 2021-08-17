package flightTracker.model;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight {
	private String id;
	private List<FlightPos> tracks;
	
	public Flight(String id, List<FlightPos> tracks) {
		if(id == null || id.isEmpty()) {
			throw new IllegalArgumentException("Id non valido");
		}
		
		if(tracks == null || tracks.isEmpty()) {
			throw new IllegalArgumentException("track non valido");
		}
		
		this.id = id;
		this.tracks = new ArrayList<>(tracks);
	}
	
	
	public String getId() {
		return this.id;
	}
	
	public List<FlightPos> getPositions() {
		return this.tracks;
	}
	
	public Duration getDuration() {
		return Duration.between(tracks.get(0).getTimestamp(), tracks.get(tracks.size()-1).getTimestamp());
	}
	
	
}
