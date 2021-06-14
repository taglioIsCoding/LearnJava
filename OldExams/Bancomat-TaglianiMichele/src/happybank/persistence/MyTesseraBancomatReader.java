package happybank.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import happybank.model.ContoCorrente;
import happybank.model.TesseraBancomat;

public class MyTesseraBancomatReader implements TesseraBancomatReader{

	@Override
	public List<TesseraBancomat> read(Reader reader, Map<String, ContoCorrente> contoCorrenteMap)
			throws IOException, BadFileFormatException {
		
		BufferedReader buffered = new BufferedReader(reader);
		List<TesseraBancomat> listaBancomats = new ArrayList<>();
		
		try {
			String line;
			while((line = buffered.readLine())!= null) {
				if (line.isEmpty()) {
					continue;
				}
				
				StringTokenizer stk = new StringTokenizer(line, "\s\t");
				String stringaConto = stk.nextToken().trim();
				if (stringaConto == null) {
					throw new BadFileFormatException("stringaConto nulla o vuota");
				}
				
				String pin =  stk.nextToken().trim();
				if (pin == null) {
					throw new BadFileFormatException("pin errata");
				}
				
				String massimale =  stk.nextToken().trim();
				int massimaleInt;
				if (massimale == null) {
					throw new BadFileFormatException("massimale errato");
				}
				try {
					massimaleInt = Integer.parseInt(massimale);
				} catch (Exception e) {
					throw new BadFileFormatException("Massimale non convertibile errato");
				}
				
				ContoCorrente client = contoCorrenteMap.get(stringaConto);
				if(client != null) {
					listaBancomats.add(new TesseraBancomat(pin, massimaleInt, client));
				}else {
					throw new BadFileFormatException("Cliente non trovato");
				}
				
				
				
			}
		} catch (Exception e) {
			throw new BadFileFormatException(e);
		}
		
		return listaBancomats;
	}

}
