package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Appointment {
	
	private String description;
	private LocalDateTime from;
	private LocalDateTime to;
	
	public Appointment(String description, LocalDateTime from, LocalDateTime to ) {
		this.description = description;
		this.from = from;
		this.to = to;
	}
	
	public Appointment(String description, LocalDateTime from, Duration duration ) {
		this.description = description;
		this.from = from;
		this.setDuration(duration);
	}
	
	public Duration getDuration(){
		return  Duration.between(this.getFrom(), this.getTo());
	}
	public void setDuration(Duration duration) {
		this.setTo(this.getFrom().plus(duration));
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDateTime getFrom() {
		return from;
	}
	public void setFrom(LocalDateTime from) {
		this.from = from;
	}
	
	public LocalDateTime getTo() {
		return to;
	}
	public void setTo(LocalDateTime to) {
		this.to = to;
	}
	
	public boolean equals(Appointment app) {
		if(this.getDescription().equals(app.getDescription()) && this.getFrom().equals(app.getFrom()) && this.getTo().equals(app.getTo())) {
			return true;
		}else {
			return false;
		}
	}
	
	public String toString() {
		return this.getDescription() + " form: "+ this.getFrom().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.ITALY))
				+", to: "+ this.getTo().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.ITALY));
	}

}
