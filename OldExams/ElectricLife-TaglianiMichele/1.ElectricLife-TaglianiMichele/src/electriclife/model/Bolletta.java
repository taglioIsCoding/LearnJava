package electriclife.model;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Bolletta
{
	private Tariffa tariffa;
	private int consumo;
	private ArrayList<VoceBolletta> lineeBolletta = new ArrayList<VoceBolletta>();
	private LocalDate date; 

	public Bolletta(LocalDate date, Tariffa tariffa, int consumo)
	{
		this.tariffa = tariffa;
		this.date = date;
		this.consumo = consumo;
	}
	
	public LocalDate getDate()
	{
		return date;
	}
	
	public String getNomeTariffa()
	{
		return tariffa.getNome();
	}
		
	public int getConsumo()
	{
		return consumo;
	}
	
	public void addLineaBolletta(VoceBolletta lineaBolletta)
	{
		lineeBolletta.add(lineaBolletta);
	}
	
	public void addLineaBolletta(String voce, double valore)
	{
		lineeBolletta.add(new VoceBolletta(voce, valore));
	}
	
	public List<VoceBolletta> getLineeBolletta()
	{
		return new ArrayList<VoceBolletta>(lineeBolletta);
	}
	
	private String linea       = "-----------------------------------------------------------";
	private String doppialinea = "===========================================================";

	public String toString()
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		stampa(pw);
		pw.close();
		return sw.toString();
	}

	public void stampa(PrintWriter pw)
	{
		pw.println(doppialinea);
		pw.println("Electric Life - L'energia che illumina!");
		pw.println("Bolletta del " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.ITALY)));
		pw.println(linea);
		
		pw.format(Locale.ITALY, "Tariffa %1s, %2s\n", getNomeTariffa(), tariffa.getDettagli());
		pw.format(Locale.ITALY, "Consumo KWh %1$5d\n", consumo);
		
		pw.println(linea);
		
		pw.println("Dettaglio importi:");
		for (VoceBolletta lineaBolletta : lineeBolletta)
		{
			pw.format(Locale.ITALY, "%1$-30s € %2$7.2f\n", lineaBolletta.getVoce(), lineaBolletta.getValore());
		}
		pw.print(doppialinea);
	}

}
