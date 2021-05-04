package zannotaxi.model;

import java.time.LocalTime;
import java.util.Optional;

public class Tassametro implements ITassametro{
		
	private FasciaOraria fasceOrarie[];
	private ITariffaTaxi tariffe[];
	
	public Tassametro( ITariffaTaxi tariffe[],FasciaOraria fasceOrarie[]) {
		this.fasceOrarie = fasceOrarie;
		this.tariffe = tariffe;
		
	}
	
	@Override
	public double calcolaCostoCorsa(CorsaTaxi corsaToCalc) {
		System.out.println("-------------------");
		int t=0;
		double s = 0, cv=0;
		double costoIn = Double.NaN;
		double rilevazioni[]= corsaToCalc.getRilevazioniDistanze();
		
		for(int i =1; i < rilevazioni.length; i++) {
			 s += rilevazioni[i] - rilevazioni[i-1];
			 t++;
			 Optional<Scatto> scatt = findScatto(cv, s, t);
			 if(scatt.isPresent()) {
				 s -= scatt.get().getSpazio();
				 t -= scatt.get().getTempo();
				 cv += scatt.get().getCosto();
			 }
		}
		
		
		System.out.println(cv);
		
		for(FasciaOraria f: fasceOrarie) {
			if(f.contiene(getScattoIniziale(corsaToCalc))) {
				//LocalTime oraPartenza = corsaToCalc.getOraPartenza();
				costoIn = f.getCostoScattoIniziale();
				System.out.println(costoIn);
			}
		}
		cv = cv + costoIn;
		return cv;
	}
	
	private Optional<Scatto> findScatto(double cv, double s, int t ) {
		
		for(ITariffaTaxi tariffa : tariffe) {
			Optional<Scatto> scattCorr = tariffa.getScattoCorrente(t, s, cv);
			if(scattCorr.isPresent()) {
				return scattCorr;
			}
		}
		
		return Optional.empty();
	}
	
	private LocalTime getScattoIniziale(CorsaTaxi corsaToCalc) {
		return corsaToCalc.getOraPartenza();
	}

}
