package media;

import java.util.Arrays;

import media.filters.HasGenre;
import utils.StringUtils;

public class Ebook extends Media implements HasGenre{
	
	private String authors[] = null;
	private String genre = null;
	
	public Ebook(String title, int year, String authors[], String genre ) {
		super(year, title);
		this.authors = authors;
		this.genre = genre;
	}
	
	public String[] getAuthors() {
		return authors;
	}
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Type getType() {
		return Type.EBOOK;
	}

	@Override
	public String toString() {
		return "Ebook [authors=" + Arrays.toString(authors) + ", genre=" + genre + "]";
	}
	public boolean equals(Object obj) {
		if( (obj instanceof Ebook)) {
			if(super.equals(obj) && StringUtils.areEquivalent(authors, ((Ebook) obj).getAuthors()) && this.getGenre() == ((Ebook) obj).getGenre()) {
				return true;
			}
		}
		return false;
	}
	
}
