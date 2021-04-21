package media;

import java.util.Arrays;

import utils.StringUtils;

public class Photo extends Media{
	
	private String authors[] = null;
	
	public Photo(String title, int year, String authors[]) {
		super(year, title);
		this.authors = authors;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}
	
	public Type getType() {
		return Type.PHOTO;
	}

	@Override
	public String toString() {
		return "Photo [authors=" + Arrays.toString(authors) + "]";
	}
	
	public boolean equals(Object obj) {
		if( (obj instanceof Photo)) {
			if(super.equals(obj) && StringUtils.areEquivalent(authors, ((Photo) obj).getAuthors())) {
				return true;
			}
		}
		return false;
	}
}
