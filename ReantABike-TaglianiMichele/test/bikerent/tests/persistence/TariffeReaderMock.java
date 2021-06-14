package bikerent.tests.persistence;

import java.io.IOException;
import java.io.Reader;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;

import bikerent.model.Periodo;
import bikerent.model.Rate;
import bikerent.persistence.BadFileFormatException;
import bikerent.persistence.RateReader;

public class TariffeReaderMock implements RateReader {

	private Map<String, Rate> map;
	
	public TariffeReaderMock() {
		map = Map.of(
				//Bologna, 59 cent per 20 minuti, poi 99 cent per 30 minuti, max 12 ore, sanzione 7.50 euro
				"Topolinia", new Rate("Topolinia", 
										new Periodo(59, Duration.ofMinutes(20)), 
										new Periodo(99, Duration.ofMinutes(30)),
										Optional.of(Duration.ofHours(12)), Optional.empty(), 7.50)
				);
	}
	
	@Override
	public Map<String, Rate> readRates(Reader reader) throws IOException, BadFileFormatException {		
		return map;
	}

}
