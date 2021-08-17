package rfd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DirectRouteFinder extends RouteFinder{

	public DirectRouteFinder(Set<RailwayLine> rwyLines) {
		super(rwyLines);
	}
	
	@Override
	public List<Route> getRoutes(String from, String to) {
		if(from == null || to == null) {
			throw new IllegalArgumentException("Stazioni nulle");
		}
		
		
		List<Route> stradeList = new ArrayList<>();
		Set<RailwayLine> stazioneFromLines = this.getLinesAtStation(from);
		
		for(RailwayLine ray: stazioneFromLines) {
			if(ray.getSegment(from, to).isPresent()) {
				stradeList.add(new Route(ray.getSegment(from, to).get()));
			}
		}
		
		
		return stradeList;
	}

}
