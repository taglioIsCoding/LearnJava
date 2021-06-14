package bikerent.tests.persistence;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalTime;
import java.util.Map;

import org.junit.Test;

import bikerent.model.*;
import bikerent.persistence.*;

public class MyTariffeReaderTests
{

	@Test
	public void testLeggiTariffe() throws IOException, BadFileFormatException
	{
		String toRead =
				"Bologna, 59 cent per 20 minuti, poi 99 cent per 30 minuti, max 12 ore, sanzione 7.50 euro\r\n" + 
				"Reggio Emilia, 30 cent per 30 minuti, poi 50 cent per 30 minuti, max entro 23:59, sanzione 7 euro\r\n" + 
				"";
		
		StringReader reader = new StringReader(toRead);
		RateReader iscrittiReader = new MyRateReader();
		Map<String,Rate> mappaTariffe = iscrittiReader.readRates(reader);
		
		assertEquals(2, mappaTariffe.size());
		
		Rate bologna = mappaTariffe.get("Bologna");
		assertEquals(59,bologna.getCostoPrimoPeriodo(), 0.01);
		assertEquals(99,bologna.getCostoPeriodiSuccessivi(), 0.01);
		assertEquals(20,bologna.getDurataPrimoPeriodo().toMinutes());
		assertEquals(30,bologna.getDurataPeriodiSuccessivi().toMinutes());
		assertEquals(12,bologna.getDurataMax().get().toHours());
		assertEquals(7.50, bologna.getSanzione(), 0.01);
		
		Rate reggio = mappaTariffe.get("Reggio Emilia");
		assertEquals(30,reggio.getCostoPrimoPeriodo(), 0.01);
		assertEquals(50,reggio.getCostoPeriodiSuccessivi(), 0.01);
		assertEquals(30,reggio.getDurataPrimoPeriodo().toMinutes());
		assertEquals(30,reggio.getDurataPeriodiSuccessivi().toMinutes());
		assertEquals(LocalTime.of(23,59), reggio.getOrarioMax().get());
		assertEquals(7.00, reggio.getSanzione(), 0.01);
	}	
}
