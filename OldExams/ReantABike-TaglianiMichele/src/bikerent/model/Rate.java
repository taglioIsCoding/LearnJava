package bikerent.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;

public class Rate {
	private String citta;
	private Periodo primo, successivi;
	private Optional<Duration> durataMax;
	private Optional<LocalTime> orarioMax;
	private double sanzione;
		
	public Rate(String citta, Periodo primo, Periodo successivi, Optional<Duration> durataMax, Optional<LocalTime> orarioMax, double sanzione) {
		this.citta = citta;
		this.primo=primo;
		this.successivi=successivi;
		this.durataMax = durataMax;
		this.orarioMax = orarioMax;
		this.sanzione=sanzione;
	}

	@Override
	public String toString() {
		return "Rate [citta=" + citta + ", primo=" + primo + ", successivi=" + successivi + ", durataMax=" + durataMax
				+ ", orarioMax=" + orarioMax + ", sanzione=" + sanzione + "]";
	}

	public String getCitta() {
		return citta;
	}

	public double getCostoPrimoPeriodo() {
		return primo.getCosto();
	}

	public double getCostoPeriodiSuccessivi() {
		return successivi.getCosto();
	}

	public Duration getDurataPrimoPeriodo() {
		return primo.getDuration();
	}

	public Duration getDurataPeriodiSuccessivi() {
		return successivi.getDuration();
	}

	public Optional<Duration> getDurataMax() {
		return durataMax;
	}

	public Optional<LocalTime> getOrarioMax() {
		return orarioMax;
	}

	public double getSanzione() {
		return sanzione;
	}
	
}
