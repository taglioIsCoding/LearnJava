package fondt2.tlc;

public class PhonePlan {
	
	private String name;
	private Rate rates[];
	
	public PhonePlan(String name,  Rate rates[]) {
		this.name = name;
		this.rates = rates;
	}

	public String getName() {
		return name;
	}
	
	private Rate getRate(PhoneCall call) {
		for(Rate r: rates) {
			if(r.isApplicableTo(call.getDestNumber())) {
				return r;
			}
		}
		return null;
	}
	
	public double getCallCost(PhoneCall call) {
		Rate toUse = this.getRate(call);
		if(toUse == null) {
			return -1;
		}else {
			return toUse.getCallCost(call);
		}
	}
	
	public boolean isValid() {
		for(Rate r: rates) {
			if(!r.isValid()) {
				return false;
			}
		}
		return true;
	}
	

}
