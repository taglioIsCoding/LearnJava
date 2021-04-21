package media.filters;

import media.Media;

public class GenreFilter implements Filter{
	
	private String genre;
	
	public GenreFilter(String genre) {
		this.genre = genre;
	}
	
	
	
	public void setGenre(String genre) {
		this.genre = genre;
	}



	@Override
	public boolean filter(Media media) {
		if(media instanceof HasGenre m) {
			return (this.genre.equals(" ") ||this.genre.equals(m.getGenre()));
		}
		return false;
	}

}
