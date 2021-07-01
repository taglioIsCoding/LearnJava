package unident.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.Set;

import unident.model.AttivitaFormativa;

public interface PianoDidatticoReader {
	public Set<AttivitaFormativa> readAll(Reader rdr) throws IOException;
}