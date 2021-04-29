package bussy.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Map.Entry;

public class MyCercatore implements Cercatore{
	
	private Map<String,Linea> mappaLinee;
	
	public MyCercatore(Map<String,Linea> mappaLinee) {
		this.mappaLinee = new HashMap<String,Linea>(mappaLinee);
	}
	
	@Override
	public SortedSet<Percorso> cercaPercorsi(String fermataDa, String fermataA, OptionalInt durataMax) {
		try {
		SortedSet<Percorso> percorsi = new TreeSet<>();
		Optional<Percorso> p = Optional.empty(); 
				
		for(Entry<String, Linea> entry : this.getMappaLinee().entrySet()) {
			p = entry.getValue().getPercorso(fermataDa, fermataA);
			if(p.isPresent() && p.get().getDurata() < durataMax.getAsInt()) {
				percorsi.add(p.get());
			}
		}
		
		return percorsi;
		
		}catch(IllegalArgumentException e) {
			SortedSet<Percorso> percorsi = new TreeSet<>();
			return percorsi;
		}
	}

	@Override
	public Map<String, Linea> getMappaLinee() {
		return this.mappaLinee;
	}

}
