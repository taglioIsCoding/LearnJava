package happybank.model;

public abstract class AbstractBancomat
{

	private TaglioBanconota piccoloTaglio;
	private int numPiccoloTaglio;
	private TaglioBanconota grandeTaglio;
	private int numGrandeTaglio;

	public AbstractBancomat()
	{
		super();
	}

	public void caricaBanconote(TaglioBanconota piccoloTaglio, int numPiccoloTaglio, TaglioBanconota grandeTaglio, int numGrandeTaglio)
	{
		if (piccoloTaglio == null || 
				grandeTaglio == null ||
				grandeTaglio.getValore() <= piccoloTaglio.getValore() ||
				numPiccoloTaglio <= 0 ||
				numGrandeTaglio <= 0)
				throw new IllegalArgumentException();
			
			this.piccoloTaglio = piccoloTaglio;
			this.numPiccoloTaglio = numPiccoloTaglio;
			this.grandeTaglio = grandeTaglio ;
			this.numGrandeTaglio = numGrandeTaglio;
	}

	public ImportoErogato erogaImporto(TesseraBancomat tessera, int importo) throws ImportoNonErogabileException
	{				
		if (importo <= 100)
		{
			return erogaImporto(tessera, importo, piccoloTaglio.getValore(), grandeTaglio.getValore());
		}
		else
		{
			return erogaImporto(tessera, importo, grandeTaglio.getValore(), piccoloTaglio.getValore());
		}
	}
	
	public abstract ImportoErogato erogaImporto(TesseraBancomat tessera, int importo, int taglio1, int taglio2) throws ImportoNonErogabileException;

	public int getSaldoSportello()
	{
		if (piccoloTaglio == null || grandeTaglio == null)
			throw new IllegalStateException();
		
		return piccoloTaglio.getValore() * numPiccoloTaglio + grandeTaglio.getValore() * numGrandeTaglio;
	}

	public TaglioBanconota getPiccoloTaglio()
	{
		return piccoloTaglio;
	}

	public int getNumPiccoloTaglio()
	{
		return numPiccoloTaglio;
	}
	
	protected void setNumPiccoloTaglio(int numPiccoloTaglio)
	{
		this.numPiccoloTaglio = numPiccoloTaglio;
	}

	public TaglioBanconota getGrandeTaglio()
	{
		return grandeTaglio;
	}

	public int getNumGrandeTaglio()
	{
		return numGrandeTaglio;
	}
	
	protected void setNumGrandeTaglio(int numGrandeTaglio)
	{
		this.numGrandeTaglio = numGrandeTaglio;
	}

}
