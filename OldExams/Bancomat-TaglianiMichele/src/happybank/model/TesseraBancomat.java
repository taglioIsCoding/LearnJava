package happybank.model;

public class TesseraBancomat 
{
	private String pin;
	private ContoCorrente conto;
	private int maxPrelevabile;
	
	public TesseraBancomat(String pin, int maxPrelevabile, ContoCorrente conto)
	{
		this.pin = pin;
		this.maxPrelevabile = maxPrelevabile;
		this.conto = conto;
	}

	public String getPin()
	{
		return pin;
	}
	
	public int getMaxPrelevabile()
	{
		return maxPrelevabile;
	}
	
	public boolean checkPrelevamentoDalConto(int importo)
	{
		 return conto.possoPrelevare(importo);
	}
	
	public boolean checkMaxPrelevabile(int importo)
	{
		return importo <= maxPrelevabile;
	}
	
	public void prelievo(int importo) 
	{
		if (!checkMaxPrelevabile(importo) || !checkPrelevamentoDalConto(importo))
			throw new IllegalStateException();
		
		conto.prelievo(importo);
	}

	public ContoCorrente getContoCorrente()
	{
		return conto;
	}

	public String getId()
	{
		return conto.getId();
	}
}
