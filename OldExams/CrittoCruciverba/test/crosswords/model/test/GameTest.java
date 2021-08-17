package crosswords.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;



import crosswords.model.*;

public class GameTest {

	@Test
	public void testOKBasicUnconfigured() {
		MyGame game = new MyGame(5);
		// System.out.print(scheme);
		assertEquals(5, game.getSize());
		assertFalse(game.isConfigured());
		// se non è configurato non si può invocare alcun metodo!
		// assertEquals(Optional.empty(), scheme.getMapping(2));
	}

	@Test
	public void testOKBasicFilled() {
		MyGame game = new MyGame(5);
		game.setCellRow(0, new int[] {1,2,3,4,5});
		game.setCellRow(1, new int[] {9,10,0,6,3});
		game.setCellRow(2, new int[] {10,4,12,0,13});
		game.setCellRow(3, new int[] {0,12,10,15,0});
		game.setCellRow(4, new int[] {16,10,1,1,10});
		//System.out.print(scheme);
		assertEquals(5, game.getSize());
		assertTrue(game.isConfigured());
		assertFalse(game.isFull());
		assertEquals(Optional.empty(), game.getMapping(2));
	}
	

	@Test
	public void testOKFilledWithMapping() {
		MyGame game = new MyGame(5);
		game.setCellRow(0, new int[] {1,2,3,4,5});
		game.setCellRow(1, new int[] {9,10,0,6,3});
		game.setCellRow(2, new int[] {10,4,12,0,13});
		game.setCellRow(3, new int[] {0,12,10,15,0});
		game.setCellRow(4, new int[] {16,10,1,1,10});
		//System.out.print(scheme);
		assertEquals(5, game.getSize());
		assertTrue(game.isConfigured());
		game.setMapping(1, "S");
		game.setMapping(2, "B");
		game.setMapping(3, "E");
		game.setMapping(4, "R");
		game.setMapping(5, "L");
		assertFalse(game.isFull());
		game.setMapping(9, "T");
		game.setMapping(10, "A");
		game.setMapping(6, "F");
		game.setMapping(8, "Q");
		assertFalse(game.isFull());
		game.setMapping(12, "M");
		game.setMapping(13, "D");
		assertFalse(game.isFull());
		game.setMapping(15, "I");
		game.setMapping(16, "P");
		//System.out.print(game);
		assertTrue(game.isFull());
		assertEquals(Optional.empty(), game.getMapping(7));
		assertEquals(Optional.of("A"), game.getMapping(10));
	}

}
