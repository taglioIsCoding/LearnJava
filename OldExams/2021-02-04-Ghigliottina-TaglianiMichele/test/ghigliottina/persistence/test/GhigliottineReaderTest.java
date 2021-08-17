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


class GhigliottineReaderTest {
	
	@Test
	public void testOK_UnaSolaGhigliottina() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO=FIRST\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
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
	public void testOK_UnaSolaGhigliottinaConSpaziVari() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO = FIRST  \r\n" + 
				"PATATA /  CAROTA=FIRST\r\n" + 
				"SOPRA\t/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO\t=\tSECOND\t\r\n" + 
				"  FANTASMA/ASMA=FIRST\r\n" + 
				"\tRisposta esatta=SPIRITO   \r\n" + 
				"------------------------";
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		List<Ghigliottina> list = ghReader.readAll(new BufferedReader(fakeReader));		
		assertEquals(1, list.size());
	}
	
	@Test
	public void testOK_DueGhigliottine() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO=FIRST\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Risposta esatta=SPIRITO   \r\n" + 
				"------------------------\r\n" + 
				"CANTATO/INCANTATO=SECOND\r\n" + 
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
	
	@Test
	public void testKO_UnaSolaGhigliottinaSenzaTrattiniFinali() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO=FIRST\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Risposta esatta=SPIRITO   \r\n";
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}

	@Test
	public void testKO_DueGhigliottineConTrattiniMancantiInMezzo() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO=FIRST\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Risposta esatta=SPARUTO   \r\n" +
				"CANTATO/INCANTATO=SECOND\r\n" + 
				"DON/DIN=FIRST\r\n" + 
				"FRUTTO/BRUTTO=FIRST\r\n" + 
				"TIGLIO/TAGLIO=SECOND\r\n" + 
				"CAPPUCCETTO ROSSO/CENERENTOLA=FIRST\r\n" + 
				"Risposta esatta=BOSCO\r\n" + 
				"------------------------"; 
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}
	
	@Test
	public void testKO_UnaSolaGhigliottinaSenzaRispostaEsatta() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO=FIRST\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"------------------------"; 
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}
	
	@Test
	public void testKO_UnaSolaGhigliottinaConRispostaSbagliata_caso1() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO=FIRST\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"RISPosta esatta=SPIRITO   \r\n"+ 
				"------------------------"; 
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}

	@Test
	public void testKO_UnaSolaGhigliottinaConRispostaSbagliata_caso2() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO=FIRST\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Risposta Esatta=SPIRITO   \r\n"+ 
				"------------------------"; 
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}
	
	@Test
	public void testKO_UnaSolaGhigliottinaConRispostaSbagliata_caso3() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO=FIRST\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"RispostaEsatta=SPIRITO   \r\n"+ 
				"------------------------"; 
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}
	
	@Test
	public void testKO_UnaSolaGhigliottinaConRispostaSbagliata_caso4() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO=FIRST\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Rispostaesatta=SPIRITO   \r\n"+ 
				"------------------------"; 
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}
	
	@Test
	public void testKO_UnaSolaGhigliottinaConRispostaSbagliata_caso5() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO=FIRST\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Risposta\tesatta=SPIRITO   \r\n"+ 
				"------------------------"; 
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}
	
	@Test
	public void testKO_UnaSolaGhigliottinaConRispostaSbagliata_caso6() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO=FIRST\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Risposta esatta SPIRITO   \r\n"+ 
				"------------------------"; 
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}
	
	@Test
	public void testKO_UnaSolaGhigliottinaConTernaSbagliata_caso1() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE DECRETO=FIRST\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Risposta esatta=SPIRITO   \r\n"+ 
				"------------------------"; 
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}
	
	@Test
	public void testKO_UnaSolaGhigliottinaConTernaSbagliata_caso2() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE\\DECRETO=FIRST\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Risposta esatta=SPIRITO   \r\n"+ 
				"------------------------"; 
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}
	
	@Test
	public void testKO_UnaSolaGhigliottinaConTernaSbagliata_caso3() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE\tDECRETO=FIRST\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Risposta esatta=SPIRITO   \r\n"+ 
				"------------------------"; 
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}
	
	@Test
	public void testKO_UnaSolaGhigliottinaConTernaSbagliata_caso4() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO FIRST\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Risposta esatta=SPIRITO   \r\n"+ 
				"------------------------"; 
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}
	
	@Test
	public void testKO_UnaSolaGhigliottinaConTernaSbagliata_caso5() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETOFIRST\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Risposta esatta=SPIRITO   \r\n"+ 
				"------------------------"; 
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}
	
	@Test
	public void testKO_UnaSolaGhigliottinaConTernaSbagliata_caso6() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO=first\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Risposta esatta=SPIRITO   \r\n"+ 
				"------------------------"; 
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}
	
	@Test
	public void testKO_UnaSolaGhigliottinaConTernaSbagliata_caso7() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO=First\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=SECOND\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Risposta esatta=SPIRITO   \r\n"+ 
				"------------------------"; 
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}
	
	@Test
	public void testKO_UnaSolaGhigliottinaConTernaSbagliata_caso8() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO=FIRST\r\n" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=second\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Risposta esatta=SPIRITO   \r\n"+ 
				"------------------------"; 
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}
	
	@Test
	public void testKO_UnaSolaGhigliottinaConTernaSenzaNewline() throws BadFileFormatException, IOException {
		String fakeFile = 
				"LEGGE/DECRETO=FIRST" + 
				"PATATA/CAROTA=FIRST\r\n" + 
				"SOPRA/SOTTO=second\r\n" + 
				"FATICA/SACRIFICIO=SECOND\r\n" + 
				"FANTASMA/ASMA=FIRST\r\n" + 
				"Risposta esatta=SPIRITO   \r\n"+ 
				"------------------------"; 
		StringReader fakeReader = new StringReader(fakeFile);
		GhigliottineReader ghReader = new MyGhigliottineReader();
		assertThrows(BadFileFormatException.class, () -> ghReader.readAll(new BufferedReader(fakeReader)));		
	}
	
}
