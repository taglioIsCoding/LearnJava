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

public class MyCalculatorTest {
	
	private Map<String, Rate> mapRates;
	
	@Before
	public void setUp() {
		mapRates = Map.of(
				"Bologna", new Rate("Bologna", 
						new Periodo(59, Duration.ofMinutes(20)), 
						new Periodo(99, Duration.ofMinutes(30)),
						Optional.of(Duration.ofHours(12)), Optional.empty(), 7.50),
				"Reggio Emilia", new Rate("Reggio Emilia", 
						new Periodo(30, Duration.ofMinutes(30)), 
						new Periodo(50, Duration.ofMinutes(30)),
						Optional.empty(), Optional.of(LocalTime.of(23,59)), 7.00)
				);
	}
	
	@Test
	public void testCalcBO1()
	{
		LocalDateTime inizio = LocalDateTime.of(2018, 9, 12, 7, 20);
		LocalDateTime fine = LocalDateTime.of(2018, 9, 12, 7, 45);
		Rate r = mapRates.get("Bologna");
		Rent t = new MyRent(inizio, fine, r);
		Calculator calculator = new MyCalculator();
		assertEquals(1.58, calculator.calc(r, t), 0.01);
	}

	@Test
	public void testCalcBO2()
	{
		LocalDateTime inizio = LocalDateTime.of(2018, 9, 12, 8, 25);
		LocalDateTime fine = LocalDateTime.of(2018, 9, 12, 8, 44);
		Rate r = mapRates.get("Bologna");
		Rent t = new MyRent(inizio, fine, r);
		Calculator calculator = new MyCalculator();
		assertEquals(0.59, calculator.calc(r, t), 0.01);
	}

	@Test
	public void testCalcBO3()
	{
		LocalDateTime inizio = LocalDateTime.of(2018, 9, 12, 7, 30);
		LocalDateTime fine = LocalDateTime.of(2018, 9, 12, 19, 40);
		Rate r = mapRates.get("Bologna");
		Rent t = new MyRent(inizio, fine, r);
		Calculator calculator = new MyCalculator();
		assertEquals(31.85, calculator.calc(r, t), 0.01);
	}

	@Test
	public void testCalcRE1()
	{
		LocalDateTime inizio = LocalDateTime.of(2018, 9, 12, 7, 30);
		LocalDateTime fine = LocalDateTime.of(2018, 9, 12, 18, 40);
		Rate r = mapRates.get("Reggio Emilia");
		Rent t = new MyRent(inizio, fine, r);
		Calculator calculator = new MyCalculator();
		assertEquals(11.30, calculator.calc(r, t), 0.01);
	}

}
