package bussy.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LineaCircolare extends Linea{
	
	public LineaCircolare(String id, Map<Integer, Fermata> orariPassaggioAlleFermate) {
		super(id, orariPassaggioAlleFermate);
		if(!this.isCircolare()) throw new IllegalArgumentException("Argomenti invalidi nel costruttore di Linea");
	}
	
	private boolean isCapolinea(String id) {
		if(this.isCapolineaFinale(id) && this.isCapolineaIniziale(id)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public Optional<Percorso> getPercorso(String da, String a) {
		try {
			int oraA = this.getOrarioPassaggioAllaFermata(a);
			int oraDA = this.getOrarioPassaggioAllaFermata(da);
			int capoIn = this.getOrarioPassaggioAllaFermata(this.getCapolineaIniziale().getValue().getNome());
			int capoFin = this.getOrarioPassaggioAllaFermata(this.getCapolineaFinale().getValue().getNome());
			
//			Fermata A = this.getOrariPassaggioAlleFermate().get(oraA);
//			Fermata DA = this.getOrariPassaggioAlleFermate().get(oraDA);
			Optional<Percorso> toSearch = Optional.empty();
			
			if(oraA > oraDA) {
				toSearch = Optional.of(new Percorso(da, a, this, oraA-oraDA));
			}if(oraDA == oraA) {
				toSearch = Optional.of(new Percorso(da, a, this, capoFin));
			}if(oraDA > oraA) {
				toSearch = Optional.of(new Percorso(da, a, this, capoFin-oraDA+oraA));
			}
		
			return toSearch;
		}catch(IllegalArgumentException e) {
			Optional<Percorso> toSearch = Optional.empty();
			return toSearch;
		}
	}

}
