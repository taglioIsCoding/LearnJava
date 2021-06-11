package electriclife.ui.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import electriclife.model.Bolletta;
import electriclife.model.Tariffa;
import electriclife.persistence.BadFileFormatException;
import electriclife.persistence.BollettaWriter;
import electriclife.persistence.MyTariffeReader;
import electriclife.persistence.TariffeReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MyController implements Controller{
	
	private Map<String, Tariffa> mappTariffe = new HashMap<>();

	@Override
	public void leggiTariffe(TariffeReader tariffaReader) throws IOException, BadFileFormatException {
		this.mappTariffe = tariffaReader.caricaTariffe().stream()
			      .collect(Collectors.toMap(Tariffa::getNome, Function.identity()));
	}

	@Override
	public ObservableList<Tariffa> getTariffe() {
		return FXCollections.observableArrayList(this.mappTariffe.values()).sorted();
	}

	@Override
	public Bolletta creaBolletta(LocalDate date, String nomeTariffa, int consumo) {
		Tariffa toReTariffa = this.mappTariffe.get(nomeTariffa);
		return toReTariffa.creaBolletta(date, consumo);
	}

	@Override
	public void stampaBolletta(Bolletta b, BollettaWriter bollettaWriter) {
		bollettaWriter.stampaBolletta(b);
	}

}
