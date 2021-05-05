package myfitnessdiary.model;

import java.time.LocalDate;

public class Workout {
	private Activity activity;
	private LocalDate date;
	private int duration;
	private Intensity intensity;
	
	public Workout(LocalDate date, int duration,  Intensity intensity, Activity activity) {
		if( date == null || duration <= 0 || intensity == null || activity == null)
			throw new IllegalArgumentException();
		this.activity = activity;
		this.date = date;
		this.duration = duration;
		this.intensity = intensity;
	}

	public Activity getActivity() {
		return activity;
	}

	public LocalDate getDate() {
		return date;
	}

	public int getDuration() {
		return duration;
	}

	public Intensity getIntensity() {
		return intensity;
	}
	
	public int getBurnedCalories(){
		
		if(this.activity.getCalories(getIntensity()) < 0 || this.duration <= 0)
			throw new IllegalArgumentException();
		int total = this.activity.getCalories(getIntensity()) * this.duration;
		
		
		return total;
	}
	
	
	
}
