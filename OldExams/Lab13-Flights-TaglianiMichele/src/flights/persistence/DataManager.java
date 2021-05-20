package flights.persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import flights.model.Aircraft;
import flights.model.Airport;
import flights.model.City;
import flights.model.FlightSchedule;

public class DataManager {
	
	private HashMap<String, Aircraft> aircraftMap = new HashMap<String, Aircraft>();
	private AircraftsReader aircraftReader;
	private HashMap<String, Airport> airportMap = new HashMap<String, Airport>();
	private CitiesReader citiesReader;
	private FlightScheduleReader flightScheduleReader;
	private Collection<FlightSchedule> flightSchedule = new ArrayList<FlightSchedule>();
	
	
	public DataManager( CitiesReader citiesReader, AircraftsReader aircraftReader,
			FlightScheduleReader flightScheduleReader) {
		this.aircraftReader = aircraftReader;
		this.citiesReader = citiesReader;
		this.flightScheduleReader = flightScheduleReader;
	}
	
	public void readAll() throws IOException, BadFileFormatException{
		Reader readerCit = null, readerAir = null, readerFli= null;
		try {
			readerCit = new FileReader("Cities.txt");
			readerAir = new FileReader("Aircrafts.txt");
			readerFli = new FileReader("FlightSchedule.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		try {
			ArrayList<City> cittArray = new ArrayList<City>(citiesReader.read(readerCit));
			for(City citt: cittArray) {
				for(Airport air: citt.getAirports()) {
					this.airportMap.put(air.getCode(), air);
				}
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ArrayList<Aircraft> aircrafArr = new ArrayList<Aircraft>(aircraftReader.read(readerAir));
				for(Aircraft air: aircrafArr) {
					this.aircraftMap.put(air.getCode(), air);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.flightSchedule = flightScheduleReader.read(readerFli, airportMap, aircraftMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Map<String, Aircraft> getAircraftMap() {
		return aircraftMap;
	}
	public Map<String, Airport> getAirportMap() {
		return airportMap;
	}
	public Collection<FlightSchedule> getFlightSchedules() {
		return flightSchedule;
	}
	
	
	
	
}
