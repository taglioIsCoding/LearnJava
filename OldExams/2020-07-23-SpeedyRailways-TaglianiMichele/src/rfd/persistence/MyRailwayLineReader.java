package rfd.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import rfd.model.RailwayLine;
import rfd.model.Station;

public class MyRailwayLineReader implements RailwayLineReader{

	@Override
	public RailwayLine getRailwayLine(Reader rdr) throws IOException {
		if(rdr == null) {
			throw new IllegalArgumentException("reader nullo");
		}
		Map<String, Station> stationsMap = new HashMap<>();
		SortedSet<String>  hubs= new TreeSet<>();
		BufferedReader bReader = new BufferedReader(rdr);
		String line;
		while((line = bReader.readLine())!= null) {
			if (line.isEmpty()) {
				continue;
			}
			NumberFormat nFormat = NumberFormat.getNumberInstance(Locale.ITALY);
			int intSpeed;
			double kilometriInt;
			String kilometriString = line.substring(0, 8).trim();
			String speed = line.substring(line.lastIndexOf(" ") , line.length()).trim();
			String nomeStazioneString = line.substring(9, line.lastIndexOf(" ")).trim();
			
			if(kilometriString.contains(".")) {
				throw new IllegalArgumentException("kilometri invalidi");
			}
			try {
				kilometriInt = nFormat.parse(kilometriString).doubleValue();
			} catch (Exception e) {
				throw new IllegalArgumentException("kilometri invalidi");
			}
			
			try {
				intSpeed = nFormat.parse(speed).intValue();
			} catch (Exception e) {
				throw new IllegalArgumentException("velocita invalida");
			}
			
			if(nomeStazioneString.toUpperCase().contains("HUB")) {
				if(nomeStazioneString.toUpperCase().indexOf("HUB")-1 <= 0) {
					throw new IllegalArgumentException("Trovata solo parola hub");
				}
				nomeStazioneString = nomeStazioneString.substring(0, nomeStazioneString.toUpperCase().indexOf("HUB")-1).trim();
				
				hubs.add(nomeStazioneString);
				stationsMap.put(nomeStazioneString, new Station(nomeStazioneString, kilometriInt, intSpeed));
			}else {
				stationsMap.put(nomeStazioneString, new Station(nomeStazioneString, kilometriInt, intSpeed));
			}
			
			
			
		}
		return new RailwayLine(stationsMap, hubs);
	}

}
