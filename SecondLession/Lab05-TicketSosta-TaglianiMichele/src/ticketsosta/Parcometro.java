package ticketsosta;

import java.time.Duration;
import java.time.LocalTime;

public class Parcometro {
	
	Tariffa tariffa;
	
	public Parcometro(Tariffa tariffa) {
		this.tariffa = tariffa;
	}
	
	public Ticket emettiTicket(LocalTime inizio, LocalTime fine) {
		double costo = calcolaCosto(this.tariffa.getTariffaOraria(), inizio, fine);
		Ticket tk = new Ticket(inizio, fine, costo);
		return tk;
	}
	
	private double calcolaCosto(double costoOrario, LocalTime da, LocalTime a) {
		Duration durataSosta = Duration.between(da, a);
		
		
		if (a.isBefore(da) || LocalTime.of(0, 0).equals(a)){
			
			return calcolaCosto(costoOrario, da, LocalTime.of(23, 59)) + calcolaCosto(costoOrario, LocalTime.of(0, 0), a);	
			
		}else if( durataSosta.toMinutes() < this.tariffa.getDurataMinima() ) {
			return costoOrario;
		}else {
			return (costoOrario / 60) * (durataSosta.toMinutes() - this.tariffa.getMinutiFranchigia());
		}
	}
	
	public String toString() {
		return "Parchimetro formattato con tariffa:" + tariffa.toString();	
	}
}
