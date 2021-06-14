package bikerent.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import bikerent.model.Rate;

public interface RateReader {
	Map<String, Rate> readRates(Reader reader) throws IOException, BadFileFormatException;
}
