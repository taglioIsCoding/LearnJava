package unident.model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unident.model.AttivitaFormativa;
import unident.model.Ssd;
import unident.model.Tipologia;

class AttivitaFormativaTest {

	@Test
	void testOK() {
		AttivitaFormativa af = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);
		assertEquals("Fondamenti di Informatica T-2", af.getNome());
		assertEquals(Ssd.INGINF05, af.getSsd());
		assertEquals(12, af.getCfu());
		AttivitaFormativa af2 = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);
		assertEquals(af, af2);
		assertTrue(af.equals(af2));
		assertEquals(af.hashCode(), af2.hashCode());
	}

	@Test
	void testKO_noName() {
		assertThrows(IllegalArgumentException.class, () -> new AttivitaFormativa("", Tipologia.B, Ssd.INGINF05, 12));
	}
	
	@Test
	void testKO_noTipologia() {
		assertThrows(IllegalArgumentException.class, () -> new AttivitaFormativa("AAA", null, Ssd.INGINF05, 12));
	}

	@Test
	void testKO_noSsd() {
		assertThrows(IllegalArgumentException.class, () -> new AttivitaFormativa("AAA", Tipologia.B, null, 12));
	}

	@Test
	void testKO_wrongCfu_0() {
		assertThrows(IllegalArgumentException.class, () -> new AttivitaFormativa("AAA", Tipologia.B, Ssd.FIS01, 0));
	}

	@Test
	void testKO_wrongCfu_Neg() {
		assertThrows(IllegalArgumentException.class, () -> new AttivitaFormativa("AAA", Tipologia.B, Ssd.FIS01, -2));
	}

}
