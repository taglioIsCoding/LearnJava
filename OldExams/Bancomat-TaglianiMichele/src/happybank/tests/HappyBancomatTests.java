package happybank.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import happybank.model.*;

public class HappyBancomatTests
{

	@Test
	public void testCaricaBanconotee()
	{
		AbstractBancomat hb = new HappyBancomat();
		hb.caricaBanconote(TaglioBanconota.VENTI, 5, TaglioBanconota.CENTO, 10);
		
		assertEquals(5, hb.getNumPiccoloTaglio());
		assertSame(TaglioBanconota.VENTI, hb.getPiccoloTaglio());
		assertEquals(10, hb.getNumGrandeTaglio());
		assertSame(TaglioBanconota.CENTO, hb.getGrandeTaglio());
	}

	@Test(expected = IllegalStateException.class)
	public void testGetSaldoSenzaBanconote()
	{
		AbstractBancomat hb = new HappyBancomat();
		hb.getSaldoSportello();
	}
	
	@Test
	public void testGetSaldoConBanconote()
	{
		AbstractBancomat hb = new HappyBancomat();
		hb.caricaBanconote(TaglioBanconota.VENTI, 5, TaglioBanconota.CENTO, 10);
		
		assertEquals(1100, hb.getSaldoSportello());
	}

	@Test
	public void testGetPiccoloTaglio()
	{
		AbstractBancomat hb = new HappyBancomat();
		assertNull(hb.getPiccoloTaglio());
	}

	@Test
	public void testGetNumPiccoloTaglio()
	{
		AbstractBancomat hb = new HappyBancomat();
		assertEquals(0, hb.getNumPiccoloTaglio());
	}

	@Test
	public void testGetGrandeTaglio()
	{
		AbstractBancomat hb = new HappyBancomat();
		assertNull(hb.getGrandeTaglio());
	}

	@Test
	public void testGetNumGrandeTaglio()
	{
		AbstractBancomat hb = new HappyBancomat();
		assertEquals(0, hb.getNumGrandeTaglio());
	}

	@Test
	public void testPreleva160() throws ImportoNonErogabileException
	{
		AbstractBancomat hb = new HappyBancomat();
		hb.caricaBanconote(TaglioBanconota.VENTI, 10, TaglioBanconota.CINQUANTA, 10);
		
		ContoCorrente cc = new ContoCorrente("12345", 1000);
		TesseraBancomat bc = new TesseraBancomat("12121", 250, cc);
		ImportoErogato prel = hb.erogaImporto(bc, 160);
		
		assertEquals(540, hb.getSaldoSportello());
		assertEquals(7, hb.getNumPiccoloTaglio());
		assertEquals(8, hb.getNumGrandeTaglio());
		assertSame(TaglioBanconota.VENTI, prel.getPiccoloTaglio());
		assertEquals(3, prel.getNumPiccoloTaglio());
		assertSame(TaglioBanconota.CINQUANTA, prel.getGrandeTaglio());
		assertEquals(2, prel.getNumGrandeTaglio());
	}

	@Test
	public void testPreleva80() throws ImportoNonErogabileException
	{
		AbstractBancomat hb = new HappyBancomat();
		hb.caricaBanconote(TaglioBanconota.VENTI, 10, TaglioBanconota.CINQUANTA, 10);
		
		ContoCorrente cc = new ContoCorrente("12345", 1000);
		TesseraBancomat bc = new TesseraBancomat("12121", 250, cc);
		ImportoErogato prel = hb.erogaImporto(bc, 80);
		
		assertEquals(620, hb.getSaldoSportello()); 
		assertEquals(6, hb.getNumPiccoloTaglio());
		assertEquals(10, hb.getNumGrandeTaglio());
		assertSame(TaglioBanconota.VENTI, prel.getPiccoloTaglio());
		assertEquals(4, prel.getNumPiccoloTaglio());
		assertSame(TaglioBanconota.CINQUANTA, prel.getGrandeTaglio());
		assertEquals(0, prel.getNumGrandeTaglio());
	}

