package crosswords.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import crosswords.model.*;

public class SchemeTest {

	@Test
	public void testOKBasicUnconfigured() {
		Scheme scheme = new Scheme(5);
		// System.out.print(scheme);
		assertEquals(5, scheme.getSize());
		assertFalse(scheme.isConfigured());
		// se non è configurato non si può invocare alcun metodo!
	}

	@Test
	public void testOKBasicFilled() {
		Scheme scheme = new Scheme(5);
		scheme.setCellRow(0, new int[] {1,2,3,4,5});
		scheme.setCellRow(1, new int[] {9,10,0,6,3});
		scheme.setCellRow(2, new int[] {10,4,12,0,13});
		scheme.setCellRow(3, new int[] {0,12,10,15,0});
		scheme.setCellRow(4, new int[] {16,10,1,1,10});
		//System.out.print(scheme);
		assertEquals(5, scheme.getSize());
		assertTrue(scheme.isConfigured());
	}

}
