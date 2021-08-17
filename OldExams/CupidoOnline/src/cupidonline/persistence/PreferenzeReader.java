package cupidonline.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import cupidonline.model.Preferenza;

public interface PreferenzeReader {
	public Map<String,Preferenza> caricaPreferenze(Reader reader) throws IOException, BadFileFormatException;
}
