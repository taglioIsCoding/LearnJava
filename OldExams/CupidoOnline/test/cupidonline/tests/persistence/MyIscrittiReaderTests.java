package cupidonline.tests.persistence;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import org.junit.Test;

import cupidonline.model.*;
import cupidonline.persistence.*;

public class MyIscrittiReaderTests
{

	@Test
	public void testLeggiTariffe() throws IOException, BadFileFormatException
	{
		String toRead = "Roberto, M, 1994-04-24, capelli neri, occhi castani, 1.78, 61, Bologna, BO,Emilia-Romagna\r\n" + 
				"Armando, M, 1997-08-01, capelli castani, occhi castani, 1.71, 65, Parma, PR,Emilia-Romagna\r\n" + 
				"Eufrasio, M, 1993-10-30, capelli biondi, occhi azzurri, 1.82, 66, Firenze, FI, Toscana\r\n" + 
				"Anna, F, 1996-06-18, capelli biondi, occhi azzurri,  1.70, 60, Imola, BO, Emilia-Romagna\r\n" + 
				"Ludovica, F, 1992-10-14, capelli neri, occhi castani,	1.75, 51, Bologna, BO,Emilia-Romagna\r\n" + 
				"Elena, F, 1999-04-10, capelli neri, occhi neri,	1.65, 57, Modena, MO,Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyIscrittiReader iscrittiReader = new MyIscrittiReader();
		Map<String,Persona> persone = iscrittiReader.caricaIscritti(reader);
		
		assertEquals(6, persone.size());
		// persone.forEach(System.out::println);
		
		// "Roberto, M, 1994-04-24, Toro, capelli neri, occhi castani, 1.78, 61, Bologna, BO,Emilia-Romagna\r\n"
		Persona t = persone.get("Roberto");	
		assertEquals("Roberto", t.getId());
		assertEquals(Sesso.MASCHIO, t.getSesso());
		assertEquals(  24, t.getDataNascita().getDayOfMonth());
		assertEquals(   4, t.getDataNascita().getMonthValue());
		assertEquals(1994, t.getDataNascita().getYear());
		assertEquals(SegnoZodiacale.TORO, t.getSegnoZodiacale());
		assertEquals(Colore.NERI, t.getColoreCapelli());
		assertEquals(Colore.CASTANI, t.getColoreOcchi());
		assertEquals(1.78, t.getAltezza(),0.01);
		assertEquals(61, t.getPeso());
		assertEquals("Bologna", t.getCitta());
		assertEquals("BO", t.getProvincia());
		assertEquals("Emilia-Romagna", t.getRegione());
		
		// "Armando, M, 1997-08-01, Leone, capelli castani, occhi castani, 1.71, 65, Parma, PR,Emilia-Romagna\r\n" 
		t = persone.get("Armando");	
		assertEquals("Armando", t.getId());
		assertEquals(Sesso.MASCHIO, t.getSesso());
		assertEquals(   1, t.getDataNascita().getDayOfMonth());
		assertEquals(   8, t.getDataNascita().getMonthValue());
		assertEquals(1997, t.getDataNascita().getYear());
		assertEquals(SegnoZodiacale.LEONE, t.getSegnoZodiacale());
		assertEquals(Colore.CASTANI, t.getColoreCapelli());
		assertEquals(Colore.CASTANI, t.getColoreOcchi());
		assertEquals(1.71, t.getAltezza(),0.01);
		assertEquals(65, t.getPeso());
		assertEquals("Parma", t.getCitta());
		assertEquals("PR", t.getProvincia());
		assertEquals("Emilia-Romagna", t.getRegione());

		//  "Anna, F, 1996-06-18, Gemelli, 	capelli biondi, occhi azzurri, 	1.70, 60, Imola, BO, Emilia-Romagna\r\n" +  
		t = persone.get("Anna");	
		assertEquals("Anna", t.getId());
		assertEquals(Sesso.FEMMINA, t.getSesso());
		assertEquals(  18, t.getDataNascita().getDayOfMonth());
		assertEquals(   6, t.getDataNascita().getMonthValue());
		assertEquals(1996, t.getDataNascita().getYear());
		assertEquals(SegnoZodiacale.GEMELLI, t.getSegnoZodiacale());
		assertEquals(Colore.BIONDI, t.getColoreCapelli());
		assertEquals(Colore.AZZURRI, t.getColoreOcchi());
		assertEquals(1.70, t.getAltezza(),0.01);
		assertEquals(60, t.getPeso());
		assertEquals("Imola", t.getCitta());
		assertEquals("BO", t.getProvincia());
		assertEquals("Emilia-Romagna", t.getRegione());
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testLeggiTariffe_Error1() throws IOException, BadFileFormatException
	{
		String toRead = "RobertoSenzaCapelli, M, 1994-04-24, occhi castani, 1.78, 61, Bologna, BO,Emilia-Romagna\r\n" + // manca capelli 
				"Armando, M, 1997-08-01, capelli castani, occhi castani, 1.71, 65, Parma, PR,Emilia-Romagna\r\n" + 
				"Eufrasio, M, 1993-10-30, capelli biondi, occhi azzurri, 1.82, 66, Firenze, FI, Toscana\r\n" + 
				"Anna, 	 F, 1996-06-18, capelli biondi, occhi azzurri, 	1.70, 60, Imola, BO, Emilia-Romagna\r\n" + 
				"Ludovica, F, 1992-10-14, capelli neri, occhi castani,	1.75, 51, Bologna, BO,Emilia-Romagna\r\n" + 
				"Elena, F, 1999-04-10, capelli neri, occhi neri,	1.65, 57, Modena, MO,Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyIscrittiReader tariffaReader = new MyIscrittiReader();
		tariffaReader.caricaIscritti(reader);
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testLeggiTariffe_Error2() throws IOException, BadFileFormatException
	{
		String toRead = "RobertoAltezzaRotta, M, 1994-04-24, capelli neri, occhi castani, 1.7X, 61, Bologna, BO,Emilia-Romagna\r\n" + // number format in altezza 
				"Armando, M, 1997-08-01, capelli castani, occhi castani, 1.71, 65, Parma, PR,Emilia-Romagna\r\n" + 
				"Eufrasio, M, 1993-10-30, capelli biondi, occhi azzurri, 1.82, 66, Firenze, FI, Toscana\r\n" + 
				"Anna, 	 F, 1996-06-18, capelli biondi, occhi azzurri, 	1.70, 60, Imola, BO, Emilia-Romagna\r\n" + 
				"Ludovica, F, 1992-10-14, capelli neri, occhi castani,	1.75, 51, Bologna, BO,Emilia-Romagna\r\n" + 
				"Elena, F, 1999-04-10, capelli neri, occhi neri,	1.65, 57, Modena, MO,Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyIscrittiReader tariffaReader = new MyIscrittiReader();
		tariffaReader.caricaIscritti(reader);
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testLeggiTariffe_Error3() throws IOException, BadFileFormatException
	{
		String toRead = "RobertoPesoRotto, M, 1994-04-24, capelli neri, occhi castani, 1.78, 61Y, Bologna, BO,Emilia-Romagna\r\n" + // number format in peso 
				"Armando, M, 1997-08-01, capelli castani, occhi castani, 1.71, 65, Parma, PR,Emilia-Romagna\r\n" + 
				"Eufrasio, M, 1993-10-30, capelli biondi, occhi azzurri, 1.82, 66, Firenze, FI, Toscana\r\n" + 
				"Anna, 	 F, 1996-06-18, capelli biondi, occhi azzurri, 	1.70, 60, Imola, BO, Emilia-Romagna\r\n" + 
				"Ludovica, F, 1992-10-14, capelli neri, occhi castani,	1.75, 51, Bologna, BO,Emilia-Romagna\r\n" + 
				"Elena, F, 1999-04-10, capelli neri, occhi neri,	1.65, 57, Modena, MO,Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyIscrittiReader tariffaReader = new MyIscrittiReader();
		tariffaReader.caricaIscritti(reader);
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testLeggiTariffe_Error4() throws IOException, BadFileFormatException
	{
		String toRead = "RobertoDataRotta, M, 1994-0U4-24, capelli neri, occhi castani, 1.78, 61, Bologna, BO,Emilia-Romagna\r\n" + // parseformat in data
				"Armando, M, 1997-08-01, capelli castani, occhi castani, 1.71, 65, Parma, PR,Emilia-Romagna\r\n" + 
				"Eufrasio, M, 1993-10-30, capelli biondi, occhi azzurri, 1.82, 66, Firenze, FI, Toscana\r\n" + 
				"Anna, 	 F, 1996-06-18, capelli biondi, occhi azzurri, 	1.70, 60, Imola, BO, Emilia-Romagna\r\n" + 
				"Ludovica, F, 1992-10-14, capelli neri, occhi castani,	1.75, 51, Bologna, BO,Emilia-Romagna\r\n" + 
				"Elena, F, 1999-04-10, capelli neri, occhi neri,	1.65, 57, Modena, MO,Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyIscrittiReader tariffaReader = new MyIscrittiReader();
		tariffaReader.caricaIscritti(reader);
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testLeggiTariffe_Error5() throws IOException, BadFileFormatException
	{
		String toRead = "RobertoCavillo, M, 1994-04-24, cavillo neri, occhi castani, 1.78, 61, Bologna, BO,Emilia-Romagna\r\n" + // keyword errata 
				"Armando, M, 1997-08-01, capelli castani, occhi castani, 1.71, 65, Parma, PR,Emilia-Romagna\r\n" + 
				"Eufrasio, M, 1993-10-30, capelli biondi, occhi azzurri, 1.82, 66, Firenze, FI, Toscana\r\n" + 
				"Anna, 	 F, 1996-06-18, capelli biondi, occhi azzurri, 	1.70, 60, Imola, BO, Emilia-Romagna\r\n" + 
				"Ludovica, F, 1992-10-14, capelli neri, occhi castani,	1.75, 51, Bologna, BO,Emilia-Romagna\r\n" + 
				"Elena, F, 1999-04-10, capelli neri, occhi neri,	1.65, 57, Modena, MO,Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyIscrittiReader tariffaReader = new MyIscrittiReader();
		tariffaReader.caricaIscritti(reader);
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testLeggiTariffe_Error6() throws IOException, BadFileFormatException
	{
		String toRead = "RobertoOculi, M, 1994-04-24, capelli neri, oculi castani, 1.78, 61, Bologna, BO,Emilia-Romagna\r\n" + // keyword errata 
				"Armando, M, 1997-08-01, capelli castani, occhi castani, 1.71, 65, Parma, PR,Emilia-Romagna\r\n" + 
				"Eufrasio, M, 1993-10-30, capelli biondi, occhi azzurri, 1.82, 66, Firenze, FI, Toscana\r\n" + 
				"Anna, 	 F, 1996-06-18, capelli biondi, occhi azzurri, 	1.70, 60, Imola, BO, Emilia-Romagna\r\n" + 
				"Ludovica, F, 1992-10-14, capelli neri, occhi castani,	1.75, 51, Bologna, BO,Emilia-Romagna\r\n" + 
				"Elena, F, 1999-04-10, capelli neri, occhi neri,	1.65, 57, Modena, MO,Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyIscrittiReader tariffaReader = new MyIscrittiReader();
		tariffaReader.caricaIscritti(reader);
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testLeggiTariffe_Error8() throws IOException, BadFileFormatException
	{
		String toRead = "RobertoOcchiCapelli, M, 1994-04-24, occhi castani, capelli neri, 1.78, 61, Bologna, BO,Emilia-Romagna\r\n" + // sequenza keyword 
				"Armando, M, 1997-08-01, capelli castani, occhi castani, 1.71, 65, Parma, PR,Emilia-Romagna\r\n" + 
				"Eufrasio, M, 1993-10-30, capelli biondi, occhi azzurri, 1.82, 66, Firenze, FI, Toscana\r\n" + 
				"Anna, 	 F, 1996-06-18, capelli biondi, occhi azzurri, 	1.70, 60, Imola, BO, Emilia-Romagna\r\n" + 
				"Ludovica, F, 1992-10-14, capelli neri, occhi castani,	1.75, 51, Bologna, BO,Emilia-Romagna\r\n" + 
				"Elena, F, 1999-04-10, capelli neri, occhi neri,	1.65, 57, Modena, MO,Emilia-Romagna\r\n" ;
		
		StringReader reader = new StringReader(toRead);
		MyIscrittiReader tariffaReader = new MyIscrittiReader();
		tariffaReader.caricaIscritti(reader);
	}
	
}
