package unident.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import unident.model.AttivitaFormativa;
import unident.model.Ssd;
import unident.model.Tipologia;

public class UniDentPianoDidatticoReader implements PianoDidatticoReader{

	@Override
	public Set<AttivitaFormativa> readAll(Reader rdr) throws IOException {
		if(rdr == null) {
			throw new BadFileFormatException("Reader null");
		}
		
		BufferedReader bReader = new BufferedReader(rdr);
		Set<AttivitaFormativa > afSet = new HashSet<>();
		
		String line;
		while((line = bReader.readLine())!= null) {
			if (line.isEmpty()) {
				continue;
			}
			
			int codice;
			int crediti;
			String nomeString;
			String semeString;
			String tipeString;
			String settoreString;
			Ssd ssd;
			String[] pezziStrings = line.split("\\t+");
			
			if(pezziStrings.length < 4) {
				throw new BadFileFormatException("Non abbastanza campi");
			}
			
			try {
				codice = Integer.parseInt(pezziStrings[0].trim());
			} catch (Exception e) {
				throw new BadFileFormatException("Codice non corretto");
			}
			nomeString = pezziStrings[1].trim();
			semeString = pezziStrings[2].trim();
			tipeString = pezziStrings[3].trim();
			if(!"ABCDEF".contains(tipeString)||tipeString == null) {
				throw new BadFileFormatException("Tipo non corretto");
			}else {
				Tipologia tipologia;
				try {
					tipologia = Tipologia.valueOf(tipeString);
				} catch (Exception e) {
					throw new BadFileFormatException("Tipo non corretto");
				}
				if (tipologia.ordinal() < Tipologia.E.ordinal()) {
					ssd = Ssd.of(pezziStrings[4].trim());
					if(ssd == null) {
						throw new BadFileFormatException("SSD non corretto");
					}
					try {
						crediti = Integer.parseInt(pezziStrings[5].trim());
					} catch (Exception e) {
						throw new BadFileFormatException("crediti non corretto 1");
					}
					
					afSet.add(new AttivitaFormativa(nomeString, tipologia, ssd, crediti));
					
				}else {
					try {
						crediti = Integer.parseInt(pezziStrings[4].trim());
					} catch (Exception e) {
						throw new BadFileFormatException("crediti non corretto 2");
					}
					
					afSet.add(new AttivitaFormativa(nomeString, tipologia, Ssd.SENZASETTORE, crediti));
					
				}
			}
			
		}
		return afSet;
	}

}
