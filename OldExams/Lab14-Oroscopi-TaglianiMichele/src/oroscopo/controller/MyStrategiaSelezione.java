package oroscopo.controller;

import java.util.List;
import java.util.Random;

import oroscopo.model.Previsione;
import oroscopo.model.SegnoZodiacale;

public class MyStrategiaSelezione implements StrategiaSelezione{
	
	Random r = new Random();

	@Override
	public Previsione seleziona(List<Previsione> previsioni, SegnoZodiacale segno) {
		Previsione scelta;
		do {
			scelta = previsioni.get(r.nextInt(previsioni.size()));
		}while(scelta == null || !scelta.validaPerSegno(segno));
		
		return scelta;
	}

}
