package cupidonline.model;

import java.time.LocalDate;

public enum SegnoZodiacale {
	
	ARIETE(SegnoZodiacale.Elementi.FUOCO, 21,3, 20,4), 
	TORO(Elementi.TERRA, 	21,4, 20,5), 
	GEMELLI(Elementi.ARIA, 	21,5, 21,6), 
	CANCRO(Elementi.ACQUA, 	22,6, 22,7), 
	LEONE(Elementi.FUOCO, 	23,7, 22,8), 
	VERGINE(Elementi.TERRA,	23,8, 22,9), 
	BILANCIA(Elementi.ARIA,	23,9, 22,10), 
	SCORPIONE(Elementi.ACQUA,  23,10, 22,11), 
	SAGITTARIO(Elementi.FUOCO, 23,11, 21,12), 
	CAPRICORNO(Elementi.TERRA, 22,12, 20,1), 
	ACQUARIO(Elementi.ARIA,	21,1, 19,2),
	PESCI(Elementi.ACQUA,	20,2, 20,3);
	
	private Elementi elem;
	private LocalDate da,a;
	private SegnoZodiacale(Elementi elem, int startDateDay, int startDateMonth, int endDateDay, int endDateMonth) {
		this.elem=elem;
		int startYear = LocalDate.now().getYear();
		this.da = LocalDate.of(startYear, startDateMonth, startDateDay);
		int endYear = endDateMonth<startDateMonth ? startYear+1 : startYear;
		this.a =  LocalDate.of(endYear,  endDateMonth, endDateDay);
	}
	
	public Elementi getElemento() {
		return elem;
	}

	public LocalDate getDataIniziale() {
		return da;
	}

	public LocalDate getDataFinale() {
		return a;
	}

	public boolean contains(LocalDate date) {
		LocalDate dataIniziale = this.getDataIniziale().withYear(date.getYear());
		LocalDate dataFinale   = this.getDataFinale().withYear(date.getYear());
		// Capricorno è a cavallo del 31 dicembre, va trattato a parte
		if (this!=CAPRICORNO) {
			return  (dataIniziale.isBefore(date) || dataIniziale.isEqual(date)) 
					&& (dataFinale.isAfter(date) || dataFinale.isEqual(date));
		} else {
			LocalDate fineAnno = LocalDate.of(date.getYear(), 12, 31);
			LocalDate capodanno = LocalDate.of(date.getYear(), 1, 1);
			//System.out.println("data " + date); 
			//System.out.println("iniziale " + dataIniziale +", finale " + dataFinale +", fine anno " + fineAnno + ", capodanno " + capodanno);
			return  (dataIniziale.isBefore(date) || dataIniziale.isEqual(date)) && (fineAnno.isAfter(date) || fineAnno.isEqual(date))
					||
					(capodanno.isBefore(date) || capodanno.isEqual(date)) && (dataFinale.isAfter(date) || dataFinale.isEqual(date));
		}
	}

	public String toString() {
		return super.toString() + " (" + this.getDataIniziale() + ", " + this.getDataFinale() + ")";
	}
	
	public enum Elementi{
		TERRA,FUOCO,ARIA,ACQUA;
	}
	
}
