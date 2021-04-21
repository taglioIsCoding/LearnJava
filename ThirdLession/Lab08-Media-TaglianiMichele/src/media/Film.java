package media;

import java.util.Arrays;

import media.filters.HasDuration;
import media.filters.HasGenre;
import utils.StringUtils;

public class Film extends Media implements HasGenre, HasDuration{
	
	private String actors[];
	private String director;
	private int duration=-1;
	private String genre = null;
	
	public Film(String title, int year, String director, int duration, String actors[], String genre) {
		super(year, title);
		this.director = director;
		this.duration = duration;
		this.genre = genre;
		this.actors = actors;
	}

	public String[] getActors() {
		return actors;
	}

	public void setActors(String[] actors) {
		this.actors = actors;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public Type getType() {
		return Type.FILM;
	}
	
	public boolean equals(Object obj) {
		if( (obj instanceof Film)) {
			if(super.equals(obj) && this.getDuration() == ((Film) obj).getDuration() && this.getDirector().equals(((Film) obj).getDirector()) && this.getGenre().equals(((Film) obj).getGenre()) && StringUtils.areEquivalent(actors, ((Film) obj).getActors())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Film director=" + director + ", duration=" + duration + ", genre=" + genre + "]";
	}
	
	

}
