package gasforlife.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MyConsumptionReader implements ConsumptionReader{
	
	private static double CONVERSIONE = 10.69;
	private Map<String, List<Double>> gasConsumption = new HashMap<>();

	@Override
	public Map<String, List<Double>> getItems() {
		return this.gasConsumption;
	}
	
	public MyConsumptionReader(Reader reader) throws IOException, BadFileFormatException {
		this.loadAllItems(reader);
	}
	
	private void loadAllItems(Reader reader) throws IOException, gasforlife.persistence.BadFileFormatException {
		if(reader == null) {
			throw new IOException();
		}
		
		BufferedReader bReader = new BufferedReader(reader);
		
		String line;
		while((line = bReader.readLine())!= null) {
			if (line.isEmpty()) {
				continue;
			}
			StringTokenizer stk = new StringTokenizer(line, ":|");
			List<Double> mensilitaDoubles = new ArrayList<>();
			String idFlat = stk.nextToken().trim();
			if (idFlat == null || idFlat.isEmpty()) {
				throw new BadFileFormatException("idFlat nulla o vuota");
			}
		
			if(stk.countTokens() != 12) {
				throw new BadFileFormatException("non abbanstanza letture");
			}
			
			for(int i = 0; i < 12; i++) {
				String mese = stk.nextToken().trim();
				if (mese == null) {
					throw new BadFileFormatException("messe nullo o vuota");
				}
				//System.out.println(mese);
				double valoreMese;
				double meseInM3;
				try {
					valoreMese = Double.parseDouble(mese);
					meseInM3 = valoreMese/ CONVERSIONE;
				} catch (Exception e) {
					throw new BadFileFormatException("impossibile convertire mese");
				}
				//System.out.println(i+")"+ meseInM3);
				mensilitaDoubles.add(meseInM3);
				
			}
			

			this.gasConsumption.put(idFlat, mensilitaDoubles);
			
		}
		
	}
}
