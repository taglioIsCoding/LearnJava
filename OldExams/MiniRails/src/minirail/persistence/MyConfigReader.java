package minirail.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import minirail.model.Gauge;
import minirail.model.Train;

public class MyConfigReader implements ConfigReader{
	
	private Gauge gauge;
	private String StringGauge;
	private List<Train> trains;

	public MyConfigReader(Reader reader) throws IOException, BadFileFormatException {
		BufferedReader rdr = new BufferedReader(reader);
		this.trains = new ArrayList<>();
		String firstLine = rdr.readLine();
		String line;
		
		try {
			String scaleString = firstLine.split(" ")[0].trim();
			String gaugeKwr = firstLine.split(" ")[1].trim();
			if(!gaugeKwr.equalsIgnoreCase("gauge")) {
				throw new BadFileFormatException("gauge errato kwrd");
			}
			this.gauge = Gauge.valueOf(scaleString);
			this.StringGauge = gaugeKwr;
		} catch (Exception e) {
			throw new BadFileFormatException("gauge errato");
		}
		
		
		while((line = rdr.readLine())!= null) {
			StringTokenizer stk = new StringTokenizer(line, ",");
			if(stk.countTokens() != 3) {
				throw new BadFileFormatException("manca un campo");
			}
			
			String idTrain = stk.nextToken().trim();
			if(idTrain.contains(" ")) {
				throw new BadFileFormatException("id errato");
			}
			
			String unitaDiMisura = stk.nextToken().trim();
			double lunghezza;
			if(!unitaDiMisura.split(" ")[1].equalsIgnoreCase("cm") && !unitaDiMisura.split(" ")[1].equalsIgnoreCase("in")) {
				throw new BadFileFormatException("unita errato");
			}
			try {
				lunghezza = Double.parseDouble(unitaDiMisura.split(" ")[0]);
			} catch (Exception e) {
				throw new BadFileFormatException("lunghezza errato");
			}
			
			String velocita = stk.nextToken().trim();
			double speed;
			if(!velocita.split(" ")[1].equalsIgnoreCase("km/h") && !velocita.split(" ")[1].equalsIgnoreCase("mph")) {
				throw new BadFileFormatException("unita errato");
			}
			try {
				speed = Double.parseDouble(velocita.split(" ")[0]);
			} catch (Exception e) {
				throw new BadFileFormatException("speed errato");
			}
			
			trains.add(new Train(idTrain, lunghezza, speed));
		
		}
		
	}
	
	@Override
	public List<Train> getTrains() {
		return trains;
	}

	@Override
	public Gauge getGauge() {
		return gauge;
	}

}
