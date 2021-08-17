package crosswords.model;

import java.util.Optional;

public interface Game {

	
	public void setMapping(int num, String value);
	
	public Optional<String> getMapping(int num);
	
	public boolean isFull();
}
