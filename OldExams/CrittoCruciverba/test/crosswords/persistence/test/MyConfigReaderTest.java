package crosswords.persistence.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.StringReader;



import crosswords.model.Scheme;
import crosswords.persistence.BadFileFormatException;
import crosswords.persistence.ConfigReader;
import crosswords.persistence.MyConfigReader;

public class MyConfigReaderTest {
	
	@Test
	public void testOK1_BasicUnconfigured() throws BadFileFormatException, IOException {
		String fakeFile = "DIM 12x12\r\n" + 
				"1 2 3 4 5 3 6 6 7 # 8 2\r\n" + 
				"9 10 # 6 3 8 # 11 12 2 4 7\r\n" + 
				"10 4 12 # 13 # 9 14 # 7 # 14\r\n" + 
				"# 12 10 15 # # 4 10 10 2 # 11\r\n" + 
				"16 10 1 1 10 9 3 12 16 7 # 1\r\n" + 
				"15 14 17 4 3 13 15 2 15 5 3 #\r\n" + 
				"12 # 10 10 4 # 10 7 # 15 7 4\r\n" + 
				"16 15 4 3 7 # # 5 18 # 5 3\r\n" + 
				"10 # 10 5 2 3 4 7 # # 7 19\r\n" + 
				"14 6 # 3 15 4 3 # 14 3 # 19\r\n" + 
				"9 10 17 # 17 7 12 12 3 13 15 10\r\n" + 
				"3 18 9 4 10 1 15 1 9 7 5 3";
		StringReader reader = new StringReader(fakeFile);
		ConfigReader configReader = new MyConfigReader(reader);
		assertEquals(12, configReader.getSize());
		Scheme scheme = configReader.getScheme();
		assertTrue(scheme.isConfigured());
	}

