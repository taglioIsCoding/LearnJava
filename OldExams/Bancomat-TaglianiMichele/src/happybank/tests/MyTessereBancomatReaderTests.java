package happybank.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import happybank.model.*;
import happybank.persistence.*;

public class MyTessereBancomatReaderTests
{

	@Test
	public void testRead() throws IOException, BadFileFormatException
	{
		String data = "1234567 02026 250\n3214576 94715 400\n";
		MyTesseraBancomatReader reader = new MyTesseraBancomatReader();

		HashMap<String, ContoCorrente> contoCorrenteMap = new HashMap<String, ContoCorrente>();
		ContoCorrente cc = new ContoCorrente("1234567", 1000);
		contoCorrenteMap.put(cc.getId(), cc);
		cc = new ContoCorrente("3214576", 2000);
		contoCorrenteMap.put(cc.getId(), cc);
		
		List<TesseraBancomat> tessere = reader.read(new StringReader(data), contoCorrenteMap);
		
		TesseraBancomat b = tessere.get(0);
		assertEquals("02026", b.getPin());
		assertEquals(250, b.getMaxPrelevabile());
		assertSame(contoCorrenteMap.get("1234567"), b.getContoCorrente());
		
		b = tessere.get(1);
		assertEquals("94715", b.getPin());
		assertEquals(400, b.getMaxPrelevabile());
		assertSame(contoCorrenteMap.get("3214576"), b.getContoCorrente());
	}
	
	@Test(expected = BadFileFormatException.class)
	public void testRead_FailNoContoCorrente() throws IOException, BadFileFormatException
	{
		String data = "x1234567x 02026 250\n3214576 94715 400\n";
		MyTesseraBancomatReader reader = new MyTesseraBancomatReader();

		HashMap<String, ContoCorrente> contoCorrenteMap = new HashMap<String, ContoCorrente>();
		ContoCorrente cc = new ContoCorrente("1234567", 1000);
		contoCorrenteMap.put(cc.getId(), cc);
		cc = new ContoCorrente("3214576", 2000);
		contoCorrenteMap.put(cc.getId(), cc);
		
		reader.read(new StringReader(data), contoCorrenteMap);
	}
	
	@Test(expected = BadFileFormatException.class)
	public void testRead_FailFormatoImportoMax() throws IOException, BadFileFormatException
	{
		String data = "1234567 02026 2x50\n3214576 94715 400\n";
		MyTesseraBancomatReader reader = new MyTesseraBancomatReader();

		HashMap<String, ContoCorrente> contoCorrenteMap = new HashMap<String, ContoCorrente>();
		ContoCorrente cc = new ContoCorrente("1234567", 1000);
		contoCorrenteMap.put(cc.getId(), cc);
		cc = new ContoCorrente("3214576", 2000);
		contoCorrenteMap.put(cc.getId(), cc);
		
		reader.read(new StringReader(data), contoCorrenteMap);
	}

}
