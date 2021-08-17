package minirail.tests.model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import minirail.model.*;

public class MyLineStatusTest {
	
	@Test
	public void testOK_1() {
		Section[] sections = new Section[] {
				new Section("Casarsa-Piana", 90), 
				new Section("Piana-Colle dell'Orso", 90),
				new Section("Colle dell'Orso-Dolmeno", 70)
		};
		Line line = new Line("Casarsa-Dolmeno", Arrays.asList(sections));
		LineStatus dir = new MyLineStatus(line);
		assertEquals(line, dir.getLine());
		Train t = new Train ("IC583", 65, Gauge.N.kmhToCms(160)); // Lunghezza modello in scala N = 65 cm, velocità 160 km/h da riportare in scala
			System.out.println("----first train positioning");
		assertTrue(dir.putTrain(t,85));
		assertEquals(0, dir.posToSectionID(85));
		assertTrue(dir.isSectionOccupied(0));
		assertFalse(dir.isSectionOccupied(1));
		assertFalse(dir.isSectionOccupied(2));
		assertEquals(List.of(0), dir.getSectionOccupancy(t));
		// facciamo avanzare il treno a cavallo della sezione successiva
			System.out.println("----moving train positioning");
		assertTrue(dir.putTrain(t,95));
		assertEquals(1, dir.posToSectionID(95));
		assertTrue(dir.isSectionOccupied(1));
		assertTrue(dir.isSectionOccupied(0));
		assertFalse(dir.isSectionOccupied(2));
		assertEquals(List.of(0,1), dir.getSectionOccupancy(t));
		// facciamo avanzare ancora il treno all'interno della sezione successiva ma con estremo sinistro proprio al limite
			System.out.println("----moving train positioning farther");
		assertTrue(dir.putTrain(t,155));
		assertEquals(1, dir.posToSectionID(155));
		assertTrue(dir.isSectionOccupied(1));
		assertTrue(dir.isSectionOccupied(0));
		assertFalse(dir.isSectionOccupied(2));
		assertEquals(List.of(0,1), dir.getSectionOccupancy(t));
		// facciamo avanzare il treno di 1 cm così da liberare la sez precedente
			System.out.println("----moving train 1cm farther, thus freeing previous section");
		assertTrue(dir.putTrain(t,156));
		assertEquals(1, dir.posToSectionID(156));
		assertTrue(dir.isSectionOccupied(1));
		assertFalse(dir.isSectionOccupied(0));
		assertFalse(dir.isSectionOccupied(2));
		assertEquals(List.of(1), dir.getSectionOccupancy(t));
		// facciamo avanzare il treno in fondo all'ultima sezione
			System.out.println("----moving train round up the beginning of the loop");
		assertTrue(dir.putTrain(t,200));
		assertEquals(2, dir.posToSectionID(200));
		assertFalse(dir.isSectionOccupied(0));
		assertTrue(dir.isSectionOccupied(2));
		assertTrue(dir.isSectionOccupied(1));
		// facciamo avanzare il treno in fondo all'ultima sezione
			System.out.println("----moving train round up the beginning of the loop");
		assertTrue(dir.putTrain(t,15));
		assertEquals(0, dir.posToSectionID(15));
		assertTrue(dir.isSectionOccupied(0));
		assertTrue(dir.isSectionOccupied(2));
		assertFalse(dir.isSectionOccupied(1));
		assertEquals(List.of(0,2), dir.getSectionOccupancy(t));
	}
	
	@Test
	public void testKO_VoidLine() {
		assertThrows(IllegalArgumentException.class, () -> new MyLineStatus(null));
	}
	
