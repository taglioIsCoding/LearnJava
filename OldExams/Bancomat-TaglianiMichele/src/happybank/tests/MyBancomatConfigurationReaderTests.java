package happybank.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import happybank.model.*;
import happybank.persistence.*;

public class MyBancomatConfigurationReaderTests
{

	@Test
	public void testConfigure() throws IOException, BadFileFormatException
	{
		String data = "20 x 400 + 50 x 500\n";
		AbstractBancomat hb = new HappyBancomat();
		MyBancomatConfigurationReader reader = new MyBancomatConfigurationReader();
		
		reader.configura(new StringReader(data), hb);
		
		assertEquals(400, hb.getNumPiccoloTaglio());
		assertSame(TaglioBanconota.VENTI, hb.getPiccoloTaglio());
		assertEquals(500, hb.getNumGrandeTaglio());
		assertSame(TaglioBanconota.CINQUANTA, hb.getGrandeTaglio());
	}
	
	@Test(expected = BadFileFormatException.class)
	public void testConfigure_FailReadBanconota1_NessunTaglio() throws IOException, BadFileFormatException
	{
		String data = "30 x 400 + 50 x 500\n";
		AbstractBancomat hb = new HappyBancomat();
		MyBancomatConfigurationReader reader = new MyBancomatConfigurationReader();
		
		reader.configura(new StringReader(data), hb);
	}
	
	@Test(expected = BadFileFormatException.class)
	public void testConfigure_FailReadBanconota1_Formato() throws IOException, BadFileFormatException
	{
		String data = "2x0 x 400 + 50 x 500\n";
		AbstractBancomat hb = new HappyBancomat();
		MyBancomatConfigurationReader reader = new MyBancomatConfigurationReader();
		
		reader.configura(new StringReader(data), hb);
	}
	
	@Test(expected = BadFileFormatException.class)
	public void testConfigure_FailReadBanconota2_NessunTaglio() throws IOException, BadFileFormatException
	{
		String data = "20 x 400 + 60 x 500\n";
		AbstractBancomat hb = new HappyBancomat();
		MyBancomatConfigurationReader reader = new MyBancomatConfigurationReader();
		
		reader.configura(new StringReader(data), hb);
	}
	
	@Test(expected = BadFileFormatException.class)
	public void testConfigure_FailReadBanconota2_Formato() throws IOException, BadFileFormatException
	{
		String data = "20 x 400 + 5x0 x 500\n";
		AbstractBancomat hb = new HappyBancomat();
		MyBancomatConfigurationReader reader = new MyBancomatConfigurationReader();
		
		reader.configura(new StringReader(data), hb);
	}

	
	@Test(expected = BadFileFormatException.class)
	public void testConfigure_FailReadNumBanconota1_Formato() throws IOException, BadFileFormatException
	{
		String data = "20 x 40x0 + 50 x 500\n";
		AbstractBancomat hb = new HappyBancomat();
		MyBancomatConfigurationReader reader = new MyBancomatConfigurationReader();
		
		reader.configura(new StringReader(data), hb);
	}
	
	@Test(expected = BadFileFormatException.class)
	public void testConfigure_FailReadNumBanconota2_Formato() throws IOException, BadFileFormatException
	{
		String data = "20 x 400 + 50 x 5x00\n";
		AbstractBancomat hb = new HappyBancomat();
		MyBancomatConfigurationReader reader = new MyBancomatConfigurationReader();
		
		reader.configura(new StringReader(data), hb);
	}

}
