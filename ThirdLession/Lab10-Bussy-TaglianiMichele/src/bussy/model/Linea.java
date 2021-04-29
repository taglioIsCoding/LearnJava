package bussy.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public abstract class Linea {
	private String id;
	private Map<Integer, Fermata> orariPassaggioAlleFermate;
	
	public Linea(String id, Map<Integer, Fermata> orariPassaggioAlleFermate) {
		if (id == null || id.contentEquals("") || orariPassaggioAlleFermate == null) {
            throw new IllegalArgumentException("Argomenti invalidi nel costruttore di Linea");
        }
		this.id=id;
		this.orariPassaggioAlleFermate = new HashMap<Integer, Fermata>(orariPassaggioAlleFermate);
		
	}
	
	
	public String getId() {
		return id;
	}
	public Map<Integer, Fermata> getOrariPassaggioAlleFermate() {
		return orariPassaggioAlleFermate;
	}
	
	public Entry<Integer, Fermata > getCapolineaIniziale(){
		Optional<Entry<Integer, Fermata>> entryIniziale = Optional.empty();
		
		for(Entry<Integer, Fermata> entry : this.getOrariPassaggioAlleFermate().entrySet()) {
			if(!entryIniziale.isPresent() || entry.getKey() < entryIniziale.get().getKey()) {
				entryIniziale = Optional.of(entry);
			}
		}
		
		if(entryIniziale.isPresent()) return entryIniziale.get();
		else throw new IllegalArgumentException("lista delle fermate vuta o illegale");
	}
	
	public Entry<Integer, Fermata > getCapolineaFinale(){
		Optional<Entry<Integer, Fermata>> entryIniziale = Optional.empty();
		
		for(Entry<Integer, Fermata> entry : this.getOrariPassaggioAlleFermate().entrySet()) {
			if(!entryIniziale.isPresent() || entry.getKey() > entryIniziale.get().getKey()) {
				entryIniziale = Optional.of(entry);
			}
		}
		
		if(entryIniziale.isPresent()) return entryIniziale.get();
		else throw new IllegalArgumentException("lista delle fermate vuta o illegale");
	}
	
	public int getOrarioPassaggioAllaFermata(String nome) {
		
		Optional<Entry<Integer, Fermata>> entryToFound = Optional.empty();
		
		for(Entry<Integer, Fermata> entry : this.getOrariPassaggioAlleFermate().entrySet()) {
			if(entry.getValue().getNome().equals(nome)) {
				entryToFound = Optional.of(entry);
			}
		}
		
		if(entryToFound.isPresent()) return entryToFound.get().getKey();
		else throw new IllegalArgumentException("lista delle fermate vuta o illegale");
		
		
	}
	
	public boolean isCapolineaFinale(String name) {
		if(this.getCapolineaFinale().getValue().getNome().equals(name)) {
			return true;
		}
		return false;
	}
	
	public boolean isCapolineaIniziale(String name) {
		if(this.getCapolineaIniziale().getValue().getNome().equals(name)) {
			return true;
		}
		return false;
	}
	
	public boolean isCircolare() {
		if(this.getCapolineaIniziale().getValue().getId() == this.getCapolineaFinale().getValue().getId()) {
			return true;
		}
		return false;
	}
	
	public abstract Optional<Percorso> getPercorso(String da, String a);
	
	@Override
	public String toString() {
		return "Linea [id=" + id + ", orariPassaggioAlleFermate=" + orariPassaggioAlleFermate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orariPassaggioAlleFermate == null) ? 0 : orariPassaggioAlleFermate.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Linea other = (Linea) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orariPassaggioAlleFermate == null) {
			if (other.orariPassaggioAlleFermate != null)
				return false;
		} else if (!orariPassaggioAlleFermate.equals(other.orariPassaggioAlleFermate))
			return false;
		return true;
	}
	
	
	
	
}
