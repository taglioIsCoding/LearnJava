package happybank.model;

public enum TaglioBanconota
{
	VENTI(20), CINQUANTA(50), CENTO(100), DUECENTO(200), CINQUECENTO(500);

	private int valore;

	private TaglioBanconota(int valore)
	{
		this.setValore(valore);
	}

	public int getValore()
	{
		return valore;
	}

	private void setValore(int valore)
	{
		this.valore = valore;
	}

}
