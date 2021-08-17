package ghigliottina.model.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ghigliottina.model.Esatta;
import ghigliottina.model.Terna;

class TernaTest {

	@Test
	void testOKBasic() {
		Terna t = new Terna("Pippo", "Pluto", Esatta.SECOND);
		assertEquals("Pippo", t.getWord1());
		assertEquals("Pluto", t.getWord2());
		assertEquals(Esatta.SECOND, t.getCorrect());
		assertEquals("Pluto", t.getCorrectWord());
		assertEquals("Pippo", t.getWrongWord());
	}

	@Test
	void testKO_NullFirstStringinCtor() {
		assertThrows(IllegalArgumentException.class, () -> new Terna(null, "Pluto", Esatta.SECOND));
	}

	@Test
	void testKO_NullSecondStringinCtor() {
		assertThrows(IllegalArgumentException.class, () -> new Terna("Pippo", null, Esatta.SECOND));
	}

	@Test
	void testKO_BlankFirstStringinCtor() {
		assertThrows(IllegalArgumentException.class, () -> new Terna(" \t ", "Pluto", Esatta.SECOND));
		assertThrows(IllegalArgumentException.class, () -> new Terna("", "Pluto", Esatta.SECOND));
	}

	@Test
	void testKO_BlankSecondStringinCtor() {
		assertThrows(IllegalArgumentException.class, () -> new Terna("Pippo", "  \t", Esatta.SECOND));
		assertThrows(IllegalArgumentException.class, () -> new Terna("Pippo", "", Esatta.SECOND));
	}

	
}
