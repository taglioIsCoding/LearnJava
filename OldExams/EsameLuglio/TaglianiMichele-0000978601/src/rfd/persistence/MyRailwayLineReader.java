package rfd.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import rfd.model.MyPointOfInterest;
import rfd.model.PointOfInterest;
import rfd.model.RailwayLine;

public class MyRailwayLineReader implements RailwayLineReader{

	@Override
	public RailwayLine getRailwayLine(Reader rdr) throws IOException {
		if(rdr == null) {
			throw new IllegalArgumentException("Reader null");
		}
		
		Map<String, PointOfInterest> puntiMap = new HashMap<>();
		SortedSet<String> hubs = new TreeSet<String>();
		
		BufferedReader bReader = new BufferedReader(rdr);
		String line;
		while((line = bReader.readLine())!= null) {
			StringTokenizer stk = new StringTokenizer(line, "\t");
			
			if(stk.countTokens() != 2) {
				throw new IllegalArgumentException("Non abbastanza token");
			}
			
			String kmEMetriString = stk.nextToken().trim();
			String stazione = stk.nextToken().trim();
		
			
			
			StringTokenizer kmMetri = new StringTokenizer(kmEMetriString, "+");
			String kmString = kmMetri.nextToken().trim();
			String mString = kmMetri.nextToken().trim();
			double kmD = 0;
			double mD= 0;
			try {
				kmD =  Double.parseDouble(kmString);
				if(kmString.length() < 1) {
					throw new IllegalArgumentException("KM non validi");
				}
			} catch (Exception e) {
				throw new IllegalArgumentException("KM non validi");
			}
			
			try {
				mD = Double.parseDouble(mString)/1000;
				if(mString.length() != 3) {
					throw new IllegalArgumentException("M non validi");
				}
			} catch (Exception e) {
				throw new IllegalArgumentException("M non validi");
			}
			
			if(stazione == null || Character.isDigit(stazione.charAt(0))) {
				throw new IllegalArgumentException("Nome stazione non valido");
			}
			
			if(stazione.charAt(0) == '+' && stazione.charAt(stazione.length()-1) == '+') {
				throw new IllegalArgumentException("Nome mancante");
			}
			
			if(stazione.charAt(stazione.length()-1) == '+') {
				hubs.add(stazione.substring(0, stazione.length()-1));
				puntiMap.put(stazione.substring(0, stazione.length()-1), new MyPointOfInterest(stazione.substring(0, stazione.length()-1), kmEMetriString));
			}else {
				puntiMap.put(stazione, new MyPointOfInterest(stazione, kmEMetriString));

			}
			
		}
		RailwayLine railwayLine = new RailwayLine(puntiMap, hubs);
		return railwayLine;
	}

}
