package bikerent.model;

import java.time.Duration;

public class MyCalculator implements Calculator{

	@Override
	public double calc(Rate rate, Rent rent) {
		Duration duretionRent = Duration.between(rent.getStart(), rent.getEnd());
		double costoTot = 0;
		
		if(duretionRent.compareTo(rate.getDurataPrimoPeriodo()) <= 0) {
			return rate.getCostoPrimoPeriodo()/100;
		}
		
		duretionRent = duretionRent.minus(rate.getDurataPrimoPeriodo());
		costoTot += rate.getCostoPrimoPeriodo();
		
		while(duretionRent.compareTo(rate.getDurataPeriodiSuccessivi()) > 0) {
			costoTot += rate.getCostoPeriodiSuccessivi();
			duretionRent = duretionRent.minus(rate.getDurataPeriodiSuccessivi());
		}
		
		costoTot += rate.getCostoPeriodiSuccessivi();
		
		
		if(!rent.isRegular()) {
			costoTot = costoTot + rate.getSanzione()*100;
		}
		return costoTot/100;
	}

}
