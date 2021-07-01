package unident.model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unident.model.Ssd;

class SsdTest {

	@Test
	void testOK_IngInf05() {
		Ssd ssd = Ssd.INGINF05;
		assertEquals("Ing-Inf/05", ssd.getValue());
	}
	
	@Test
	void testOK_NoSector() {
		Ssd ssd = Ssd.SENZASETTORE;
		assertEquals("\t\t", ssd.getValue());
	}
	
	@Test
	void testOK_of() {
		assertEquals(Ssd.FIS01, Ssd.of("fis/01"));
		assertEquals(Ssd.FIS01, Ssd.of("FIS/01"));
		assertEquals(Ssd.FIS01, Ssd.of("Fis/01"));
		assertEquals(Ssd.INGINF05, Ssd.of("Ing-Inf/05"));
		assertEquals(Ssd.INGINF05, Ssd.of("ing-inf/05"));
		assertEquals(Ssd.INGINF05, Ssd.of("ING-Inf/05"));
		assertEquals(Ssd.INGINF05, Ssd.of("ING-iNF/05"));
		// gli altri li controlliamo solo in un'unica versione, tanto il case è già testato sopra
		assertEquals(Ssd.INGINF04, Ssd.of("Ing-Inf/04"));
		assertEquals(Ssd.INGINF03, Ssd.of("Ing-Inf/03"));
		assertEquals(Ssd.INGINF01, Ssd.of("Ing-Inf/01"));
		assertEquals(Ssd.INGIND35, Ssd.of("Ing-Ind/35"));
		assertEquals(Ssd.INGIND31, Ssd.of("Ing-Ind/31"));
		assertEquals(Ssd.MAT03, Ssd.of("Mat/03"));
		assertEquals(Ssd.MAT05, Ssd.of("Mat/05"));
		assertEquals(Ssd.MAT07, Ssd.of("Mat/07"));
		assertEquals(Ssd.IUS20, Ssd.of("Ius/20"));
	}
	
}
