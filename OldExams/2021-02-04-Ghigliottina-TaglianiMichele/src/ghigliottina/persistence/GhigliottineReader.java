package ghigliottina.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import ghigliottina.model.Ghigliottina;

public interface GhigliottineReader {
	public List<Ghigliottina> getGhigliottine();
	public List<Ghigliottina> readAll(BufferedReader reader) throws IOException, BadFileFormatException ;
}
