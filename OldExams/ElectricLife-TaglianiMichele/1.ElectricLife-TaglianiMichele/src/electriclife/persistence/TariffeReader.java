package electriclife.persistence;

import java.io.IOException;
import java.util.Collection;

import electriclife.model.Tariffa;

public interface TariffeReader
{
	Collection<Tariffa> caricaTariffe() throws IOException, BadFileFormatException;	
}
