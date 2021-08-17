package tris.tests.model;

import static org.junit.Assert.*;

import org.junit.Test;

import tris.model.*;

public class MyBoardValueTest {

	@Test
	public void testValoriStringa() {
		BoardValue[] valori = BoardValue.values();
		assertEquals(" ", valori[0].get());
		assertEquals("X", valori[1].get());
		assertEquals("O", valori[2].get());
	}

	@Test
	public void testValoriEnumerativo() {
		BoardValue[] valori = BoardValue.values();
		assertEquals(BoardValue.EMPTY, valori[0]);
		assertEquals(BoardValue.X, valori[1]);
		assertEquals(BoardValue.O, valori[2]);
	}
	
	@Test
	public void testOpposti() {
		assertEquals(BoardValue.O, BoardValue.X.other());
		assertEquals(BoardValue.X, BoardValue.O.other());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testOppostoAlVuoto() {
		BoardValue.EMPTY.other();
	}
	
	@Test
	public void testGetAllValues() {
		assertEquals(" XO", BoardValue.getAllValues());
	}
	
}
