package fondt2.tlc;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import fondt2.tlc.util.*;

public class Band {
	
	private DayOfWeek combinedDays[];
	private double costPerInterval;
	private LocalTime endTime;
	private LocalTime startTime;
	
	public Band(LocalTime startTime, LocalTime endTime,  DayOfWeek combinedDays[], double costPerInterval ) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.costPerInterval = costPerInterval;
		this.combinedDays = combinedDays;
	}

	public DayOfWeek[] getCombinedDays() {
		return combinedDays;
	}

	public double getCostPerInterval() {
		return costPerInterval;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public LocalTime getStartTime() {
		return startTime;
	}
	
	public boolean isInBand(LocalDateTime localTime) {
		if(DayOfWeekHelper.isDayIn(localTime.getDayOfWeek(), this.combinedDays) && startTime.isBefore(localTime.toLocalTime()) && endTime.isAfter(localTime.toLocalTime())) {
			return true;
		}
		return false;
	}
	
	public boolean isValid() {
		if(this.startTime.isBefore(this.endTime) && this.combinedDays.length != 0 && this.combinedDays != null && this.costPerInterval > 0) {
			for(DayOfWeek d: this.combinedDays) {
				if(d == null)
					return false;
			}
			return true;
		}
		return false;
	}

}
