package sudoku.controller.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sudoku.controller.Controller;
import sudoku.controller.MyController;
import sudoku.model.SudokuBoard;

public class MyControllerTest {
	private Controller myControl;
	
	@BeforeEach
	public void setUp()
	{
		SudokuBoard sudoku = new SudokuBoard();
		sudoku.setCellRow(0, new int[] {0,0,0,0,9,8,0,3,0});
		sudoku.setCellRow(1, new int[] {3,0,0,4,0,5,9,0,0});
		sudoku.setCellRow(2, new int[] {0,9,0,0,0,0,0,4,6});
		sudoku.setCellRow(3, new int[] {1,6,0,0,0,0,0,7,5});
		sudoku.setCellRow(4, new int[] {0,0,0,3,8,0,0,0,9});
		sudoku.setCellRow(5, new int[] {2,3,9,0,0,0,4,0,0});
		sudoku.setCellRow(6, new int[] {0,4,7,0,0,1,0,5,0});
		sudoku.setCellRow(7, new int[] {0,1,0,8,0,0,0,0,0});
		sudoku.setCellRow(8, new int[] {0,0,0,0,3,0,0,9,7});
		myControl= new MyController(sudoku);
		
	}
	

	@Test
	public void testGetSize() {
		assertEquals(myControl.getSize(),9);
	}
	
	
	@Test
	public void testGetCellLabel() {
		assertEquals(myControl.getCellLabel(8, 8), "7");
		assertEquals(myControl.getCellLabel(0, 0), " ");
		
	}
	
	@Test
	public void testSetCell() {
		assertEquals(myControl.getCellLabel(0, 4), "9");
		assertTrue(myControl.setCell(0, 4, " "));
		assertEquals(myControl.getCellLabel(0, 4), " ");
		assertFalse(myControl.setCell(0, 4, "8"));
		
	}
	
	
}
