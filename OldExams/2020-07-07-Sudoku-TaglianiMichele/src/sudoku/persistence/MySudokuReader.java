package sudoku.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import sudoku.model.SudokuBoard;

public class MySudokuReader implements SudokuReader{
	
	private SudokuBoard sudoku = new SudokuBoard();
	private static int DIM = 9;

	@Override
	public SudokuBoard getSudoku() {
		return this.sudoku;
	}
	
	public MySudokuReader(Reader reader) throws IOException, BadFileFormatException {
		BufferedReader rdr = new BufferedReader(reader);
		System.out.println("----------------");
		
		for(int i = 0; i < DIM; i ++) {
			String line = rdr.readLine();
			System.out.println(line);
			if(line == null || line.isEmpty()) {
				throw new BadFileFormatException("Mancano delle righe");
			}
			
			StringTokenizer stk = new StringTokenizer(line, "\t");
			
			if(stk.countTokens() != DIM) {
				throw new BadFileFormatException("Mancano delle colonne");
			}
			
			int integers[] = new int[DIM];
			for(int j = 0; j < DIM; j ++) {
				String valString = stk.nextToken().trim();
				if(valString.equals("#")) {
					integers[j] = 0;
				}else {
					try {
						int intvalue = Integer.parseInt(valString);
						if(intvalue <= 0 || intvalue > DIM) {
							throw new BadFileFormatException("Numero non valido");
						}
						integers[j] = intvalue;
					} catch (Exception e) {
						throw new BadFileFormatException("Numero non valido");
					}
				}
				
			}
			
			for(int j = 0; j < DIM; j ++) {
				System.out.println(integers[j]);
			}
			
			this.sudoku.setCellRow(i, integers);
			System.out.println(this.sudoku.toString());
			
		}
		
		if (rdr.readLine() != null) {
			throw new BadFileFormatException("Troppe righe");
		}
		
	}

}
