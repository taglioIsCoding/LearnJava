package electriclife.model;

public class VoceBolletta
{
	private String voce;
	private double valore;

	public VoceBolletta(String voce, double valore)
	{
		this.voce = voce;
		this.valore = valore;
	}

	public String getVoce()
	{
		return voce;
	}

	public double getValore()
	{
		return valore;
	}
}
