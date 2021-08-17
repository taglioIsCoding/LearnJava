package mastermind.tests.persistence;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.StringReader;

import org.junit.jupiter.api.Test;

import mastermind.persistence.BadFileFormatException;
import mastermind.persistence.ConfigReader;
import mastermind.persistence.MyConfigReader;

public class MyConfigReaderTest {
	
	@Test
	public void testOK1a() throws BadFileFormatException {
		String fakeFile = "Tentativi=5\r\n" + "Lunghezza combinazione=4";
		StringReader reader = new StringReader(fakeFile);
		ConfigReader configReader = new MyConfigReader(reader);
		assertEquals(4, configReader.dimensioneCodice());
		assertEquals(5, configReader.numeroTentativi());
	}
	@Test
	public void testOK1b() throws BadFileFormatException {
		String fakeFile = "Lunghezza combinazione=4\r\n" + "Tentativi=5";
		StringReader reader = new StringReader(fakeFile);
		ConfigReader configReader = new MyConfigReader(reader);
		assertEquals(4, configReader.dimensioneCodice());
		assertEquals(5, configReader.numeroTentativi());
	}
	
	@Test
	public void testOK2() throws BadFileFormatException {
		String fakeFile = "Tentativi = 5 \r\n" + "  Lunghezza combinazione	=	4";
		StringReader reader = new StringReader(fakeFile);
		ConfigReader configReader = new MyConfigReader(reader);
		assertEquals(4, configReader.dimensioneCodice());
		assertEquals(5, configReader.numeroTentativi());
	}

	@Test
	public void testOK3() throws BadFileFormatException {
		String fakeFile = "tenTAtivi=5\r\n" + "lunghezza Combinazione=4";
		StringReader reader = new StringReader(fakeFile);
		ConfigReader configReader = new MyConfigReader(reader);
		assertEquals(4, configReader.dimensioneCodice());
		assertEquals(5, configReader.numeroTentativi());
	}
	
	@Test
	public void testKO_missingFirstNumber() {
		String fakeFile = "Tentativi=XX\r\n" + "Lunghezza combinazione=4";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_missingSecondNumber() {
		String fakeFile = "Tentativi = 5\r\n" + "Lunghezza combinazione = YY";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_wrongFirstWord1() {
		String fakeFile = "Tentativ = 5\r\n" + "Lunghezza combinazione = 4";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}
	@Test
	public void testKO_wrongFirstWord2() {
		String fakeFile = "Prove=5\r\n" + "Lunghezza combinazione=4";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_wrongSecondWord1() {
		String fakeFile = "Tentativi=5\r\n" + "Lunghezzacombinazione=4";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_wrongSecondWord2() {
		String fakeFile = "Tentativi= 5\r\n" + "Lunghezza  combinazione= 4";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_wrongSecondWord3() {
		String fakeFile = "Tentativi= 5\r\n" + "Lunghezza= 4";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_missingFirstWord() {
		String fakeFile = "5\r\n" + "Lunghezza combinazione= 4";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_missingSecondWord() {
		String fakeFile = "Tentativi= 5\r\n" + "= 4";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_missingFirstSeparator() {
		String fakeFile = "Tentativi 5\r\n" + "Lunghezza combinazione= 4";;
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_missingSecondSeparator() {
		String fakeFile = "Tentativi= 5\r\n" + "Lunghezza combinazione 4";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_missingNewline() {
		String fakeFile = "Tentativi= 5 " + "Lunghezza combinazione= 4";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

}
