package cupidonline.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import cupidonline.model.Persona;

public interface IscrittiReader {
	Map<String, Persona> caricaIscritti(Reader reader) throws IOException, BadFileFormatException;
}
