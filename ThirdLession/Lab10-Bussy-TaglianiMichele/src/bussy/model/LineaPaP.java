package bussy.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

public class LineaPaP extends Linea{
	
	public LineaPaP(String id, Map<Integer, Fermata> orariPassaggioAlleFermate) {
			super(id, orariPassaggioAlleFermate);
			if(id.isEmpty() || !Optional.ofNullable(id).isPresent()) {
				System.out.println("Ciao");
				throw new  IllegalArgumentException("id vuoto o nullo");
			}
			if( orariPassaggioAlleFermate == null) throw new  IllegalArgumentException("orariVuoti vuoto o nullo");
	}
	
	
	@Override
	public Optional<Percorso> getPercorso(String da, String a) {
		try {
			int oraA = this.getOrarioPassaggioAllaFermata(a);
			int oraDA = this.getOrarioPassaggioAllaFermata(da);
			Optional<Percorso> toSearch = Optional.empty();
			
			if(oraA > oraDA) {
				
				toSearch = Optional.of(new Percorso(da, a, this, oraA-oraDA));
			
			}
		
			return toSearch;
		}catch(IllegalArgumentException e) {
			Optional<Percorso> toSearch = Optional.empty();
			return toSearch;
		}
		
		
		
	}
}
