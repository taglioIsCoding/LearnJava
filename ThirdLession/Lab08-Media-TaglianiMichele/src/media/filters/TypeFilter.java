package media.filters;

import media.Media;
import media.Type;

public class TypeFilter implements Filter{
	
	private Type type;
	
	public TypeFilter(Type type) {
		this.type = type;
	}

	@Override
	public boolean filter(Media media) {
		if(media instanceof HasType m) {
			return (this.type.equals(m.getType()));
		}
		return false;
	}
	
	
}
