package electriclife.tests.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import electriclife.model.Tariffa;
import electriclife.model.TariffaAConsumo;
import electriclife.model.TariffaFlat;
import electriclife.persistence.BadFileFormatException;
import electriclife.persistence.TariffeReader;

public class TariffaReaderMock implements TariffeReader {

	@Override
	public Collection<Tariffa> caricaTariffe() throws IOException, BadFileFormatException {
		List<Tariffa> lista = new ArrayList<>();
		lista.add(new TariffaFlat("CASA MINI", 150, 20, 0.25));
		lista.add(new TariffaFlat("CASA CLASSIC", 250, 30, 0.24));
		lista.add(new TariffaAConsumo("MONORARIA", 0.14));
		return lista;
	}

}
