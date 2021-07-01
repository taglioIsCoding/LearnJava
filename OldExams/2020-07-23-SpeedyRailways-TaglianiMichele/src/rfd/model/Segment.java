package rfd.model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Segment {
	private Station from, to;
	private NumberFormat formatter;
	private RailwayLine line;

	public Segment(RailwayLine line, Station from, Station to) {
		if (!line.getStation(from.getStationName()).isPresent() || !line.getStation(to.getStationName()).isPresent()) {
			throw new IllegalArgumentException("one or both stations " + from + ", "+ to + " do not belong to line " + line);
		}
		this.line = line;
		this.from = from;
		this.to = to;
		formatter = NumberFormat.getNumberInstance();
		formatter.setMaximumFractionDigits(2);
	}

	@Override
	public String toString() {
		return "Da " + from.getStationName() + " a " + to.getStationName() +", km " + formatter.format(this.getLength());
	}

	public RailwayLine getLine() {
		return line;
	}

	public Station getFrom() {
		return from;
	}

	public Station getTo() {
		return to;
	}
	
	public double getLength(){
		return Math.abs(to.getKm()-from.getKm());
	}
	
	public int getSpeed() {
		return from.getSpeed();
	}
	
	public Segment normalize() {
		if (this.getFrom().getKm()<this.getTo().getKm()) return this;
		else return new Segment(line,to,from);
	}
	
	public List<Segment> split(){
		Segment normalized = this.normalize();
		List<Segment> result = new ArrayList<>();
		boolean inside = false;
		List<Station> stations = normalized.getLine().getStations();
		Station previous = null;
		for(Station station : stations) {
			if(station.equals(normalized.getFrom())) {
				inside=true; 
				previous = station;
			}
			//System.out.println("checking " + station + ",previous = " + previous + ", inside=" + inside);
			if (inside && !station.equals(previous)) {
				result.add(new Segment(normalized.getLine(), previous, station));
			}
			if(station.equals(normalized.getTo())) inside=false;
			previous=station;
		} 
		if (!normalized.equals(this)) Collections.reverse(result);
		return result;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((line == null) ? 0 : line.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Segment other = (Segment) obj;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (line == null) {
			if (other.line != null)
				return false;
		} else if (!line.equals(other.line))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}
	
}
