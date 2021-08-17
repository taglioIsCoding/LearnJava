package cupidonline.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class MyCupido extends Cupido{
	
	public MyCupido(Map<String, Persona> persone, Map<String, Preferenza> preferenze) {
		super(persone, preferenze);
	}

	@Override
	public SortedSet<Corrispondenza> trovaCorrispondenze(String pName, Preferenza pPref) {
		
		SortedSet<Corrispondenza> corrispondenze = new TreeSet<>(Comparator.comparing(Corrispondenza::getIndice).reversed());
		
		for (Persona persona: this.getIscritti()) {
			if(verificaSesso(persona, pPref)) {
				Corrispondenza toInsert = new Corrispondenza(pName, persona.getId(), indiceCompatibilità(persona, pPref));
				corrispondenze.add(toInsert);
			}
		}
		
		
		return corrispondenze;
	}

	@Override
	public int indiceCompatibilità(Persona p, Preferenza pref) {
		int puntiEta = pref.etaOutOfRange(p.getEta());
		//System.out.println(p.getEta()+ " "+ puntiEta);
		puntiEta = 100 - 5 * puntiEta;
		
		int puntiCitta;
		if(p.getCitta().equals(pref.getCitta())) {
			puntiCitta = 100;
		} else if(p.getProvincia().equals(pref.getProvincia())) {
			puntiCitta = 90;
		} else if (p.getRegione().equals(pref.getRegione())) {
			puntiCitta = 60;
		} else {
			puntiCitta = 40;
		}
		
		int puntiZod;
		if(pref.getSegnoZodiacale().isPresent()) {
			if(p.getSegnoZodiacale().equals(pref.getSegnoZodiacale().get())) {
				puntiZod = 100;
			}else {
				puntiZod = 90;
			}
		} else {
			puntiZod = 100;
		}
		
		int puntiPeso = 100;
		if(pref.getPeso().isPresent()) {
			puntiPeso = 100 - (Math.abs(p.getPeso() - pref.getPeso().getAsInt()));
		}
		
		int puntiAltezza = 100;
		if(pref.getAltezza().isPresent()) {
			puntiAltezza = 100 - (Math.abs(Math.round(p.getAltezza()*100) - Math.round(pref.getAltezza().get()*100)));
		}
		
		int puntiOcchi = 100;
		if(pref.getColoreOcchi().isPresent() && !pref.getColoreOcchi().get().equals(p.getColoreOcchi())) {
			puntiOcchi = 95;
		}
		
		int puntiCapelli = 100;
		if(pref.getColoreCapelli().isPresent() && !pref.getColoreCapelli().get().equals(p.getColoreCapelli())) {
			puntiOcchi = 95;
		}
		
		int minOcchiCapelli = Math.min(puntiCapelli, puntiOcchi);
		
		//System.out.println(puntiEta);
//		System.out.println(p.getAltezza()+ " "+ pref.getAltezza()+" "+ puntiAltezza);
//		System.out.println(Math.round(p.getAltezza()*100) - Math.round(pref.getAltezza().get()*100));
//		
		return Math.min(puntiEta, Math.min(puntiCitta, Math.min(puntiZod, Math.min(puntiPeso, Math.min(puntiAltezza, minOcchiCapelli)))));
	}

}
