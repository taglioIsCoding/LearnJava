package flightTracker.test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import flightTracker.model.Flight;
import flightTracker.model.FlightPos;

public class FlightTest {
	private List<FlightPos> tracking;
	
	@BeforeEach
	void setup() {
		tracking = List.of(
				new FlightPos(ZonedDateTime.parse("2019-05-10T10:54:39Z"), 45.661972, 8.726303,  1975, 183, 356),
				new FlightPos(ZonedDateTime.parse("2019-05-10T10:57:06Z"), 45.715649, 8.608337,  6300, 241, 254),
				new FlightPos(ZonedDateTime.parse("2019-05-10T11:01:05Z"), 45.613094, 8.218460, 16375, 292, 286),
				new FlightPos(ZonedDateTime.parse("2019-05-10T11:07:28Z"), 45.980347, 7.524094, 27100, 371, 308),
				new FlightPos(ZonedDateTime.parse("2019-05-10T11:16:06Z"), 46.567741, 6.554237, 36000, 375, 310)
				);
	}

	@Test
	void testOK() {
		Flight flight = new Flight("Dent001", tracking);
		assertEquals("Dent001", flight.getId());
		assertEquals(tracking, flight.getPositions());
		assertEquals(Duration.ofMinutes(21).plusSeconds(27), flight.getDuration());
	}
	
	@Test
	void testIllegalArg1A() {
		assertThrows(IllegalArgumentException.class, () -> new Flight("", tracking) );
	}

	@Test
	void testIllegalArg1B() {
		assertThrows(IllegalArgumentException.class, () -> new Flight(null, tracking) );
	}

	@Test
	void testIllegalArg2A() {
		assertThrows(IllegalArgumentException.class, () -> new Flight("Dent001", List.of()) );
	}

	@Test
	void testIllegalArg2B() {
		assertThrows(IllegalArgumentException.class, () -> new Flight("Dent001", null) );
	}
	
}

