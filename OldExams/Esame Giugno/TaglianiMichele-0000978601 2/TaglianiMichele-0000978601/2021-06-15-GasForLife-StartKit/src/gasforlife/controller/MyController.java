package gasforlife.controller;

import java.text.Normalizer.Form;
import java.util.List;
import java.util.Map;

import gasforlife.model.Bill;
import gasforlife.model.Flat;
import gasforlife.model.Share;
import gasforlife.model.BillingFrequency;
import gasforlife.persistence.ConsumptionReader;
import gasforlife.persistence.FlatReader;

public class MyController implements Controller {
	private Map<String, List<Double>> gasConsumption;
	private Map<String, Flat> flats;

	public MyController(FlatReader flatReader, ConsumptionReader conReader) {
		this.gasConsumption = conReader.getItems();
		this.flats = flatReader.getItems();
	}

	@Override
	public void computeShare(Bill bill) {
		switch(bill.getBillingFrequency()) {
			case ANNUAL:  computeAnnualCost(bill); break;
			case MONTHLY: computeMonthlyCost(bill); break;
		}
	}

	private void computeAnnualCost(Bill bill) {
		double totalPrice = 0;
		for (String flat : flats.keySet()) {
			double price = 0;
			double totalCons = 0;
			for (int month = 0; month < 12; month++) {
				double realCons = gasConsumption.get(flat).get(month);
				price += this.getMonthlyCostForFlat(flats.get(flat), bill, realCons);
				totalCons += realCons;

			}
			totalPrice += price;
			price += bill.getFixedCost() / flats.size();
			bill.addShare(new Share(flats.get(flat), totalCons, price));
		}
		updateShare(bill, totalPrice);
	}

	private void updateShare(Bill bill, double totalPrice) {
		double update = (bill.getVariableCost() - totalPrice) / flats.size();
		for (Share q : bill.getShares()) {
			q.addCorrection(update);
		}

	}

	// ------------ PRIMO METODO DA REALIZZARE------------------
	private void computeMonthlyCost(Bill bill) {
		int totalPrice = 0;
		for (String flat : flats.keySet()) {
				double price = 0;
				double realCons = gasConsumption.get(flat).get(bill.getMonth().get().getValue()-1);
				price += this.getMonthlyCostForFlat(flats.get(flat), bill, realCons);
				totalPrice += price;
				price += bill.getFixedCost() / flats.size();
				
				bill.addShare(new Share(flats.get(flat), realCons, price));
			}
		updateShare(bill, totalPrice);
	}
	
	private double getMonthlyCostForFlat(Flat flat, Bill bill, double realCons) {
		double totale = 0;
		double cosumeMax = flat.getMaxConsumption();
		double quantitaInPiu = 0;
		System.out.println(bill.getShares());
		if(realCons > cosumeMax) {
			quantitaInPiu = realCons - cosumeMax;
			totale += quantitaInPiu * bill.getExtraCostm3();
		}
		
//		for(Share share: bill.getShares()) {
//			if(share.getFlat().equals(flat)) {
//				totale += (realCons - quantitaInPiu) * bill.getValue();
//			}
//		}
		
		totale += (realCons-quantitaInPiu) * bill.getCostm3();
		System.out.println(totale);
		return totale;
		
	}
	// ------------ FINE METODO DA REALIZZARE-------------------

	@Override
	public double getMonthlyTotalConsumption(int index) {
		double total = 0;
		for (String flat : flats.keySet()) {
			total += gasConsumption.get(flat).get(index);
		}
		return total;
	}

	@Override
	public double getAnnualTotalConsumption() {
		double total = 0;

		for (int i = 0; i < 12; i++) {
			total += getMonthlyTotalConsumption(i);
		}

		return total;
	}

	// ------------ SECONDO METODO DA REALIZZARE------------------
	@Override
	public double getDiffCons(Bill bill) {
		if(bill.getBillingFrequency().equals(BillingFrequency.ANNUAL)) {
			return bill.getConsumption() - getAnnualTotalConsumption();
		}else {
			return bill.getConsumption() - getMonthlyTotalConsumption(bill.getMonth().get().getValue());
		}
	}
	// ------------ FINE METODO DA REALIZZARE-------------------

}
