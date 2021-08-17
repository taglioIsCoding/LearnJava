package minirail.model;

public enum TrainStatus {
	STOPPED, MOVING;
	
	public TrainStatus getOther() {
		switch(this) {
			case STOPPED: return TrainStatus.MOVING;
			case MOVING: return TrainStatus.STOPPED;
		}
		return null; // unreachable code
	}
}
