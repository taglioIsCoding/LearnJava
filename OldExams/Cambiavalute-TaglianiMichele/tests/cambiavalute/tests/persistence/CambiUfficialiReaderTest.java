package cambiavalute.tests.persistence;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import static org.junit.Assert.*;

import org.junit.Test;

import cambiavalute.model.*;
import cambiavalute.persistence.*;

public class CambiUfficialiReaderTest {
	
	@Test
	public void testRead_ShouldSucceed() throws IOException, BadFileFormatException {
		String toRead = "COSTA D AVORIO,Franco CFA,XOF,209,655.957,Quantità di valuta per 1 Euro,CAMBI INDICATIVI CALCOLATI GIORNALMENTE DA BI SULLA BASE DELLE RILEVAZIONI DI MERCATO\n" +
				"COSTA RICA,Colon Costa Rica,CRC,077,585.635205,Quantità di valuta per 1 Euro,CAMBI INDICATIVI CALCOLATI GIORNALMENTE DA BI SULLA BASE DELLE RILEVAZIONI DI MERCATO\n";

		CambiUfficialiReader myReader = new MyCambiUfficialiReader();
		Map<String, TassoDiCambioUfficiale> map = myReader.leggiTabellaCambiUfficiali(new StringReader(toRead));
		
		assertEquals(2, map.size());
		
		TassoDiCambioUfficiale cambio;
		
		cambio = map.get("XOF");
		assertEquals("COSTA D AVORIO", cambio.getNomePaese());
		assertEquals("Franco CFA", cambio.getNomeValuta());
		assertEquals("XOF", cambio.getSiglaValuta());
		assertEquals(655.957, cambio.getTassoDiCambio(), 0);
		
		cambio = map.get("CRC");
		assertEquals("COSTA RICA", cambio.getNomePaese());
		assertEquals("Colon Costa Rica", cambio.getNomeValuta());
		assertEquals("CRC", cambio.getSiglaValuta());
		assertEquals(585.635205, cambio.getTassoDiCambio(), 0);
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_ShouldFailForMissingToken() throws IOException, BadFileFormatException {
		String toRead = "COSTA D AVORIO,XOF,209,655.957,Quantità di valuta per 1 Euro,CAMBI INDICATIVI CALCOLATI GIORNALMENTE DA BI SULLA BASE DELLE RILEVAZIONI DI MERCATO\n" +
				"COSTA RICA,Colon Costa Rica,CRC,077,585.635205,Quantità di valuta per 1 Euro,CAMBI INDICATIVI CALCOLATI GIORNALMENTE DA BI SULLA BASE DELLE RILEVAZIONI DI MERCATO\n";

		CambiUfficialiReader myReader = new MyCambiUfficialiReader();
		myReader.leggiTabellaCambiUfficiali(new StringReader(toRead));
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_ShouldFailForFewTokens() throws BadFileFormatException {
		String toRead = "COSTA D AVORIO,Franco CFA\n" +
				"COSTA RICA,Colon Costa Rica,CRC,077,585.635205,Quantità di valuta per 1 Euro,CAMBI INDICATIVI CALCOLATI GIORNALMENTE DA BI SULLA BASE DELLE RILEVAZIONI DI MERCATO\n";

		CambiUfficialiReader myReader = new MyCambiUfficialiReader();
		myReader.leggiTabellaCambiUfficiali(new StringReader(toRead));
	}

}
