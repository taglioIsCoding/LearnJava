package cupidonline.tests.persistence;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import org.junit.Test;

import cupidonline.model.*;
import cupidonline.persistence.*;

public class MyPreferenzeReaderTests
{

	@Test
	public void testLeggiPreferenza1() throws IOException, BadFileFormatException
	{
		String toRead = "Roberto, F, 20-25, Bilancia, capelli biondi, occhi -, 1.70, 58, Bologna, BO, Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyPreferenzeReader prefReader = new MyPreferenzeReader();
		Map<String,Preferenza> preferenze = prefReader.caricaPreferenze(reader);
		
		Preferenza p = preferenze.get("Roberto");
		
		assertEquals(Sesso.FEMMINA, p.getSesso());
		assertEquals(20, p.getEtaMin());
		assertEquals(25, p.getEtaMax());
		assertEquals(SegnoZodiacale.BILANCIA, p.getSegnoZodiacale().get());
		assertTrue(p.getColoreCapelli().isPresent());
		assertEquals(Colore.BIONDI, p.getColoreCapelli().get());
		assertFalse(p.getColoreOcchi().isPresent());
		assertEquals(1.70, p.getAltezza().get(), 0.01);
		assertEquals(58, p.getPeso().getAsInt());
		assertEquals("Bologna", p.getCitta());
		assertEquals("BO", p.getProvincia());
		assertEquals("Emilia-Romagna", p.getRegione());
	}
	
