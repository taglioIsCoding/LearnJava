package cambiavalute.persistence;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import cambiavalute.model.CambiaValute;
import cambiavalute.model.TassiDiCambio;

public class MyCambiReader implements CambiReader{

	@Override
	public Map<String, TassiDiCambio> leggiTabellaCambiApplicati(Reader reader) throws BadFileFormatException {
		if(reader == null) {
			throw new BadFileFormatException();
		}
		BufferedReader bReader = new BufferedReader(reader);
		Map<String, TassiDiCambio> tabellaMap = new HashMap<>();
		
		try {
			String line;
			while((line = bReader.readLine())!= null) {
				if (line.isEmpty()) {
					continue;
				}
				
				StringTokenizer stk = new StringTokenizer(line, "\s\t");
				String sigla = stk.nextToken().trim();
				if (sigla == null) {
					throw new BadFileFormatException("Descrizione nulla o vuota");
				}
				
				String priceVendita =  stk.nextToken().trim();
				if (priceVendita == null) {
					throw new BadFileFormatException("Vendita errata");
				}
				
				String priceAcquisto =  stk.nextToken().trim();
				if (priceAcquisto == null) {
					throw new BadFileFormatException("Acquisto errato");
				}
				
				tabellaMap.put(sigla, new TassiDiCambio(sigla, CambiaValute.convertiInDouble(priceAcquisto),  CambiaValute.convertiInDouble(priceVendita)));
			}
		} catch (Exception e) {
			throw new BadFileFormatException(e);
		}
		
		return tabellaMap;
	}

}
