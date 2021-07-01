package rfd.model;

import java.time.Duration;

public interface TripDurationCalculator {
	Duration getDuration(Route route);
}
