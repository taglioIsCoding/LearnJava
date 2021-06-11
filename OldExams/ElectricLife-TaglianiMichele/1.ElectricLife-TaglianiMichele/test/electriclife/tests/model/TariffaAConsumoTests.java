package electriclife.tests.model;

import static electriclife.tests.model.BollettaTestUtils.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import electriclife.model.*;

public class TariffaAConsumoTests
{
//	static {
//		System.setProperty("java.locale.providers", "JRE");
//	}
	
	@Test
	public void testAConsumo()
	{
		TariffaAConsumo t = new TariffaAConsumo("NoSoglia", 0.14);
		Bolletta b = t.creaBolletta(LocalDate.of(2018, 5, 31), 184);	
		mustHave(b, 25.76);
		mustHave(b, 0.77);
		mustHave(b, 2.65);
		mustHave(b, 29.18);		
	}
	
	@Test
	public void testTariffaAConsumoCtor1()
	{
		TariffaAConsumo t = new TariffaAConsumo("Pippo", 0.1);
		assertEquals("Pippo", t.getNome());
		assertEquals(0.1, t.getPrezzoKWh(), 0.01);
	}

}
