package rfd.controller;

import java.time.Duration;
import java.util.List;

import rfd.model.Route;

public interface Controller {
	List<String> getStationNames();
	List<Route> getRoutes(String from, String to);
	Duration getRouteDuration(Route route);
}
