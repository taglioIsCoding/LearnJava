package minesweeper.persistence;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.StringTokenizer;

public class MyConfigReader implements ConfigReader{
	
	private int mines;
	private int size;
	
	@Override
	public int getSize() {
		return this.size;
	}
	@Override
	public int getMinesNumber() {
		return this.mines;
	}
	
	public MyConfigReader(Reader reader) throws BadFileFormatException {
		BufferedReader rdr = new BufferedReader(reader);
		
		for(int i = 0; i <= 1; i++) {
			String line;
			try {
				 line = rdr.readLine();
			} catch (Exception e) {
				throw new BadFileFormatException(e);
			}
			
			if(line == null) {
			throw new BadFileFormatException("Linea nulla");
			}
			String[] items = line.split(":");
			if(items.length == 2) {
				String comando = items[0];
				if(comando != null && comando.equalsIgnoreCase("Mines")) {
					try {
						String nMine = items[1].strip();
						this.mines = Integer.parseInt(nMine);
					} catch (Exception e) {
						throw new BadFileFormatException("Errore mine");
					}
				}else if (comando != null && comando.equalsIgnoreCase("Size")) {
					try {
						String size = items[1].strip();
						this.size = Integer.parseInt(size);
					} catch (Exception e) {
						throw new BadFileFormatException("Errore size");
					}
				}else {
					throw new BadFileFormatException("Qualcosa è andato storto");
				}
			}else {
				throw new BadFileFormatException("Formato Errato");
			}	
		}
	}
	

}
