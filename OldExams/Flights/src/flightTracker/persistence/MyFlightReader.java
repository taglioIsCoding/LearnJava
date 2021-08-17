package flightTracker.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import flightTracker.model.Flight;
import flightTracker.model.FlightPos;

public class MyFlightReader implements FlightReader{
	
	public MyFlightReader() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Flight readFlight(String id, BufferedReader reader) throws IOException, BadFileFormatException {
		if(reader == null) {
			throw new IOException("Reader null");
		}
		
		List<FlightPos> tracks = new ArrayList<>();
		
		String line = reader.readLine();
		StringTokenizer firstLineTok = new StringTokenizer(line, ";");
		
		if(firstLineTok.countTokens() != 5) {
			throw new BadFileFormatException("Intestazione non valida");
		}
		
		if(!firstLineTok.nextToken().equals("UTC")) {
			throw new BadFileFormatException("UTC non valida");
		}
		if(!firstLineTok.nextToken().equals("Position")) {
			throw new BadFileFormatException("Position non valida");
		}
		if(!firstLineTok.nextToken().equals("Altitude")) {
			throw new BadFileFormatException("Altitude non valida");
		}
		if(!firstLineTok.nextToken().equals("Speed")) {
			throw new BadFileFormatException("Speed non valida");
		}
		if(!firstLineTok.nextToken().equals("Direction")) {
			throw new BadFileFormatException("Direction non valida");
		}
		
		while((line = reader.readLine())!= null) {
			StringTokenizer lineTok = new StringTokenizer(line, ";");
			if(lineTok.countTokens() != 5) {
				throw new BadFileFormatException("non abbaztanza elementi");
			}
			
			String utcString = lineTok.nextToken().trim();
			ZonedDateTime time;
			try {
				time = ZonedDateTime.parse(utcString);
			} catch (Exception e) {
				throw new BadFileFormatException("Utc non valido");
			}
			
			String latLongString= lineTok.nextToken();
			String latitudeString = latLongString.split(",")[0];
			String longitudeString = latLongString.split(",")[1];
			double latitude;
			double longitude;
			
			try {
				latitude = Double.parseDouble(latitudeString);
				longitude = Double.parseDouble(longitudeString);
			} catch (Exception e) {
				throw new BadFileFormatException("Pos non valido");
			}
			
			String altitudeString = lineTok.nextToken();
			double altitude;
			try {
				altitude = Double.parseDouble(altitudeString);
			} catch (Exception e) {
				throw new BadFileFormatException("Altitude non valido");
			}
			
			String speedString = lineTok.nextToken();
			double speed;
			try {
				speed = Double.parseDouble(speedString);
			} catch (Exception e) {
				throw new BadFileFormatException("speed non valido");
			}
			
			String direction = lineTok.nextToken();
			int dir;
			try {
				dir = Integer.parseInt(direction);
			} catch (Exception e) {
				throw new BadFileFormatException("direction non valido");
			}
			
			tracks.add(new FlightPos(time, latitude, longitude, altitude, speed, dir));
			
		}
		
		
		return new Flight(id, tracks);
	}

}
