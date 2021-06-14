package happybank.model;

public class ImportoNonErogabileException extends Exception
{

	private static final long serialVersionUID = 1L;

	public ImportoNonErogabileException()
	{
	}

	public ImportoNonErogabileException(String arg0)
	{
		super(arg0);
	}

	public ImportoNonErogabileException(Throwable arg0)
	{
		super(arg0);
	}

	public ImportoNonErogabileException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

	public ImportoNonErogabileException(String arg0, Throwable arg1, boolean arg2, boolean arg3)
	{
		super(arg0, arg1, arg2, arg3);
	}

}
