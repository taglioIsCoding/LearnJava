package rfd.model.test;

import org.junit.jupiter.api.Test;

import rfd.model.Station;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StationTest {

	@Test
	public void testOK1() {
		Station poi = new Station("Santarcangelo di Romagna", 101.27, 180);
		assertEquals("Santarcangelo di Romagna", poi.getStationName());
		assertEquals(101.27, poi.getKm(), 0.001);
		assertEquals(180, poi.getSpeed(), 0.001);
	}

	@Test
	public void testOK2() {
		Station poi = new Station("RiminiFiera", 106.97, 160);
		assertEquals("RiminiFiera", poi.getStationName());
		assertEquals(106.97, poi.getKm(), 0.001);
		assertEquals(160, poi.getSpeed(), 0.001);
	}

	@Test
	public void testOK3() {
		Station poi = new Station("Bologna Centrale", 0.0, 160);
		assertEquals("Bologna Centrale", poi.getStationName());
		assertEquals(0.0, poi.getKm(), 0.001);
		assertEquals(160, poi.getSpeed(), 0.001);
	}
	
	/*@Test
	public void testKO_BadSeparator() {
		assertThrows(IllegalArgumentException.class, () -> new Station("Topolinia", 101e273"));
		assertThrows(IllegalArgumentException.class, () -> new Station("Topolinia", x101.273"));
		assertThrows(IllegalArgumentException.class, () -> new Station("Topolinia", "101,273"));
		assertThrows(IllegalArgumentException.class, () -> new Station("Topolinia", "101 273"));
		assertThrows(IllegalArgumentException.class, () -> new Station("Topolinia", "101\t273"));
	}
	
	@Test
	public void testKO_MetresNotThreeDigits() {
		assertThrows(IllegalArgumentException.class, () -> new Station("Topolinia", "101+27"));
		assertThrows(IllegalArgumentException.class, () -> new Station("Topolinia", "101+0"));
		assertThrows(IllegalArgumentException.class, () -> new Station("Topolinia", "101+0001"));
	}

	@Test
	public void testKO_MetresMissing() {
		assertThrows(IllegalArgumentException.class, () -> new Station("Topolinia", "101"));
		assertThrows(IllegalArgumentException.class, () -> new Station("Topolinia", "101+"));
	}

	@Test
	public void testKO_KmMissing() {
		assertThrows(IllegalArgumentException.class, () -> new Station("Topolinia", "+270"));
	}
	*/

}
