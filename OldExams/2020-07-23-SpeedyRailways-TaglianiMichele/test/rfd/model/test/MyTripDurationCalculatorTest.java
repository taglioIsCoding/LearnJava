package rfd.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import rfd.model.MyTripDurationCalculator;
import rfd.model.RailwayLine;
import rfd.model.Route;
import rfd.model.Segment;
import rfd.model.Station;
import rfd.model.TripDurationCalculator;

public class MyTripDurationCalculatorTest {

	@Test
	void testOK1() {
		RailwayLine lineaBoLe = new RailwayLine(Map.of(
				"Santarcangelo di Romagna", new Station("Santarcangelo di Romagna", 101.27, 120),
				"RiminiFiera", new Station("RiminiFiera", 106.97, 120),
				"Bologna Centrale", new Station("Bologna Centrale", 0, 180),
				"Giulianova", new Station("Giulianova", 312.36, 120)
				),
				new TreeSet<>(Set.of("Bologna Centrale")
				));
		RailwayLine lineaBoMi = new RailwayLine(Map.of(
				"Modena", new Station("Modena", 36.93, 160),
				"Reggio Emilia", new Station("Reggio Emilia", 61.44, 160),
				"Parma", new Station("Parma", 89.74, 160),
				"Bologna Centrale", new Station("Bologna Centrale", 0.0, 160),
				"Piacenza", new Station("Piacenza", 146.82, 160)
				),
				new TreeSet<>(Set.of("Bologna Centrale", "Parma", "Modena")
				));
		Route route = new Route(
				new Segment(lineaBoMi, new Station("Modena", 36.93, 160), new Station("Bologna Centrale", 0.0, 160)),
				new Segment(lineaBoLe, new Station("Bologna Centrale", 0.0, 180), new Station("Giulianova", 312.36, 120))
				);
		TripDurationCalculator calculator = new MyTripDurationCalculator();
		assertEquals(Duration.ofMinutes(153), calculator.getDuration(route));
	}
	
	@Test
	void testOK2() {
		RailwayLine lineaBoLe = new RailwayLine(Map.of(
				"Santarcangelo di Romagna", new Station("Santarcangelo di Romagna", 101.27, 120),
				"RiminiFiera", new Station("RiminiFiera", 106.97, 120),
				"Bologna Centrale", new Station("Bologna Centrale", 0, 180),
				"Giulianova", new Station("Giulianova", 312.36, 120)
				),
				new TreeSet<>(Set.of("Bologna Centrale")
				));
		RailwayLine lineaBoMi = new RailwayLine(Map.of(
				"Modena", new Station("Modena", 36.93, 160),
				"Reggio Emilia", new Station("Reggio Emilia", 61.44, 160),
				"Parma", new Station("Parma", 89.74, 160),
				"Bologna Centrale", new Station("Bologna Centrale", 0.0, 160),
				"Piacenza", new Station("Piacenza", 146.82, 160)
				),
				new TreeSet<>(Set.of("Bologna Centrale", "Parma", "Modena")
				));
		Route route = new Route(
				new Segment(lineaBoLe, new Station("Giulianova", 312.36, 120), new Station("Bologna Centrale", 0.0, 180)),
				new Segment(lineaBoMi, new Station("Bologna Centrale", 0.0, 160), new Station("Modena", 36.93, 160))
				);
		TripDurationCalculator calculator = new MyTripDurationCalculator();
		assertEquals(Duration.ofMinutes(153), calculator.getDuration(route));
	}
	
	@Test
	void testOK3() {
		RailwayLine lineaBoLe = new RailwayLine(Map.of(
				"Santarcangelo di Romagna", new Station("Santarcangelo di Romagna", 101.27, 120),
				"RiminiFiera", new Station("RiminiFiera", 106.97, 120),
				"Bologna Centrale", new Station("Bologna Centrale", 0, 180),
				"Giulianova", new Station("Giulianova", 312.36, 120)
				),
				new TreeSet<>(Set.of("Bologna Centrale")
				));
		RailwayLine lineaBoMi = new RailwayLine(Map.of(
				"Modena", new Station("Modena", 36.93, 160),
				"Reggio Emilia", new Station("Reggio Emilia", 61.44, 160),
				"Parma", new Station("Parma", 89.74, 160),
				"Bologna Centrale", new Station("Bologna Centrale", 0.0, 160),
				"Piacenza", new Station("Piacenza", 146.82, 160)
				),
				new TreeSet<>(Set.of("Bologna Centrale", "Parma", "Modena")
				));

		Route route = new Route(
				new Segment(lineaBoLe, new Station("Giulianova", 312.36, 120), new Station("Bologna Centrale", 0.0, 180)),
				new Segment(lineaBoMi, new Station("Bologna Centrale", 0.0, 60), new Station("Modena", 36.93, 160)),
				new Segment(lineaBoMi, new Station("Modena", 36.93, 160), new Station("Reggio Emilia", 61.44, 160))
				);
		TripDurationCalculator calculator = new MyTripDurationCalculator();
		assertEquals(Duration.ofMinutes(148), calculator.getDuration(route));
	}

}
