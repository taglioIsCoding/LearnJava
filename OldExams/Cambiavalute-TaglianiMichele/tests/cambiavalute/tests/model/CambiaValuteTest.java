package cambiavalute.tests.model;


import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;

import org.junit.Test;

import cambiavalute.model.*;

public class CambiaValuteTest {
	public CambiaValute createSubject() {
		Map<String,TassiDiCambio> map = new HashMap<>();		
		map.put("UNO", new TassiDiCambio("UNO", 0.9, 2.777));
		map.put("DUE", new TassiDiCambio("DUE", 3, 4));
		map.put("TRE", new TassiDiCambio("TRE", 5, 6));		
		CambiaValute cv = new CambiaValute("MyCV", map);
		
		return cv;
	}
	
	@Test
	public void CtorAndAccessorsTest() {
		Map<String,TassiDiCambio> map = new HashMap<>();		
		map.put("UNO", new TassiDiCambio("UNO", 1, 2));
		map.put("DUE", new TassiDiCambio("DUE", 3, 4));
		map.put("TRE", new TassiDiCambio("TRE", 5, 6));		
		CambiaValute cv = new CambiaValute("MyCV", map);
		
		assertEquals("MyCV", cv.getNomeAgenzia());
		Set<String> set = map.keySet();
		assertTrue(set.contains("UNO") && set.contains("DUE") && set.contains("TRE"));
	}
	
	@Test
	public void testAcquisto() {
		CambiaValute cv = createSubject();
		
		OptionalDouble value = cv.acquisto("UNO", 1);
		
		assertTrue(value.isPresent());
		assertEquals(1.11, value.getAsDouble(),  0.0001);
	}
	
	@Test
	public void testAcquisto_ValutaNonPresente() {
		CambiaValute cv = createSubject();
		
		OptionalDouble value = cv.acquisto("XXX", 1);
		
		assertFalse(value.isPresent());
	}
	
	@Test
	public void testVendita() {
		CambiaValute cv = createSubject();
		
		OptionalDouble value = cv.vendita("UNO", 1);
		
		assertTrue(value.isPresent());
		assertEquals(2.78, value.getAsDouble(), 0.0001);
	}
	
	@Test
	public void testVendita_ValutaNonPresente() {
		CambiaValute cv = createSubject();
		
		OptionalDouble value = cv.vendita("XXX", 1);
		
		assertFalse(value.isPresent());
	}
	
	@Test
	public void testArrotonda_InSu() {
		assertEquals(CambiaValute.arrotonda(0.999), 1, 0.00001);
	}
	
	@Test
	public void testArrotonda_InGiu() {
		assertEquals(CambiaValute.arrotonda(1.001), 1, 0.00001);
	}
	
	@Test
	public void testFormatta() {
		assertEquals("1.234,56", CambiaValute.formatta(1234.558));
	}
	
	@Test
	public void convertiInDouble() throws ParseException {
		assertEquals(12.345678, CambiaValute.convertiInDouble("12,345678"), 0.0000001);
	}
	
	@Test(expected = ParseException.class)
	public void convertiInDouble_ConPunto() throws ParseException {
		CambiaValute.convertiInDouble("12.345678");
	}
}
