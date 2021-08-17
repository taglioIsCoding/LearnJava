package minirail.console;

import java.util.Arrays;
import java.util.Map;

import minirail.model.*;

public class ConsoleMainNoPersistence {
	
	public static void main(String[] args) {
		// run just ONE of these!
		testOK_1();
		testOK_2();
	}
	
	public static void testOK_1() {
		System.out.println("==============TEST #1==============");
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
		dir.putTrain(t1,85);
		Runner runner = new MyRunner(Map.of("Casarsa-Dolmeno", dir), Gauge.N);
		t1.changeStatus(); // now MOVING
		System.out.println(runner);
		while (dir.getTrainLocation(t1)<249) {
			runner.clock(1.0);
			System.out.println(runner);
		}
		runner.clock(1.0);
		System.out.println(runner);
	}
	
	public static void testOK_2() {
		System.out.println("==============TEST #2==============");
		// With two trains, the minimum number of sections to prevent deadlock is 4
		// Otherwise, the clock period should be kept small enough to prevent the risk that a train blocks two consequent sections forever
		// (See the Runner documentation for details)
		Section[] sections = new Section[] {
				new Section("A-B", 90), 
				new Section("B-C", 90),
				new Section("C-D", 70),
				new Section("D-A", 70)
		};
		Line line = new Line("Infernale", Arrays.asList(sections));
		LineStatus dir = new MyLineStatus(line);
		Train t1 = new Train ("IC583", 65, Gauge.N.kmhToCms(160)); // Lunghezza modello in scala N = 65 cm, velocità 160 km/h da riportare in scala
		Train t2 = new Train ("R2961", 68, Gauge.N.kmhToCms(140)); // Lunghezza modello in scala N = 68 cm, velocità 140 km/h da riportare in scala
		dir.putTrain(t1,85);
		dir.putTrain(t2,160);
		Runner runner = new MyRunner(Map.of("Infernale", dir), Gauge.N);
		t1.changeStatus(); // now MOVING
		t2.changeStatus(); // now MOVING
		System.out.println(runner);
		while (dir.getTrainLocation(t2)<290) {
			runner.clock(1.0);
			System.out.println(runner);
		}
	}

}
