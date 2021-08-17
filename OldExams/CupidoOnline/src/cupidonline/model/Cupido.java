package cupidonline.model;

import java.util.Collection;
import java.util.Map;
import java.util.SortedSet;

public abstract class Cupido {
	private Map<String,Persona> iscritti;
	private Map<String,Preferenza> preferenze;

	protected Cupido(Map<String, Persona> iscritti, Map<String, Preferenza> preferenze) {
		super();
		this.iscritti = iscritti;
		this.preferenze = preferenze;
	}
	
	protected Collection<Persona> getIscritti() {
		return iscritti.values();
	}
	
	protected Persona getIscritto(String nick) {
		return iscritti.get(nick);
	}
	
	protected Preferenza getPreferenza(String nick) {
		return preferenze.get(nick);
	}
	
	public SortedSet<Corrispondenza> trovaCorrispondenze(String pName){
		return trovaCorrispondenze(pName, getPreferenza(pName));
	}
	
	public abstract SortedSet<Corrispondenza> trovaCorrispondenze(String pName, Preferenza pPref);

	public boolean verificaSesso(Persona q, Preferenza pref) {
		return pref!=null && q.getSesso()==pref.getSesso();  
	}

	public Corrispondenza calcolaCorrispondenza(Persona q, Preferenza pPref, String pName) {
		return new Corrispondenza(pName,q.getId(), indiceCompatibilità(q,pPref));  
	}
	
	public abstract int indiceCompatibilità(Persona q, Preferenza pref);
	
}
