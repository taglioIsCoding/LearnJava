package cambiavalute.ui.controller;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import cambiavalute.model.CambiaValute;
import cambiavalute.model.TassoDiCambioUfficiale;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class Controller {
	
	private UserInteractor userInteractor;
	private Map<String,TassoDiCambioUfficiale> cambiUfficiali; 
	private List<CambiaValute> listaCambiaValute;

	public Controller(UserInteractor userInteractor,
			Map<String,TassoDiCambioUfficiale> cambiUfficiali, 
			List<CambiaValute> listaCambiaValute) {
		this.userInteractor = userInteractor;
		this.cambiUfficiali = cambiUfficiali;
		this.listaCambiaValute = listaCambiaValute;
	}

	public Map<String,TassoDiCambioUfficiale> getCambiUfficiali() {
		return cambiUfficiali;
	}
	
	public List<CambiaValute> getListaCambiaValute() {
		return listaCambiaValute;
	}

	public UserInteractor getUserInteractor() {
		return this.userInteractor;
	}
	
	public abstract Map<String,OptionalDouble> changeTo(String siglaValutaEstera, double importo);
	public abstract Map<String,OptionalDouble> changeFrom(String siglaValutaEstera, double importo);
	
	public List<String> getListaValuteEstere(){
		//return getCambiUfficiali().values().stream().map(c->c.getSiglaValuta()).collect(Collectors.toList());
		return getCambiUfficiali().values().stream().map(TassoDiCambioUfficiale::getSiglaValuta).sorted().collect(Collectors.toList());
	}
	
	public String getNomeCompletoValutaEstera(String siglaValuta){
		List<TassoDiCambioUfficiale> tmp = getCambiUfficiali().values().stream().filter(c->c.getSiglaValuta().equals(siglaValuta)).collect(Collectors.toList());
		return tmp.get(0).getNomeValuta();
	}
	
	public CambiaValute getCambiaValute(String nome){
		List<CambiaValute> tmp = getListaCambiaValute().stream().filter(cv -> cv.getNomeAgenzia().equalsIgnoreCase(nome)).collect(Collectors.toList());
		return tmp.get(0);
	}

	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
}
