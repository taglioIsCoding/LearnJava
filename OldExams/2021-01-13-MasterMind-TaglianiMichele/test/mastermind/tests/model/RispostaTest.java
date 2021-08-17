package mastermind.tests.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mastermind.model.*;

public class RispostaTest {

	@Test
	public void testInizialmenteVuota() {
		Risposta c = new Risposta(4);
		for (int i=0; i<c.dim(); i++) {
			assertEquals(PioloRisposta.VUOTO, c.getPiolo(i));
		}
	}
	
	@Test
	public void testWon() {
		Risposta c = new Risposta(4);
		c.setPiolo(0, PioloRisposta.NERO);
		c.setPiolo(1, PioloRisposta.NERO);
		c.setPiolo(2, PioloRisposta.NERO);
		c.setPiolo(3, PioloRisposta.NERO);
		assertTrue(c.vittoria());
	}

	@Test
	public void testOK1() {
		Risposta c = new Risposta(4);
		c.setPiolo(0, PioloRisposta.BIANCO);
		c.setPiolo(1, PioloRisposta.BIANCO);
		c.setPiolo(2, PioloRisposta.NERO);
		c.setPiolo(3, PioloRisposta.NERO);
		assertEquals(PioloRisposta.BIANCO, c.getPiolo(0));
		assertEquals(PioloRisposta.BIANCO, c.getPiolo(1));
		assertEquals(PioloRisposta.NERO, c.getPiolo(2));
		assertEquals(PioloRisposta.NERO, c.getPiolo(3));
	}

	@Test
	public void testEquals() {
		Risposta c1 = new Risposta(4);
		c1.setPiolo(0, PioloRisposta.BIANCO);
		c1.setPiolo(1, PioloRisposta.BIANCO);
		c1.setPiolo(2, PioloRisposta.NERO);
		c1.setPiolo(3, PioloRisposta.NERO);
		Risposta c2 = new Risposta(4);
		c2.setPiolo(0, PioloRisposta.BIANCO);
		c2.setPiolo(1, PioloRisposta.BIANCO);
		c2.setPiolo(2, PioloRisposta.NERO);
		c2.setPiolo(3, PioloRisposta.NERO);
		assertEquals(c1,c2);
	}

	@Test
	public void testNotEqualsForDifferentSize() {
		Risposta c1 = new Risposta(4);
		c1.setPiolo(0, PioloRisposta.BIANCO);
		c1.setPiolo(1, PioloRisposta.BIANCO);
		c1.setPiolo(2, PioloRisposta.NERO);
		c1.setPiolo(3, PioloRisposta.NERO);
		Risposta c2 = new Risposta(4);
		c2.setPiolo(0, PioloRisposta.BIANCO);
		c2.setPiolo(1, PioloRisposta.BIANCO);
		c2.setPiolo(2, PioloRisposta.NERO);
		assertNotEquals(c1,c2);
	}

	@Test
	public void testNotEqualsForDifferentColours() {
		Risposta c1 = new Risposta(4);
		c1.setPiolo(0, PioloRisposta.BIANCO);
		c1.setPiolo(1, PioloRisposta.BIANCO);
		c1.setPiolo(2, PioloRisposta.NERO);
		c1.setPiolo(3, PioloRisposta.NERO);
		Risposta c2 = new Risposta(4);
		c2.setPiolo(0, PioloRisposta.BIANCO);
		c2.setPiolo(1, PioloRisposta.BIANCO);
		c2.setPiolo(2, PioloRisposta.BIANCO);
		c2.setPiolo(3, PioloRisposta.NERO);
		assertNotEquals(c1,c2);
	}

}
