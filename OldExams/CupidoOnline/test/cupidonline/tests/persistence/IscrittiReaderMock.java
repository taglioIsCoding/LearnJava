package cupidonline.tests.persistence;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.Map;

import cupidonline.model.Colore;
import cupidonline.model.Persona;
import cupidonline.model.SegnoZodiacale;
import cupidonline.model.Sesso;
import cupidonline.persistence.BadFileFormatException;
import cupidonline.persistence.IscrittiReader;

public class IscrittiReaderMock implements IscrittiReader {

	@Override
	public Map<String, Persona> caricaIscritti(Reader reader) throws IOException, BadFileFormatException {		
		return Map.of(
		  "Roberto", new Persona("Roberto", Sesso.MASCHIO, LocalDate.parse("1994-04-24"), SegnoZodiacale.TORO, Colore.NERI, Colore.CASTANI, 1.78F, 61, "Bologna", "BO", "Emilia-Romagna"),
		  "Armando", new Persona("Armando", Sesso.MASCHIO, LocalDate.parse("1997-08-01"), SegnoZodiacale.LEONE, Colore.CASTANI, Colore.CASTANI, 1.71F, 65, "Parma", "PR", "Emilia-Romagna"),
		  "Ludovica", new Persona("Ludovica",Sesso.FEMMINA, LocalDate.parse("1992-10-14"), SegnoZodiacale.BILANCIA, Colore.NERI, Colore.CASTANI, 1.75F, 51, "Bologna", "BO", "Emilia-Romagna"),
		 "Elena", new Persona("Elena", Sesso.FEMMINA, LocalDate.parse("1999-04-10"), SegnoZodiacale.ARIETE, Colore.NERI, Colore.NERI, 1.65F, 57, "Modena", "MO", "Emilia-Romagna")
		 );
	}

}
