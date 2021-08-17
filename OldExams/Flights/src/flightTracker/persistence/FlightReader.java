package flightTracker.persistence;
import java.io.*;

import flightTracker.model.Flight;

public interface FlightReader {
	public Flight readFlight(String id, BufferedReader reader) throws IOException, BadFileFormatException;
	public static FlightReader of() {return new MyFlightReader();}
}
