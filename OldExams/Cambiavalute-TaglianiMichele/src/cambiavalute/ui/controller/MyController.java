package cambiavalute.ui.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import cambiavalute.model.CambiaValute;
import cambiavalute.model.TassoDiCambioUfficiale;

public class MyController extends Controller{
	
	public MyController(UserInteractor userInteractor,
			Map<String,TassoDiCambioUfficiale> cambiUfficiali, 
			List<CambiaValute> listaCambiaValute) {
		super(userInteractor, cambiUfficiali, listaCambiaValute);
	}

	@Override
	public Map<String, OptionalDouble> changeTo(String siglaValutaEstera, double importo) {
		Map<String, OptionalDouble> cambiMap = new HashMap<>();
		for(CambiaValute cambiaValute: getListaCambiaValute()) {
			OptionalDouble cambiOptionalDouble = cambiaValute.vendita(siglaValutaEstera, importo);
			cambiMap.put(cambiaValute.getNomeAgenzia(), cambiOptionalDouble);
		}
		return cambiMap;
	}

	@Override
	public Map<String, OptionalDouble> changeFrom(String siglaValutaEstera, double importo) {
		Map<String, OptionalDouble> cambiMap = new HashMap<>();
		for(CambiaValute cambiaValute: getListaCambiaValute()) {
			OptionalDouble cambiOptionalDouble = cambiaValute.acquisto(siglaValutaEstera, importo);
			cambiMap.put(cambiaValute.getNomeAgenzia(), cambiOptionalDouble);
		}
		return cambiMap;
	}

}
