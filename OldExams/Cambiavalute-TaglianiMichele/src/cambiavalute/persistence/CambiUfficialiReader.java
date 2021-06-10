package cambiavalute.persistence;

import java.io.Reader;
import java.util.Map;
import cambiavalute.model.TassoDiCambioUfficiale;

public interface CambiUfficialiReader {
	public Map<String,TassoDiCambioUfficiale> leggiTabellaCambiUfficiali(Reader reader) throws BadFileFormatException;
}
