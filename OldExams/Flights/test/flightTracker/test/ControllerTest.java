package flightTracker.test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.StringReader;
import java.time.Duration;
import java.util.List;


import org.junit.jupiter.api.Test;
import flightTracker.model.Flight;
import flightTracker.model.Point;
import flightTracker.persistence.BadFileFormatException;
import flightTracker.ui.controller.Controller;
import flightTracker.ui.controller.MyController;


public class ControllerTest {
	
	private String[] filenames = { 
				"AF1228_20190511.csv","AF1229_20190511.csv", "AZ604_20190510.csv", "AZ604_20190511.csv", "AZ605_20190511.csv", "AZ605_20190512.csv" };



	
	@Test
	public void testCtorOK() throws IOException, BadFileFormatException {
		new MyController(filenames); // check che non esploda
	}
	
	
	
	@Test
	public void testCtorKO_NullArray() throws IOException, BadFileFormatException {
		assertThrows(IllegalArgumentException.class, () -> new MyController( null));
	}

	@Test
	public void testCtorKO_EmptyArray() throws IOException, BadFileFormatException {
		assertThrows(IllegalArgumentException.class, () -> new MyController( new String[0]));
	}

	@Test
	public void testOK() throws IOException, BadFileFormatException {
		Controller ctrl = new MyController(filenames);
		Flight flight = ctrl.load("Dent003", new BufferedReader(new StringReader("UTC;Position;Altitude;Speed;Direction\r\n" + 
				"2019-05-11T05:15:47Z;49.02066,2.571415;0;17;275\r\n" + 
				"2019-05-11T05:16:12Z;49.022205,2.570638;0;32;354\r\n" + 
				"2019-05-11T05:16:19Z;49.022461,2.570607;0;19;354\r\n" + 
				"2019-05-11T05:17:45Z;49.022659,2.570584;0;0;314\r\n" + 
				"2019-05-11T05:20:15Z;49.023651,2.569216;0;0;318\r\n" + 
				"2019-05-11T05:21:11Z;49.023148,2.56014;0;105;264\r\n" + 
				"2019-05-11T05:21:22Z;49.022556,2.548955;0;145;264\r\n" + 
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;142;265")));		
		assertEquals("Dent003", flight.getId());
		assertEquals(Duration.ofMinutes(6).minusSeconds(16), flight.getDuration());
		List<Point> points = ctrl.getPoints(flight);
		assertEquals(points.get(0), new Point(2.571415F,49.02066F));
		assertEquals(points.get(1), new Point(2.570638F,49.022205F));
		assertEquals(points.get(2), new Point(2.570607F,49.02246F));
		assertEquals(points.get(3), new Point(2.570584F,49.02266F));
		assertEquals(points.get(4), new Point(2.569216F,49.02365F));
		assertEquals(points.get(5), new Point(2.560140F,49.023148F));
		assertEquals(points.get(6), new Point(2.548955F,49.022556F));
		assertEquals(points.get(7), new Point(2.539721F,49.022020F));
	}
	
	@Test
	public void testKO_BadHeader() throws IOException, BadFileFormatException {
		Controller ctrl = new MyController(filenames);
		assertThrows(BadFileFormatException.class, () -> 
			ctrl.load("Dent003", new BufferedReader(new StringReader("UTC;xxxx;Altitude;Speed;Direction\r\n" + 
				"2019-05-11T05:15:47Z;49.02066,2.571415;0;17;275\r\n" + 
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;142;265")))  );		
	}
	
	@Test
	public void testKO_BadRow_UTC() throws IOException, BadFileFormatException {
		Controller ctrl = new MyController(filenames);
		assertThrows(BadFileFormatException.class, () -> 
			ctrl.load("Dent003", new BufferedReader(new StringReader("UTC;Position;Altitude;Speed;Direction\r\n" + 
				"2019-05-11T05:15:47ZULU;49.02066,2.571415;0;17;275\r\n" + 
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;142;265")))  );		
	}
	
	@Test
	public void testKO_BadRow_Pos() throws IOException, BadFileFormatException {
		Controller ctrl = new MyController( filenames);
		assertThrows(BadFileFormatException.class, () -> 
			ctrl.load("Dent003", new BufferedReader(new StringReader("UTC;Position;Altitude;Speed;Direction\r\n" + 
				"2019-05-11T05:15:47Z;49UUU02066,2.571415;0;17;275\r\n" + 
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;142;265")))  );		
	}
	
	@Test
	public void testKO_BadRow_Altitude() throws IOException, BadFileFormatException {
		Controller ctrl = new MyController( filenames);
		assertThrows(BadFileFormatException.class, () -> 
			ctrl.load("Dent003", new BufferedReader(new StringReader("UTC;Position;Altitude;Speed;Direction\r\n" + 
				"2019-05-11T05:15:47Z;49.02066,2.571415;eehhh;17;275\r\n" + 
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;142;265")))  );		
	}
	
	
	@Test
	public void testKO_BadRow_Speed() throws IOException, BadFileFormatException {
		Controller ctrl = new MyController(filenames);
		assertThrows(BadFileFormatException.class, () -> 
			ctrl.load("Dent003", new BufferedReader(new StringReader("UTC;Position;Altitude;Speed;Direction\r\n" + 
				"2019-05-11T05:15:47Z;49.02066,2.571415;0;prrrr;275\r\n" + 
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;142;265")))  );		
	}
	
	
	@Test
	public void testKO_BadRow_Direction() throws IOException, BadFileFormatException {
		Controller ctrl = new MyController(filenames);
		assertThrows(BadFileFormatException.class, () -> 
			ctrl.load("Dent003", new BufferedReader(new StringReader("UTC;Position;Altitude;Speed;Direction\r\n" + 
				"2019-05-11T05:15:47Z;49.02066,2.571415;0;17;275\r\n" + 
				"2019-05-11T05:21:31Z;49.022018,2.539721;775;142;gooo")))  );		
	}
}

