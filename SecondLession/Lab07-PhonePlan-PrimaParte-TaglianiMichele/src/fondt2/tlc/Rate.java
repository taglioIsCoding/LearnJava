package fondt2.tlc;

public class Rate {
	
	private Band bands[];
	private int intervalInMillis;
	private String name;
	private String numberRoot;
	private double startCallCost;
	
	public Rate(String name, Band bands[], int intervalInMillis, double startCallCost, String numberRoot ) {
		this.name = name;
		this.intervalInMillis = intervalInMillis;
		this.startCallCost = startCallCost;
		this.numberRoot = numberRoot;
		this.bands = bands;
	}

	public Band[] getBands() {
		return bands;
	}

	public String getName() {
		return name;
	}
	
	public boolean isApplicableTo(String destinationNumber) {
		if(destinationNumber.startsWith(this.numberRoot)) {
			return true;
		}
		return false;
	}
}
