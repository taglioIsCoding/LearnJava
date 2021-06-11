package electriclife.model;

import java.time.LocalDate;


public class TariffaFlat extends Tariffa{
	
	private double prezzoKWExtra;
	private double quotaFissaMensile;
	private int SogliaMensile;

	public TariffaFlat(String nome,double quotaFissaMensile ,int SogliaMensile, double prezzoKWExtra) {
		super(nome);
		this.prezzoKWExtra = prezzoKWExtra;
		this.SogliaMensile = SogliaMensile;
		this.quotaFissaMensile = quotaFissaMensile;
	}
	
	

	public double getPrezzoKWhExtra() {
		return prezzoKWExtra;
	}



	public double getQuotaFissaMensile() {
		return quotaFissaMensile;
	}



	public int getSogliaMensile() {
		return SogliaMensile;
	}
	
	@Override
	public Bolletta creaBolletta(LocalDate date, int consumo) {
		Bolletta toSendBolletta = new Bolletta(date, this, consumo);
		double accise =  this.calcAccise(consumo);
		double costoExtra = (consumo - this.SogliaMensile) > 0 ? (consumo - this.SogliaMensile)*this.prezzoKWExtra : 0;
		double iva =  this.calcIVA(this.quotaFissaMensile + costoExtra + accise);
		toSendBolletta.addLineaBolletta("Accise", accise);
		toSendBolletta.addLineaBolletta("Iva", iva);
		toSendBolletta.addLineaBolletta("Quota Fissa",this.quotaFissaMensile);
		toSendBolletta.addLineaBolletta("Costo energia extra", costoExtra);
		toSendBolletta.addLineaBolletta("Totale", this.quotaFissaMensile + costoExtra + iva + accise);
		return toSendBolletta;
	}

	@Override
	public String getDettagli() {
		return "Tariffa "+ this.getNome()+ 
				", €" + this.quotaFissaMensile+"/mese per"+
				this.getSogliaMensile()+" KWh, poi €"+
				this.getPrezzoKWhExtra()+"/KWh";
	}

}
