package minirail.model;

public enum Gauge {
	H0(87), TT(120), N(160), Z(220);
	
	private int ratio;

	private Gauge(int ratio) {
		this.ratio=ratio;
	}
	
	public int getRatio() {
		return ratio;
	}
	
	public double kmhToCms(double speed) {
		return speed*(1000.0/36)/ratio;
	}
	
}
