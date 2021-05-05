package myfitnessdiary.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyFitnessDiary implements FitnessDiary{
		
	private List<Workout> workouts = new ArrayList<Workout>();
	
	public MyFitnessDiary() {
		
	}
	
	@Override
	public List<Workout> getWeekWorkouts(LocalDate date) {
		List<Workout> dayWorkouts = new ArrayList<Workout>();
		LocalDate start = date.with(DayOfWeek.MONDAY).minusDays(1);
		LocalDate end = start.plusWeeks(1);//date.with(DayOfWeek.SUNDAY);

		
		for( Workout wo: workouts) {
			if(wo.getDate().isAfter(start) && wo.getDate().isBefore(end)) {
				dayWorkouts.add(wo);
			}
		}
		return dayWorkouts;
	}

	@Override
	public List<Workout> getDayWorkouts(LocalDate date) {
		
		List<Workout> dayWorkouts = new ArrayList<Workout>();
		
		for( Workout wo: workouts) {
			if(wo.getDate().equals(date)) {
				dayWorkouts.add(wo);
			}
		}
		return dayWorkouts;
	}

	@Override
	public void addWorkout(Workout wo) {
		this.workouts.add(wo);
		
	}

	@Override
	public int getWeekWorkoutCalories(LocalDate date) {
		List<Workout> toSum = this.getWeekWorkouts(date);
		int totalCal = 0;
		
		for( Workout wo: toSum) {
			totalCal += wo.getBurnedCalories();
		}
		
		return totalCal;
	}

	@Override
	public int getDayWorkoutCalories(LocalDate date) {
		List<Workout> toSum = this.getDayWorkouts(date);
		int totalCal = 0;
		
		for( Workout wo: toSum) {
			totalCal += wo.getBurnedCalories();
		}
		
		return totalCal;
	}

	@Override
	public int getWeekWorkoutMinutes(LocalDate date) {
		List<Workout> toSum = this.getWeekWorkouts(date);
		int totalTime = 0;
		
		for( Workout wo: toSum) {
			totalTime += wo.getDuration();
		}
		
		return totalTime;
	}

	@Override
	public int getDayWorkoutMinutes(LocalDate date) {
		List<Workout> toSum = this.getDayWorkouts(date);
		int totalTime = 0;
		
		for( Workout wo: toSum) {
			totalTime += wo.getDuration();
		}
		
		return totalTime;
	}

}
