package tris.tests.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tris.model.*;

//tests non-full boards, that is, boards made of 9 possibly-empty cells, where each cell can be "X", "O" or even " "
public class MyBoardTest2
{
	MyBoard sk1, sk2;
	
	@Before
	public void setUp() {
		sk1 = new MyBoard("         ");
		sk2 = new MyBoard("        X");
	}
	
	@Test
	public void testEstraiRiga() {
		assertEquals("   ", sk1.getRow(0));
		assertEquals("   ", sk1.getRow(1));
		assertEquals("   ", sk1.getRow(2));

		assertEquals("   ", sk2.getRow(0));
		assertEquals("   ", sk2.getRow(1));
		assertEquals("  X", sk2.getRow(2));
	}
	@Test
	public void testEstraiColonna() {
		assertEquals("   ", sk1.getColumn(0));
		assertEquals("   ", sk1.getColumn(1));
		assertEquals("   ", sk1.getColumn(2));
		assertEquals("   ", sk2.getColumn(0));
		assertEquals("   ", sk2.getColumn(1));
		assertEquals("  X", sk2.getColumn(2));
	}

	@Test
	public void testEstraiDiagonale() {
		assertEquals("   ", sk1.getDiagonal(0));
		assertEquals("   ", sk1.getDiagonal(1));
		assertEquals("  X", sk2.getDiagonal(0));
		assertEquals("   ", sk2.getDiagonal(1));
	}

	@Test
	public void testToString() {
		assertEquals(" \t \t " + System.lineSeparator() + 
			         " \t \t " + System.lineSeparator() + 
			         " \t \t " + System.lineSeparator(), sk1.toString());
		assertEquals(" \t \t " + System.lineSeparator() + 
			         " \t \t " + System.lineSeparator() + 
			         " \t \tX" + System.lineSeparator(), sk2.toString());
	}

	// --- test distanze ---
	
	

	// --- test vincente ---

	@Test
	public void testVincente() {
		assertFalse( new MyBoard("         ").winning());
		assertFalse( new MyBoard("        X").winning());
	}
	
	@Test
	public void testVincenteSequenza() {
		assertFalse(MyBoard.winningSequence("   "));
		assertFalse(MyBoard.winningSequence("XX "));
		assertFalse(MyBoard.winningSequence("O X"));
	}

	// --- test costruttori ---

	@Test
	public void testCtorFromString() {
		sk1 = new MyBoard("         ");
	}

	@Test
	public void testCtorFromCharArray() {
		char[][] sc = {	{'X', ' ', ' '}, {' ', 'O', ' '}, {'X', 'O', 'O'} };
		sk1 = new MyBoard(sc);
	}

	@Test(expected=IllegalArgumentException.class) // illegal char
	public void testCtorKO_1() {
		sk1 = new MyBoard("Oo   OOXXO");
	}
	
	@Test(expected=IllegalArgumentException.class) // missing element (string.length==8)
	public void testCtorKO_2() {
		sk1 = new MyBoard("       X");
	}

	@Test(expected=IllegalArgumentException.class) // illegal char
	public void testCtorFromCharArray_KO_1() {
		char[][] sc = {	{'X', 'O', 'O'}, {'o', ' ', 'O'}, {' ', ' ', ' '} };
		sk1 = new MyBoard(sc);
	}

	@Test(expected=IllegalArgumentException.class) // missing element (string.length==8)
	public void testCtorFromCharArray_KO_2() {
		char[][] sc = {	{' ', ' ', 'O'}, {' ', ' '}, {' ', ' ', 'O'} };
		sk1 = new MyBoard(sc);
	}

}
