package happybank.model;

public class ContoCorrente
{
	private String id;
	private int saldo;
	
	public ContoCorrente(String id, int saldoIniziale)
	{
		this.id = id;
		this.saldo = saldoIniziale;
	}
	
	public String getId()
	{
		return id;
	}
	
	public int getSaldo()
	{
		return saldo;
	}
	
	public void deposito(int importo)
	{
		saldo += importo;
	}
	
	public boolean possoPrelevare(int importo)
	{
		return saldo >= importo;
	}
	
	public void prelievo(int importo)
	{
		if (!possoPrelevare(importo))
			throw new IllegalArgumentException();
		
		saldo -= importo;		
	}
}
