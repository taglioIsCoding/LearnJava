package oroscopo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import oroscopo.model.Oroscopo;
import oroscopo.model.OroscopoMensile;
import oroscopo.model.Previsione;
import oroscopo.model.SegnoZodiacale;
import oroscopo.persistence.OroscopoRepository;

public class MyController extends AbstractController{
	
	private StrategiaSelezione strategiaSelezione;
	
	public MyController(OroscopoRepository myReader, StrategiaSelezione strategiaSelezione) throws IOException {
		super(myReader);
		this.strategiaSelezione = strategiaSelezione;
	}

	@Override
	public SegnoZodiacale[] getSegni() {
		return SegnoZodiacale.values();
	}

	@Override
	public Oroscopo[] generaOroscopoAnnuale(SegnoZodiacale segno, int fortunaMin) {
		List<Oroscopo> oroscopi = new ArrayList<>();
		
		while(oroscopi.size() != 12) {
			Oroscopo mensile = generaOroscopoCasuale(segno);
			if(mensile.getFortuna() > fortunaMin) {
				oroscopi.add(mensile);
			}
		}
		
		return  oroscopi.toArray(new Oroscopo[0]);
	}

	@Override
	public Oroscopo generaOroscopoCasuale(SegnoZodiacale segno) {
		
		List<Previsione> amore = this.getRepository().getPrevisioni("Amore");
		List<Previsione> lavoro = this.getRepository().getPrevisioni("Lavoro");
		List<Previsione> salute = this.getRepository().getPrevisioni("Salute");
		Previsione love = this.strategiaSelezione.seleziona(amore, segno);
		Previsione work = this.strategiaSelezione.seleziona(lavoro, segno);
		Previsione health = this.strategiaSelezione.seleziona(salute, segno);
		Oroscopo toGive = new OroscopoMensile(segno, love, work, health);		
				
		return toGive;
	}

}
