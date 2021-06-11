package electriclife.model;

import java.time.LocalDate;

public class TariffaAConsumo extends Tariffa{

	private double prezzoKw;
	
	public TariffaAConsumo(String nome, double prezzoKw ) {
		super(nome);
		this.prezzoKw = prezzoKw;
	}

	@Override
	public Bolletta creaBolletta(LocalDate date, int consumo) {
		Bolletta toSendBolletta = new Bolletta(date, this, consumo);
		double accise =  this.calcAccise(consumo);
		double costoEnergia =  this.prezzoKw * consumo;
		double iva =  this.calcIVA(costoEnergia + accise);
		toSendBolletta.addLineaBolletta("Accise", accise);
		toSendBolletta.addLineaBolletta("Iva", iva);
		toSendBolletta.addLineaBolletta("Costo Energia",costoEnergia);
		toSendBolletta.addLineaBolletta("Totale", costoEnergia + iva + accise);
		
		return toSendBolletta;
	}

	@Override
	public String getDettagli() {
		return "Tariffa A CONSUMO, Costo KWh €"+ this.prezzoKw;
	}

	public double getPrezzoKWh() {
		return prezzoKw;
	}

	
}
