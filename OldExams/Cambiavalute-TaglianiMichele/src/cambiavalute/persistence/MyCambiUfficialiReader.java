package cambiavalute.persistence;

import java.io.BufferedReader;
import java.io.Reader;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import cambiavalute.model.CambiaValute;
import cambiavalute.model.TassiDiCambio;
import cambiavalute.model.TassoDiCambioUfficiale;

public class MyCambiUfficialiReader implements CambiUfficialiReader{

	@Override
	public Map<String, TassoDiCambioUfficiale> leggiTabellaCambiUfficiali(Reader reader) throws BadFileFormatException {
		if(reader == null) {
			throw new BadFileFormatException();
		}
		BufferedReader bReader = new BufferedReader(reader);
		Map<String, TassoDiCambioUfficiale> tabellaMap = new HashMap<>();
		
		try {
			String line;
			while((line = bReader.readLine())!= null) {
				if (line.isEmpty()) {
					continue;
				}
				
				StringTokenizer stk = new StringTokenizer(line, ",");
				String nomePaese = stk.nextToken().trim();
				if (nomePaese == null) {
					throw new BadFileFormatException("Nome paese scorretto");
				}
				
				String nomeValuta = stk.nextToken().trim();
				if (nomeValuta == null) {
					throw new BadFileFormatException("nomeValuta scorretto");
				}
				
				String sigla = stk.nextToken().trim();
				if (sigla == null) {
					throw new BadFileFormatException("sigla nulla o vuota");
				}
				stk.nextToken();
				
				String tassoDiCambio =  stk.nextToken().trim();
				if (tassoDiCambio == null) {
					throw new BadFileFormatException("tassoDiCambio errato");
				}
				double tassoDouble;
				try {
					tassoDouble = Double.parseDouble(tassoDiCambio);
				} catch (Exception e) {
					throw new BadFileFormatException("conversione errato");
				}
				
				tabellaMap.put(sigla, new TassoDiCambioUfficiale(sigla,
						nomeValuta, nomePaese,  tassoDouble));
			}
		} catch (Exception e) {
			throw new BadFileFormatException(e);
		}
		
		return tabellaMap;
		
	}

}
