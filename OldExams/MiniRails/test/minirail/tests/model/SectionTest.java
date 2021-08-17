package minirail.tests.model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import minirail.model.*;

public class SectionTest {
	
	@Test
	public void testOK_1() {
		Section s = new Section("s1", 90); // Lunghezza in cm
		assertEquals(90, s.getLength(), 0.01);
	}

	@Test
	public void testOK_equals() {
		Section sA = new Section("s1", 90); // Lunghezza in cm
		Section sB = new Section("s1", 90); // Lunghezza in cm
		Section sC = new Section("s2", 90); // Lunghezza in cm
		assertEquals(sA,sB);
		assertNotEquals(sA,sC);
		assertNotEquals(sB,sC);
	}

	@Test
	public void testKO_VoidName() {
		assertThrows(IllegalArgumentException.class, () -> new Section(null, 90));
		assertThrows(IllegalArgumentException.class, () -> new Section("",   90));
	}

	@Test
	public void testKO_ZeroLen() {
		assertThrows(IllegalArgumentException.class, () -> new Section("s1",0));
	}

}
