package bikerent.model;

import java.time.Duration;

public class Periodo {
	private double costo;
	private Duration duration;
	
	public double getCosto() {
		return costo;
	}

	public Duration getDuration() {
		return duration;
	}

	public Periodo(int costo, Duration duration) {
		super();
		this.costo = costo;
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Periodo [costo=" + costo + ", duration=" + duration + "]";
	}

}
