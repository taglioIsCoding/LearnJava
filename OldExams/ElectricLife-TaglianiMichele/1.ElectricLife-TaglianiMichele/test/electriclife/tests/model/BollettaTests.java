package electriclife.tests.model;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;

import org.junit.Test;

import electriclife.model.*;

public class BollettaTests
{
//	static {
//		System.setProperty("java.locale.providers", "JRE");
//	}
	
	@Test
	public void testBolletta()
	{
		TariffaAConsumo t = new TariffaAConsumo("MyTariffa", 0.16);
		Bolletta b = new Bolletta(LocalDate.of(2018, 5, 31), t, 123);
		assertEquals("MyTariffa", b.getNomeTariffa());
		assertEquals(2018, b.getDate().getYear());
		assertEquals(5, b.getDate().getMonthValue());
		assertEquals(31, b.getDate().getDayOfMonth());
		assertEquals(123, b.getConsumo());
	}

	@Test
	public void testAddLineaBollettaLineaBolletta()
	{
		TariffaAConsumo t = new TariffaAConsumo("MyTariffa", 0.16);
		Bolletta b = new Bolletta(LocalDate.of(2018, 5, 31), t, 123);
		assertEquals(0, b.getLineeBolletta().size());
		VoceBolletta lb = new VoceBolletta("Ciao", 100);
		b.addLineaBolletta(lb);
		assertEquals(1, b.getLineeBolletta().size());
		assertTrue(b.getLineeBolletta().contains(lb));
	}

	@Test
	public void testStampa()
	{
		TariffaAConsumo t = new TariffaAConsumo("MyTariffa", 0.16);
		Bolletta b = new Bolletta(LocalDate.of(2018, 5, 31), t, 123);
		b.addLineaBolletta(new VoceBolletta("Ciao1", 100));
		b.addLineaBolletta(new VoceBolletta("Ciao2", 200));
		b.addLineaBolletta(new VoceBolletta("Ciao3", 300));
		b.addLineaBolletta(new VoceBolletta("Ciao4", 400));
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);		
		b.stampa(pw);		
		pw.close();
		
		String res = sw.toString();
		
		assertTrue(res.contains("MyTariffa"));
		assertTrue(res.contains("maggio"));
		assertTrue(res.contains("2018"));
		assertTrue(res.contains("123"));
		
		assertTrue(res.contains("Ciao1"));
		assertTrue(res.contains("100"));
		
		assertTrue(res.contains("Ciao2"));
		assertTrue(res.contains("200"));

		assertTrue(res.contains("Ciao3"));
		assertTrue(res.contains("300"));

		assertTrue(res.contains("Ciao4"));
		assertTrue(res.contains("400"));

	}

}
