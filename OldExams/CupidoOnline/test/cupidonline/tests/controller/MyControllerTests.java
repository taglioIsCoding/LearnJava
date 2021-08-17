package cupidonline.tests.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.SortedSet;

import org.junit.BeforeClass;
import org.junit.Test;

import cupidonline.model.*;
import cupidonline.ui.controller.MyController;
import javafx.collections.ObservableList;

public class MyControllerTests
{
	static Map<String,Persona> iscritti;
	static Map<String,Preferenza> preferenze;
	
	@BeforeClass public static void popolaIscritti() {
		iscritti = Map.of(
			 "Roberto", new Persona("Roberto", Sesso.MASCHIO, LocalDate.parse("1994-04-24"), SegnoZodiacale.TORO, Colore.NERI, Colore.CASTANI, 1.78F, 61, "Bologna", "BO", "Emilia-Romagna"),
			 "Armando", new Persona("Armando", Sesso.MASCHIO, LocalDate.parse("1997-08-01"), SegnoZodiacale.LEONE, Colore.CASTANI, Colore.CASTANI, 1.71F, 65, "Parma", "PR", "Emilia-Romagna"),		 
			 "Anna", new Persona("Anna",Sesso.FEMMINA, LocalDate.parse("1996-06-18"), SegnoZodiacale.GEMELLI, Colore.BIONDI, Colore.AZZURRI, 1.70F, 60, "Imola", "BO", "Emilia-Romagna"),
			 "Ludovica", new Persona("Ludovica",Sesso.FEMMINA, LocalDate.parse("1992-10-14"), SegnoZodiacale.BILANCIA, Colore.NERI, Colore.CASTANI, 1.75F, 51, "Bologna", "BO", "Emilia-Romagna"),
			 "Elena", new Persona("Elena", Sesso.FEMMINA, LocalDate.parse("1999-04-10"), SegnoZodiacale.ARIETE, Colore.NERI, Colore.NERI, 1.65F, 57, "Modena", "MO", "Emilia-Romagna")
			 );
		preferenze = Map.of(				
				 "Roberto", new Preferenza(Sesso.FEMMINA, 20,25, Optional.of(SegnoZodiacale.BILANCIA), Optional.of(Colore.BIONDI), Optional.empty(), Optional.of(1.70F), OptionalInt.of(58), "Bologna", "BO", "Emilia-Romagna")
			);
	}

	@Test 
	public void testTrovaCorrispondenze() {
		MyController ctrl = new MyController(iscritti, preferenze);
		SortedSet<Corrispondenza> corrispondenzeTrovate = ctrl.trovaCorrispondenze("Roberto", preferenze.get("Roberto"));
		//System.out.println(corrispondenzeTrovate);
		Cupido c = new MyCupido(iscritti, preferenze);
		Corrispondenza corr1 = c.calcolaCorrispondenza(iscritti.get("Anna"), preferenze.get("Roberto"),"Roberto");
		Corrispondenza corr2 = c.calcolaCorrispondenza(iscritti.get("Ludovica"), preferenze.get("Roberto"), "Roberto");
		Corrispondenza corr3 = c.calcolaCorrispondenza(iscritti.get("Elena"), preferenze.get("Roberto"), "Roberto");
		assertTrue(corrispondenzeTrovate.contains(corr1));
		assertTrue(corrispondenzeTrovate.contains(corr2));
		assertTrue(corrispondenzeTrovate.contains(corr3));
	}

	@Test 
	public void testTrovaCorrispondenzeDiUnaPersona() {
		MyController ctrl = new MyController(iscritti, preferenze);
		SortedSet<Corrispondenza> corrispondenzeTrovate = ctrl.trovaCorrispondenze("Roberto");
		//System.out.println(corrispondenzeTrovate);
		Cupido c = new MyCupido(iscritti, preferenze);
		Corrispondenza corr1 = c.calcolaCorrispondenza(iscritti.get("Anna"), preferenze.get("Roberto"),"Roberto");
		Corrispondenza corr2 = c.calcolaCorrispondenza(iscritti.get("Ludovica"), preferenze.get("Roberto"), "Roberto");
		Corrispondenza corr3 = c.calcolaCorrispondenza(iscritti.get("Elena"), preferenze.get("Roberto"), "Roberto");
		assertTrue(corrispondenzeTrovate.contains(corr1));
		assertTrue(corrispondenzeTrovate.contains(corr2));
		assertTrue(corrispondenzeTrovate.contains(corr3));
	}
	
	@Test 
	public void testGetPersone() {
		MyController ctrl = new MyController(iscritti, preferenze);
		ObservableList<String> listaNomiIscritti = ctrl.getNomiIscritti();
		//System.out.println(listaNomiIscritti);
		assertEquals(iscritti.size(), listaNomiIscritti.size());
		listaNomiIscritti.forEach(elem -> assertTrue(iscritti.keySet().contains(elem)));
		//iscritti.keySet().forEach(elem -> System.out.println(elem + ": " + listaNomiIscritti.contains(elem)));
	}
}

