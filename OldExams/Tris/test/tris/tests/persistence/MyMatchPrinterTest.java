package tris.tests.persistence;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Before;
import org.junit.Test;

import tris.model.Match;
import tris.model.MyBoard;
import tris.model.MyMatch;
import tris.persistence.MatchPrinter;
import tris.persistence.MyMatchPrinter;
import tris.persistence.SadPrinterException;


public class MyMatchPrinterTest {
	
	private Match match;
	private MatchPrinter persister;
	
	@Before
	public void setUp() {
		match = new MyMatch();
		persister = new MyMatchPrinter();
	}
	
	@Test
	public void testStore_OK() {
		match.add(new MyBoard("X        "));
		match.add(new MyBoard("X   O    "));
		match.add(new MyBoard("X X O    "));
		match.add(new MyBoard("XOX O    "));
		match.add(new MyBoard("XOX O  X "));
		match.add(new MyBoard("XOXOO  X "));
		match.add(new MyBoard("XOXOOX X "));
		match.add(new MyBoard("XOXOOX XO"));
		match.add(new MyBoard("XOXOOXOXO"));
		StringWriter bos = new StringWriter();
		try {
			persister.print(new PrintWriter(bos), match);
		} catch (SadPrinterException e) {
			fail(e.getMessage());
		}
		assertTrue(bos.toString().length()>0);
	}
	
	@Test
	public void testStore_Load_OK() {
		match.add(new MyBoard("X        "));
		match.add(new MyBoard("X   O    "));
		match.add(new MyBoard("X X O    "));
		match.add(new MyBoard("XOX O    "));
		match.add(new MyBoard("XOX O  X "));
		match.add(new MyBoard("XOXOO  X "));
		match.add(new MyBoard("XOXOOX X "));
		match.add(new MyBoard("XOXOOX XO"));
		match.add(new MyBoard("XOXOOXOXO"));
		StringWriter bos = new StringWriter();
		try {
			persister.print(new PrintWriter(bos), match);
		} catch (SadPrinterException e) {
			fail(e.getMessage());
		}
		assertEquals(bos.toString().trim(), match.toString().trim());
		
	}
	
}
