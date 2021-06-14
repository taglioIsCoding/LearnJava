package bikerent.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import bikerent.model.Periodo;
import bikerent.model.Rate;

public class MyRateReader implements RateReader{

	@Override
	public Map<String, Rate> readRates(Reader reader) throws IOException, BadFileFormatException {
		if(reader == null) {
			throw new IOException("Reader Null");
		}
		
		Map<String, Rate> rateMap = new HashMap<>();
		
		BufferedReader rdReader = new BufferedReader(reader);
		String line;
		while((line = rdReader.readLine())!= null) {
			Rate toInsertRate = readOneRate(line);
			rateMap.put(toInsertRate.getCitta(), toInsertRate);
			
		}
		
		return rateMap;
	}
	
	private Rate readOneRate(String line) throws BadFileFormatException {
		System.out.println(line);
		StringTokenizer stk = new StringTokenizer(line, ",");
		ArrayList<String> pezziArrayList = new ArrayList<>();
		while(stk.hasMoreTokens()) {
			pezziArrayList.add(stk.nextToken().trim());
		}
		
		System.out.println(pezziArrayList);
		if(pezziArrayList.size() != 5) {
			throw new BadFileFormatException("Non ci sono abbastanza campi");
		}
		
		String nomeCitta = pezziArrayList.get(0);
		Periodo primoPeriodo = estraiPrimoPeriodo(pezziArrayList.get(1));
		Periodo periodiSuccessivi = estraiSuccesiviPeriodo(pezziArrayList.get(2));
		Optional<Duration> dutaMAxOptional = estraiDurataMax(pezziArrayList.get(3));
		Optional<LocalTime> oraMAxOptional = Optional.empty();
		if(dutaMAxOptional.isEmpty()) {
			oraMAxOptional = estraiOraMax(pezziArrayList.get(3));
			System.out.println(oraMAxOptional.get());
		}
		Double sanDouble = estraiSanzione(pezziArrayList.get(4));
		
		
		return new Rate(nomeCitta, primoPeriodo, periodiSuccessivi, dutaMAxOptional, oraMAxOptional, sanDouble);
	}
	
	private Periodo estraiPrimoPeriodo(String piece) throws BadFileFormatException {
		String[] partiStrings = piece.split("\s");
		int costo;
		Duration duration;
		try {
			costo = Integer.parseInt(partiStrings[0]);
		} catch (Exception e) {
			throw new BadFileFormatException("Impossibile convertire costo primo periodo");
		}
		
		if(!partiStrings[1].equals("cent") || partiStrings[1] == null) {
			throw new BadFileFormatException("Manca cent primo periodo");
		}
		
		if(!partiStrings[2].equals("per") || partiStrings[2] == null) {
			throw new BadFileFormatException("Manca parola chiave per");
		}
		try {
			duration = Duration.parse("PT0H"+partiStrings[3].trim()+"M");
		} catch (Exception e) {
			throw new BadFileFormatException("Impossibile convertire durata periodo");
		}
		
		
		if(!partiStrings[4].equals("minuti") || partiStrings[4] == null) {
			throw new BadFileFormatException("Manca parola chiave minuti");
		}
		return new Periodo(costo, duration);
	}
	
	private Periodo estraiSuccesiviPeriodo(String piece) throws BadFileFormatException {
		String[] partiStrings = piece.split("\s");
		int costo;
		Duration duration;
		
		if(!partiStrings[0].equals("poi") || partiStrings[0] == null) {
			throw new BadFileFormatException("Manca poi");
		}
		
		try {
			costo = Integer.parseInt(partiStrings[1]);
		} catch (Exception e) {
			throw new BadFileFormatException("Impossibile convertire costo primo periodo");
		}
		
		if(!partiStrings[2].equals("cent") || partiStrings[2] == null) {
			throw new BadFileFormatException("Manca cent primo periodo");
		}
		
		if(!partiStrings[3].equals("per") || partiStrings[3] == null) {
			throw new BadFileFormatException("Manca parola chiave per");
		}
		try {
			duration = Duration.parse("PT0H"+partiStrings[4].trim()+"M");
		} catch (Exception e) {
			throw new BadFileFormatException("Impossibile convertire durata periodo");
		}
		
		
		if(!partiStrings[5].equals("minuti") || partiStrings[5] == null) {
			throw new BadFileFormatException("Manca parola chiave minuti");
		}
		return new Periodo(costo, duration);
	}
	
	private Optional<Duration> estraiDurataMax(String piece) throws BadFileFormatException{
		String[] partiStrings = piece.split("\s");
		int oraMax;
		
		if(!partiStrings[0].equals("max") || partiStrings[0] == null) {
			throw new BadFileFormatException("Manca parolachiave max");
		}
		
		if(!"1234567890".contains(partiStrings[1])) {
			return Optional.empty();
		}
		try {
			oraMax = Integer.parseInt(partiStrings[1]);
		} catch (Exception e) {
			throw new BadFileFormatException("Impossibile convertire orario");
		}
		if(!partiStrings[2].equals("ore") || partiStrings[0] == null) {
			throw new BadFileFormatException("Manca parolachiave ore");
		}
		
		return Optional.of(Duration.ofHours(oraMax));
	}
	
	private Optional<LocalTime> estraiOraMax(String piece) throws BadFileFormatException{
		String[] partiStrings = piece.split("\s");
		LocalTime timeMax;
		
		if(!partiStrings[0].equals("max") || partiStrings[0] == null) {
			throw new BadFileFormatException("Manca parolachiave max");
		}
		
		if(!partiStrings[1].equals("entro") || partiStrings[1] == null) {
			throw new BadFileFormatException("Manca parolachiave entro");
		}
		
		try {
			timeMax = LocalTime.of(Integer.parseInt(partiStrings[2].split(":")[0]),Integer.parseInt(partiStrings[2].split(":")[1]));
		} catch (Exception e) {
			throw new BadFileFormatException("Orario impossibile");
		}
		
		
		return Optional.of(timeMax);
	}
	
	private double estraiSanzione(String piece) throws BadFileFormatException {
		String[] partiStrings = piece.split("\s");
		double sanzione;
		
		if(!partiStrings[0].equals("sanzione") || partiStrings[0] == null) {
			throw new BadFileFormatException("Manca parolachiave sanzione");
		}
		
		
		try {
			sanzione = Double.parseDouble(partiStrings[1]);
		} catch (Exception e) {
			throw new BadFileFormatException("Impossibile convertire sanzione");
		}
		if(!partiStrings[2].equals("euro") || partiStrings[0] == null) {
			throw new BadFileFormatException("Manca parolachiave euro");
		}
		
		return sanzione;
	}
	
	
}
