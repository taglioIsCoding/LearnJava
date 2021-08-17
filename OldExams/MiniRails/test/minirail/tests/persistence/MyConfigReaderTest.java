package minirail.tests.persistence;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import minirail.model.Gauge;
import minirail.persistence.BadFileFormatException;
import minirail.persistence.ConfigReader;
import minirail.persistence.MyConfigReader;

public class MyConfigReaderTest {
	
	@Test
	public void testOK1() throws BadFileFormatException, IOException {
		String fakeFile = "N gauge\r\n" + 
				"IC583, 65 cm, 160 km/h\r\n" + 
				"R2961, 68 cm, 140 km/h\r\n" +
				"R2111, 17 in, 90 mph";
		StringReader reader = new StringReader(fakeFile);
		ConfigReader configReader = new MyConfigReader(reader);
		assertEquals("N", configReader.getGauge().toString());
		assertEquals(Gauge.N, configReader.getGauge());
		assertEquals(3,   configReader.getTrains().size());
	}
	
	@Test
	public void testOK2() throws BadFileFormatException, IOException {
		String fakeFile = "H0 gauge\r\n" + 
				"IC583, 65 cm, 160 km/h\r\n" + 
				"R2961, 68 cm, 140 km/h";
		StringReader reader = new StringReader(fakeFile);
		ConfigReader configReader = new MyConfigReader(reader);
		assertEquals("H0", configReader.getGauge().toString());
		assertEquals(Gauge.H0, configReader.getGauge());
		assertEquals(2,   configReader.getTrains().size());
	}
	
	@Test
	public void testOK3() throws BadFileFormatException, IOException {
		String fakeFile = "Z gauge\r\n" + 
				"IC583, 25.7 cm, 160 km/h\r\n" + 
				"R2961, 26.9 cm, 140 km/h";
		StringReader reader = new StringReader(fakeFile);
		ConfigReader configReader = new MyConfigReader(reader);
		assertEquals("Z", configReader.getGauge().toString());
		assertEquals(Gauge.Z, configReader.getGauge());
		assertEquals(2,   configReader.getTrains().size());
	}

	@Test
	public void testOK4() throws BadFileFormatException, IOException {
		String fakeFile = "TT gauge\r\n" + 
				"IC583, 66 cm, 160 km/h\r\n" + 
				"R2961, 69 cm, 140 km/h";
		StringReader reader = new StringReader(fakeFile);
		ConfigReader configReader = new MyConfigReader(reader);
		assertEquals("TT", configReader.getGauge().toString());
		assertEquals(Gauge.TT, configReader.getGauge());
		assertEquals(2,   configReader.getTrains().size());
	}
	
	@Test
	public void testKO_missingGauge() {
		String fakeFile =  
				"IC583, 65 cm, 160 km/h\r\n" + 
				"R2961, 68 cm, 140 km/h";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_badGauge() {
		String fakeFile = "QQ gauge\r\n" +
				"IC583, 65 cm, 160 km/h\r\n" + 
				"R2961, 68 cm, 140 km/h";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_noGaugeKwd() {
		String fakeFile = "HO xxxx\r\n" +
				"IC583, 65 cm, 160 km/h\r\n" + 
				"R2961, 68 cm, 140 km/h";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_missingFirstNumber() {
		String fakeFile = "H0 gauge\r\n" + 
				"IC583, xx cm, 160 km/h\r\n" + 
				"R2961, 68 cm, 140 km/h";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_missingSecondNumber() {
		String fakeFile = "H0 gauge\r\n" + 
				"IC583, 65 cm, 160 km/h\r\n" + 
				"R2961, 68 cm, xxx km/h";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}
	
	@Test
	public void testKO_WrongUnit1() {
		String fakeFile = "H0 gauge\r\n" + 
				"IC583, 155 mm, 160 km/h\r\n" + 
				"R2961, 160 cm, 140 km/h";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_WrongUnit2() {
		String fakeFile = "H0 gauge\r\n" + 
				"IC583, 155 cm, 160 kmh\r\n" + 
				"R2961, 160 cm, 140 km/h";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_missingFirstSeparator() {
		String fakeFile = "H0 gauge\r\n" + 
				"IC583 155 cm, 160 km/h\r\n" + 
				"R2961, 160 cm, 140 km/h";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_missingSecondSeparator() {
		String fakeFile = "H0 gauge\r\n" + 
				"IC583, 155 cm 160 km/h\r\n" + 
				"R2961, 160 cm, 140 km/h";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

	@Test
	public void testKO_missingNewline() {
		String fakeFile = "H0 gauge\r\n" + 
				"IC583, 155 cm, 160 km/h " + 
				"R2961, 160 cm, 140 km/h";
		StringReader reader = new StringReader(fakeFile);		
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader(reader));
	}

}
