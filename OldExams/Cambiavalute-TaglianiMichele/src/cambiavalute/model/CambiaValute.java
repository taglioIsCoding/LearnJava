package cambiavalute.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;


public class CambiaValute {
	
	private String name; 
	private Map<String, TassiDiCambio> elencoValute = new HashMap<>();
	
	public CambiaValute(String name, Map<String, TassiDiCambio> elencoValute) {
		super();
		this.name = name;
		this.elencoValute = elencoValute;
	}

	public String getNomeAgenzia() {
		return name;
	}

	public Map<String, TassiDiCambio> getElencoValute() {
		return elencoValute;
	}
	
	public String toString() {
		return this.name;
	}
	
	public OptionalDouble vendita(String siglaValutaEsetera, double importo ){
		TassiDiCambio tasso = this.elencoValute.get(siglaValutaEsetera);
		if(tasso == null) {
			return OptionalDouble.empty();
		}else {
			System.out.println(importo);
			System.out.println(tasso.getPrezzoVendita());
			return OptionalDouble.of(arrotonda(importo*tasso.getPrezzoVendita()));
		}
	}
	
	public OptionalDouble acquisto(String siglaValutaEsetera, double importo ){
		TassiDiCambio tasso = this.elencoValute.get(siglaValutaEsetera);
		if(tasso == null) {
			return OptionalDouble.empty();
		}else {
			System.out.println(importo);
			System.out.println(tasso.getPrezzoAcquisto());
			return OptionalDouble.of(arrotonda(importo/tasso.getPrezzoAcquisto()));
		}
	}
	
	public static double arrotonda(double importo) {      
		return Math.rint(importo*100)/100;
	}
	
	public static String formatta(double importo) {
		NumberFormat nFormat = NumberFormat.getNumberInstance(Locale.ITALY);
		nFormat.setMaximumFractionDigits(2);
		
		return nFormat.format(importo);
	}
	
	public static double convertiInDouble(String importo) throws ParseException {
		NumberFormat nFormat = NumberFormat.getNumberInstance(Locale.ITALY);
		double formattedString;
		try {
			formattedString = (double) nFormat.parse(importo).doubleValue();
		} catch (Exception e) {
			throw new ParseException("Errore", 1);
		}
		
		return formattedString;
		
	}
	
	
	
	

}
