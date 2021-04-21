package media;

import media.filters.HasDuration;
import media.filters.HasGenre;
import utils.StringUtils;

public class Song extends Media implements HasGenre, HasDuration{
	
	private int duration=-1;
	private String genre = null;
	private String singer = null;
	
	public Song(String title, int year, String singer, int duration, String genre){
		super(year, title);
		this.duration = duration;
		this.genre = genre;
		this.singer = singer;
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
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public Type getType() {
		return Type.SONG;
	}
	public boolean equals(Object obj) {
		if( (obj instanceof Song)) {
			if(super.equals(obj) && this.getDuration() == ((Song) obj).getDuration() && this.getSinger().equals(((Song) obj).getSinger()) && this.getGenre() == ((Song) obj).getGenre()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Song [duration=" + duration + ", genre=" + genre + ", singer=" + singer + "]";
	}
	
}
