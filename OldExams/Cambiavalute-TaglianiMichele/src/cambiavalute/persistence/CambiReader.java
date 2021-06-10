package cambiavalute.persistence;

import java.io.Reader;
import java.util.Map;

import cambiavalute.model.TassiDiCambio;

public interface CambiReader {
	public Map<String,TassiDiCambio> leggiTabellaCambiApplicati(Reader reader) throws BadFileFormatException;
}
