package electriclife.tests.persistence;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Test;

import electriclife.model.*;
import electriclife.persistence.*;

public class MyTariffaReaderTests
{

	@Test
	public void testLeggiTariffe() throws IOException, BadFileFormatException
	{
		String toRead = "FLAT ; CASA MINI  ; SOGLIA 150; € 20,00; KWh EXTRA € 0,25\n" +
				"FLAT; CASA CLASSIC;SOGLIA 250 ; € 30,00; KWh EXTRA € 0,24\n"+
				"A CONSUMO ; € 0,14\n";
//		String toRead = 
//				"A CONSUMO ; € 0,14\n" +
//				"FLAT; CASA CLASSIC;SOGLIA 250 ; € 30,00; KWh EXTRA € 0,24\n"+
//				"FLAT ; CASA MINI  ; SOGLIA 150; € 20,00; KWh EXTRA € 0,25\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyTariffeReader tariffaReader = new MyTariffeReader(reader);
		ArrayList<Tariffa> tariffe = new ArrayList<Tariffa>(tariffaReader.caricaTariffe());
		tariffe.sort(Comparator.comparing(Tariffa::getNome).reversed());
		
		assertEquals(3, tariffe.size());
		
		Tariffa t = tariffe.get(0);	
		TariffaFlat tf = (TariffaFlat)t;
		assertEquals("CASA MINI", t.getNome());
		assertEquals(150, tf.getSogliaMensile(), 0.01);
		assertEquals(20, tf.getQuotaFissaMensile(), 0.01);
		assertEquals(0.25, tf.getPrezzoKWhExtra(), 0.005);
		
		t = tariffe.get(1);	
		tf = (TariffaFlat)t;
		assertEquals("CASA CLASSIC", t.getNome());
		assertEquals(250, tf.getSogliaMensile(), 0.01);
		assertEquals(30, tf.getQuotaFissaMensile(), 0.01);
		assertEquals(0.24, tf.getPrezzoKWhExtra(), 0.005);

		t = tariffe.get(2);
		TariffaAConsumo tc = (TariffaAConsumo)t;
		assertEquals("A CONSUMO", t.getNome());
		assertEquals(0.14, tc.getPrezzoKWh(), 0.005);
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testLeggiTariffe_Error1() throws IOException, BadFileFormatException
	{
		String toRead = 
				"FLAT ; CASA MINI  ; SOGLIA 150; € 20,00\n" +	//Un campo in meno per tariffa FLAT
				"FLAT; CASA CLASSIC;SOGLIA 250 ; € 30,00; KWh EXTRA € 0,24\n"+
				"A CONSUMO ; € 0,14\n";
		
		StringReader reader = new StringReader(toRead);
		MyTariffeReader tariffaReader = new MyTariffeReader(reader);
		ArrayList<Tariffa> tariffe = new ArrayList<Tariffa>(tariffaReader.caricaTariffe());
		tariffe.sort(Comparator.comparing(Tariffa::getNome).reversed());
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testLeggiTariffe_Error2() throws IOException, BadFileFormatException
	{
		String toRead = 
				"FLAT ; CASA MINI  ; SOGLIA 1X50; € 20,00; KWh EXTRA € 0,25\n" + //NumberFormatException
				"FLAT; CASA CLASSIC;SOGLIA 250 ; € 30,00; KWh EXTRA € 0,24\n"+
				"A CONSUMO ; € 0,14\n";
		
		StringReader reader = new StringReader(toRead);
		MyTariffeReader tariffaReader = new MyTariffeReader(reader);
		ArrayList<Tariffa> tariffe = new ArrayList<Tariffa>(tariffaReader.caricaTariffe());
		tariffe.sort(Comparator.comparing(Tariffa::getNome).reversed());
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testLeggiTariffe_Error3() throws IOException, BadFileFormatException
	{
		String toRead = 
				"FLAT ; CASA MINI  ; SOGLIA 150; € 20X,00; KWh EXTRA € 0,25\n" + //NumberFormatException
				"FLAT; CASA CLASSIC;SOGLIA 250 ; € 30,00; KWh EXTRA € 0,24\n"+
				"A CONSUMO ; € 0,14\n";
		
		StringReader reader = new StringReader(toRead);
		MyTariffeReader tariffaReader = new MyTariffeReader(reader);
		ArrayList<Tariffa> tariffe = new ArrayList<Tariffa>(tariffaReader.caricaTariffe());
		tariffe.sort(Comparator.comparing(Tariffa::getNome).reversed());
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testLeggiTariffe_Error4() throws IOException, BadFileFormatException
	{
		String toRead = 
				"FLAT ; CASA MINI  ; SOGLIA 150; € 20,00; KWh EXTRA € 0,X25\n" + //NumberFormatException
				"FLAT; CASA CLASSIC;SOGLIA 250 ; € 30,00; KWh EXTRA € 0,24\n"+
				"A CONSUMO ; € 0,14\n";
		
		StringReader reader = new StringReader(toRead);
		MyTariffeReader tariffaReader = new MyTariffeReader(reader);
		ArrayList<Tariffa> tariffe = new ArrayList<Tariffa>(tariffaReader.caricaTariffe());
		tariffe.sort(Comparator.comparing(Tariffa::getNome).reversed());
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testLeggiTariffe_Error5() throws IOException, BadFileFormatException
	{
		String toRead = 
				"FLAT ; CASA MINI  ; SOOGLIA 150; € 20,00; KWh EXTRA € 0,25\n" + //Parola chiave errata (SOGLIA)
				"FLAT; CASA CLASSIC;SOGLIA 250 ; € 30,00; KWh EXTRA € 0,24\n"+
				"A CONSUMO ; € 0,14\n";
		
		StringReader reader = new StringReader(toRead);
		MyTariffeReader tariffaReader = new MyTariffeReader(reader);
		ArrayList<Tariffa> tariffe = new ArrayList<Tariffa>(tariffaReader.caricaTariffe());
		tariffe.sort(Comparator.comparing(Tariffa::getNome).reversed());
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testLeggiTariffe_Error6() throws IOException, BadFileFormatException
	{
		String toRead = 
				"FLAT ; CASA MINI  ; SOGLIA 150; € 20,00; KWWh EXTRA € 0,25\n" + //Parola chiave errata (KWWh)
				"FLAT; CASA CLASSIC;SOGLIA 250 ; € 30,00; KWh EXTRA € 0,24\n"+
				"A CONSUMO ; € 0,14\n";
		
		StringReader reader = new StringReader(toRead);
		MyTariffeReader tariffaReader = new MyTariffeReader(reader);
		ArrayList<Tariffa> tariffe = new ArrayList<Tariffa>(tariffaReader.caricaTariffe());
		tariffe.sort(Comparator.comparing(Tariffa::getNome).reversed());
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testLeggiTariffe_Error7() throws IOException, BadFileFormatException
	{
		String toRead = 
				"FLAT ; CASA MINI  ; SOGLIA 150; € 20,00; KWh EXXTRA € 0,25\n" + //Parola chiave errata (EXXTRA)
				"FLAT; CASA CLASSIC;SOGLIA 250 ; € 30,00; KWh EXTRA € 0,24\n"+
				"A CONSUMO ; € 0,14\n";
		
		StringReader reader = new StringReader(toRead);
		MyTariffeReader tariffaReader = new MyTariffeReader(reader);
		ArrayList<Tariffa> tariffe = new ArrayList<Tariffa>(tariffaReader.caricaTariffe());
		tariffe.sort(Comparator.comparing(Tariffa::getNome).reversed());
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testLeggiTariffe_Error8() throws IOException, BadFileFormatException
	{
		String toRead = 
				"FLAT ; CASA MINI  ; SOGLIA 150; € 20,00; KWh  EXTRA € 0,25\n" + //Sequenza parole chiave errata (KWh  EXTRA)
				"FLAT; CASA CLASSIC;SOGLIA 250 ; € 30,00; KWh EXTRA € 0,24\n"+
				"A CONSUMO ; € 0,14\n";
		
		StringReader reader = new StringReader(toRead);
		MyTariffeReader tariffaReader = new MyTariffeReader(reader);
		ArrayList<Tariffa> tariffe = new ArrayList<Tariffa>(tariffaReader.caricaTariffe());
		tariffe.sort(Comparator.comparing(Tariffa::getNome).reversed());
	}

	
	@Test(expected=BadFileFormatException.class)
	public void testLeggiTariffe_Error9() throws IOException, BadFileFormatException
	{
		String toRead =  
				"FLAT ; CASA MINI  ; SOGLIA 150; € 20,00; KWh EXTRA € 0,25\n" +
				"FLAT; CASA CLASSIC;SOGLIA 250 ; € 30,00; KWh EXTRA € 0,24\n"+
				"A CONSUMO ; € 0X,14\n"; //NumberFormatException
		
		StringReader reader = new StringReader(toRead);
		MyTariffeReader tariffaReader = new MyTariffeReader(reader);
		ArrayList<Tariffa> tariffe = new ArrayList<Tariffa>(tariffaReader.caricaTariffe());
		tariffe.sort(Comparator.comparing(Tariffa::getNome).reversed());
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testLeggiTariffe_Error10() throws IOException, BadFileFormatException
	{
		String toRead =  
				"FLAT ; CASA MINI  ; SOGLIA 150; € 20,00; KWh EXTRA € 0,25\n" +
				"A CONSUMO ; € 0,14\n" + "A CONSUMO ; € 0,16\n" + // tariffa a consumo duplicata
				"FLAT; CASA CLASSIC;SOGLIA 250 ; € 30,00; KWh EXTRA € 0,24\n";
		
		StringReader reader = new StringReader(toRead);
		MyTariffeReader tariffaReader = new MyTariffeReader(reader);
		ArrayList<Tariffa> tariffe = new ArrayList<Tariffa>(tariffaReader.caricaTariffe());
		tariffe.sort(Comparator.comparing(Tariffa::getNome).reversed());
	}

}
