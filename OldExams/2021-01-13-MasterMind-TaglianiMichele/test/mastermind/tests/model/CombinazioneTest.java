package mastermind.tests.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import mastermind.model.*;

public class CombinazioneTest {

	@Test
	public void testInizialmenteVuota() {
		Combinazione c = new Combinazione(4);
		for (int i=0; i<c.dim(); i++) {
			assertEquals(PioloDiGioco.VUOTO, c.getPiolo(i));
		}
	}
	
	@Test
	public void testOK1() {
		Combinazione c = new Combinazione(4);
		c.setPiolo(0, PioloDiGioco.ROSSO);
		c.setPiolo(1, PioloDiGioco.GIALLO);
		c.setPiolo(2, PioloDiGioco.VERDE);
		c.setPiolo(3, PioloDiGioco.BLU);
		assertEquals(PioloDiGioco.ROSSO, c.getPiolo(0));
		assertEquals(PioloDiGioco.GIALLO, c.getPiolo(1));
		assertEquals(PioloDiGioco.VERDE, c.getPiolo(2));
		assertEquals(PioloDiGioco.BLU, c.getPiolo(3));
	}
	
	@Test
	public void testOK2() {
		Combinazione c = new Combinazione(4);
		c.setPiolo(0, PioloDiGioco.ROSSO);
		assertEquals(PioloDiGioco.ROSSO, c.getPiolo(0));
		assertEquals(PioloDiGioco.VUOTO, c.getPiolo(1));
		assertEquals(PioloDiGioco.VUOTO, c.getPiolo(2));
		assertEquals(PioloDiGioco.VUOTO, c.getPiolo(3));
		c.setPiolo(1, PioloDiGioco.GIALLO);
		assertEquals(PioloDiGioco.ROSSO, c.getPiolo(0));
		assertEquals(PioloDiGioco.GIALLO, c.getPiolo(1));
		assertEquals(PioloDiGioco.VUOTO, c.getPiolo(2));
		assertEquals(PioloDiGioco.VUOTO, c.getPiolo(3));
		c.setPiolo(2, PioloDiGioco.VERDE);
		assertEquals(PioloDiGioco.ROSSO, c.getPiolo(0));
		assertEquals(PioloDiGioco.GIALLO, c.getPiolo(1));
		assertEquals(PioloDiGioco.VERDE, c.getPiolo(2));
		assertEquals(PioloDiGioco.VUOTO, c.getPiolo(3));
		c.setPiolo(3, PioloDiGioco.BLU);
		assertEquals(PioloDiGioco.ROSSO, c.getPiolo(0));
		assertEquals(PioloDiGioco.GIALLO, c.getPiolo(1));
		assertEquals(PioloDiGioco.VERDE, c.getPiolo(2));
		assertEquals(PioloDiGioco.BLU, c.getPiolo(3));
	}

	@Test
	public void testEquals() {
		Combinazione c1 = new Combinazione(4);
		c1.setPiolo(0, PioloDiGioco.ROSSO);
		c1.setPiolo(1, PioloDiGioco.GIALLO);
		c1.setPiolo(2, PioloDiGioco.VERDE);
		c1.setPiolo(3, PioloDiGioco.BLU);
		Combinazione c2 = new Combinazione(4);
		c2.setPiolo(0, PioloDiGioco.ROSSO);
		c2.setPiolo(1, PioloDiGioco.GIALLO);
		c2.setPiolo(2, PioloDiGioco.VERDE);
		c2.setPiolo(3, PioloDiGioco.BLU);
		assertEquals(c1,c2);
	}

	@Test
	public void testNotEqualsForDifferentSize() {
		Combinazione c1 = new Combinazione(4);
		c1.setPiolo(0, PioloDiGioco.ROSSO);
		c1.setPiolo(1, PioloDiGioco.GIALLO);
		c1.setPiolo(2, PioloDiGioco.VERDE);
		c1.setPiolo(3, PioloDiGioco.BLU);
		Combinazione c2 = new Combinazione(3);
		c2.setPiolo(0, PioloDiGioco.ROSSO);
		c2.setPiolo(1, PioloDiGioco.GIALLO);
		c2.setPiolo(2, PioloDiGioco.VERDE);
		assertNotEquals(c1,c2);
	}

	@Test
	public void testNotEqualsForDifferentColours() {
		Combinazione c1 = new Combinazione(4);
		c1.setPiolo(0, PioloDiGioco.ROSSO);
		c1.setPiolo(1, PioloDiGioco.GIALLO);
		c1.setPiolo(2, PioloDiGioco.VERDE);
		c1.setPiolo(3, PioloDiGioco.BLU);
		Combinazione c2 = new Combinazione(4);
		c2.setPiolo(0, PioloDiGioco.ROSSO);
		c2.setPiolo(1, PioloDiGioco.GIALLO);
		c2.setPiolo(2, PioloDiGioco.VERDE);
		c2.setPiolo(3, PioloDiGioco.GRIGIO);
		assertNotEquals(c1,c2);
	}

}
