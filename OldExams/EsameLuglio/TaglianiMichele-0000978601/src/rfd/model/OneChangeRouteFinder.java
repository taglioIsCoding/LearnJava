package rfd.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class OneChangeRouteFinder extends RouteFinder{

	public OneChangeRouteFinder(Set<RailwayLine> rwyLines) {
		super(rwyLines);
	}
	
	@Override
	public List<Route> getRoutes(String from, String to) {
		if(from == null || to == null) {
			throw new IllegalArgumentException("Stazioni nulle");
		}
		List<Route> percoList = new ArrayList<>();
		DirectRouteFinder directRouteFinder = new DirectRouteFinder(rwylines);
		
		//System.out.println(from);
		//System.out.println(to);
		
		if(from.equalsIgnoreCase(to)){
			return Collections.emptyList();
		}
		
		
		Set<RailwayLine> stazioneFromLines = this.getLinesAtStation(from);
		
		
		List<Route> percorsoPrimaCambio = new ArrayList<>();
		List<Route> percorsoDopoCambio= new ArrayList<>();
		
		
		
		for(RailwayLine ray: stazioneFromLines) {
			if(ray.getSegment(from, to).isEmpty()) {
				for(String scambio: ray.getTransferPoints()) {
					percorsoDopoCambio = directRouteFinder.getRoutes(scambio, to);
					//System.out.println(percorsoDopoCambio);
					if(!percorsoDopoCambio.isEmpty()) {
						percorsoPrimaCambio = directRouteFinder.getRoutes(from, scambio);
//						System.out.println(percorsoPrimaCambio.get(0));
//						System.out.println(percorsoDopoCambio.get(0));
//						System.out.println("#");
						if(!percorsoPrimaCambio.isEmpty()) {
							Route route = new Route();
							route.add((percorsoPrimaCambio.get(0).getRouteSegments().get(0)));
							route.add((percorsoDopoCambio.get(0).getRouteSegments().get(0)));
							//System.out.println(route);
							percoList.add(route);
						}
						
					}
				}
				
			}
		}
		//System.out.println("--------------------");
		
		return percoList;
	}
	
	
	
	
	

}