	@Test
	public void testPreleva100() throws ImportoNonErogabileException
	{
		AbstractBancomat hb = new HappyBancomat();
		hb.caricaBanconote(TaglioBanconota.VENTI, 10, TaglioBanconota.CINQUANTA, 10);
		
		ContoCorrente cc = new ContoCorrente("12345", 1000);
		TesseraBancomat bc = new TesseraBancomat("12121", 250, cc);
		ImportoErogato prel = hb.erogaImporto(bc, 100);
		
		assertEquals(600, hb.getSaldoSportello()); 
		assertEquals(5, hb.getNumPiccoloTaglio());
		assertEquals(10, hb.getNumGrandeTaglio());
		assertSame(TaglioBanconota.VENTI, prel.getPiccoloTaglio());
		assertEquals(5, prel.getNumPiccoloTaglio());
		assertSame(TaglioBanconota.CINQUANTA, prel.getGrandeTaglio());
		assertEquals(0, prel.getNumGrandeTaglio());
	}

	@Test(expected = ImportoNonErogabileException.class)
	public void testPreleva30ImpossibileComporre() throws ImportoNonErogabileException
	{
		AbstractBancomat hb = new HappyBancomat();
		hb.caricaBanconote(TaglioBanconota.VENTI, 10, TaglioBanconota.CINQUANTA, 10);
		
		ContoCorrente cc = new ContoCorrente("12345", 1000);
		TesseraBancomat bc = new TesseraBancomat("12121", 250, cc);
		hb.erogaImporto(bc, 30);
	}

	@Test(expected = ImportoNonErogabileException.class)
	public void testPreleva80PiccoloTaglioNonSufficiente() throws ImportoNonErogabileException
	{
		AbstractBancomat hb = new HappyBancomat();
		hb.caricaBanconote(TaglioBanconota.VENTI, 2, TaglioBanconota.CINQUANTA, 1);
		
		ContoCorrente cc = new ContoCorrente("12345", 1000);
		TesseraBancomat bc = new TesseraBancomat("12121", 250, cc);
		hb.erogaImporto(bc, 80);
	}

	@Test(expected = ImportoNonErogabileException.class)
	public void testPreleva200GrandeTaglioNonSufficiente() throws ImportoNonErogabileException
	{
		AbstractBancomat hb = new HappyBancomat();
		hb.caricaBanconote(TaglioBanconota.VENTI, 2, TaglioBanconota.CINQUANTA, 1);
		
		ContoCorrente cc = new ContoCorrente("12345", 1000);
		TesseraBancomat bc = new TesseraBancomat("12121", 250, cc);
		hb.erogaImporto(bc, 200);
	}

	@Test(expected = ImportoNonErogabileException.class)
	public void testPreleva200SommaNonDisponibileNelConto() throws ImportoNonErogabileException
	{
		AbstractBancomat hb = new HappyBancomat();
		hb.caricaBanconote(TaglioBanconota.VENTI, 10, TaglioBanconota.CINQUANTA, 10);
		
		ContoCorrente cc = new ContoCorrente("12345", 100);
		TesseraBancomat bc = new TesseraBancomat("12121", 250, cc);
		hb.erogaImporto(bc, 200);
	}

	@Test(expected = ImportoNonErogabileException.class)
	public void testPreleva200SommaOltreMaxPrelevabile() throws ImportoNonErogabileException
	{
		AbstractBancomat hb = new HappyBancomat();
		hb.caricaBanconote(TaglioBanconota.VENTI, 10, TaglioBanconota.CINQUANTA, 10);
		
		ContoCorrente cc = new ContoCorrente("12345", 1000);
		TesseraBancomat bc = new TesseraBancomat("12121", 250, cc);
		hb.erogaImporto(bc, 300);
	}

}
