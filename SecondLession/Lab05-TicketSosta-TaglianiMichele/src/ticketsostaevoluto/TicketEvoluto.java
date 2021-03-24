package ticketsostaevoluto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import java.text.NumberFormat;


public class TicketEvoluto {
	double costo;
	LocalDateTime fine, inizio;
	
	public TicketEvoluto(LocalDateTime inizio, LocalDateTime fine, double costo ) {
		this.costo = costo;
		this.inizio = inizio;
		this.fine = fine;
	}
	
	public double getCosto() {
		return costo;
	}
	
	public LocalDateTime getFineSosta() {
		return fine;
	}
	
	public LocalDateTime getInizioSosta() {
		return inizio;
	}
	
	public String getCostoAsString() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
		
		return formatter.format(costo);
	}
	
	private String toStringDuration(Duration duration) {
		int minuti = duration.toMinutesPart();
		String sMinuti = (minuti < 10 ? "0" : "") + minuti;
		return duration.toHours() + ":" + sMinuti;
	}
	
	public String toString() {
		return "Sosta autorizzata\n dalle "
				+ inizio.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY))+
				" alle " +
					fine.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY)) + "\n "
 				+ "Durata Totate: " + toStringDuration(Duration.between(inizio, fine)) + 
 				"\n Totale Pagato " + getCostoAsString() ;
				
	}
}
