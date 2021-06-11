package electriclife.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import electriclife.model.Tariffa;
import electriclife.model.TariffaAConsumo;
import electriclife.model.TariffaFlat;

public class MyTariffeReader implements TariffeReader{

	private boolean aConsumoTrovata = false;
	private Reader innerReader;
	
	
	
	public MyTariffeReader(Reader innerReader) {
		super();
		this.innerReader = innerReader;
	}



	@Override
	public Collection<Tariffa> caricaTariffe() throws IOException, BadFileFormatException {
		if(innerReader == null) {
			throw new IOException();
		}
		BufferedReader bReader = new BufferedReader(innerReader);
		ArrayList<Tariffa > tariffeArray = new ArrayList<>();
		
		String line;
		while((line = bReader.readLine())!= null) {
			if (line.isEmpty()) {
				continue;
			}
			StringTokenizer stk = new StringTokenizer(line, ";");
			
			String tipoTariffa = stk.nextToken().trim();
			if (tipoTariffa == null) {
				throw new BadFileFormatException("Descrizione nulla o vuota");
			}else if(tipoTariffa.equals("FLAT")){
				tariffeArray.add(readFlat(stk));
			}else if (tipoTariffa.equals("A CONSUMO")) {
				if(this.aConsumoTrovata) {
					throw new BadFileFormatException("A cosumo piu di una");
				}else {
					tariffeArray.add(readConsumo(stk));
					this.aConsumoTrovata = true;
				}
			}else {
				throw new BadFileFormatException("Descrizione sbagliata");
			}
		}
		
		
		return tariffeArray;
	}
	
	private Tariffa readConsumo(StringTokenizer sTokenizer) throws BadFileFormatException {
		
		
		String costoAKW =  sTokenizer.nextToken().trim();
		double costoAKwaDouble;
		if (costoAKW == null) {
			throw new BadFileFormatException("costo extra errato");
		}else {
			int indexSpace = costoAKW.indexOf("€");
			costoAKW = costoAKW.substring(indexSpace, costoAKW.length()).trim();
			DecimalFormat currencyF = new DecimalFormat("¤ #,##0.##");
			currencyF.setMaximumFractionDigits(2);
			try {
				costoAKwaDouble = currencyF.parse(costoAKW).doubleValue();
			} catch (ParseException e) {
				throw new BadFileFormatException("costo extra non convertibile");
			}
		}
		
		return new TariffaAConsumo("A CONSUMO", costoAKwaDouble);
	}
	
	private Tariffa readFlat(StringTokenizer sTokenizer) throws BadFileFormatException {
		if (sTokenizer.countTokens() != 4) {
			throw new BadFileFormatException("formato errato");
		}
		String nome =  sTokenizer.nextToken().trim();
		if (nome == null) {
			throw new BadFileFormatException("nome errato");
		}
		
		String sogliaString =  sTokenizer.nextToken().trim();
		int sogliaInt;
		if (sogliaString == null) {
			throw new BadFileFormatException("soglia errato");
		}else {
			int indexSpace = sogliaString.indexOf(" ");
			if(!sogliaString.substring(0, indexSpace).trim().equals("SOGLIA")) {
				throw new BadFileFormatException("soglia non convertibile");
			}
			sogliaString = sogliaString.substring(indexSpace, sogliaString.length()).trim();
			try {
				sogliaInt = Integer.parseInt(sogliaString);
			} catch (Exception e) {
				throw new BadFileFormatException("soglia non convertibile");
			}
		}
		
		String costoFisso =  sTokenizer.nextToken().trim();
		double costoFissoDouble;
		if (costoFisso == null) {
			throw new BadFileFormatException("costo fisse errato");
		}else {
			DecimalFormat currencyF = new DecimalFormat("¤ #,##0.##");
			currencyF.setMaximumFractionDigits(2);
			
			try {
				costoFissoDouble = currencyF.parse(costoFisso).doubleValue();
			} catch (ParseException e) {
				throw new BadFileFormatException("costo fisso non convertibile");
			}
			if(costoFissoDouble == 0) {
				throw new BadFileFormatException("costo extra non convertibile");
			}
		}
		
		String costoExtra =  sTokenizer.nextToken().trim();
		double costoExtraDouble;
		if (costoExtra == null) {
			throw new BadFileFormatException("costo extra errato");
		}else {
			int indexSpace = costoExtra.indexOf("€");
			if(!costoExtra.substring(0, indexSpace).trim().equals("KWh EXTRA")) {
				throw new BadFileFormatException("soglia non convertibile");
			}
			costoExtra = costoExtra.substring(indexSpace, costoExtra.length()).trim();
			DecimalFormat currencyF = new DecimalFormat("¤ #,##0.##");
			currencyF.setMaximumFractionDigits(2);
			try {
				costoExtraDouble = currencyF.parse(costoExtra).doubleValue();
			} catch (ParseException e) {
				throw new BadFileFormatException("costo extra non convertibile");
			}
			if(costoExtraDouble == 0) {
				throw new BadFileFormatException("costo extra non convertibile");
			}
		}
		
		return new TariffaFlat(nome, costoFissoDouble, sogliaInt, costoExtraDouble);
	}

}
