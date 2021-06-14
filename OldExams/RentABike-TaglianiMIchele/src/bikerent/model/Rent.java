package bikerent.model;

import java.time.LocalDateTime;

public interface Rent {
	public boolean isRegular();
	public LocalDateTime getStart();
	public LocalDateTime getEnd() ;
}
