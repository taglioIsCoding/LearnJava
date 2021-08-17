package cupidonline.tests.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Test;

import cupidonline.model.*;

public class ZodiacoTest
{

	@Test
	public void testElementiZodiacoCiSonoTuttiESonoProprioQuesti()
	{
		assertEquals(4, SegnoZodiacale.Elementi.values().length);
		assertTrue(Arrays.asList(SegnoZodiacale.Elementi.values()).contains(SegnoZodiacale.Elementi.ACQUA));
		assertTrue(Arrays.asList(SegnoZodiacale.Elementi.values()).contains(SegnoZodiacale.Elementi.ARIA));
		assertTrue(Arrays.asList(SegnoZodiacale.Elementi.values()).contains(SegnoZodiacale.Elementi.FUOCO));
		assertTrue(Arrays.asList(SegnoZodiacale.Elementi.values()).contains(SegnoZodiacale.Elementi.TERRA));
	}

	@Test
	public void testSegniZodiacoCiSonoTuttiESonoProprioQuesti()
	{
		assertEquals(12, SegnoZodiacale.values().length);
		assertTrue( SegnoZodiacale.ARIETE.contains(LocalDate.of(LocalDate.now().getYear(), 4, 13)));
		assertTrue( SegnoZodiacale.ARIETE.contains(LocalDate.of(LocalDate.now().getYear(), 4, 20)));
		assertFalse(SegnoZodiacale.ARIETE.contains(LocalDate.of(LocalDate.now().getYear(), 4, 21)));
		//caso limite fra due anni: CAPRICORNO(Elementi.TERRA, 22,12, 20,1),
		assertTrue( SegnoZodiacale.CAPRICORNO.contains(LocalDate.of(LocalDate.now().getYear(), 12, 22)));
		assertFalse(SegnoZodiacale.CAPRICORNO.contains(LocalDate.of(LocalDate.now().getYear(), 12, 21)));
		assertTrue( SegnoZodiacale.CAPRICORNO.contains(LocalDate.of(LocalDate.now().getYear(), 1, 20)));
		assertFalse(SegnoZodiacale.CAPRICORNO.contains(LocalDate.of(LocalDate.now().getYear(), 1, 21)));
	}

	@Test
	public void testFactory()
	{
		SegnoZodiacale z = SegnoZodiacale.CAPRICORNO;
		assertEquals(z.toString(), "CAPRICORNO (2018-12-22, 2019-01-20)");
	}
}
