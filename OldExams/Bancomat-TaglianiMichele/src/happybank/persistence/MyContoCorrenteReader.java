package happybank.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import happybank.model.ContoCorrente;

public class MyContoCorrenteReader implements ContoCorrenteReader
{

	@Override
	public List<ContoCorrente> read(Reader reader) throws IOException, BadFileFormatException
	{
		BufferedReader buffered = new BufferedReader(reader);
		ArrayList<ContoCorrente> conti = new ArrayList<ContoCorrente>();

		try
		{
			String line;
			while ((line = buffered.readLine()) != null)
			{
				StringTokenizer tokenizer = new StringTokenizer(line);
				String id = tokenizer.nextToken();
				int saldoIniziale = Integer.parseInt(tokenizer.nextToken());
				conti.add(new ContoCorrente(id, saldoIniziale));
			}
		}
		catch (NoSuchElementException | NumberFormatException e)
		{
			throw new BadFileFormatException(e);
		}

		return conti;
	}

}
