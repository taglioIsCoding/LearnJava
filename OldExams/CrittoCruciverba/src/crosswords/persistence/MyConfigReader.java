package crosswords.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.StringTokenizer;

import crosswords.model.Scheme;

public class MyConfigReader implements ConfigReader{

	private Scheme board;
	private int size;
	
	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public Scheme getScheme() {
		return this.board;
	}
	
	public MyConfigReader(Reader reader) throws IOException, BadFileFormatException {
		BufferedReader rdr = new BufferedReader(reader);
		
		String line = rdr.readLine();
		if(line.isEmpty()) {
			throw new BadFileFormatException("line nulla");
		}else {
			this.size = parseFirstLine(line);
		}
		
		this.board = new Scheme(size);
		
		for (int i = 0; i < this.size; i++) {
			line = rdr.readLine();
			if (line == null) {
				throw new BadFileFormatException("line nulla");
			}
			
			String pezzi[] = line.split(" ");
			int numeri[] = new int[this.size];
			
			if(pezzi.length != this.size) {
				throw new BadFileFormatException("non abbastanza elementi");
			}
			
			for(int j = 0; j < this.size; j++) {
				if(pezzi[j].equals("#")) {
					numeri[j] = 0;
				}else{
					try {
						numeri[j] = Integer.parseInt(pezzi[j]);
					} catch (Exception e) {
						throw new BadFileFormatException("Elementi errati");
					}
				}	
			}
			
			this.board.setCellRow(i, numeri);
			
		}
		
	}

	private int parseFirstLine(String line) throws BadFileFormatException {
		StringTokenizer stk = new StringTokenizer(line, " x");
		if(stk.countTokens() != 3) {
			throw new BadFileFormatException("priva riga numero non giusto di elementi");
		}
		
		String dimString = stk.nextToken().trim();
		if(!dimString.equals("DIM")) {
			throw new BadFileFormatException("DIM errato");
		}
		
		String primoNumeroS = stk.nextToken().trim();
		int primo;
		try {
			primo = Integer.parseInt(primoNumeroS);
		} catch (Exception e) {
			throw new BadFileFormatException("non converto primo");
		}
		
		String secondoNumeroS = stk.nextToken().trim();
		int secondo;
		try {
			secondo = Integer.parseInt(secondoNumeroS);
		} catch (Exception e) {
			throw new BadFileFormatException("non converto secondo");
		}
		
		if(primo != secondo) {
			throw new BadFileFormatException("Numeri diversi");
		}
		
		return primo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
