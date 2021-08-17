package flightTracker.ui.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import flightTracker.model.Flight;
import flightTracker.model.FlightPos;
import flightTracker.model.Point;
import flightTracker.persistence.BadFileFormatException;
import flightTracker.persistence.MyFlightReader;
import flightTracker.persistence.FlightReader;

public class MyController extends Controller{

	public MyController(String[] availableFlightFiles) {
		super(availableFlightFiles);
	}

	@Override
	public List<Point> getPoints(Flight flight) {
		List<Point> points = new ArrayList<>();
		for(FlightPos pos : flight.getPositions()) {
			points.add(pos.getPosition());
		}
		return points;
	}

	@Override
	public Flight load(String flightId, Reader reader) throws IOException, BadFileFormatException {
		FlightReader fReader = new MyFlightReader();
		BufferedReader rdReader = new BufferedReader(reader);
		
		Flight flyFlight = fReader.readFlight(flightId, rdReader);
		
		return flyFlight;
	}

}
