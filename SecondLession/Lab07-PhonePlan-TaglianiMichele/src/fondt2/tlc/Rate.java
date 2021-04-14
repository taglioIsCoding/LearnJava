package fondt2.tlc;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

import fondt2.tlc.util.DayOfWeekHelper;

public class Rate {
	
	private Band bands[];
	private int intervalInMillis;
	private String name;
	private String numberRoot;
	private double startCallCost;
	
	public Rate(String name, Band bands[], int intervalInMillis, double startCallCost, String numberRoot ) {
		this.name = name;
		this.intervalInMillis = intervalInMillis;
		this.startCallCost = startCallCost;
		this.numberRoot = numberRoot;
		this.bands = bands;
	}

	public Band[] getBands() {
		return bands;
	}

	public String getName() {
		return name;
	}
	
	public boolean isApplicableTo(String destinationNumber) {
		if(destinationNumber.startsWith(this.numberRoot)) {
			return true;
		}
		return false;
	}
	
	public double getCallCost(PhoneCall call) {
		Duration duration = Duration.between(call.getStart(), call.getEnd());
		
		int intervall = (int)Math.ceil((double)duration.toMillis()/this.intervalInMillis);
		
		for(Band band: this.bands) {
			if(band.isInBand( call.getStart())) {
				
				return intervall*band.getCostPerInterval() + this.startCallCost;
			}
		}
		return -1;
	}
	
	public boolean isValid() {
		for(Band band: this.bands) {
			if(!band.isValid()) {
				return false;
			}
		}
		for(int i=0; i < 6; i++) {
			if(!validateDay(DayOfWeek.of(i+1))){
				return false;
			}
		}
		return true;
	}
	
	private boolean validateDay(DayOfWeek day) {
		Band bands[] = selectBandsInDay(day);
		sortBandsByStartTime(bands);
		if( !validateBandsInDay(bands)) {
			return false;
		}
		return true;
	}
	
	private Band[] selectBandsInDay(DayOfWeek day) {
		int i = 0;
		int j = 0;
		for(Band band: this.bands) {
			if(DayOfWeekHelper.isDayIn(day,band.getCombinedDays())) {
				i++;
			}
		}
		Band dayBand[] = new Band[i];  
		for(Band band: this.bands) {
			if(DayOfWeekHelper.isDayIn(day,band.getCombinedDays())) {
				dayBand[j] = band;
				j++;
			}
		}
		
		return dayBand;
	}
	
	private void sortBandsByStartTime(Band bands[]) {
		int n = bands.length;  
        Band temp;  
         for(int i=0; i < n; i++){  
                 for(int j=1; j < (n-i); j++){  
                          if(bands[j-1].getStartTime().isAfter(bands[j].getStartTime())){  
                                 //swap elements  
                                 temp = bands[j-1];  
                                 bands[j-1] = bands[j];  
                                 bands[j] = temp;  
                         }  
                          
                 }  
         }  
	}
	
	private boolean validateBandsInDay(Band bands[]) {
		if(bands[0].getStartTime() != LocalTime.MIN || bands[bands.length-1].getEndTime() != LocalTime.MAX ) {
			return false;
		}
		
		for(int i=1; i < bands.length - 2; i++){  
            if(bands[i].getStartTime() != bands[i-1].getEndTime().plusNanos(1)) {
            	return false;
            }
        }  
		
		return true;
	}
}












