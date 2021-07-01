package unident.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Esame {
	private AttivitaFormativa af;
	private Voto voto;
	private LocalDate date;
	private DateTimeFormatter dateFormatter;
	
	public Esame(AttivitaFormativa af, Voto voto, LocalDate date) {
		if(af==null || voto==null || date==null) throw new IllegalArgumentException("wrong args in ctor: " + af +", "+ voto + ", "+ date);
		this.af = af;
		this.voto = voto;
		this.date = date;
		this.dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
	}
	
	public AttivitaFormativa getAf() {
		return af;
	}
	
	public Voto getVoto() {
		return voto;
	}
	
	public LocalDate getDate() {
		return date;
	}

	@Override
	public String toString() {
		return String.format("%-60s  CFU: %2s  Data:%10s   Voto: %-4s", af.getNome(), af.getCfu(), dateFormatter.format(date), voto);
	}
		
}
