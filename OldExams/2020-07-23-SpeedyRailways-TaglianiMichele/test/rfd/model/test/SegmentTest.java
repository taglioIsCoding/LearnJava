package rfd.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import rfd.model.RailwayLine;
import rfd.model.Segment;
import rfd.model.Station;

public class SegmentTest {

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
		assertEquals(new Station("Alpha",102.20, 80), s1.getFrom());
		assertEquals(new Station("Beta", 122.41, 100), s1.getTo());
		Segment s2 = new Segment(line,new Station("Alpha",102.20, 80), new Station("Beta", 122.41, 100));
		assertEquals(s1,s2);
		assertEquals(20.21, s1.getLength(), 0.001);
		assertEquals(80, s1.getSpeed());
		Segment s3 = new Segment(line,new Station("Beta", 122.41, 100),new Station("Alpha",102.20, 80));
		assertEquals(20.21, s3.getLength(), 0.001);
		assertEquals(100, s3.getSpeed());
		assertNotEquals(s1,s3);
	}

	@Test
	void testNormalizeOK() {
		RailwayLine line = new RailwayLine(Map.of(
				"Alpha", new Station("Alpha",102.20, 80),
				"Beta", new Station("Beta",122.41, 100),
				"Gamma", new Station("Gamma",126.20, 100),
				"Delta", new Station("Delta", 226.21, 130)
				),
				new TreeSet<>(Set.of("Beta")
				));
		Segment s1 = new Segment(line,new Station("Alpha",102.20, 80), new Station("Beta", 122.41, 100));
		assertTrue(s1==s1.normalize());
		Segment s3 = new Segment(line,new Station("Beta", 122.41, 100),new Station("Alpha",102.20, 80));
		assertFalse(s3==s3.normalize());
		assertFalse(s3==s3.normalize().normalize());
		assertNotEquals(s3,s3.normalize().normalize());
		assertEquals(s3.normalize(),s1.normalize());
	}
	
	@Test
	void testOK_Split() {
		RailwayLine line = new RailwayLine(Map.of(
				"Alpha", new Station("Alpha",102.20, 80),
				"Beta", new Station("Beta",122.41, 100),
				"Gamma", new Station("Gamma",126.20, 100),
				"Delta", new Station("Delta", 226.21, 130)
				),
				new TreeSet<>(Set.of("Beta")
				));
		
		Segment s1 = new Segment(line,new Station("Alpha",102.20, 80), new Station("Delta", 226.21, 130));
		assertEquals(List.of(
				new Segment(line,new Station("Alpha",102.20, 80),new Station("Beta",122.41, 100)),
				new Segment(line,new Station("Beta",122.41, 100),new Station("Gamma",126.20, 100)),
				new Segment(line,new Station("Gamma",126.20, 100),new Station("Delta", 226.21, 130))				
				), s1.split());

		Segment s2 = new Segment(line,new Station("Delta", 226.21, 130),new Station("Alpha",102.20, 80));
		assertEquals(List.of(
				new Segment(line,new Station("Gamma",126.20, 100),new Station("Delta", 226.21, 130)),				
				new Segment(line,new Station("Beta",122.41, 100),new Station("Gamma",126.20, 100)),
				new Segment(line,new Station("Alpha",102.20, 80),new Station("Beta",122.41, 100))
				), s2.split());
	
		Segment s3 = new Segment(line,new Station("Alpha",102.20, 80), new Station("Beta",122.41, 100));
		assertEquals(List.of(
				new Segment(line,new Station("Alpha",102.20, 80),new Station("Beta",122.41, 100))				
				), s3.split());
		
		Segment s4 = new Segment(line,new Station("Gamma",126.20, 100),new Station("Alpha",102.20, 80));
		assertEquals(List.of(
				new Segment(line,new Station("Beta",122.41, 100),new Station("Gamma",126.20, 100)),
				new Segment(line,new Station("Alpha",102.20, 80),new Station("Beta",122.41, 100))
				), s4.split());

		Segment s5 = new Segment(line,new Station("Gamma",126.20, 100), new Station("Beta",122.41, 100));
		assertEquals(List.of(
				new Segment(line,new Station("Beta",122.41, 100),new Station("Gamma",126.20, 100))
				), s5.split());
		
		Segment s6 = new Segment(line,new Station("Gamma",126.20, 100), new Station("Gamma",126.20, 100));
		assertEquals(Collections.emptyList(), s6.split());
	}
	
	@Test
	void testOK_split_complicated() {
		RailwayLine line = new RailwayLine(Map.of(
				"Santarcangelo di Romagna", new Station("Santarcangelo di Romagna", 101.27, 120),
				"RiminiFiera", new Station("RiminiFiera", 106.97, 120),
				"Bologna Centrale", new Station("Bologna Centrale", 0, 180),
				"Giulianova", new Station("Giulianova", 312.36, 120)
				),
				new TreeSet<>(Set.of("Bologna Centrale")
				));
		Segment s = new Segment(line, new Station("Bologna Centrale", 0.0, 180), new Station("Giulianova", 312.36, 120));
		assertEquals(List.of(
				new Segment(line,new Station("Bologna Centrale", 0, 180),new Station("Santarcangelo di Romagna", 101.27, 120)),
				new Segment(line,new Station("Santarcangelo di Romagna", 101.27, 120),new Station("RiminiFiera", 106.97, 120)),
				new Segment(line,new Station("RiminiFiera", 106.97, 120),new Station("Giulianova", 312.36, 120))
				), s.split()); // !!!!!!!
	}
	
	@Test
	void testKO() {
		RailwayLine line = new RailwayLine(Map.of(
				"Alpha", new Station("Alpha",102.20, 80),
				"Beta", new Station("Beta",122.41, 100)
				),
				new TreeSet<>(Set.of("Beta")
				));
		RailwayLine line2 = new RailwayLine(Map.of(
				"Gamma", new Station("Gamma",126.20, 100),
				"Delta", new Station("Delta", 226.21, 130)
				),
				new TreeSet<>(Set.of("Beta")
				));
		assertThrows(IllegalArgumentException.class, () -> new Segment(line2, new Station("Alpha",102.20, 80), new Station("Beta", 122.41, 100)));
		assertThrows(IllegalArgumentException.class, () -> new Segment(line, new Station("Gamma",126.20, 100), new Station("Beta", 122.41, 100)));
		assertThrows(IllegalArgumentException.class, () -> new Segment(line2, new Station("Alpha",102.20, 80), new Station("Delta",226.21, 130)));
	}
	
	@Test
	void testKO_Split() {
		RailwayLine line = new RailwayLine(Map.of(
				"Alpha", new Station("Alpha",102.20, 80),
				"Beta", new Station("Beta",122.41, 100),
				"Gamma", new Station("Gamma",126.20, 100),
				"Delta", new Station("Delta", 226.21, 130)
				),
				new TreeSet<>(Set.of("Beta")
				));
		assertThrows(IllegalArgumentException.class, () -> new Segment(line,new Station("XXX",126.20, 100), new Station("Gamma",126.20, 100)).split());
	}

}
