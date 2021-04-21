package media;

import media.filters.HasType;

public abstract class Media implements HasType{
	
	private String title = null;
	private int year = -1;
	
	public Media(int year, String title) {
		this.title = title;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public abstract Type getType();
	
	public boolean equals(Object obj) {
		if((obj instanceof Media)) {
			if(this.getTitle().equals(((Media) obj).getTitle()) && this.getYear() == ((Media) obj).getYear()) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return "Media type "+this.getType()+" with Title: "+ this.getYear()+" of year"+ this.getYear();
	}
}
