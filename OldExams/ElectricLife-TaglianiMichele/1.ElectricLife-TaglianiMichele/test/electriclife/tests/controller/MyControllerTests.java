package electriclife.tests.controller;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.Test;

import electriclife.model.*;
import electriclife.persistence.*;
import electriclife.ui.controller.MyController;

public class MyControllerTests
{
//	static {
//		System.setProperty("java.locale.providers", "JRE");
//	}

	@Test
	public void testReadTariffe() throws IOException, BadFileFormatException
	{
		MyController ctrl = new MyController();
		ctrl.leggiTariffe(new TariffaReaderMock());
	}
	
	@Test
	public void testGetNomiTariffe() throws IOException, BadFileFormatException
	{
		MyController ctrl = new MyController();
		ctrl.leggiTariffe(new TariffaReaderMock());
		
		Collection<String> nomi = ctrl.getTariffe().stream().map(Tariffa::getNome).collect(Collectors.toList());
		
		assertTrue(nomi.contains("Uno"));
		assertTrue(nomi.contains("Due"));
		assertTrue(nomi.contains("Tre"));
	}

	@Test
	public void testCreaBolletta() throws IOException, BadFileFormatException
	{
		MyController ctrl = new MyController();
		ctrl.leggiTariffe(new TariffaReaderMock());
	
		Bolletta b = ctrl.creaBolletta(LocalDate.of(2018, 5, 31), "Uno", 111);
		
		assertEquals("Uno", b.getNomeTariffa());
		assertEquals(2018, b.getDate().getYear());
		assertEquals(5, b.getDate().getMonthValue());
		assertEquals(31, b.getDate().getDayOfMonth());
		assertEquals(111, b.getConsumo(), 0.01);
		assertEquals(1, b.getLineeBolletta().size());
		assertEquals("LineaBolletta - Test", b.getLineeBolletta().get(0).getVoce());
	}

}

class TariffaMock extends Tariffa
{

	public TariffaMock(String nome)
	{
		super(nome);
	}

	@Override
	public Bolletta creaBolletta(LocalDate date, int consumo)
	{		
		Bolletta bolletta = new Bolletta(date, this, consumo);
		bolletta.addLineaBolletta(new VoceBolletta("LineaBolletta - Test", 100));
		return bolletta;
	}

	@Override
	public String getDettagli() {
		return "Tariffa fantastica " + getNome();
	}
}

class TariffaReaderMock implements TariffeReader
{

	@Override
	public Collection<Tariffa> caricaTariffe() throws IOException, BadFileFormatException
	{
		ArrayList<Tariffa> tariffe = new ArrayList<Tariffa>();
		tariffe.add(new TariffaMock("Uno"));
		tariffe.add(new TariffaMock("Due"));
		tariffe.add(new TariffaMock("Tre"));
		return tariffe;
	}
	
}

class TariffaReaderMock_Error implements TariffeReader
{

	@Override
	public Collection<Tariffa> caricaTariffe() throws IOException, BadFileFormatException
	{
		throw new BadFileFormatException();
	}
	
}
