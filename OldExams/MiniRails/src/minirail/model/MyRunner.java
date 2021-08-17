package minirail.model;

import java.util.HashMap;
import java.util.Map;

public class MyRunner implements Runner{

	private Gauge gauge;
	private Map<String, LineStatus> lines;
	private StringBuilder logger = new StringBuilder();
	
	public MyRunner(Map<String, LineStatus> lines, Gauge gauge) {
		this.gauge = gauge;
		this.lines = new HashMap<>(lines);
	}
	
	
	@Override
	public void clock(double period) {
		for (String name : lines.keySet()) {
			LineStatus status = lines.get(name);
			for(Train train: status.getAllTrains()) {
				moveTrain(status, train, period);
			}
		}
	}
	
	private void moveTrain(LineStatus status, Train train, double period) {
		double location = status.getTrainLocation(train);
		double space = gauge.kmhToCms(train.getSpeed() * period);
		double possibleLocatio = (location + space) % status.getLine().getLength();
		
		boolean check = status.moveTrain(train, possibleLocatio);
		
		logger.append("Tryng to move "+ train.getName()+ " at "+ train.getSpeed()+ " km/h " +
						"form "+ location + " to "+ possibleLocatio+ " "+ check + "\n");
		
	}

	@Override
	public Map<String, LineStatus> getLines() {
		return lines;
	}

	@Override
	public Gauge getGauge() {
		return gauge;
	}
	
	@Override
	public String toString() {
		return logger.toString();
	}

}
