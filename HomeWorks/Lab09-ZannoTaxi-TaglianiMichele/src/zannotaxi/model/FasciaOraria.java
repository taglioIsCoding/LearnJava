package zannotaxi.model;

import java.time.LocalTime;

public class FasciaOraria {
	private double costoScattoIniziale;
	private LocalTime inizio,fine;
	
	public FasciaOraria(LocalTime inizio,LocalTime fine, double costoScattoIniziale) {
		this.costoScattoIniziale = costoScattoIniziale;
		this.fine = fine;
		this.inizio = inizio;
	}
	
	public double getCostoScattoIniziale() {
		return costoScattoIniziale;
	}

	public boolean contiene(LocalTime toCheck) {
		if(toCheck.isAfter(inizio) && toCheck.isBefore(fine)) {
			return true;
		}
		return false;
	}
	
}
