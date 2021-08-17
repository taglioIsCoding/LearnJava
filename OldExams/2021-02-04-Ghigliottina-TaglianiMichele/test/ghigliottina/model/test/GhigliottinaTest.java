package ghigliottina.model.test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import ghigliottina.model.Esatta;
import ghigliottina.model.Ghigliottina;
import ghigliottina.model.Terna;


class GhigliottinaTest {

	private final List<Terna> terne = List.of(
			new Terna("Lordo",    "Lardo",   Esatta.SECOND),
			new Terna("Zampino",  "Zampone", Esatta.FIRST),
			new Terna("Tetto",    "Veranda", Esatta.FIRST),
			new Terna("Lenta", "Frettolosa", Esatta.SECOND)
			);

	@Test
	void testOKBasic() {
		Ghigliottina gh = new Ghigliottina(terne, "Gatta");
		assertEquals("Gatta", gh.getRispostaEsatta());
		assertEquals(terne, gh.getTerne());
		assertTrue(gh.hasNext());
		assertEquals(gh.next(), new Terna("Lordo",    "Lardo",   Esatta.SECOND));
		assertTrue(gh.hasNext());
		assertEquals(gh.next(), new Terna("Zampino",  "Zampone", Esatta.FIRST));
		assertTrue(gh.hasNext());
		assertEquals(gh.next(), new Terna("Tetto",    "Veranda", Esatta.FIRST));
		assertTrue(gh.hasNext());
		assertEquals(gh.next(), new Terna("Lenta", "Frettolosa", Esatta.SECOND));
		assertFalse(gh.hasNext());
	}

	@Test
	void testKO_NullFirstArgInCtor() {
		assertThrows(IllegalArgumentException.class, () -> new Ghigliottina(null, "Gatta"));
	}

	@Test
	void testKO_NullSecondArgInCtor() {
		assertThrows(IllegalArgumentException.class, () -> new Ghigliottina(terne, null));
	}

	@Test
	void testKO_BlankSecondArgInCtor() {
		assertThrows(IllegalArgumentException.class, () -> new Ghigliottina(terne, " \t"));
	}
	
}
