package electriclife.tests.model;

import static electriclife.tests.model.BollettaTestUtils.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import electriclife.model.*;

public class TariffaFlatTests
{
//	static {
//		System.setProperty("java.locale.providers", "JRE");
//	}
	
	@Test
	public void testFlat_Soglia_SottoSoglia()
	{
		TariffaFlat t = new TariffaFlat("Flat", 30, 250, 0.24); 
		Bolletta bolletta = t.creaBolletta(LocalDate.of(2018, 5, 31), 184);	
		mustHave(bolletta, 30);
		mustHave(bolletta, 0.77);
		mustHave(bolletta, 3.08);
		mustHave(bolletta, 33.85);		
	}
	
	@Test
	public void testFlat_Soglia_SopraSoglia()
	{
		TariffaFlat t = new TariffaFlat("Flat", 30, 250, 0.24); 
		Bolletta bolletta = t.creaBolletta(LocalDate.of(2018, 5, 31), 284);	
		mustHave(bolletta, 30);
		mustHave(bolletta, 8.16);
		mustHave(bolletta, 4.12);
		mustHave(bolletta, 3.04);
		mustHave(bolletta, 45.32);		
	}

	@Test
	public void testTariffaAFlatCtor()
	{
		TariffaFlat t = new TariffaFlat("Pluto", 20, 10, 1);
		assertEquals("Pluto", t.getNome());
		assertEquals(1, t.getPrezzoKWhExtra(), 0.01);
		assertEquals(20, t.getQuotaFissaMensile(), 0.01);
		assertEquals(10, t.getSogliaMensile(), 0.01);
	}
}
