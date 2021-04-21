package media.filters;

import media.Media;

public class DurationFilter implements Filter{

	private int duration = -1;
	
	public DurationFilter(int duration) {
		this.duration = duration;
	}
	
	
	public void setDuration(int duration) {
		this.duration = duration;
	}


	@Override
	public boolean filter(Media media) {
		if(media instanceof HasDuration m) {
			return (this.duration == 0 || (m.getDuration() <= this.duration));
		}
		return false;
	}

}
