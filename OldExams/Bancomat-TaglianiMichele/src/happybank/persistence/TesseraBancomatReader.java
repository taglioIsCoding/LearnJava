package happybank.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import happybank.model.*;

public interface TesseraBancomatReader
{
	List<TesseraBancomat> read(Reader reader, Map<String, ContoCorrente> contoCorrenteMap) throws IOException, BadFileFormatException;
}
