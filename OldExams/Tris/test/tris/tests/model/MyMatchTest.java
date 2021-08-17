package tris.tests.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tris.model.*;

public class MyMatchTest
{
	private Match match;
	
	@Before
	public void setUp() {
		match = new MyMatch();
	}
	
	@Test
	public void testPartitaPatta1() {
		match.add(new MyBoard("X        "));
		match.add(new MyBoard("X   O    "));
		match.add(new MyBoard("X X O    "));
		match.add(new MyBoard("XOX O    "));
		match.add(new MyBoard("XOX O  X "));
		match.add(new MyBoard("XOXOO  X "));
		match.add(new MyBoard("XOXOOX X "));
		match.add(new MyBoard("XOXOOX XO"));
		match.add(new MyBoard("XOXOOXOXO"));
		assertFalse(match.getCurrentState().winning());
	}
	
	@Test
	public void testPartitaVinta1() {
		match.add(new MyBoard("X        "));
		match.add(new MyBoard("X   O    "));
		match.add(new MyBoard("X X O    "));
		match.add(new MyBoard("XOX O    "));
		match.add(new MyBoard("XOX O  X "));
		match.add(new MyBoard("XOXOO  X "));
		match.add(new MyBoard("XOXOOX X "));
		match.add(new MyBoard("XOXOOXOX "));
		match.add(new MyBoard("XOXOOXOXX"));
		assertTrue(match.getCurrentState().winning());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPartitaIllegale_DueMosseInsieme() {
		match.add(new MyBoard("X        "));
		match.add(new MyBoard("X   O    "));
		//match.add(new MyBoard("X X O    "));
		match.add(new MyBoard("XOX O    "));
		//match.add(new MyBoard("XOX O  X "));
		//match.add(new MyBoard("XOXOO  X "));
		//match.add(new MyBoard("XOXOOX X "));
		//match.add(new MyBoard("XOXOOXOX "));
		//match.add(new MyBoard("XOXOOXOXX"));
		//assertTrue(match.getCurrentState().winning());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPartitaIllegale_DueMosseNonAdiacenti() {
		match.add(new MyBoard("X        "));
		match.add(new MyBoard("X   X    "));
		//match.add(new MyBoard("X X O    "));
		//match.add(new MyBoard("XOX O    "));
		//match.add(new MyBoard("XOX O  X "));
		//match.add(new MyBoard("XOXOO  X "));
		//match.add(new MyBoard("XOXOOX X "));
		//match.add(new MyBoard("XOXOOXOX "));
		//match.add(new MyBoard("XOXOOXOXX"));
		//assertTrue(match.getCurrentState().winning());
	}
}
