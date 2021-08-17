package sudoku.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.OptionalInt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sudoku.model.SudokuBoard;

public class SudokuBoardTest {
	private SudokuBoard sudoku;

	@BeforeEach
	void setUp()
	{
		sudoku = new SudokuBoard();
		sudoku.setCellRow(0, new int[] {0,0,0,0,9,8,0,3,0});
		sudoku.setCellRow(1, new int[] {3,0,0,4,0,5,9,0,0});
		sudoku.setCellRow(2, new int[] {0,9,0,0,0,0,0,4,6});
		sudoku.setCellRow(3, new int[] {1,6,0,0,0,0,0,7,5});
		sudoku.setCellRow(4, new int[] {0,0,0,3,8,0,0,0,9});
		sudoku.setCellRow(5, new int[] {2,3,9,0,0,0,4,0,0});
		sudoku.setCellRow(6, new int[] {0,4,7,0,0,1,0,5,0});
		sudoku.setCellRow(7, new int[] {0,1,0,8,0,0,0,0,0});
		sudoku.setCellRow(8, new int[] {0,0,0,0,3,0,0,9,7});
	}
	
	@Test
	public void testOKBasicUnconfigured() {
		sudoku = new SudokuBoard();
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++)
			{assertFalse(sudoku.getCell(i, j).isPresent());}
		}
	
	}

	@Test
	public void testKOsetCellRow1() {
	
		assertThrows(IllegalArgumentException.class, () -> sudoku.setCellRow(9, new int[] {0,0,0,0,3,0,0,9,7}));
	
	}
	
	@Test
	public void testKOsetCellRow2() {
		sudoku = new SudokuBoard();
		assertThrows(IllegalArgumentException.class, () -> sudoku.setCellRow(0, new int[] {0,0,0,0,3,0,0,9}));
	}

	@Test
	public void testKOsetCellRow3() {
		sudoku = new SudokuBoard();
		assertThrows(IllegalArgumentException.class, () -> sudoku.setCellRow(0, new int[] {0,0,0,0,3,0,0,9,-1}));
	}

	@Test
	public void testKOsetCellRow4() {
		sudoku = new SudokuBoard();
		assertThrows(IllegalArgumentException.class, () -> sudoku.setCellRow(0, new int[] {0,0,0,0,3,0,0,9,10}));
	}

	@Test
	public void testOKBasicFilled() {
		
		  assertEquals(sudoku.getCell(1, 0).getAsInt(), 3); 
		  assertEquals(sudoku.getCell(4, 3).getAsInt(),3);
		  assertEquals(sudoku.getCell(8, 1), OptionalInt.empty());
		  assertFalse(sudoku.isFull());
		 
	}
	
	@Test
	public void testOKFilledWithSetting() {
		
		//prima riga
		
		assertTrue(sudoku.setCell(0, 0, 4));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(0, 1, 2));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(0, 2, 6));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(0, 3, 7));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(0, 6, 5));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(0, 8, 1));
		assertFalse(sudoku.isFull());
		//seconda riga
		assertTrue(sudoku.setCell(1, 1, 7));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(1, 2, 1));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(1, 4, 6));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(1, 7, 8));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(1, 8, 2));
		//terza riga
		assertTrue(sudoku.setCell(2, 0, 8));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(2, 2, 5));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(2, 3, 2));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(2, 4, 1));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(2, 5, 3));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(2, 6, 7));
		assertFalse(sudoku.isFull());
		//quarta riga
		assertTrue(sudoku.setCell(3, 2, 8));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(3, 3, 9));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(3, 4, 4));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(3, 5, 2));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(3, 6, 3));
		assertFalse(sudoku.isFull());
		//quinta riga
		assertTrue(sudoku.setCell(4, 0,7 ));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(4, 1,5 ));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(4, 2, 4));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(4, 5, 6));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(4, 6, 2));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(4, 7, 1));
		assertFalse(sudoku.isFull());
		//sesta riga
		assertTrue(sudoku.setCell(5, 3, 1));
		assertFalse(sudoku.isFull());	
		assertTrue(sudoku.setCell(5, 4,5 ));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(5, 5, 7));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(5, 7, 6));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(5, 8,8 ));
		assertFalse(sudoku.isFull());
		//settima riga
		assertTrue(sudoku.setCell(6, 0, 9));
		assertFalse(sudoku.isFull());	
		assertTrue(sudoku.setCell(6, 3, 6));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(6, 4, 2));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(6, 6, 8));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(6, 8, 3));
		assertFalse(sudoku.isFull());
	//ottava riga
		assertTrue(sudoku.setCell(7, 0, 5));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(7, 2, 3));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(7, 4, 7 ));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(7, 5,9 ));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(7, 6, 6));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(7, 7,2 ));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(7, 8, 4 ));
		assertFalse(sudoku.isFull());
	//nona riga
		assertTrue(sudoku.setCell(8, 0,6 ));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(8, 1, 8 ));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(8, 2, 2 ));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(8, 3, 5));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(8, 5, 4));
		assertFalse(sudoku.isFull());
		assertTrue(sudoku.setCell(8, 6, 1 ));
		assertTrue(sudoku.isFull());

	}	
	@Test
	public void testOKChangeNumber() {
		assertEquals(sudoku.getEmpyCellNumber(),51);
		assertTrue(sudoku.setCell(0, 0, 4));
		assertEquals(sudoku.getEmpyCellNumber(),50);
		assertTrue(sudoku.setCell(0, 0, 5));
		assertEquals(sudoku.getEmpyCellNumber(),50);
	}
	
	
	@Test
	public void testKO1FilledWithSetting() {
		// check coloumns
		assertFalse(sudoku.setCell(0, 0, 9));
		assertFalse(sudoku.setCell(1, 8, 7));
	}
	
	
	@Test
	public void testKO2FilledWithSetting() {
		//check rows
		assertFalse(sudoku.setCell(0, 0, 3));
		assertFalse(sudoku.setCell(7, 8, 7));
	}
	
	
	@Test
	public void testKO3FilledWithSetting() {
		// check sub-squares 
		assertFalse(sudoku.setCell(0, 1, 3));
		assertFalse(sudoku.setCell(7, 8, 7));
	}
	
	
	
	@Test
	public void testKOChangeNumber1() {
		assertThrows(IllegalArgumentException.class, () -> sudoku.setCell(10, 0,1));
	}
	
	@Test
	public void testKOChangeNumber2() {
		assertThrows(IllegalArgumentException.class, () -> sudoku.setCell(0, 0,12));
	}
	
	@Test
	public void testClearCell() {
		assertEquals(sudoku.getEmpyCellNumber(),51);
		assertTrue(sudoku.setCell(0, 0, 4));
		assertEquals(sudoku.getEmpyCellNumber(),50);
		sudoku.clearCell(0, 0);
		assertEquals(sudoku.getEmpyCellNumber(),51);
	}
	
	
	@Test
	public void testKOClearCell() {
		assertThrows(IllegalArgumentException.class, () -> sudoku.clearCell(10, 0));
	}
	
}
