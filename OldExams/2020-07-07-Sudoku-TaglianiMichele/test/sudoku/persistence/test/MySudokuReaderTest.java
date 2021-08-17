package sudoku.persistence.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.OptionalInt;

import org.junit.jupiter.api.Test;

import sudoku.model.SudokuBoard;
import sudoku.persistence.BadFileFormatException;
import sudoku.persistence.MySudokuReader;
import sudoku.persistence.SudokuReader;



public class MySudokuReaderTest {
	
	@Test
	public void testOK1_BasicUnconfigured() throws BadFileFormatException, IOException {
		String fakeFile = "#\t#\t#\t#\t9\t8\t#\t3\t#\r\n" + 
				"3\t#\t#\t4\t#\t5\t9\t#\t#\r\n" +
				"#\t9\t#\t#\t#\t#\t#\t4\t6\r\n" +
				"1\t6\t#\t#\t#\t#\t#\t7\t5\r\n" +
				"#\t#\t#\t3\t8\t#\t#\t#\t9\r\n" +
				"2\t3\t9\t#\t#\t#\t4\t#\t#\r\n" +
				"#\t4\t7\t#\t#\t1\t#\t5\t#\r\n" +
				"#\t1\t#\t8\t#\t#\t#\t#\t#\r\n" +
				"#\t#\t#\t#\t3\t#\t#\t9\t7\r\n";
		StringReader reader = new StringReader(fakeFile);
		SudokuReader sudokuReader = new MySudokuReader(reader);
	    SudokuBoard sudoku= sudokuReader.getSudoku();
	    assertEquals(sudoku.getEmpyCellNumber(),51);

	    assertEquals(sudoku.getCell(1, 0).getAsInt(), 3);
	    assertEquals(sudoku.getCell(0, 4).getAsInt(), 9); 
		assertEquals(sudoku.getCell(4, 3).getAsInt(), 3);
		assertEquals(sudoku.getCell(8, 1), OptionalInt.empty());
		assertEquals(sudoku.getCell(8, 8).getAsInt(),7);
		assertFalse(sudoku.isFull());
	}
	
