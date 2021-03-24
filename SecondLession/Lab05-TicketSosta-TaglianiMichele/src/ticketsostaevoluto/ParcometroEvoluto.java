package ticketsostaevoluto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.StringJoiner;
import ticketsosta.Tariffa;

public class ParcometroEvoluto {
	
	Tariffa tariffe[];
	
	public ParcometroEvoluto(Tariffa tariffe[]) {
		this.tariffe = tariffe;
	}
	
	public TicketEvoluto emettiTicket(LocalDateTime inizio, LocalDateTime fine) {
		double costo = 0;
		Duration durataSosta = Duration.between(inizio, fine);
		int days = (int) durataSosta.toDaysPart();
		
		if(days == 0) {
			costo = calcolaCosto(this.tariffe[inizio.getDayOfWeek().ordinal()].getTariffaOraria(), inizio.toLocalTime(), fine.toLocalTime(), inizio.getDayOfWeek().ordinal())  - (this.tariffe[inizio.getDayOfWeek().ordinal()].getTariffaOraria() / 60) * this.tariffe[inizio.getDayOfWeek().ordinal()].getMinutiFranchigia();;
				
		}else{
			costo = calcolaCosto(this.tariffe[inizio.getDayOfWeek().ordinal()].getTariffaOraria(), inizio.toLocalTime() , LocalTime.of(23, 59, 59), inizio.getDayOfWeek().ordinal()) - (this.tariffe[inizio.getDayOfWeek().ordinal()].getTariffaOraria() / 60) * this.tariffe[inizio.getDayOfWeek().ordinal()].getMinutiFranchigia();
			for(int i = 1; i < days ; i++) {
				costo += calcolaCosto(this.tariffe[(inizio.getDayOfWeek().ordinal() + i) % 7].getTariffaOraria(), LocalTime.of(0, 0), LocalTime.of(23,59, 59), (inizio.getDayOfWeek().ordinal() + i) % 7);
			}
			costo += calcolaCosto(this.tariffe[(inizio.getDayOfWeek().ordinal() + (int)days) % 7].getTariffaOraria(), LocalTime.of(0, 0), fine.toLocalTime(), (inizio.getDayOfWeek().ordinal() + (int)days) % 7);
		}
		
		TicketEvoluto tk = new TicketEvoluto(inizio, fine, costo);
		return tk;
	}
	
	private double calcolaCosto(double costoOrario, LocalTime da, LocalTime a, int dayOfWeek) {
		Duration durataSosta = Duration.between(da, a);
		
		
		if (a.isBefore(da) || LocalTime.of(0, 0).equals(a)){
			return calcolaCosto(costoOrario, da, LocalTime.of(23, 59), dayOfWeek) + calcolaCosto(costoOrario, LocalTime.of(0, 0), a, dayOfWeek+1);	
		}else if( durataSosta.toMinutes() < this.tariffe[dayOfWeek].getDurataMinima() ) {
			return costoOrario + (this.tariffe[dayOfWeek].getTariffaOraria() / 60) * this.tariffe[dayOfWeek].getMinutiFranchigia();
		}else {
			return (costoOrario / 60) * (durataSosta.toMinutes()) ;
		}
	}
	
	public String toString() {
		StringJoiner joiner = new StringJoiner("\n", "[", "]" );
		for(Tariffa t: this.tariffe)
			joiner.add(t.toString());
		return "Parchimetro formattato con tariffe:" + joiner.toString();	
	}
}







