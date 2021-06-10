package cambiavalute.tests.persistence;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import static org.junit.Assert.*;

import org.junit.Test;

import cambiavalute.model.*;
import cambiavalute.persistence.*;

public class CambiReaderTest {

	@Test
	public void testRead_ShouldSucceed() throws IOException, BadFileFormatException {
		String toRead = "SEK 9,0227 9,3910\n" +
						"GBP 0,7184 0,7477\n";

		CambiReader myReader = new MyCambiReader();
		Map<String, TassiDiCambio> map = myReader.leggiTabellaCambiApplicati(new StringReader(toRead));
		
		assertEquals(2, map.size());
		
		TassiDiCambio tipo;
		
		tipo = map.get("SEK");
		assertEquals("SEK", tipo.getSiglaValuta());
		assertEquals(9.0227, tipo.getPrezzoVendita(), 0);
		assertEquals(9.3910, tipo.getPrezzoAcquisto(), 0);
		
		tipo = map.get("GBP");
		assertEquals("GBP", tipo.getSiglaValuta());
		assertEquals(0.7184, tipo.getPrezzoVendita(), 0);
		assertEquals(0.7477, tipo.getPrezzoAcquisto(), 0);
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_ShouldFailForFewTokens() throws IOException, BadFileFormatException {
		String toRead = "SEK 9,0227\n" +
						"GBP 0,7184 0,7477\n";


		CambiReader myReader = new MyCambiReader();
		myReader.leggiTabellaCambiApplicati(new StringReader(toRead));
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_ShouldFailForBadNumber() throws IOException, BadFileFormatException {
		String toRead = "SEK 9,0227 XXX\n" +
						"GBP 0,7184 0,7477\n";


		CambiReader myReader = new MyCambiReader();
		myReader.leggiTabellaCambiApplicati(new StringReader(toRead));
	}
}
