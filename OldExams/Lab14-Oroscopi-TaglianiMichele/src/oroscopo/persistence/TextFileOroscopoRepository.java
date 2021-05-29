package oroscopo.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import oroscopo.model.Previsione;
import oroscopo.model.SegnoZodiacale;

public class TextFileOroscopoRepository implements OroscopoRepository{
	
	private Map<String, List<Previsione>> data = new HashMap<String, List<Previsione>>();

	public TextFileOroscopoRepository(Reader BaseReader) throws IOException, BadFileFormatException{
		if(BaseReader == null) {
			throw new IllegalArgumentException();
		}
		BufferedReader rdr = new BufferedReader(BaseReader);
//		String line;
//		while((line = rdr.readLine()) != null) {
//			String nomeSezione = line;
//			
//			
//			if(nomeSezione.contains("\t")) {
//				throw new BadFileFormatException("Nome sezione errato");
//			}
//			List<Previsione> previsioni = caricaPrevisioni(rdr);
//			
//			System.out.println(nomeSezione +" "+ previsioni);
//			
//			}
		
		String settore;
		while ((settore = readLineSkippingEmpty(rdr)) != null) {
			if(settore.contains(" ") || settore.contains("\t")) {
				throw new BadFileFormatException("Linea sezione errata");
			}
			
			List<Previsione> previsioni = caricaPrevisioni(rdr);	
			data.put(settore.trim().toLowerCase(), previsioni);
		}
		
		
		
	}
	
	private List<Previsione> MiaSbagliatacaricaPrevisioni(BufferedReader rdr) throws IOException, BadFileFormatException{
		List<Previsione> previsioniList = new ArrayList<>();
		String line = rdr.readLine();
		String previsione ;
		String voto ;
		String segni ;
		while(!(line = rdr.readLine().trim()).equalsIgnoreCase("FINE")) {
			System.out.println("-------------------");
			if (line.isEmpty()) {
				line = rdr.readLine();
				continue;
			}
			StringTokenizer stk = new StringTokenizer(line);
			 previsione = stk.nextToken("\t");
			 voto = stk.nextToken("\t");
			 segni = stk.nextToken("\t");
			 int votoInt;
			 
			 if (previsione == null) {
					throw new BadFileFormatException("Descrizione nulla o vuota");
			}
			 try {
				 votoInt = Integer.parseInt(voto);
				} catch (NumberFormatException e) {
					throw new BadFileFormatException("Campo fortuna non presente");
				}
				if (votoInt < 0) {
					throw new BadFileFormatException("Campo fortuna negativo");
				}

				System.out.println(previsione);
				System.out.println(votoInt);
				System.out.println(segni);
			
			
			 if(segni.length() > 0) {
				 Set<SegnoZodiacale> segniSet = new HashSet<SegnoZodiacale>();
				 String[] segniSingoli = segni.split(",");
				 for (int i = 0; i < segniSingoli.length; i++) {
						SegnoZodiacale segno = SegnoZodiacale.valueOf(segniSingoli[i]);
						if (segno != null) {
							segniSet.add(segno);
						}
				}
				 previsioniList.add(new Previsione(previsione, votoInt, segniSet));
				 
			 } else {
				 previsioniList.add(new Previsione(previsione, votoInt));
			 }
			
		}
		
		for(Previsione p : previsioniList ) {
			System.out.println(p);
		}
		
		System.out.println(previsioniList.size());
		
		if (previsioniList.size() == 0) {
			throw new BadFileFormatException("Settore senza previsioni");
		}
		
		return previsioniList;
	}
	
	
	private List<Previsione> caricaPrevisioni(BufferedReader reader) throws IOException, BadFileFormatException {
		String line;
		List<Previsione> previsioni = new ArrayList<>();
		try {
			while (!(line = reader.readLine().trim()).equalsIgnoreCase("FINE")) {
				if (line.isEmpty()) {
					continue;
				}

				StringTokenizer stk = new StringTokenizer(line, "\t");
				String descr = stk.nextToken();
				if (descr == null) {
					throw new BadFileFormatException("Descrizione nulla o vuota");
				}
				int fort;
				try {
					fort = Integer.parseInt(stk.nextToken());
				} catch (NumberFormatException e) {
					throw new BadFileFormatException("Campo fortuna non presente");
				}
				if (fort < 0) {
					throw new BadFileFormatException("Campo fortuna negativo");
				}
				Set<SegnoZodiacale> segniZodiacali = new HashSet<>();
				if (stk.hasMoreElements()) {
					String l = stk.nextToken().trim();
					String[] segni = l.split(",");
					for (int i = 0; i < segni.length; i++) {
						SegnoZodiacale segno = SegnoZodiacale.valueOf(segni[i]);
						if (segno != null) {
							segniZodiacali.add(segno);
						}
					}
				}

				if (segniZodiacali.isEmpty()) {
					previsioni.add(new Previsione(descr, fort));
				} else {
					previsioni.add(new Previsione(descr, fort, segniZodiacali));
				}
			}
		} catch (Exception e) {
			throw new BadFileFormatException(e);
		}
		if (previsioni.size() == 0) {
			throw new BadFileFormatException("Settore senza previsioni");
		}
		return previsioni;
	}
	
	@Override
	public Set<String> getSettori() {
		return data.keySet();
	}

	@Override
	public List<Previsione> getPrevisioni(String settore) {
		return this.data.get(settore.trim().toLowerCase());
	}
	
	
	private String readLineSkippingEmpty(BufferedReader reader) throws IOException {
		String line;
		do { // skips empty lines
			line = reader.readLine();
		} while (line != null && line.trim().isEmpty());
		return line;
	}

	
	
}
