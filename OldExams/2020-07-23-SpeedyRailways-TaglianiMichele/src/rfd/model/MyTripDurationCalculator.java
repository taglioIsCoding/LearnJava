package rfd.model;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

public class MyTripDurationCalculator implements TripDurationCalculator {

	@Override
	public Duration getDuration(Route route) {
		List<Segment> segments= route.getRouteSegments();
		System.out.println(segments);
		double tempoTot = 0;
		for (Segment s: segments) {
			s = s.normalize();
			List<Segment> splitted = s.split();
			for (Segment spitt: splitted) {
				tempoTot +=  getSegmentDuration(spitt);
			}
		}
		return Duration.ofMinutes((long) (tempoTot * 60));
	}
	
	private double getSegmentDuration(Segment segment) {
		double tempo = segment.getLength()/ segment.getSpeed();
		
		return tempo;
	}

}