	@Test
	public void testOK_2Trains() {
		Section[] sections = new Section[] {
				new Section("Casarsa-Piana", 90), 
				new Section("Piana-Colle dell'Orso", 90),
				new Section("Colle dell'Orso-Dolmeno", 70)
		};
		Line line = new Line("Casarsa-Dolmeno", Arrays.asList(sections));
		LineStatus dir = new MyLineStatus(line);
		assertEquals(line, dir.getLine());
		Train t1 = new Train ("IC583", 65, Gauge.N.kmhToCms(160)); // Lunghezza modello in scala N = 65 cm, velocità 160 km/h da riportare in scala
		Train t2 = new Train ("R2961", 68, Gauge.N.kmhToCms(140)); // Lunghezza modello in scala N = 68 cm, velocità 140 km/h da riportare in scala
		
			System.out.println("----initial train positioning");
		assertTrue(dir.putTrain(t1,85));
		assertTrue(dir.putTrain(t2,160));
		assertEquals(0, dir.posToSectionID(85));
		assertEquals(1, dir.posToSectionID(160));
		assertTrue(dir.isSectionOccupied(0)); // by train 1
		assertTrue(dir.isSectionOccupied(1)); // by train 2
		assertFalse(dir.isSectionOccupied(2));
		assertEquals(t1, dir.occupyingTrain(0));
		assertEquals(t2, dir.occupyingTrain(1));
		assertEquals(List.of(0), dir.getSectionOccupancy(t1));
		assertEquals(List.of(1), dir.getSectionOccupancy(t2));
		// tentiamo di far avanzare il treno1, ma non ci riesce perché la sezione successiva è occupata
			System.out.println("----trying to move train1 forward: fails due to section1 occupied");
		assertFalse(dir.putTrain(t1,95));
		assertTrue(dir.isSectionOccupied(0));
		assertTrue(dir.isSectionOccupied(1));
		assertFalse(dir.isSectionOccupied(2));
		assertEquals(t1, dir.occupyingTrain(0));
		assertEquals(t2, dir.occupyingTrain(1));
		assertEquals(List.of(0), dir.getSectionOccupancy(t1));
		assertEquals(List.of(1), dir.getSectionOccupancy(t2));
		// allora facciamo facciamo avanzare il treno1 solo di pochi cm
			System.out.println("----trying to move train1 a little forward: succeeds, as it remains inside section 0");
		assertTrue(dir.putTrain(t1,89));
		assertTrue(dir.isSectionOccupied(0)); // by train 1
		assertTrue(dir.isSectionOccupied(1)); // by train 2
		assertFalse(dir.isSectionOccupied(2));
		assertEquals(t1, dir.occupyingTrain(0));
		assertEquals(t2, dir.occupyingTrain(1));
		assertEquals(List.of(0), dir.getSectionOccupancy(t1));
		assertEquals(List.of(1), dir.getSectionOccupancy(t2));
		// facciamo avanzare il treno di 1 cm così da liberare la sez precedente
			System.out.println("----moving train 1cm farther, just to the limit: still ok, success");
		assertTrue(dir.putTrain(t1,90));
		assertTrue(dir.isSectionOccupied(0)); // by train 1
		assertTrue(dir.isSectionOccupied(1)); // by train 2
		assertFalse(dir.isSectionOccupied(2));
		assertEquals(t1, dir.occupyingTrain(0));
		assertEquals(t2, dir.occupyingTrain(1));
		assertEquals(List.of(0), dir.getSectionOccupancy(t1));
		assertEquals(List.of(1), dir.getSectionOccupancy(t2));
		// facciamo avanzare il treno 2 a cavallo fra penultima e ultima sezione
			System.out.println("----moving train2 round up the beginning of the loop");
		assertTrue(dir.putTrain(t2,200));
		assertEquals(2, dir.posToSectionID(200));
		assertTrue(dir.isSectionOccupied(0)); // by train 1
		assertTrue(dir.isSectionOccupied(2)); // by train 2
		assertTrue(dir.isSectionOccupied(1)); // by train 2
		assertEquals(t1, dir.occupyingTrain(0));
		assertEquals(t2, dir.occupyingTrain(1));
		assertEquals(t2, dir.occupyingTrain(2));
		// tentiamo di far avanzare il treno1, ma non ci riesce perché la sezione successiva è occupata
			System.out.println("----trying to move train1 forward: fails due to section1 occupied");
		assertFalse(dir.putTrain(t1,95));
		assertTrue(dir.isSectionOccupied(0)); // by train 1
		assertTrue(dir.isSectionOccupied(1)); // by train 2
		assertTrue(dir.isSectionOccupied(2)); // by train 2
		assertEquals(List.of(0), dir.getSectionOccupancy(t1));
		assertEquals(List.of(1,2), dir.getSectionOccupancy(t2));
		// facciamo avanzare il treno 2 tutto entro l'ultima sezione, così da liberare la sezione 1
			System.out.println("----moving train2 round up the beginning of the loop");
		assertTrue(dir.putTrain(t2,249));
		assertTrue(dir.isSectionOccupied(0)); // by train 1
		assertTrue(dir.isSectionOccupied(2)); // by train 2
		assertFalse(dir.isSectionOccupied(1));
		assertEquals(t1, dir.occupyingTrain(0));
		assertEquals(t2, dir.occupyingTrain(2));
		assertEquals(List.of(0), dir.getSectionOccupancy(t1));
		assertEquals(List.of(2), dir.getSectionOccupancy(t2));
		// ritentiamo di far avanzare il treno1, stavolta ci riesce perché la sezione 1 successiva è stata liberata
			System.out.println("----trying to move train1 forward: succeeds due to section1 freed meanwhile");
		assertTrue(dir.putTrain(t1,95));
		assertTrue(dir.isSectionOccupied(0)); // by train 1
		assertTrue(dir.isSectionOccupied(1)); // by train 1
		assertTrue(dir.isSectionOccupied(2)); // by train 2
		assertEquals(t1, dir.occupyingTrain(0));
		assertEquals(t1, dir.occupyingTrain(1));
		assertEquals(t2, dir.occupyingTrain(2));
		assertEquals(List.of(0,1), dir.getSectionOccupancy(t1));
		assertEquals(List.of(2), dir.getSectionOccupancy(t2));
		// facciamo avanzare il treno1 in modo che liberi la sezione 0
			System.out.println("----trying to move train1 forward: succeeds due to section1 freed meanwhile");
		assertTrue(dir.putTrain(t1,160));
		assertFalse(dir.isSectionOccupied(0));
		assertTrue(dir.isSectionOccupied(1)); // by train 1
		assertTrue(dir.isSectionOccupied(2)); // by train 2
		assertEquals(t1, dir.occupyingTrain(1));
		assertEquals(t2, dir.occupyingTrain(2));
		assertEquals(List.of(1), dir.getSectionOccupancy(t1));
		assertEquals(List.of(2), dir.getSectionOccupancy(t2));
		// facciamo avanzare il treno in fondo all'ultima sezione
			System.out.println("----moving train round up the beginning of the loop");
		assertTrue(dir.putTrain(t2,15));
		assertEquals(0, dir.posToSectionID(15));
		assertTrue(dir.isSectionOccupied(1)); // by train 1
		assertTrue(dir.isSectionOccupied(2)); // by train 2
		assertTrue(dir.isSectionOccupied(0)); // by train 2
		assertEquals(List.of(1), dir.getSectionOccupancy(t1));
		assertEquals(List.of(0,2), dir.getSectionOccupancy(t2));

	}

	
	@Test
	public void testOK_remove() {
		Section[] sections = new Section[] {
				new Section("Casarsa-Piana", 90), 
				new Section("Piana-Colle dell'Orso", 90),
				new Section("Colle dell'Orso-Dolmeno", 70)
		};
		Line line = new Line("Casarsa-Dolmeno", Arrays.asList(sections));
		LineStatus dir = new MyLineStatus(line);
		assertEquals(line, dir.getLine());
		Train t1 = new Train ("IC583", 65, Gauge.N.kmhToCms(160)); // Lunghezza modello in scala N = 65 cm, velocità 160 km/h da riportare in scala
		Train t2 = new Train ("R2961", 68, Gauge.N.kmhToCms(140)); // Lunghezza modello in scala N = 68 cm, velocità 140 km/h da riportare in scala
			System.out.println("----initial train positioning");
		assertTrue(dir.putTrain(t1,85));
		assertTrue(dir.putTrain(t2,160));
		assertEquals(0, dir.posToSectionID(85));
		assertEquals(1, dir.posToSectionID(160));
		assertTrue(dir.isSectionOccupied(0)); // by train 1
		assertTrue(dir.isSectionOccupied(1)); // by train 2
		assertFalse(dir.isSectionOccupied(2));
		assertEquals(t1, dir.occupyingTrain(0));
		assertEquals(t2, dir.occupyingTrain(1));
		// remove t1
		assertTrue(dir.removeTrain(t1));
		assertFalse(dir.isSectionOccupied(0)); // was train 1
		assertTrue(dir.isSectionOccupied(1));  // by train 2
		assertFalse(dir.isSectionOccupied(2));
		assertNull(dir.occupyingTrain(0));
		assertEquals(t2, dir.occupyingTrain(1));
		// remove t2
		assertTrue(dir.removeTrain(t2));
		assertFalse(dir.isSectionOccupied(1));  // was train 2
		assertFalse(dir.isSectionOccupied(2));
		assertNull(dir.occupyingTrain(1));
	}
}