	@Test
	public void testKO_wrongFirstLine_uncapitalisedDim() throws BadFileFormatException {
		String fakeFile = "dim 12x12\r\n" + 
				"1 2 3 4 5 3 6 6 7 # 8 2\r\n" + 
				"9 10 # 6 3 8 # 11 12 2 4 7\r\n" + 
				"10 4 12 # 13 # 9 14 # 7 # 14\r\n" + 
				"# 12 10 15 # # 4 10 10 2 # 11\r\n" + 
				"16 10 1 1 10 9 3 12 16 7 # 1\r\n" + 
				"15 14 17 4 3 13 15 2 15 5 3 #\r\n" + 
				"12 # 10 10 4 # 10 7 # 15 7 4\r\n" + 
				"16 15 4 3 7 # # 5 18 # 5 3\r\n" + 
				"10 # 10 5 2 3 4 7 # # 7 19\r\n" + 
				"14 6 # 3 15 4 3 # 14 3 # 19\r\n" + 
				"9 10 17 # 17 7 12 12 3 13 15 10\r\n" + 
				"3 18 9 4 10 1 15 1 9 7 5 3";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));		
	}
	
	@Test
	public void testKO_wrongFirstLine_wrongKeyword() throws BadFileFormatException {
		String fakeFile = "DM 12x12\r\n" + 
				"1 2 3 4 5 3 6 6 7 # 8 2\r\n" + 
				"9 10 # 6 3 8 # 11 12 2 4 7\r\n" + 
				"10 4 12 # 13 # 9 14 # 7 # 14\r\n" + 
				"# 12 10 15 # # 4 10 10 2 # 11\r\n" + 
				"16 10 1 1 10 9 3 12 16 7 # 1\r\n" + 
				"15 14 17 4 3 13 15 2 15 5 3 #\r\n" + 
				"12 # 10 10 4 # 10 7 # 15 7 4\r\n" + 
				"16 15 4 3 7 # # 5 18 # 5 3\r\n" + 
				"10 # 10 5 2 3 4 7 # # 7 19\r\n" + 
				"14 6 # 3 15 4 3 # 14 3 # 19\r\n" + 
				"9 10 17 # 17 7 12 12 3 13 15 10\r\n" + 
				"3 18 9 4 10 1 15 1 9 7 5 3";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));		
	}
	
	@Test
	public void testKO_wrongFirstLine_missingSpaceAfterKeyword() throws BadFileFormatException {
		String fakeFile = "DIM\t12x12\r\n" + 
				"1 2 3 4 5 3 6 6 7 # 8 2\r\n" + 
				"9 10 # 6 3 8 # 11 12 2 4 7\r\n" + 
				"10 4 12 # 13 # 9 14 # 7 # 14\r\n" + 
				"# 12 10 15 # # 4 10 10 2 # 11\r\n" + 
				"16 10 1 1 10 9 3 12 16 7 # 1\r\n" + 
				"15 14 17 4 3 13 15 2 15 5 3 #\r\n" + 
				"12 # 10 10 4 # 10 7 # 15 7 4\r\n" + 
				"16 15 4 3 7 # # 5 18 # 5 3\r\n" + 
				"10 # 10 5 2 3 4 7 # # 7 19\r\n" + 
				"14 6 # 3 15 4 3 # 14 3 # 19\r\n" + 
				"9 10 17 # 17 7 12 12 3 13 15 10\r\n" + 
				"3 18 9 4 10 1 15 1 9 7 5 3";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));		
	}
	
	@Test
	public void testKO_wrongFirstLine_missingFirstNumber() throws BadFileFormatException {
		String fakeFile = "DIM ax12\r\n" + 
				"1 2 3 4 5 3 6 6 7 # 8 2\r\n" + 
				"9 10 # 6 3 8 # 11 12 2 4 7\r\n" + 
				"10 4 12 # 13 # 9 14 # 7 # 14\r\n" + 
				"# 12 10 15 # # 4 10 10 2 # 11\r\n" + 
				"16 10 1 1 10 9 3 12 16 7 # 1\r\n" + 
				"15 14 17 4 3 13 15 2 15 5 3 #\r\n" + 
				"12 # 10 10 4 # 10 7 # 15 7 4\r\n" + 
				"16 15 4 3 7 # # 5 18 # 5 3\r\n" + 
				"10 # 10 5 2 3 4 7 # # 7 19\r\n" + 
				"14 6 # 3 15 4 3 # 14 3 # 19\r\n" + 
				"9 10 17 # 17 7 12 12 3 13 15 10\r\n" + 
				"3 18 9 4 10 1 15 1 9 7 5 3";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));		
	}
	
	@Test
	public void testKO_wrongFirstLine_missingXbetweenNumbers() throws BadFileFormatException {
		String fakeFile = "DIM 12*12\r\n" + 
				"1 2 3 4 5 3 6 6 7 # 8 2\r\n" + 
				"9 10 # 6 3 8 # 11 12 2 4 7\r\n" + 
				"10 4 12 # 13 # 9 14 # 7 # 14\r\n" + 
				"# 12 10 15 # # 4 10 10 2 # 11\r\n" + 
				"16 10 1 1 10 9 3 12 16 7 # 1\r\n" + 
				"15 14 17 4 3 13 15 2 15 5 3 #\r\n" + 
				"12 # 10 10 4 # 10 7 # 15 7 4\r\n" + 
				"16 15 4 3 7 # # 5 18 # 5 3\r\n" + 
				"10 # 10 5 2 3 4 7 # # 7 19\r\n" + 
				"14 6 # 3 15 4 3 # 14 3 # 19\r\n" + 
				"9 10 17 # 17 7 12 12 3 13 15 10\r\n" + 
				"3 18 9 4 10 1 15 1 9 7 5 3";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));		
	}
	
	@Test
	public void testKO_wrongFirstLine_digitbetweenNumbers() throws BadFileFormatException {
		String fakeFile = "DIM 12312\r\n" + 
				"1 2 3 4 5 3 6 6 7 # 8 2\r\n" + 
				"9 10 # 6 3 8 # 11 12 2 4 7\r\n" + 
				"10 4 12 # 13 # 9 14 # 7 # 14\r\n" + 
				"# 12 10 15 # # 4 10 10 2 # 11\r\n" + 
				"16 10 1 1 10 9 3 12 16 7 # 1\r\n" + 
				"15 14 17 4 3 13 15 2 15 5 3 #\r\n" + 
				"12 # 10 10 4 # 10 7 # 15 7 4\r\n" + 
				"16 15 4 3 7 # # 5 18 # 5 3\r\n" + 
				"10 # 10 5 2 3 4 7 # # 7 19\r\n" + 
				"14 6 # 3 15 4 3 # 14 3 # 19\r\n" + 
				"9 10 17 # 17 7 12 12 3 13 15 10\r\n" + 
				"3 18 9 4 10 1 15 1 9 7 5 3";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));		
	}

	@Test
	public void testKO_wrongFirstLine_missingSecondNumber() throws BadFileFormatException {
		String fakeFile = "DIM 12xa2\r\n" + 
				"1 2 3 4 5 3 6 6 7 # 8 2\r\n" + 
				"9 10 # 6 3 8 # 11 12 2 4 7\r\n" + 
				"10 4 12 # 13 # 9 14 # 7 # 14\r\n" + 
				"# 12 10 15 # # 4 10 10 2 # 11\r\n" + 
				"16 10 1 1 10 9 3 12 16 7 # 1\r\n" + 
				"15 14 17 4 3 13 15 2 15 5 3 #\r\n" + 
				"12 # 10 10 4 # 10 7 # 15 7 4\r\n" + 
				"16 15 4 3 7 # # 5 18 # 5 3\r\n" + 
				"10 # 10 5 2 3 4 7 # # 7 19\r\n" + 
				"14 6 # 3 15 4 3 # 14 3 # 19\r\n" + 
				"9 10 17 # 17 7 12 12 3 13 15 10\r\n" + 
				"3 18 9 4 10 1 15 1 9 7 5 3";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));		
	}
	
	@Test
	public void testKO_wrongFirstLine_unsquaredMatrix() throws BadFileFormatException {
		String fakeFile = "DIM 12x10\r\n" + 
				"1 2 3 4 5 3 6 6 7 # 8 2\r\n" + 
				"9 10 # 6 3 8 # 11 12 2 4 7\r\n" + 
				"10 4 12 # 13 # 9 14 # 7 # 14\r\n" + 
				"# 12 10 15 # # 4 10 10 2 # 11\r\n" + 
				"16 10 1 1 10 9 3 12 16 7 # 1\r\n" + 
				"15 14 17 4 3 13 15 2 15 5 3 #\r\n" + 
				"12 # 10 10 4 # 10 7 # 15 7 4\r\n" + 
				"16 15 4 3 7 # # 5 18 # 5 3\r\n" + 
				"10 # 10 5 2 3 4 7 # # 7 19\r\n" + 
				"14 6 # 3 15 4 3 # 14 3 # 19\r\n" + 
				"9 10 17 # 17 7 12 12 3 13 15 10\r\n" + 
				"3 18 9 4 10 1 15 1 9 7 5 3";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));		
	}
	
	@Test
	public void testKO_wrongDataLine_missingItem() throws BadFileFormatException {
		String fakeFile = "DIM 12x12\r\n" + 
				"1 2 4 5 3 6 6 7 # 8 2\r\n" + 
				"9 10 # 6 3 8 # 11 12 2 4 7\r\n" + 
				"10 4 12 # 13 # 9 14 # 7 # 14\r\n" + 
				"# 12 10 15 # # 4 10 10 2 # 11\r\n" + 
				"16 10 1 1 10 9 3 12 16 7 # 1\r\n" + 
				"15 14 17 4 3 13 15 2 15 5 3 #\r\n" + 
				"12 # 10 10 4 # 10 7 # 15 7 4\r\n" + 
				"16 15 4 3 7 # # 5 18 # 5 3\r\n" + 
				"10 # 10 5 2 3 4 7 # # 7 19\r\n" + 
				"14 6 # 3 15 4 3 # 14 3 # 19\r\n" + 
				"9 10 17 # 17 7 12 12 3 13 15 10\r\n" + 
				"3 18 9 4 10 1 15 1 9 7 5 3";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));		
	}

	@Test
	public void testKO_wrongDataLine_illegalItem() throws BadFileFormatException {
		String fakeFile = "DIM 12x12\r\n" + 
				"1 2 3 4 a 3 6 6 7 # 8 2\r\n" + 
				"9 10 # 6 3 8 # 11 12 2 4 7\r\n" + 
				"10 4 12 # 13 # 9 14 # 7 # 14\r\n" + 
				"# 12 10 15 # # 4 10 10 2 # 11\r\n" + 
				"16 10 1 1 10 9 3 12 16 7 # 1\r\n" + 
				"15 14 17 4 3 13 15 2 15 5 3 #\r\n" + 
				"12 # 10 10 4 # 10 7 # 15 7 4\r\n" + 
				"16 15 4 3 7 # # 5 18 # 5 3\r\n" + 
				"10 # 10 5 2 3 4 7 # # 7 19\r\n" + 
				"14 6 # 3 15 4 3 # 14 3 # 19\r\n" + 
				"9 10 17 # 17 7 12 12 3 13 15 10\r\n" + 
				"3 18 9 4 10 1 15 1 9 7 5 3";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));		
	}

}