	@Test
	public void testSegnoZodiacaleErratoOMancante() throws IOException, BadFileFormatException
	{
		String toRead = "Roberto, F, 20-25, Bilanciona, capelli biondi, occhi -, 1.70, 58, Bologna, BO, Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyPreferenzeReader prefReader = new MyPreferenzeReader();
		Map<String,Preferenza> preferenze = prefReader.caricaPreferenze(reader);
		
		Preferenza p = preferenze.get("Roberto");
		
		assertEquals(Sesso.FEMMINA, p.getSesso());
		assertEquals(20, p.getEtaMin());
		assertEquals(25, p.getEtaMax());
		assertFalse(p.getSegnoZodiacale().isPresent());
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRangeEtaErrato1() throws IOException, BadFileFormatException
	{
		String toRead = "Roberto, F, 2X0-25, Bilancia, capelli biondi, occhi -, 1.70, 58, Bologna, BO, Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyPreferenzeReader prefReader = new MyPreferenzeReader();
		Map<String,Preferenza> preferenze = prefReader.caricaPreferenze(reader);
		
		Preferenza p = preferenze.get("Roberto");
		assertEquals(Sesso.FEMMINA, p.getSesso());
		assertEquals(20, p.getEtaMin());
		assertEquals(25, p.getEtaMax());
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRangeEtaErrato2() throws IOException, BadFileFormatException
	{
		String toRead = "Roberto, F, 20=25, Bilancia, capelli biondi, occhi -, 1.70, 58, Bologna, BO, Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyPreferenzeReader prefReader = new MyPreferenzeReader();
		Map<String,Preferenza> preferenze = prefReader.caricaPreferenze(reader);
		
		Preferenza p = preferenze.get("Roberto");
		assertEquals(Sesso.FEMMINA, p.getSesso());
		assertEquals(20, p.getEtaMin());
		assertEquals(25, p.getEtaMax());
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testCapelliKeyowrdErratoOMancante() throws IOException, BadFileFormatException
	{
		String toRead = "Roberto, F, 20-25, Bilancia, capellU biondi, occhi -, 1.70, 58, Bologna, BO, Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyPreferenzeReader prefReader = new MyPreferenzeReader();
		Map<String,Preferenza> preferenze = prefReader.caricaPreferenze(reader);
		
		Preferenza p = preferenze.get("Roberto");
		assertEquals(Sesso.FEMMINA, p.getSesso());
		assertEquals(20, p.getEtaMin());
		assertEquals(25, p.getEtaMax());
		assertFalse(p.getSegnoZodiacale().isPresent());
	}

	@Test
	public void testCapelliErratoOMancante() throws IOException, BadFileFormatException
	{
		String toRead = "Roberto, F, 20-25, Bilancia, capelli ROSSI, occhi -, 1.70, 58, Bologna, BO, Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyPreferenzeReader prefReader = new MyPreferenzeReader();
		Map<String,Preferenza> preferenze = prefReader.caricaPreferenze(reader);
		
		Preferenza p = preferenze.get("Roberto");
		assertEquals(Sesso.FEMMINA, p.getSesso());
		assertEquals(20, p.getEtaMin());
		assertEquals(25, p.getEtaMax());
		assertEquals(SegnoZodiacale.BILANCIA, p.getSegnoZodiacale().get());
		assertFalse(p.getColoreCapelli().isPresent());
		//assertEquals(Colore.BIONDI, p.getColoreCapelli().get());
	}

	@Test(expected=BadFileFormatException.class)
	public void testOcchiKeyowrdErratoOMancante() throws IOException, BadFileFormatException
	{
		String toRead = "Roberto, F, 20-25, Bilancia, capelli biondi, oKKi -, 1.70, 58, Bologna, BO, Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyPreferenzeReader prefReader = new MyPreferenzeReader();
		Map<String,Preferenza> preferenze = prefReader.caricaPreferenze(reader);
		
		Preferenza p = preferenze.get("Roberto");
		assertEquals(Sesso.FEMMINA, p.getSesso());
		assertEquals(20, p.getEtaMin());
		assertEquals(25, p.getEtaMax());
		assertFalse(p.getColoreOcchi().isPresent());
	}

	@Test
	public void testOcchiErratoOMancante() throws IOException, BadFileFormatException
	{
		String toRead = "Roberto, F, 20-25, Bilancia, capelli biondi, occhi -, 1.70, 58, Bologna, BO, Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyPreferenzeReader prefReader = new MyPreferenzeReader();
		Map<String,Preferenza> preferenze = prefReader.caricaPreferenze(reader);
		
		Preferenza p = preferenze.get("Roberto");
		assertEquals(Sesso.FEMMINA, p.getSesso());
		assertEquals(20, p.getEtaMin());
		assertEquals(25, p.getEtaMax());
		assertEquals(SegnoZodiacale.BILANCIA, p.getSegnoZodiacale().get());
		assertEquals(Colore.BIONDI, p.getColoreCapelli().get());
		assertFalse(p.getColoreOcchi().isPresent());
	}
	
	@Test
	public void testOcchiPresenti() throws IOException, BadFileFormatException
	{
		String toRead = "Roberto, F, 20-25, Bilancia, capelli biondi, occhi verdi, 1.70, 58, Bologna, BO, Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyPreferenzeReader prefReader = new MyPreferenzeReader();
		Map<String,Preferenza> preferenze = prefReader.caricaPreferenze(reader);
		
		Preferenza p = preferenze.get("Roberto");
		assertEquals(Sesso.FEMMINA, p.getSesso());
		assertEquals(20, p.getEtaMin());
		assertEquals(25, p.getEtaMax());
		assertEquals(SegnoZodiacale.BILANCIA, p.getSegnoZodiacale().get());
		assertEquals(Colore.BIONDI, p.getColoreCapelli().get());
		assertEquals(Colore.VERDI, p.getColoreOcchi().get());
	}

	@Test
	public void testAltezzaErratoOMancante() throws IOException, BadFileFormatException
	{
		String toRead = "Roberto, F, 20-25, Bilancia, capelli biondi, occhi -, 1.X70, 58, Bologna, BO, Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyPreferenzeReader prefReader = new MyPreferenzeReader();
		Map<String,Preferenza> preferenze = prefReader.caricaPreferenze(reader);
		
		Preferenza p = preferenze.get("Roberto");
		assertEquals(Sesso.FEMMINA, p.getSesso());
		assertEquals(20, p.getEtaMin());
		assertEquals(25, p.getEtaMax());
		assertFalse(p.getAltezza().isPresent());
	}
	
	@Test
	public void testPesoErratoOMancante() throws IOException, BadFileFormatException
	{
		String toRead = "Roberto, F, 20-25, Bilancia, capelli biondi, occhi -, 1.70, 5X8, Bologna, BO, Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyPreferenzeReader prefReader = new MyPreferenzeReader();
		Map<String,Preferenza> preferenze = prefReader.caricaPreferenze(reader);
		
		Preferenza p = preferenze.get("Roberto");
		assertEquals(Sesso.FEMMINA, p.getSesso());
		assertEquals(20, p.getEtaMin());
		assertEquals(25, p.getEtaMax());
		assertFalse(p.getPeso().isPresent());
	}
}
