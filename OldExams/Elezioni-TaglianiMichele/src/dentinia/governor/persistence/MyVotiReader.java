package dentinia.governor.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.StringTokenizer;

import dentinia.governor.model.Elezioni;
import dentinia.governor.model.Partito;

public class MyVotiReader implements VotiReader{

	private Elezioni elezioni;
	private BufferedReader reader;
	private long seggiDaAssegnare;
	
	@Override
	public Elezioni getElezioni() {
		return this.elezioni;
	}
	
	public MyVotiReader(Reader reader) throws IOException, BadFileFormatException {
		if(reader == null) {
			throw new IllegalArgumentException();
		}
		this.reader = new BufferedReader(reader);
		this.elezioni = caricaElementi();
	}
	
	private Elezioni caricaElementi() throws IOException, BadFileFormatException {
		Elezioni toRetElezioni;
		int seggiInteri;
		String line;
		try {
			line = reader.readLine();
			if(line != null) {
				StringTokenizer stk = new StringTokenizer(line, " ");
				stk.nextToken().trim();
				String seggi = stk.nextToken().trim();
				
				if (seggi == null) {
					throw new BadFileFormatException("Seggi nulli");
				}
				try {
					NumberFormat nFormat = NumberFormat.getNumberInstance(Locale.ITALY);
					seggiInteri = nFormat.parse(seggi).intValue();
				} catch (Exception e) {
					throw new BadFileFormatException("Seggi non convertibili");
				}
			}else {
				throw new BadFileFormatException("Linea seggi errata");
			}
			
			toRetElezioni = new Elezioni(seggiInteri);
			
			line = reader.readLine();
			if(line != null) {
				StringTokenizer stk = new StringTokenizer(line, ",");
				
				while (stk.hasMoreElements()) {
					String nameVoteString = stk.nextToken().trim();
					NumberFormat nFormat = NumberFormat.getNumberInstance(Locale.ITALY);
					String nameString = nameVoteString.split("[0-9]")[0].trim();
					if(nameString.isBlank()) {
						throw new BadFileFormatException("Nome partito assente");
					}
					String votiString = nameVoteString.substring(nameString.length()).trim();
					int numberVoti;
					try {
						numberVoti = nFormat.parse(votiString).intValue();
					} catch (ParseException e) {
						throw new BadFileFormatException("Voti non convertibili");
					}
					
					toRetElezioni.setVoti(new Partito(nameString), numberVoti);
					
				}
				
			}else {
				throw new BadFileFormatException("Linea seggi errata");
			}
		} catch (IOException e) {
			throw new IOException(e);
		}
		
		return toRetElezioni;
	}

}
