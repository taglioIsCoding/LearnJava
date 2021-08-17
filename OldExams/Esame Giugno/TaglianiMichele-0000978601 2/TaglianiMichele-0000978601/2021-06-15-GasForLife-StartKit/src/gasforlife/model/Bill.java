package gasforlife.model;

import java.text.Normalizer.Form;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;



public class Bill {
	
	private BillingFrequency billingFrequency;
	private double consumtion;
	private double costom3;
	private double extraCostom3;
	private double fixedCost;
	private Optional<Month> month;
	private List<Share> quoteShares = new ArrayList<>();
	private double value;
	private double varaibleCost;
	
	
	public Bill(BillingFrequency billingFrequency, double value,  double fixedCost,
			double varaibleCost, double consumtion, double costom3, double extraCostom3,
			  Optional<Month> month) {
		
		
		if(consumtion <=0) {
			throw new IllegalArgumentException("Consunumi non validi");
		}else if(costom3 <= 0) {
			throw new IllegalArgumentException("costo m3 non validi");
		}else if(extraCostom3 <= 0) {
			throw new IllegalArgumentException("costo EXTRA m3 non validi");
		}else if(fixedCost <= 0) {
			throw new IllegalArgumentException("costo fisso non validi");
		}else if(month == null) {
			throw new IllegalArgumentException("mese nullo");
		}else if(value <= 0) {
			throw new IllegalArgumentException("valore non validi");
		}else if(varaibleCost <= 0) {
			throw new IllegalArgumentException("costo variabile non validi");
		}else if (billingFrequency == null){
			throw new IllegalArgumentException("billingFrequency non validi");
		}
		
		this.consumtion = consumtion;
		this.costom3 = costom3;
		this.extraCostom3 = extraCostom3;
		this.fixedCost = fixedCost;
		
		if(month.isPresent()) {
			this.month = month;
		}else {
			this.month = Optional.empty();
		}
		this.billingFrequency = billingFrequency;
		this.value = value;
		this.varaibleCost = varaibleCost;
	}


	public BillingFrequency getBillingFrequency() {
		return billingFrequency;
	}


	public double getConsumption() {
		return consumtion;
	}


	public double getCostm3() {
		return costom3;
	}


	public double getExtraCostm3() {
		return extraCostom3;
	}


	public double getFixedCost() {
		return fixedCost;
	}


	public Optional<Month> getMonth() {
		return month;
	}


	public List<Share> getShares() {
		return quoteShares;
	}


	public double getValue() {
		return value;
	}


	public double getVariableCost() {
		return varaibleCost;
	}
	
	public boolean addShare(Share e) {
		return this.quoteShares.add(e);
	}
	
	public String getMonthAsString() {
		if(this.month.isPresent()) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM").localizedBy(Locale.ITALY);
			return dateTimeFormatter.format(this.getMonth().get());
		}else {
			return "mese non presente";
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("billingFrequency=" + billingFrequency +System.lineSeparator());
		sBuilder.append("value= " + value  +System.lineSeparator());
		sBuilder.append("consumtion= " + consumtion +System.lineSeparator());
		sBuilder.append("costom3= " + costom3 +System.lineSeparator());
		sBuilder.append("extraCostom3=" + extraCostom3 +System.lineSeparator());
		sBuilder.append("fixedCost=" + fixedCost + System.lineSeparator());
		sBuilder.append("month= " + this.getMonthAsString() +System.lineSeparator());
		sBuilder.append("varaibleCost=" + varaibleCost +System.lineSeparator());
		
		Comparator<Share>  compare= (Share r1, Share r2) -> {
			return r1.getFlat().getId().compareTo(r2.getFlat().getId());
		};
		
		Collections.sort(this.quoteShares, compare);
		
		for(Share share : this.quoteShares) {
			//sBuilder.append("Flat= "+ share.getFlat().getId()+ " Total=" + share.getValue() +System.lineSeparator());
			sBuilder.append(share.toString() + System.lineSeparator());
		}
		
		return sBuilder.toString();
	}
	
	

}
