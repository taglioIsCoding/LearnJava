package mastermind.tests.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mastermind.model.*;

public class PioloRispostaTest {

	@Test
	public void testCardinalita() {
		assertEquals(3, PioloRisposta.values().length);
	}
	
	@Test
	public void testValori() {
		assertEquals(1, PioloRisposta.BIANCO.ordinal());
		assertEquals(2, PioloRisposta.NERO.ordinal());
		assertEquals(0, PioloRisposta.VUOTO.ordinal());
	}

}
