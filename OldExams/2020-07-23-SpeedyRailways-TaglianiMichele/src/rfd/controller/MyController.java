package rfd.controller;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import rfd.model.MyTripDurationCalculator;
import rfd.model.RailwayLine;
import rfd.model.Route;
import rfd.model.TripDurationCalculator;
import rfd.model.routefinder.ClassicRouteFinder;
import rfd.model.routefinder.RouteFinder;

public class MyController implements Controller {
	
	private RouteFinder finder;
	private List<String> stationNames;
	private TripDurationCalculator tripCalculator;

	public MyController(Set<RailwayLine> rwylines) {
		this.finder = new ClassicRouteFinder(rwylines);
		this.stationNames = rwylines.stream()
									.flatMap(rwy -> rwy.getStationNames().stream())
									.distinct()
									.sorted()
									.collect(Collectors.toList());
		this.tripCalculator = new MyTripDurationCalculator();
	}
	
	@Override
	public List<String> getStationNames() {
		return stationNames;
	}
	
	@Override
	public List<Route> getRoutes(String from, String to){
		return finder.getRoutes(from, to).stream().sorted(Comparator.comparingDouble(Route::getLength)).collect(Collectors.toList());
	}
	
	@Override
	public Duration getRouteDuration(Route route){
		return tripCalculator.getDuration(route);
	}

}
