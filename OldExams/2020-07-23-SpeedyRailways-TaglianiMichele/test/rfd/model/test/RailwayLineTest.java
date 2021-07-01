package rfd.model.test;

import org.junit.jupiter.api.Test;

import rfd.model.RailwayLine;
import rfd.model.Segment;
import rfd.model.Station;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class RailwayLineTest {

	@Test
	public void testOK1() {
		RailwayLine lineaBoLe = new RailwayLine(Map.of(
				"Santarcangelo di Romagna", new Station("Santarcangelo di Romagna", 101.27, 120),
				"RiminiFiera", new Station("RiminiFiera", 106.97, 120),
				"Bologna Centrale", new Station("Bologna Centrale", 0, 120),
				"Giulianova", new Station("Giulianova", 312.36, 120)
				),
				new TreeSet<>(Set.of("Bologna Centrale")
				));
		
		List<String> stations = lineaBoLe.getStationNames(); // must be in alphabetical order
		assertEquals("Bologna Centrale", stations.get(0));
		assertEquals("Giulianova", 	stations.get(1));
		assertEquals("RiminiFiera", stations.get(2));
		assertEquals("Santarcangelo di Romagna", stations.get(3));
		
		assertTrue(lineaBoLe.getHubNames().contains("Bologna Centrale")); 
		
		assertEquals("Santarcangelo di Romagna", lineaBoLe.getStation("Santarcangelo di Romagna").get().getStationName());
		assertEquals(101.27, lineaBoLe.getStation("Santarcangelo di Romagna").get().getKm(), 0.001);
		
		assertEquals("RiminiFiera", lineaBoLe.getStation("RiminiFiera").get().getStationName());
		assertEquals(106.97, lineaBoLe.getStation("RiminiFiera").get().getKm(), 0.001);
		
		assertEquals("Bologna Centrale", lineaBoLe.getStation("Bologna Centrale").get().getStationName());
		assertEquals(0, lineaBoLe.getStation("Bologna Centrale").get().getKm(), 0.001);
		
		assertEquals("Giulianova", lineaBoLe.getStation("Giulianova").get().getStationName());
		assertEquals(312.36, lineaBoLe.getStation("Giulianova").get().getKm(), 0.001);

		assertEquals(106.97, lineaBoLe.getDistance("Bologna Centrale", "RiminiFiera").getAsDouble());
		assertEquals(106.97, lineaBoLe.getDistance("RiminiFiera","Bologna Centrale").getAsDouble());
		assertEquals(101.27, lineaBoLe.getDistance("Bologna Centrale", "Santarcangelo di Romagna").getAsDouble());
		assertEquals(312.36, lineaBoLe.getDistance("Giulianova","Bologna Centrale").getAsDouble());
		
		assertEquals(101.27, lineaBoLe.getDistance("Santarcangelo di Romagna","Bologna Centrale").getAsDouble());
		assertEquals(101.27, lineaBoLe.getDistance("Bologna Centrale","Santarcangelo di Romagna").getAsDouble());
		assertEquals(  5.70, lineaBoLe.getDistance("Santarcangelo di Romagna", "RiminiFiera").getAsDouble(), 0.001);
		assertEquals(  5.70, lineaBoLe.getDistance("RiminiFiera","Santarcangelo di Romagna").getAsDouble(), 0.001);
		assertEquals(211.09, lineaBoLe.getDistance("Giulianova","Santarcangelo di Romagna").getAsDouble(), 0.001);
		assertEquals(211.09, lineaBoLe.getDistance("Santarcangelo di Romagna","Giulianova").getAsDouble(), 0.001);
		
		assertEquals(new Segment(lineaBoLe, new Station("RiminiFiera", 106.97, 120), new Station("Bologna Centrale", 0.00, 120)), 
				 lineaBoLe.getSegment("RiminiFiera","Bologna Centrale").get());
		assertEquals(new Segment(lineaBoLe, new Station("Bologna Centrale", 0.00, 120),new Station("RiminiFiera", 106.97, 120)), 
				 lineaBoLe.getSegment("Bologna Centrale","RiminiFiera").get());
		assertEquals(new Segment(lineaBoLe, new Station("Giulianova", 312.36, 120), new Station("Bologna Centrale", 0.00, 120)), 
				 lineaBoLe.getSegment("Giulianova","Bologna Centrale").get());
		assertEquals(new Segment(lineaBoLe, new Station("Giulianova", 312.36, 120), new Station("Santarcangelo di Romagna", 101.27, 120)), 
				 lineaBoLe.getSegment("Giulianova","Santarcangelo di Romagna").get());
	}
	
	@Test
	public void testOK_getStations() {
		RailwayLine lineaBoLe = new RailwayLine(Map.of(
				"Santarcangelo di Romagna", new Station("Santarcangelo di Romagna", 101.27, 120),
				"RiminiFiera", new Station("RiminiFiera", 106.97, 120),
				"Bologna Centrale", new Station("Bologna Centrale", 0, 120),
				"Giulianova", new Station("Giulianova", 312.36, 120)
				),
				new TreeSet<>(Set.of("Bologna Centrale")
				));
		
		List<Station> stations = lineaBoLe.getStations();

		assertEquals(List.of(
				new Station("Bologna Centrale", 0, 120),
				new Station("Santarcangelo di Romagna", 101.27, 120),
				new Station("RiminiFiera", 106.97, 120),
				new Station("Giulianova", 312.36, 120)
				), stations);
	}
}
