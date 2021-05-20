package flights.controller;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import flights.model.Airport;
import flights.model.FlightSchedule;
import flights.persistence.BadFileFormatException;
import flights.persistence.DataManager;
import javafx.collections.transformation.SortedList;

public class MyController implements Controller{
	
	private DataManager dataManager;
	private List<Airport> sortedAirports = new ArrayList<Airport>(); 
	
	
	public MyController(DataManager dataManager) {
		this.dataManager = dataManager;
		ArrayList<Airport> airportList = new ArrayList<Airport>(this.dataManager.getAirportMap().values());
		airportList.sort(new Comparator<Airport>(){

			@Override
			public int compare(Airport o1, Airport o2) {
				if(o1.getCity().getName().compareTo(o2.getCity().getName()) > 0) {
					return 1;
				}else if(o1.getCity().getName().compareTo(o2.getCity().getName()) < 0) {
					return -1;
				}else {
					if(o1.getName().compareTo(o2.getName()) > 0) {
						return 1;
					}else if(o1.getName().compareTo(o2.getName()) < 0) {
						return -1;
				}}
					
				return 0;
			}
		});
		
		this.sortedAirports = airportList;
	}

	@Override
	public List<Airport> getSortedAirports() {
		return this.sortedAirports;
	}

	@Override
	public List<FlightSchedule> searchFlights(Airport departureAirport, Airport arrivalAirport, LocalDate date) {
		
		ArrayList<FlightSchedule> toFind= new ArrayList<FlightSchedule>();
		for(FlightSchedule fly: this.dataManager.getFlightSchedules()) {
			
			if(fly.getDepartureAirport().equals(departureAirport) && fly.getArrivalAirport().equals(arrivalAirport)
					&& fly.getDaysOfWeek().contains(date.getDayOfWeek())) {
				toFind.add(fly);
			}
		}
		return toFind;
	}

}
