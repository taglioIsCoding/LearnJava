package saferepo.persistence;

public class SadPersisterException extends Exception
{

	private static final long serialVersionUID = 1L;

	public SadPersisterException()
	{
	}

	public SadPersisterException(String arg0)
	{
		super(arg0);
	}

	public SadPersisterException(Throwable arg0)
	{
		super(arg0);
	}

	public SadPersisterException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

}
