package unident.model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;


public class Carriera {
	
	private SortedMap<AttivitaFormativa, List<Esame>> esami;
	private NumberFormat numberFormat;
	
	public Carriera(Set<AttivitaFormativa> attivita) {
		this.esami = new TreeMap<AttivitaFormativa, List<Esame>>(Comparator.comparing(AttivitaFormativa:: getNome));
		for(AttivitaFormativa af: attivita) {
			this.esami.put(af, new ArrayList<>());
		}
		this.numberFormat = NumberFormat.getNumberInstance(Locale.ITALY);
		this.numberFormat.setMaximumFractionDigits(2);
	}
	
	public List<Esame>istanzeDi(AttivitaFormativa af) {
		return this.esami.get(af);
	}
	
	public void registra(Esame esame) {
		List<Esame> esamiEsames = istanzeDi(esame.getAf());
		if(esamiEsames == null) {
			throw new IllegalArgumentException("Af non presente");
		}
		if(esamiEsames.size() == 0) {
			istanzeDi(esame.getAf()).add(esame);
			return;
		}
		
		Esame lastaEsame = esamiEsames.get(esamiEsames.size()-1);
		
		if(esame.getDate().isBefore(lastaEsame.getDate()) || esame.getDate().equals(lastaEsame.getDate())) {
			throw new IllegalArgumentException("Esame nel passato?");
		}
		if(lastaEsame.getVoto().getValue().isPresent()) {
			throw new IllegalArgumentException("Esame gia dato");
		}
		
		istanzeDi(esame.getAf()).add(esame);
		
	}
	
	public double mediaPesata() {
		double sommaPesi = 0;
		int totEsami = 0;
		for(AttivitaFormativa af: this.esami.keySet()) {
			if(istanzeDi(af).size() > 0) {
				Esame lastEsame = istanzeDi(af).get(istanzeDi(af).size()-1);
				if(lastEsame!=null && lastEsame.getVoto().getValue().isPresent()) {
					sommaPesi += lastEsame.getVoto().getValue().getAsInt() * af.getCfu();
					totEsami += af.getCfu();
				}
			}
		}
		
		return sommaPesi/totEsami;
	}
	
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Esami sostenuti:"+ System.lineSeparator());
		
		for(AttivitaFormativa af: this.esami.keySet()) {
			if(istanzeDi(af).size() > 0) {
				for(Esame esame : istanzeDi(af)) {
					if(esame!=null) {
					sBuilder.append(esame);
					sBuilder.append(System.lineSeparator());
				}
				}
				//Esame lastEsame = istanzeDi(af).get(istanzeDi(af).size()-1);
				
			}
		}
		
		sBuilder.append("Media pesata: "+numberFormat.format(mediaPesata())+"/30");
		System.out.println(sBuilder);
		return sBuilder.toString();
	}
	
	
}
