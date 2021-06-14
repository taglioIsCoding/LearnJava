package happybank.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import happybank.model.ContoCorrente;

public interface ContoCorrenteReader
{
	List<ContoCorrente> read(Reader reader) throws IOException, BadFileFormatException;
}
