package cupidonline.tests.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

import cupidonline.model.Colore;
import cupidonline.model.Preferenza;
import cupidonline.model.SegnoZodiacale;
import cupidonline.model.Sesso;
import cupidonline.persistence.BadFileFormatException;
import cupidonline.persistence.PreferenzeReader;

public class PreferenzeReaderMock implements PreferenzeReader {

	@Override
	public Map<String, Preferenza> caricaPreferenze(Reader reader) throws IOException, BadFileFormatException {		
		return Map.of(
		  "Roberto", new Preferenza(Sesso.FEMMINA, 20, 25, Optional.of(SegnoZodiacale.BILANCIA), Optional.of(Colore.BIONDI), Optional.empty(), Optional.of(1.70F), OptionalInt.of(58), "Bologna", "BO", "Emilia-Romagna")
		 );
	}

}
