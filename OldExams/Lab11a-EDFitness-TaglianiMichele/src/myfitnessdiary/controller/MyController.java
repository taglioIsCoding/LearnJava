package myfitnessdiary.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

import myfitnessdiary.model.FitnessDiary;
import myfitnessdiary.model.Workout;
import myfitnessdiary.persistence.ActivityRepository;
import myfitnessdiary.persistence.ReportWriter;

public class MyController extends Controller{
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.ITALY);
	
	public MyController(FitnessDiary diary, ActivityRepository activityRepository, ReportWriter reportWriter){
		super(diary, activityRepository, reportWriter);
	}
	
	@Override
	public String getDayWorkout(LocalDate data) {
		String toRet;
		
		toRet = "Allenamento di "+ formatter.format(data) + "\n";
		System.out.println(toRet);
		
		List<Workout> wo = this.getFitnessDiary().getDayWorkouts(data);
		
		for( Workout w: wo) {
			toRet = toRet  + w.getActivity().getName() + " minuti: "+ w.getDuration()+ " calorie bruciate: " + w.getBurnedCalories() + "\n";
		}
		
		toRet = toRet + "\n"; 
		toRet = toRet + "Minuti Totali di allenamento:" + this.getFitnessDiary().getDayWorkoutMinutes(data) + 
				"\nCalorie bruciate: "+ this.getFitnessDiary().getDayWorkoutCalories(data);
		
		
		return toRet;
	}

}
