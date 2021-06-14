package bikerent.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class MyRent implements Rent{

	private LocalDateTime inizio;
	private LocalDateTime fine;
	private Rate rate;
	
	public MyRent(LocalDateTime inizio, LocalDateTime fine, Rate rate) {
		this.inizio = inizio;
		this.fine = fine;
		this.rate = rate;
	}
	
	@Override
	public boolean isRegular() {
		if(this.rate.getOrarioMax().isPresent() && this.fine.toLocalTime().isAfter(this.rate.getOrarioMax().get())){
			return false;
		}
		Duration duration = Duration.between(inizio, fine);
		if(this.rate.getDurataMax().isPresent() && this.rate.getDurataMax().get().compareTo(duration) <= 0){
			return false;
		}if(this.inizio.getDayOfMonth() != this.fine.getDayOfMonth()) {
			return false;
		}
		return true;
		
	}

	@Override
	public LocalDateTime getStart() {
		return inizio;
	}

	@Override
	public LocalDateTime getEnd() {
		return fine;
	}
	
	
	@Override
	public String toString() {
		return "MyRent [inizio=" + inizio + ", fine=" + fine + "durata="+toStringDuration()+"]";
	}

	private String toStringDuration() {
		Duration duration = Duration.between(inizio, fine);
		return duration.toString();
	}

}
