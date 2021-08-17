package tris.tests.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tris.model.*;

// tests full boards only, that is, boards made of 9 full cells, where each cell is just "X" or "O"
public class MyBoardTest1
{
	MyBoard sk;
	
	@Before
	public void setUp() {
		sk = new MyBoard("OOXXOOXXO");
		//System.out.println(sk);
	}
	
	@Test
	public void testEstraiRiga() {
		assertEquals("OOX", sk.getRow(0));
		assertEquals("XOO", sk.getRow(1));
		assertEquals("XXO", sk.getRow(2));
	}
	@Test
	public void testEstraiColonna() {
		assertEquals("OXX", sk.getColumn(0));
		assertEquals("OOX", sk.getColumn(1));
		assertEquals("XOO", sk.getColumn(2));
	}

	@Test
	public void testEstraiDiagonale() {
		assertEquals("OOO", sk.getDiagonal(0));
		assertEquals("XOX", sk.getDiagonal(1));
	}

	@Test
	public void testToString() {
		assertEquals("O\tO\tX" + System.lineSeparator() + 
				     "X\tO\tO" + System.lineSeparator() + 
				     "X\tX\tO" + System.lineSeparator(), sk.toString());
	}

	// --- test distanze ---
	
	@Test
	public void testDistanze() {
		assertEquals(0, new MyBoard("OOXXOOXXO").distanceFrom(new MyBoard("OOXXOOXXO")) );
		assertEquals(1, new MyBoard("OOXXOOXXO").distanceFrom(new MyBoard("OOXXOOXXX")) );
		assertEquals(0, new MyBoard("OO  OOXXO").distanceFrom(new MyBoard("OO  OOXXO")) );
		assertEquals(2, new MyBoard("OOXXOOXXO").distanceFrom(new MyBoard("OO  OOXXO")) );
	}

	@Test
	public void testAdiacenze() {
		assertTrue( new MyBoard("OOXXOOXXO").adjacent(new MyBoard("OOXXOOXXX")) );
		assertTrue( new MyBoard("OOXXOOXXO").adjacent(new MyBoard("OOXX OXXO")) );
		assertFalse(new MyBoard("OOXXOOXXO").adjacent(new MyBoard("OOX OOXXO")) );
	}

	@Test
	public void testEquals() {
		assertTrue(new MyBoard("OOXXOOXXO").equals(new MyBoard("OOXXOOXXO")) );
		assertTrue(new MyBoard("OO  OOXXO").equals(new MyBoard("OO  OOXXO")) );
	}

	// --- test vincente ---

	@Test
	public void testVincente() {
		assertTrue( new MyBoard("OOXXOOXXO").winning());
		assertTrue( new MyBoard("OOXXOOXXX").winning());
		assertTrue( new MyBoard("OOXOXOXXO").winning());
		assertFalse(new MyBoard("OOXXOOOXX").winning());
	}
	
	@Test
	public void testVincenteSequenza() {
		assertTrue( MyBoard.winningSequence("OOO"));
		assertTrue( MyBoard.winningSequence("XXX"));
		assertFalse(MyBoard.winningSequence("OXO"));
		assertFalse(MyBoard.winningSequence("XXO"));
		assertFalse(MyBoard.winningSequence("OXX"));
	}

	// --- test costruttori ---

	@Test
	public void testCtorFromString() {
		sk = new MyBoard("OOXXOOXXO");
	}

	@Test
	public void testCtorFromCharArray() {
		char[][] sc = {	{'X', 'O', 'O'}, {'X', 'O', 'O'}, {'X', 'O', 'O'} };
		sk = new MyBoard(sc);
	}

	@Test(expected=IllegalArgumentException.class) // illegal char
	public void testCtorKO_1() {
		sk = new MyBoard("OoOXXOOXXO");
	}
	
	@Test(expected=IllegalArgumentException.class) // missing element (string.length==8)
	public void testCtorKO_2() {
		sk = new MyBoard("OOXXOOXX");
	}

	@Test(expected=IllegalArgumentException.class) // illegal char
	public void testCtorFromCharArray_KO_1() {
		char[][] sc = {	{'X', 'O', 'O'}, {'o', 'O', 'O'}, {'X', 'O', 'O'} };
		sk = new MyBoard(sc);
	}

	@Test(expected=IllegalArgumentException.class) // missing element (string.length==8)
	public void testCtorFromCharArray_KO_2() {
		char[][] sc = {	{'X', 'O', 'O'}, {'O', 'O'}, {'X', 'O', 'O'} };
		sk = new MyBoard(sc);
	}

}
