package flightTracker.test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import flightTracker.model.Flight;
import flightTracker.persistence.BadFileFormatException;
import flightTracker.persistence.FlightReader;

public class FlightReaderTest {
	
	private BufferedReader fakeRdr;

	@BeforeEach
	void setup() {
	}
	
	@Test
	public void testFlightReaderOK() throws IOException, BadFileFormatException {
		fakeRdr = new BufferedReader(new StringReader("UTC;Position;Altitude;Speed;Direction\r\n" + 
				"2019-05-11T05:15:47Z;49.02066,2.571415;0;17;275\r\n" + 
				"2019-05-11T05:16:12Z;49.022205,2.570638;0;32;354\r\n" + 
				"2019-05-11T05:16:19Z;49.022461,2.570607;0;19;354\r\n" + 
				"2019-05-11T05:17:45Z;49.022659,2.570584;0;0;314\r\n" + 
				"2019-05-11T05:20:15Z;49.023651,2.569216;0;0;318\r\n" + 
				"2019-05-11T05:21:11Z;49.023148,2.56014;0;105;264\r\n" + 
				"2019-05-11T05:21:22Z;49.022556,2.548955;0;145;264\r\n" + 
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;142;265"));		
		FlightReader fltRdr = FlightReader.of();
		Flight flight = fltRdr.readFlight("Dent002", fakeRdr);// throws IOException, BadFileFormatException
		assertEquals("Dent002", flight.getId());
		assertEquals(Duration.ofMinutes(6).minusSeconds(16), flight.getDuration());
	}
	
	@Test
	public void testFlightReaderK0_HeaderNoUTC() throws IOException, BadFileFormatException {
		fakeRdr = new BufferedReader(new StringReader("xxx;Position;Altitude;Speed;Direction\r\n" + 
				"2019-05-11T05:15:47Z;49.02066,2.571415;0;17;275\r\n" + 
				"2019-05-11T05:16:12Z;49.022205,2.570638;0;32;354\r\n" + 
				"2019-05-11T05:16:19Z;49.022461,2.570607;0;19;354\r\n" + 
				"2019-05-11T05:17:45Z;49.022659,2.570584;0;0;314\r\n" + 
				"2019-05-11T05:20:15Z;49.023651,2.569216;0;0;318\r\n" + 
				"2019-05-11T05:21:11Z;49.023148,2.56014;0;105;264\r\n" + 
				"2019-05-11T05:21:22Z;49.022556,2.548955;0;145;264\r\n" + 
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;142;265"));		
		FlightReader fltRdr = FlightReader.of();
		assertThrows(BadFileFormatException.class, () -> fltRdr.readFlight("Dent002", fakeRdr) );
	}

	@Test
	public void testFlightReaderK0_HeaderNoPosition() throws IOException, BadFileFormatException {
		fakeRdr = new BufferedReader(new StringReader("UTC;xyz;Altitude;Speed;Direction\r\n" + 
				"2019-05-11T05:15:47Z;49.02066,2.571415;0;17;275\r\n" + 
				"2019-05-11T05:16:12Z;49.022205,2.570638;0;32;354\r\n" + 
				"2019-05-11T05:16:19Z;49.022461,2.570607;0;19;354\r\n" + 
				"2019-05-11T05:17:45Z;49.022659,2.570584;0;0;314\r\n" + 
				"2019-05-11T05:20:15Z;49.023651,2.569216;0;0;318\r\n" + 
				"2019-05-11T05:21:11Z;49.023148,2.56014;0;105;264\r\n" + 
				"2019-05-11T05:21:22Z;49.022556,2.548955;0;145;264\r\n" + 
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;142;265"));		
		FlightReader fltRdr = FlightReader.of();
		assertThrows(BadFileFormatException.class, () -> fltRdr.readFlight("Dent002", fakeRdr) );
	}
	
	@Test
	public void testFlightReaderK0_HeaderNoAltitude() throws IOException, BadFileFormatException {
		fakeRdr = new BufferedReader(new StringReader("UTC;Position;zzz;Speed;Direction\r\n" + 
				"2019-05-11T05:15:47Z;49.02066,2.571415;0;17;275\r\n" + 
				"2019-05-11T05:16:12Z;49.022205,2.570638;0;32;354\r\n" + 
				"2019-05-11T05:16:19Z;49.022461,2.570607;0;19;354\r\n" + 
				"2019-05-11T05:17:45Z;49.022659,2.570584;0;0;314\r\n" + 
				"2019-05-11T05:20:15Z;49.023651,2.569216;0;0;318\r\n" + 
				"2019-05-11T05:21:11Z;49.023148,2.56014;0;105;264\r\n" + 
				"2019-05-11T05:21:22Z;49.022556,2.548955;0;145;264\r\n" + 
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;142;265"));		
		FlightReader fltRdr = FlightReader.of();
		assertThrows(BadFileFormatException.class, () -> fltRdr.readFlight("Dent002", fakeRdr) );
	}
	
	@Test
	public void testFlightReaderK0_HeaderNoSpeed() throws IOException, BadFileFormatException {
		fakeRdr = new BufferedReader(new StringReader("UTC;Position;Altitude;uuuu;Direction\r\n" + 
				"2019-05-11T05:15:47Z;49.02066,2.571415;0;17;275\r\n" + 
				"2019-05-11T05:16:12Z;49.022205,2.570638;0;32;354\r\n" + 
				"2019-05-11T05:16:19Z;49.022461,2.570607;0;19;354\r\n" + 
				"2019-05-11T05:17:45Z;49.022659,2.570584;0;0;314\r\n" + 
				"2019-05-11T05:20:15Z;49.023651,2.569216;0;0;318\r\n" + 
				"2019-05-11T05:21:11Z;49.023148,2.56014;0;105;264\r\n" + 
				"2019-05-11T05:21:22Z;49.022556,2.548955;0;145;264\r\n" + 
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;142;265"));		
		FlightReader fltRdr = FlightReader.of();
		assertThrows(BadFileFormatException.class, () -> fltRdr.readFlight("Dent002", fakeRdr) );
	}
	
	@Test
	public void testFlightReaderK0_HeaderNoDirection() throws IOException, BadFileFormatException {
		fakeRdr = new BufferedReader(new StringReader("UTC;Position;Altitude;Speed;vvvv\r\n" + 
				"2019-05-11T05:15:47Z;49.02066,2.571415;0;17;275\r\n" + 
				"2019-05-11T05:16:12Z;49.022205,2.570638;0;32;354\r\n" + 
				"2019-05-11T05:16:19Z;49.022461,2.570607;0;19;354\r\n" + 
				"2019-05-11T05:17:45Z;49.022659,2.570584;0;0;314\r\n" + 
				"2019-05-11T05:20:15Z;49.023651,2.569216;0;0;318\r\n" + 
				"2019-05-11T05:21:11Z;49.023148,2.56014;0;105;264\r\n" + 
				"2019-05-11T05:21:22Z;49.022556,2.548955;0;145;264\r\n" + 
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;142;265"));		
		FlightReader fltRdr = FlightReader.of();
		assertThrows(BadFileFormatException.class, () -> fltRdr.readFlight("Dent002", fakeRdr) );
	}

	@Test
	public void testFlightReaderK0_TrackWrongTime() throws IOException, BadFileFormatException {
		fakeRdr = new BufferedReader(new StringReader("UTC;Position;Altitude;Speed;Direction\r\n" + 
				"2019-05-11tt05:15:47Z;49.02066,2.571415;0;17;275\r\n" +  
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;142;265"));		
		FlightReader fltRdr = FlightReader.of();
		assertThrows(BadFileFormatException.class, () -> fltRdr.readFlight("Dent002", fakeRdr) );
	}

	@Test
	public void testFlightReaderK0_TrackWrongPosition1() throws IOException, BadFileFormatException {
		fakeRdr = new BufferedReader(new StringReader("UTC;Position;Altitude;Speed;Direction\r\n" + 
				"2019-05-11T05:15:47Z;A49.02066,2.571415;0;17;275\r\n" + 
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;142;265"));		
		FlightReader fltRdr = FlightReader.of();
		assertThrows(BadFileFormatException.class, () -> fltRdr.readFlight("Dent002", fakeRdr) );
	}

	@Test
	public void testFlightReaderK0_TrackWrongPosition2() throws IOException, BadFileFormatException {
		fakeRdr = new BufferedReader(new StringReader("UTC;Position;Altitude;Speed;Direction\r\n" + 
				"2019-05-11T05:15:47Z;49.02066,2.571415;0;17;275\r\n" + 
				"2019-05-11T05:21:31Z;49.022018,B2.539721;775;142;265"));		
		FlightReader fltRdr = FlightReader.of();
		assertThrows(BadFileFormatException.class, () -> fltRdr.readFlight("Dent002", fakeRdr) );
	}

	@Test
	public void testFlightReaderK0_TrackWrongAltitude() throws IOException, BadFileFormatException {
		fakeRdr = new BufferedReader(new StringReader("UTC;Position;Altitude;Speed;Direction\r\n" + 
				"2019-05-11T05:15:47Z;49.02066,2.571415;_;17;275\r\n" +  
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;142;265"));		
		FlightReader fltRdr = FlightReader.of();
		assertThrows(BadFileFormatException.class, () -> fltRdr.readFlight("Dent002", fakeRdr) );
	}
	
	@Test
	public void testFlightReaderK0_TrackWrongSpeed() throws IOException, BadFileFormatException {
		fakeRdr = new BufferedReader(new StringReader("UTC;Position;Altitude;Speed;Direction\r\n" + 
				"2019-05-11T05:15:47Z;49.02066,2.571415;0;17;275\r\n" +  
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;pippo;265"));		
		FlightReader fltRdr = FlightReader.of();
		assertThrows(BadFileFormatException.class, () -> fltRdr.readFlight("Dent002", fakeRdr) );
	}
	
	@Test
	public void testFlightReaderK0_TrackWrongDirection() throws IOException, BadFileFormatException {
		fakeRdr = new BufferedReader(new StringReader("UTC;Position;Altitude;Speed;Direction\r\n" + 
				"2019-05-11T05:15:47Z;49.02066,2.571415;0;17;275\r\n" +  
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;142;pippo"));		
		FlightReader fltRdr = FlightReader.of();
		assertThrows(BadFileFormatException.class, () -> fltRdr.readFlight("Dent002", fakeRdr) );
	}


}

