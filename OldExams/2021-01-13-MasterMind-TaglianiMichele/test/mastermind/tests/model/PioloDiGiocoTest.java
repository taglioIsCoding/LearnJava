package mastermind.tests.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mastermind.model.*;

public class PioloDiGiocoTest {

	@Test
	public void testCardinalita() {
		assertEquals(7, PioloDiGioco.values().length);
	}
	
	@Test
	public void testValori() {
		assertEquals(1, PioloDiGioco.ROSSO.ordinal());
		assertEquals(2, PioloDiGioco.GIALLO.ordinal());
		assertEquals(3, PioloDiGioco.VERDE.ordinal());
		assertEquals(4, PioloDiGioco.BLU.ordinal());
		assertEquals(5, PioloDiGioco.GRIGIO.ordinal());
		assertEquals(6, PioloDiGioco.MARRONE.ordinal());
		assertEquals(0, PioloDiGioco.VUOTO.ordinal());
	}

}
