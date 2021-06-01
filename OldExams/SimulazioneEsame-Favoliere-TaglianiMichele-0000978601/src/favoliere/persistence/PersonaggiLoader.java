package favoliere.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import favoliere.model.Conclusione;
import favoliere.model.Personaggio;
import favoliere.model.Tipologia;


public class PersonaggiLoader implements SectionLoader<Personaggio>{

	private List<Personaggio> pers = new ArrayList<>();
	
	@Override
	public List<Personaggio> getItems() {
		return this.pers;
	}

	@Override
	public void loadAllItems(Reader baseReader) throws IOException, BadFileFormatException {
		String line;
		List<Personaggio> personaggi = new ArrayList<>();
		BufferedReader rdr = new BufferedReader(baseReader);
		while ((line = rdr.readLine()) != null) {
			StringTokenizer stk = new StringTokenizer(line, ":");
			
			String pos = stk.nextToken();
			Tipologia tip;
			if (pos == null) {
				throw new BadFileFormatException("Descrizione nulla o vuota");
			}
			try {
				tip = Tipologia.valueOf(pos);
			} catch (Exception e) {
				throw new BadFileFormatException("Tipo Scorretto");
			}
			String nome = stk.nextToken();
			if (nome == null || nome.isBlank()) {
				throw new BadFileFormatException("Campo nome errato");
			}
			String desc = stk.nextToken();
			if (desc == null || desc.isBlank()) {
				throw new BadFileFormatException("desc errato");
			}
			
			System.out.println(new Personaggio(nome , tip, desc));
			
			this.pers.add(new Personaggio(nome.trim() , tip, desc.trim()));
			
		}
	}

}
