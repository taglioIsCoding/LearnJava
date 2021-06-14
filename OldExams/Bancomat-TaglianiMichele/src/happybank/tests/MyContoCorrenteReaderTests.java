package happybank.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import happybank.model.ContoCorrente;
import happybank.persistence.BadFileFormatException;
import happybank.persistence.MyContoCorrenteReader;

public class MyContoCorrenteReaderTests
{

	@Test
	public void testRead() throws IOException, BadFileFormatException
	{
		String data = "1234567 6145\n3214576 1700\n";
		MyContoCorrenteReader reader = new MyContoCorrenteReader();
		
		List<ContoCorrente> conti = reader.read(new StringReader(data));
		
		assertEquals(2, conti.size());
			
		ContoCorrente cc;
		
		cc = conti.get(0);
		assertEquals("1234567", cc.getId());
		assertEquals(6145, cc.getSaldo());
		
		cc = conti.get(1);
		assertEquals("3214576", cc.getId());
		assertEquals(1700, cc.getSaldo());
	}

}