	@Test
	public void testKO1_MissingRow() throws BadFileFormatException, IOException {
		String fakeFile = "#\t#\t#\t#\t9\t8\t#\t3\t#\r\n" + 
				"3\t#\t#\t4\t#\t5\t9\t#\t#\r\n" +
				"#\t9\t#\t#\t#\t#\t#\t4\t6\r\n" +
				"1\t6\t#\t#\t#\t#\t#\t7\t5\r\n" +
				"2\t3\t9\t#\t#\t#\t4\t#\t#\r\n" +
				"#\t4\t7\t#\t#\t1\t#\t5\t#\r\n" +
				"#\t1\t#\t8\t#\t#\t#\t#\t#\r\n" +
				"#\t#\t#\t#\t3\t#\t#\t9\t7\r\n";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> {
			SudokuReader sudokuReader = new MySudokuReader(reader);
			sudokuReader.getSudoku();
		});
	}
	@Test
	public void testKO2_TooManyRows() throws BadFileFormatException, IOException {
		String fakeFile = "#\t#\t#\t#\t9\t8\t#\t3\t#\r\n" + 
				"3\t#\t#\t4\t#\t5\t9\t#\t#\r\n" +
				"#\t9\t#\t#\t#\t#\t#\t4\t6\r\n" +
				"1\t6\t#\t#\t#\t#\t#\t7\t5\r\n" +
				"#\t#\t#\t3\t8\t#\t#\t#\t9\r\n" +
				"#\t#\t#\t3\t8\t#\t#\t#\t9\r\n" +
				"2\t3\t9\t#\t#\t#\t4\t#\t#\r\n" +
				"#\t4\t7\t#\t#\t1\t#\t5\t#\r\n" +
				"#\t1\t#\t8\t#\t#\t#\t#\t#\r\n" +
				"#\t#\t#\t#\t3\t#\t#\t9\t7\r\n";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> {
			SudokuReader sudokuReader = new MySudokuReader(reader);
			sudokuReader.getSudoku();
		});
	}
	@Test
	public void testKO3_MissingItem() throws BadFileFormatException, IOException {
		String fakeFile = "#\t#\t#\t#\t9\t8\t#\t3\t#\r\n" + 
				"3\t#\t#\t4\t#\t5\t9\t#\t#\r\n" +
				"#\t9\t#\t#\t#\t#\t#\t4\t6\r\n" +
				"1\t6\t#\t#\t#\t#\t#\t7\t5\r\n" +
				"#\t#\t#\t3\t8\t#\t#\t#\t\r\n" +
				"2\t3\t9\t#\t#\t#\t4\t#\t#\r\n" +
				"#\t4\t7\t#\t#\t1\t#\t5\t#\r\n" +
				"#\t1\t#\t8\t#\t#\t#\t#\t#\r\n" +
				"#\t#\t#\t#\t3\t#\t#\t9\t7\r\n";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> {
			SudokuReader sudokuReader = new MySudokuReader(reader);
			sudokuReader.getSudoku();
		});
	}
	@Test
	public void testKO4_TooManyItems() throws BadFileFormatException, IOException {
		String fakeFile = "#\t#\t#\t#\t9\t8\t#\t3\t#\r\n" + 
				"3\t#\t#\t4\t#\t5\t9\t#\t#\r\n" +
				"#\t9\t#\t#\t#\t#\t#\t4\t6\r\n" +
				"1\t6\t#\t#\t#\t#\t#\t7\t5\r\n" +
				"#\t#\t#\t3\t8\t#\t#\t#\t9\r\n" +
				"2\t3\t9\t#\t#\t#\t4\t#\t#\r\n" +
				"#\t4\t7\t#\t#\t1\t#\t5\t#\r\n" +
				"#\t1\t#\t8\t#\t#\t#\t#\t#\r\n" +
				"#\t#\t#\t#\t3\t#\t#\t9\t7\t#\r\n";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> {
			SudokuReader sudokuReader = new MySudokuReader(reader);
			sudokuReader.getSudoku();
		});
	}
	@Test
	public void testKO5_InvalidItem() throws BadFileFormatException, IOException {
		String fakeFile = "#\tx\t#\t#\t9\t8\t#\t3\t#\r\n" + 
				"3\t#\t#\t4\t#\t5\t9\t#\t#\r\n" +
				"#\t9\t#\t#\t#\t#\t#\t4\t6\r\n" +
				"1\t6\t#\t#\t#\t#\t#\t7\t5\r\n" +
				"#\t#\t#\t3\t8\t#\t#\t#\t9\r\n" +
				"2\t3\t9\t#\t#\t#\t4\t#\t#\r\n" +
				"#\t4\t7\t#\t#\t1\t#\t5\t#\r\n" +
				"#\t1\t#\t8\t#\t#\t#\t#\t#\r\n" +
				"#\t#\t#\t#\t3\t#\t#\t9\t7\r\n";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> {
			SudokuReader sudokuReader = new MySudokuReader(reader);
			sudokuReader.getSudoku();
		});
	}
	@Test
	public void testKO6_MissingSeparator() throws BadFileFormatException, IOException {
		String fakeFile = "#\t#\t#\t#\t9\t8\t#\t3\t#\r\n" + 
				"3\t#\t#\t4\t#\t59\t#\t#\r\n" +
				"#\t9\t#\t#\t#\t#\t#\t4\t6\r\n" +
				"1\t6\t#\t#\t#\t#\t#\t7\t5\r\n" +
				"#\t#\t#\t3\t8\t#\t#\t#\t9\r\n" +
				"2\t3\t9\t#\t#\t#\t4\t#\t#\r\n" +
				"#\t4\t7\t#\t#\t1\t#\t5\t#\r\n" +
				"#\t1\t#\t8\t#\t#\t#\t#\t#\r\n" +
				"#\t#\t#\t#\t3\t#\t#\t9\t7\r\n";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> {
			SudokuReader sudokuReader = new MySudokuReader(reader);
			sudokuReader.getSudoku();
		});
	}
	@Test
	public void testKO7_Zero() throws BadFileFormatException, IOException {
		String fakeFile = "#\t#\t#\t#\t9\t8\t#\t3\t#\r\n" + 
				"3\t#\t#\t4\t#\t5\t9\t#\t#\r\n" +
				"#\t9\t#\t#\t#\t#\t#\t4\t6\r\n" +
				"1\t6\t#\t#\t#\t#\t#\t7\t5\r\n" +
				"#\t#\t#\t3\t8\t#\t#\t#\t9\r\n" +
				"2\t3\t9\t#\t#\t#\t4\t#\t#\r\n" +
				"#\t4\t7\t#\t#\t1\t#\t5\t#\r\n" +
				"#\t1\t#\t8\t#\t#\t#\t#\t#\r\n" +
				"0\t#\t#\t#\t3\t#\t#\t9\t7\r\n";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> {
			SudokuReader sudokuReader = new MySudokuReader(reader);
			sudokuReader.getSudoku();
		});
	}
	@Test
	public void testKO8_Ten() throws BadFileFormatException, IOException {
		String fakeFile = "#\t#\t#\t#\t9\t8\t#\t3\t#\r\n" + 
				"3\t#\t#\t4\t#\t5\t9\t#\t#\r\n" +
				"#\t9\t#\t#\t#\t#\t#\t4\t6\r\n" +
				"1\t6\t#\t#\t#\t#\t#\t7\t5\r\n" +
				"#\t#\t#\t3\t8\t#\t#\t#\t9\r\n" +
				"2\t3\t9\t#\t#\t#\t4\t#\t#\r\n" +
				"#\t4\t7\t#\t#\t1\t#\t5\t#\r\n" +
				"#\t1\t#\t8\t#\t#\t#\t#\t#\r\n" +
				"10\t#\t#\t#\t3\t#\t#\t9\t7\r\n";
		StringReader reader = new StringReader(fakeFile);
		assertThrows(BadFileFormatException.class, () -> {
			SudokuReader sudokuReader = new MySudokuReader(reader);
			sudokuReader.getSudoku();
		});
	}
}
