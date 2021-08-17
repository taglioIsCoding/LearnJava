package minirail.tests.persistence;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import minirail.persistence.BadFileFormatException;
import minirail.persistence.LineReader;
import minirail.persistence.MyLineReader;

public class MyLineReaderTest {
	
	@Test
	public void testOK1() throws BadFileFormatException, IOException {
		String fakeFile = "Line A-D\r\n" + 
				"Section A-B 90\r\n" + 
				"Section B-C 90\r\n" + 
				"Section C-D 70\r\n" + 
				"Section D-A 70";
		StringReader reader = new StringReader(fakeFile);
		LineReader lineReader = new MyLineReader(reader);
		assertEquals("A-D", lineReader.getLine().getName());
		assertEquals(4, lineReader.getLine().getSections().size());
	}
	
	@Test
	public void testKO_missingLineKeyword() {
		String fakeFile = "xxxx A-D\r\n" + 
				"Section A-B 90\r\n" + 
				"Section B-C 90\r\n" + 
				"Section C-D 70\r\n" + 
				"Section D-A 70";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyLineReader(reader));
	}
	
	@Test
	public void testKO_missingLineName() {
		String fakeFile = "Line \r\n" + 
				"Section A-B 90\r\n" + 
				"Section B-C 90\r\n" + 
				"Section C-D 70\r\n" + 
				"Section D-A 70";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyLineReader(reader));
	}

	@Test
	public void testKO_missingSectionKeyword() {
		String fakeFile = "Line A-D\r\n" + 
				"qqqq A-B 90\r\n" + 
				"Section B-C 90\r\n" + 
				"Section C-D 70\r\n" + 
				"Section D-A 70";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyLineReader(reader));
	}

	@Test
	public void testKO_missingSectionName() {
		String fakeFile = "Line A-D\r\n" + 
				"Section 90\r\n" + 
				"Section B-C 90\r\n" + 
				"Section C-D 70\r\n" + 
				"Section D-A 70";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyLineReader(reader));
	}
	
	@Test
	public void testKO_missingSectionLength() {
		String fakeFile = "Line A-D\r\n" + 
				"Section A-B \r\n" + 
				"Section B-C 90\r\n" + 
				"Section C-D 70\r\n" + 
				"Section D-A 70";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyLineReader(reader));
	}

	@Test
	public void testKO_missingSeparatorinLine() {
		String fakeFile = "LineA-D\r\n" + 
				"Section A-B \r\n" + 
				"Section B-C 90\r\n" + 
				"Section C-D 70\r\n" + 
				"Section D-A 70";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyLineReader(reader));
	}

	@Test
	public void testKO_missingFirstSeparatorInSection() {
		String fakeFile = "Line A-D\r\n" + 
				"SectionA-B 90\r\n" + 
				"Section B-C 90\r\n" + 
				"Section C-D 70\r\n" + 
				"Section D-A 70";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyLineReader(reader));
	}
	
	@Test
	public void testKO_missingSecondSeparatorInSection() {
		String fakeFile = "Line A-D\r\n" + 
				"Section A-B90\r\n" + 
				"Section B-C 90\r\n" + 
				"Section C-D 70\r\n" + 
				"Section D-A 70";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyLineReader(reader));
	}

	@Test
	public void testKO_missingNewlineInFirstLine() {
		String fakeFile = "Line A-D" + 
				"Section A-B 90\r\n" + 
				"Section B-C 90\r\n" + 
				"Section C-D 70\r\n" + 
				"Section D-A 70";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyLineReader(reader));
	}

	@Test
	public void testKO_missingNewlineInOtherLines() {
		String fakeFile = "Line A-D\r\n" + 
				"Section A-B 90" + 
				"Section B-C 90\r\n" + 
				"Section C-D 70\r\n" + 
				"Section D-A 70";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyLineReader(reader));
	}

}
