package minirail.tests.model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Map;

import org.junit.Test;

import minirail.model.*;

public class MyRunnerTest {
	
	@Test
	public void testOK_1() {
		// With one train, the minimum number of sections to prevent deadlock is 3
		// (See the Runner documentation for details)
		Section[] sections = new Section[] {
				new Section("Casarsa-Piana", 90), 
				new Section("Piana-Colle dell'Orso", 90),
				new Section("Colle dell'Orso-Dolmeno", 70)
		};
		Line line = new Line("Casarsa-Dolmeno", Arrays.asList(sections));
		LineStatus dir = new MyLineStatus(line);
		Train t1 = new Train ("IC583", 65, Gauge.N.kmhToCms(160)); // Lunghezza modello in scala N = 65 cm, velocità 160 km/h da riportare in scala
		assertTrue(dir.putTrain(t1,85));
		Runner runner = new MyRunner(Map.of("Casarsa-Dolmeno", dir), Gauge.N);
		assertEquals(Gauge.N, runner.getGauge());
		assertEquals(Map.of("Casarsa-Dolmeno", dir), runner.getLines());
		t1.changeStatus(); // now MOVING
			System.err.println(runner);
		while (dir.getTrainLocation(t1)<249) {
			runner.clock(1.0);
			System.err.println(runner);
		}
		runner.clock(1.0);
			System.err.println(runner);
	}
	
	@Test
	public void testOK_2() {
		// With one train, the minimum number of sections to prevent deadlock is 3
		// (See the Runner documentation for details)
		Section[] sections = new Section[] {
				new Section("Casarsa-Piana", 90), 
				new Section("Piana-Colle dell'Orso", 90),
				new Section("Colle dell'Orso-Dolmeno", 70)
		};
		Line line = new Line("Infernale", Arrays.asList(sections));
		LineStatus dir = new MyLineStatus(line);
		Train t1 = new Train ("IC583", 65, Gauge.N.kmhToCms(160)); // Lunghezza modello in scala N = 65 cm, velocità 160 km/h da riportare in scala
		assertTrue(dir.putTrain(t1,85));
		Runner runner = new MyRunner(Map.of("Infernale", dir), Gauge.N);
		assertEquals(Gauge.N, runner.getGauge());
		assertEquals(Map.of("Infernale", dir), runner.getLines());
		t1.changeStatus(); // now MOVING
			System.err.println(runner);
		runner.clock(1.0);
			System.err.println(runner);
		runner.clock(1.0);
			System.err.println(runner);
		t1.changeStatus(); // now STOPPED
		assertThrows(RuntimeException.class, () -> runner.clock(1.0));
	}
	
	@Test
	public void testOK_2trains() {
		// With two trains, the minimum number of sections to prevent deadlock is 4
		// Otherwise, the clock period should be kept small enough to prevent the risk that a train blocks two consequent sections forever
		// (See the Runner documentation for details)
		Section[] sections = new Section[] {
				new Section("s0", 90), 
				new Section("s1", 90),
				new Section("s2", 70),
				new Section("s3", 70)
		};
		Line line = new Line("Infernale", Arrays.asList(sections));
		LineStatus dir = new MyLineStatus(line);
		Train t1 = new Train ("IC583", 65, Gauge.N.kmhToCms(160)); // Lunghezza modello in scala N = 65 cm, velocità 160 km/h da riportare in scala
		Train t2 = new Train ("R2961", 68, Gauge.N.kmhToCms(140)); // Lunghezza modello in scala N = 68 cm, velocità 140 km/h da riportare in scala
		assertTrue(dir.putTrain(t1,85));
		assertTrue(dir.putTrain(t2,160));

		Runner runner = new MyRunner(Map.of("Infernale", dir), Gauge.N);
		assertEquals(Gauge.N, runner.getGauge());
		assertEquals(Map.of("Infernale", dir), runner.getLines());
		t1.changeStatus(); // now MOVING
		t2.changeStatus(); // now MOVING
			System.err.println(runner);
		while (dir.getTrainLocation(t2)<290) {
			runner.clock(1.0);
				System.err.println(runner);
		}
	}

}
