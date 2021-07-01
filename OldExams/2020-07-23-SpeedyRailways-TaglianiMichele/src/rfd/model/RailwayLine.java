package rfd.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

public class RailwayLine {
	private SortedMap<String,Station> map;
	private SortedSet<String> hubs;
	
	public RailwayLine(Map<String,Station> map, SortedSet<String> hubs){
		this.map=new TreeMap<>(map);
		this.hubs=hubs;
	}
	
	public Optional<Station> getStation(String name) {
		return Optional.ofNullable(name==null? null : map.get(name));
	}
	
	public OptionalDouble getDistance(String name1, String name2){
		if(name1==null || name2==null) return OptionalDouble.empty();
		if (name1.equals(name2)) return OptionalDouble.empty();
		Optional<Station> poi1 = this.getStation(name1);
		Optional<Station> poi2 = this.getStation(name2);
		if (!poi1.isPresent() || !poi2.isPresent()) return OptionalDouble.empty();
		double km1 = this.getStation(name1).get().getKm();
		double km2 = this.getStation(name2).get().getKm();
		return OptionalDouble.of(Math.abs(km1-km2));
	}
	
	public Optional<Segment> getSegment(String name1, String name2){
		if(name1==null || name2==null) return Optional.empty();
		if (name1.equals(name2)) return Optional.empty();
		Optional<Station> poi1 = this.getStation(name1);
		Optional<Station> poi2 = this.getStation(name2);
		if (!poi1.isPresent() || !poi2.isPresent()) return Optional.empty();
		return Optional.of(new Segment(this,poi1.get(), poi2.get()));
	}
	
	public List<String> getStationNames(){
		return new ArrayList<>(this.map.keySet());
	}

	public List<Station> getStations(){
		List<Station> stations = new ArrayList<>(this.map.values());
		stations.sort(Comparator.comparingDouble(Station::getKm));
		return stations;
	}
	
	public SortedSet<String> getHubNames(){
		return hubs;
	}

	
	@Override
	public String toString() {
		return this.map.toString() + " - hubs: " + this.hubs.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hubs == null) ? 0 : hubs.hashCode());
		result = prime * result + ((map == null) ? 0 : map.hashCode());
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
		RailwayLine other = (RailwayLine) obj;
		if (hubs == null) {
			if (other.hubs != null)
				return false;
		} else if (!hubs.equals(other.hubs))
			return false;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		return true;
	}
	
}
