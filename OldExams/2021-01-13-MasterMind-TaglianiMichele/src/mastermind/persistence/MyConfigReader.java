package mastermind.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class MyConfigReader implements ConfigReader{
	
	private int dim = 0;
	private int MaxTentativi = 0;
	
	
	public MyConfigReader(Reader reader) throws BadFileFormatException {
		BufferedReader rdr = new BufferedReader(reader);
		
		for(int i = 0; i < 2; i++) {
			String lineString;
			try {
				lineString = rdr.readLine();
			} catch (IOException e1) {
				throw new BadFileFormatException("Errore IO");
			}
			String splotted[] = lineString.split("=");
			
			if(splotted.length != 2) {
				throw new BadFileFormatException("Non abbastanza elemnti");
			}
			
			if(splotted[0].trim().equalsIgnoreCase("tentativi")) {
				try {
					MaxTentativi = Integer.parseInt(splotted[1].trim());
				} catch (Exception e) {
					throw new BadFileFormatException("Numero MaxTentativi illeggibile");
				}
			}else{
				String divise[] = splotted[0].trim().split(" ");
				if(divise.length != 2 || !divise[0].equalsIgnoreCase("lunghezza") || !divise[1].equalsIgnoreCase("combinazione")) {
					throw new BadFileFormatException("Parola non riconosciuta");
				}
				try {
					dim = Integer.parseInt(splotted[1].trim());
				} catch (Exception e) {
					throw new BadFileFormatException("Numero dim illeggibile");
				}
				
			}
		}
	}
	
	@Override
	public int dimensioneCodice() {
		return dim;
	}
	@Override
	public int numeroTentativi() {
		return MaxTentativi;
	}
	
	
}
