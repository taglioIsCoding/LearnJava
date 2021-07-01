package rfd.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import rfd.model.RailwayLine;
import rfd.model.Route;
import rfd.model.Segment;
import rfd.model.Station;

public class RouteTest {

	@Test
	void testOK() {
		RailwayLine line = new RailwayLine(Map.of(
				"Alpha", new Station("Alpha",102.20, 80),
				"Beta", new Station("Beta",122.41, 100),
				"Gamma", new Station("Gamma",126.20, 100),
				"Delta", new Station("Delta", 226.21, 130)
				),
				new TreeSet<>(Set.of("Beta")
				));
		Segment s1 = new Segment(line,new Station("Alpha",102.20, 80), new Station("Beta", 122.41, 100));
		Segment s2 = new Segment(line,new Station("Beta", 122.41, 100),new Station("Gamma",126.20, 80));
		Route rABC = new Route();
		rABC.add(s1); rABC.add(s2);
		assertEquals(24.00, rABC.getLength(), 0.001);
		Segment s3 = new Segment(line,new Station("Gamma",126.20, 80), new Station("Delta", 226.21, 130));
		rABC.add(s3);
		assertEquals(124.01, rABC.getLength(), 0.001);
	}
	
	@Test
	void testOK2() {
		Route rABC = new Route();
		assertEquals(0, rABC.getLength(), 0.001);
	}
	
	@Test
	void testOK3() {
		RailwayLine line = new RailwayLine(Map.of(
				"Alpha", new Station("Alpha",102.20, 80),
				"Beta", new Station("Beta",122.41, 100),
				"Gamma", new Station("Gamma",126.20, 100),
				"Delta", new Station("Delta", 226.21, 130)
				),
				new TreeSet<>(Set.of("Beta")
				));
		Segment s1 = new Segment(line,new Station("Alpha", 102.20, 80), new Station("Beta",  122.41, 100));
		Segment s2 = new Segment(line,new Station("Beta",  122.41, 100),new Station("Gamma", 126.20, 80));
		Route rABC = new Route(s1);
		assertEquals(20.21, rABC.getLength(), 0.001);
		rABC.add(s2);
		assertEquals(24.00, rABC.getLength(), 0.001);
	}
	
	@Test
	void testOK4() {
		RailwayLine line = new RailwayLine(Map.of(
				"Alpha", new Station("Alpha",102.20, 80),
				"Beta", new Station("Beta",122.41, 100),
				"Gamma", new Station("Gamma",126.20, 100),
				"Delta", new Station("Delta", 226.21, 130)
				),
				new TreeSet<>(Set.of("Beta")
				));
		Segment s1 = new Segment(line,new Station("Alpha", 102.20, 80), new Station("Beta",  122.41, 100));
		Segment s2 = new Segment(line,new Station("Beta",  122.41, 100),new Station("Gamma", 126.20, 80));
		Segment s3 = new Segment(line,new Station("Gamma", 126.20, 80), new Station("Delta", 226.21, 130));
		Route rABC = new Route(s1,s2,s3);
		assertEquals(124.01, rABC.getLength(), 0.001);
	}
	
	@Test
	void testK0_notAdjacent() {
		RailwayLine line = new RailwayLine(Map.of(
				"Alpha", new Station("Alpha",102.20, 80),
				"Beta", new Station("Beta",122.41, 100),
				"Gamma", new Station("Gamma",126.20, 100),
				"Delta", new Station("Delta", 226.21, 130)
				),
				new TreeSet<>(Set.of("Beta")
				));
		Segment s1 = new Segment(line,new Station("Alpha", 102.20, 80), new Station("Beta",  122.41, 100));
		Segment s3 = new Segment(line,new Station("Gamma", 126.20, 80), new Station("Delta", 226.21, 130));
		Route rABC = new Route();
		rABC.add(s1); 
		assertEquals(20.210, rABC.getLength(), 0.001);
		assertThrows(IllegalArgumentException.class, () -> rABC.add(s3));
	}

}
