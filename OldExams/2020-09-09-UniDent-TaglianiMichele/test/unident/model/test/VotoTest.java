package unident.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.OptionalInt;

import org.junit.jupiter.api.Test;

import unident.model.Voto;

class VotoTest {

	@Test
	void testAssente() {
		Voto v = Voto.ASSENTE;
		assertEquals(OptionalInt.empty(), v.getValue());
		assertFalse(v.getLode());
		assertEquals("ASSENTE", v.name());
	}
	
	@Test
	void testRitirato() {
		Voto v = Voto.RITIRATO;
		assertEquals(OptionalInt.empty(), v.getValue());
		assertFalse(v.getLode());
		assertEquals("RITIRATO", v.name());
	}
	
	@Test
	void testRespinto() {
		Voto v = Voto.RESPINTO;
		assertEquals(OptionalInt.empty(), v.getValue());
		assertFalse(v.getLode());
		assertEquals("RESPINTO", v.name());
	}
	
	@Test
	void testV18() {
		Voto v = Voto.V18;
		assertEquals(18, v.getValue().getAsInt());
		assertFalse(v.getLode());
		assertEquals("V18", v.name());
	}
	
	// .. evito gli altri identici
	
	@Test
	void testV30() {
		Voto v = Voto.V30;
		assertEquals(30, v.getValue().getAsInt());
		assertFalse(v.getLode());
		assertEquals("V30", v.name());
	}

	@Test
	void testV30L() {
		Voto v = Voto.V30L;
		assertEquals(30, v.getValue().getAsInt());
		assertTrue(v.getLode());
		assertEquals("V30L", v.name());
	}
	
	@Test
	void testOf_OK() {
		assertEquals(Voto.V18, Voto.of("18"));
		assertEquals(Voto.V19, Voto.of("19"));
		assertEquals(Voto.V20, Voto.of("20"));
		assertEquals(Voto.V21, Voto.of("21"));
		assertEquals(Voto.V22, Voto.of("22"));
		assertEquals(Voto.V23, Voto.of("23"));
		assertEquals(Voto.V24, Voto.of("24"));
		assertEquals(Voto.V25, Voto.of("25"));
		assertEquals(Voto.V26, Voto.of("26"));
		assertEquals(Voto.V27, Voto.of("27"));
		assertEquals(Voto.V28, Voto.of("28"));
		assertEquals(Voto.V29, Voto.of("29"));
		assertEquals(Voto.V30, Voto.of("30"));
		assertEquals(Voto.V30L, Voto.of("30L"));
		assertEquals(Voto.ASSENTE, Voto.of("ASSENTE"));
		assertEquals(Voto.RITIRATO, Voto.of("RITIRATO"));
		assertEquals(Voto.RESPINTO, Voto.of("RESPINTO"));		
	}

	@Test
	void testOf_KO_votoMinoreDi18() {
		assertThrows(IllegalArgumentException.class, () -> Voto.of("17"));
	}

	@Test
	void testOf_KO_votoMinoreDiZero() {
		assertThrows(IllegalArgumentException.class, () -> Voto.of("-1"));
	}

	@Test
	void testOf_KO_votoZero() {
		assertThrows(IllegalArgumentException.class, () -> Voto.of("0"));
	}
	
	@Test
	void testOf_KO_votoGenerico() {
		assertThrows(IllegalArgumentException.class, () -> Voto.of("BLA"));
	}

	@Test
	void testOf_KO_votoNonUpperCase() {
		assertThrows(IllegalArgumentException.class, () -> Voto.of("assente"));
		assertThrows(IllegalArgumentException.class, () -> Voto.of("Assente"));
		assertThrows(IllegalArgumentException.class, () -> Voto.of("ritirato"));
		assertThrows(IllegalArgumentException.class, () -> Voto.of("Ritirato"));
		assertThrows(IllegalArgumentException.class, () -> Voto.of("respinto"));
		assertThrows(IllegalArgumentException.class, () -> Voto.of("Respinto"));
	}
	


}
