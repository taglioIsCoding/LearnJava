package happybank.persistence;

import java.io.IOException;
import java.io.Reader;

import happybank.model.AbstractBancomat;

public interface BancomatConfigurationReader
{
	void configura(Reader reader, AbstractBancomat bancomat) throws IOException, BadFileFormatException;
}
