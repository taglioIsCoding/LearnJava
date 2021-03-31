package control;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import model.Appointment;
import model.AppointmentCollection;

public class MyCalendar {
	private AppointmentCollection allAppointments;
	
	public MyCalendar() {
		this.allAppointments = new AppointmentCollection();
	}
	

	public AppointmentCollection getAllAppointments() {
		AppointmentCollection toReturn = new AppointmentCollection();
		for (int i =0; i < this.allAppointments.size(); i++) {
			toReturn.add(this.allAppointments.get(i));
		}
		return  toReturn;
	}
	
	public void add(Appointment app) {
		this.allAppointments.add(app);
	}
	
	public boolean remove(Appointment app) {
		int i = allAppointments.indexOf(app);
		if(i >= 0) {
			this.allAppointments.remove(i);
			return true;
		}
		return false;	
	}
	
	private AppointmentCollection getAppointsmentsIn(LocalDateTime start, LocalDateTime end) {
		AppointmentCollection appCollIn = new AppointmentCollection();
		
		for(int i = 0; i < this.allAppointments.size(); i++) {
			if(this.isOverLapped(start, end, this.getAllAppointments().get(i).getFrom(), this.getAllAppointments().get(i).getTo())) {
				appCollIn.add(this.allAppointments.get(i));
			}
		}
		
		return appCollIn;
	}
	
	private boolean isOverLapped(LocalDateTime start, LocalDateTime end,  LocalDateTime refStart,  LocalDateTime refEnd) {
		if(start.isBefore(refStart) && end.isAfter(refEnd)) {
			return true;
		} else if(refEnd.isAfter(start) && refEnd.isBefore(end)) {
			return true;
		}else if(refStart.isAfter(start) && refStart.isBefore(end)) {
			return true;
		}else if(refStart.isBefore(start) && refEnd.isAfter(end)) {
			return true;
		}
		
		return false;
	}
	
	public AppointmentCollection getDayAppointments(LocalDate date) {
		return this.getAppointsmentsIn(date.atStartOfDay(), date.atTime(23, 59));
	}
	
	public AppointmentCollection getMonthAppointments(LocalDate date) {
		LocalDateTime start =  LocalDateTime.of(date.getYear(), Month.of(date.getMonthValue()), 1 , 0, 0,0 );
		
		return this.getAppointsmentsIn( start, start.plusMonths(1));
	}
	
	public AppointmentCollection getWeekAppointments(LocalDate date) {
		LocalDateTime start =  LocalDateTime.of(date.getYear(), Month.of(date.getMonthValue()), date.getDayOfMonth() , 0, 0,0 );
		start = start.with(DayOfWeek.MONDAY);
		
		return this.getAppointsmentsIn( start, start.plusWeeks(1));
	}
}
