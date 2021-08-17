package minirail.tests.model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import minirail.model.*;

public class TrainTest {
	
	@Test
	public void testOK_N() {
		Train t = new Train ("IC583", 65, Gauge.N.kmhToCms(160)); // Lunghezza modello in scala N = 65 cm, velocità 160 km/h da riportare in scala
		assertEquals(TrainStatus.STOPPED, t.getStatus());
		assertEquals(65, t.getLength(), 0.01);
		assertEquals(27.77, t.getSpeed(), 0.01);
		assertEquals("IC583", t.getName());
	}
	
	@Test
	public void testOK_H0() {
		Train t = new Train ("IC583", 117, Gauge.H0.kmhToCms(160)); // Lunghezza modello in scala H0 = 117 cm, velocità 160 km/h da riportare in scala
		assertEquals(TrainStatus.STOPPED, t.getStatus());
		assertEquals(117, t.getLength(), 0.01);
		assertEquals(51.08, t.getSpeed(), 0.01);
	}

	@Test
	public void testOK_SetStatus() {
		Train t = new Train ("IC583", 117, Gauge.H0.kmhToCms(160)); // Lunghezza modello in scala H0 = 117 cm, velocità 160 km/h da riportare in scala
		assertEquals(TrainStatus.STOPPED, t.getStatus());
		t.setStatus(TrainStatus.MOVING);
		assertEquals(TrainStatus.MOVING, t.getStatus());
		t.setStatus(TrainStatus.STOPPED);
		assertEquals(TrainStatus.STOPPED, t.getStatus());
	}
	
	@Test
	public void testOK_ChangeStatus() {
		Train t = new Train ("IC583", 117, Gauge.H0.kmhToCms(160)); // Lunghezza modello in scala H0 = 117 cm, velocità 160 km/h da riportare in scala
		assertEquals(TrainStatus.STOPPED, t.getStatus());
		t.changeStatus();
		assertEquals(TrainStatus.MOVING, t.getStatus());
		t.changeStatus();
		t.changeStatus();
		assertEquals(TrainStatus.MOVING, t.getStatus());
		t.changeStatus();
		assertEquals(TrainStatus.STOPPED, t.getStatus());
	}

	@Test
	public void testOK_Equals() {
		Train t = new Train ("IC583", 117, Gauge.H0.kmhToCms(160)); // Lunghezza modello in scala H0 = 117 cm, velocità 160 km/h da riportare in scala
		Train tBis = new Train ("IC583", 117, Gauge.H0.kmhToCms(160)); // Lunghezza modello in scala H0 = 117 cm, velocità 160 km/h da riportare in scala
		assertEquals(t,tBis);
		assertEquals(t.hashCode(),tBis.hashCode());
	}

	@Test
	public void testKO_ZeroLen() {
		assertThrows(IllegalArgumentException.class, () -> new Train ("boo", 0, Gauge.H0.kmhToCms(160)));
	}

	@Test
	public void testKO_ZeroSpeed() {
		assertThrows(IllegalArgumentException.class, () -> new Train ("boo", 65, 0));
	}

}
