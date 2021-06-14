package happybank.model;

public class ImportoErogato
{
	private TaglioBanconota piccoloTaglio;
	private int numPiccoloTaglio;
	private TaglioBanconota grandeTaglio;
	private int numGrandeTaglio;

	public ImportoErogato(TaglioBanconota piccoloTaglio, int numPiccoloTaglio, 
			TaglioBanconota grandeTaglio, int numGrandeTaglio)
	{
		this.piccoloTaglio = piccoloTaglio;
		this.numPiccoloTaglio = numPiccoloTaglio;
		this.grandeTaglio = grandeTaglio;
		this.numGrandeTaglio = numGrandeTaglio;
	}
	
	public TaglioBanconota getPiccoloTaglio()
	{
		return piccoloTaglio;
	}
	
	public int getNumPiccoloTaglio()
	{
		return numPiccoloTaglio;
	}
	
	public TaglioBanconota getGrandeTaglio()
	{
		return grandeTaglio;
	}
	
	public int getNumGrandeTaglio()
	{
		return numGrandeTaglio;
	}
}
