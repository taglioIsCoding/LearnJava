package ghigliottina.persistence.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import ghigliottina.model.Ghigliottina;
import ghigliottina.persistence.BadFileFormatException;
import ghigliottina.persistence.GhigliottineReader;
import ghigliottina.persistence.MyGhigliottineReader;


class GhigliottineReaderTest2 {
	
	// questi test ulteriori verificano che il reader legga anche ghigliottine "non standard",
	// ossia con numero di indizi diverso dai classici cinque
	
	@Test
	public void testOK_UnaGhigliottinaConSolo4Indizi() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Risposta esatta=SPIRITO   \r\n" + 
				"------------------------";
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		List<Ghigliottina> list = ghReader.readAll(new BufferedReader(fakeReader));		
		assertEquals(1, list.size());
	}
	
	@Test
	public void testOK_DueGhigliottineCondDiversiNumeriDiIndizi() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO=FIRST\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Risposta esatta=SPIRITO   \r\n" + 
				"------------------------\r\n" + 
				"CANTATO/INCANTATO=SECOND\r\n" + 
				"DON/DIN=FIRST\r\n" + 
				"DON/DIN=FIRST\r\n" + 
				"FRUTTO/BRUTTO=FIRST\r\n" + 
				"TIGLIO/TAGLIO=SECOND\r\n" + 
				"CAPPUCCETTO ROSSO/CENERENTOLA=FIRST\r\n" + 
				"Risposta esatta=BOSCO\r\n" + 
				"------------------------";
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		List<Ghigliottina> list = ghReader.readAll(new BufferedReader(fakeReader));		
		assertEquals(2, list.size());
	}
}
