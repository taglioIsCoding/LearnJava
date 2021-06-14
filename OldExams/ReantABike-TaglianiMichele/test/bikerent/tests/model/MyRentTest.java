package bikerent.tests.model;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import bikerent.model.*;

public class MyRentTest
{
	private Map<String, Rate> map;
	
	@Before
	public void setUp() {
		map = Map.of(
				//Bologna, 59 cent per 20 minuti, poi 99 cent per 30 minuti, max 12 ore, sanzione 7.50 euro
				"Topolinia", new Rate("Topolinia", 
						new Periodo(59, Duration.ofMinutes(20)), 
						new Periodo(99, Duration.ofMinutes(30)),
						Optional.of(Duration.ofHours(12)), Optional.empty(), 7.50),
				"Paperopoli", new Rate("Paperopoli", 
						new Periodo(59, Duration.ofMinutes(20)), 
						new Periodo(99, Duration.ofMinutes(30)),
						Optional.empty(), Optional.of(LocalTime.of(23,59)), 7.00)
				);
	}
	
	@Test
	public void testRent1_OK()
	{
		LocalDateTime inizio = LocalDateTime.of(2018, 9, 12, 8, 30);
		LocalDateTime fine = LocalDateTime.of(2018, 9, 12, 9, 15);
		Rent t = new MyRent(inizio, fine, map.get("Topolinia"));
		assertEquals(inizio, t.getStart());
		assertEquals(fine, t.getEnd());
		assertTrue(t.isRegular());
	}

	@Test
	public void testRent1_KO()
	{
		LocalDateTime inizio = LocalDateTime.of(2018, 9, 12, 8, 30);
		LocalDateTime fine = LocalDateTime.of(2018, 9, 12, 20, 31);
		Rent t = new MyRent(inizio, fine, map.get("Topolinia"));
		assertEquals(inizio, t.getStart());
		assertEquals(fine, t.getEnd());
		assertFalse(t.isRegular()); // supera le 12 ore
	}

	@Test
	public void testRent2_OK()
	{
		LocalDateTime inizio = LocalDateTime.of(2018, 9, 12, 8, 30);
		LocalDateTime fine = LocalDateTime.of(2018, 9, 12, 9, 15);
		Rent t = new MyRent(inizio, fine, map.get("Paperopoli"));
		assertEquals(inizio, t.getStart());
		assertEquals(fine, t.getEnd());
		assertTrue(t.isRegular());
	}

	@Test
	public void testRent2_KO()
	{
		LocalDateTime inizio = LocalDateTime.of(2018, 9, 12, 8, 30);
		LocalDateTime fine = LocalDateTime.of(2018, 9, 13, 0, 00);
		Rent t = new MyRent(inizio, fine, map.get("Paperopoli"));
		assertEquals(inizio, t.getStart());
		assertEquals(fine, t.getEnd());
		assertFalse(t.isRegular()); // supera le 23:59 del giorno di inizio noleggio
	}

	@Test
	public void testRent2_ok2()
	{
		LocalDateTime inizio = LocalDateTime.of(2018, 9, 12, 8, 30);
		LocalDateTime fine = LocalDateTime.of(2018, 9, 12, 23, 59);
		Rent t = new MyRent(inizio, fine, map.get("Paperopoli"));
		assertEquals(inizio, t.getStart());
		assertEquals(fine, t.getEnd());
		assertTrue(t.isRegular()); // al pelo, termina alle 23:59 del giorno di inizio noleggio
	}

}
