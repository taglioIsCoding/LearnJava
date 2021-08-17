package cupidonline.tests.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.SortedSet;

import org.junit.BeforeClass;
import org.junit.Test;

import cupidonline.model.*;

public class CupidoTests
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
	public void testIndiceCompatibilità()
	{	
		Cupido c = new MyCupido(iscritti, preferenze);
		assertEquals(90, c.indiceCompatibilità(iscritti.get("Anna"), preferenze.get("Roberto")));
		assertEquals(85, c.indiceCompatibilità(iscritti.get("Ludovica"), preferenze.get("Roberto")));
		assertEquals(60, c.indiceCompatibilità(iscritti.get("Elena"), preferenze.get("Roberto")));
	}
	
	@Test
	public void testVerificaSesso()
	{	
		Cupido c = new MyCupido(iscritti, preferenze);
		assertTrue(c.verificaSesso(iscritti.get("Anna"), preferenze.get("Roberto")));
		assertFalse(c.verificaSesso(iscritti.get("Anna"), null));
	}

	@Test
	public void testCorrispondenze()
	{	
		Cupido c = new MyCupido(iscritti, preferenze);
		Corrispondenza corr1 = c.calcolaCorrispondenza(iscritti.get("Anna"), preferenze.get("Roberto"),"Roberto");
		Corrispondenza corr2 = c.calcolaCorrispondenza(iscritti.get("Ludovica"), preferenze.get("Roberto"), "Roberto");
		Corrispondenza corr3 = c.calcolaCorrispondenza(iscritti.get("Elena"), preferenze.get("Roberto"), "Roberto");
		assertEquals(60, corr3.getIndice());
		assertTrue(corr2.compareTo(corr1)==-1); // corr2>corr1 perché 93>90
		assertTrue(corr2.compareTo(corr3)==-1); // corr2>corr3 perché 93>60
		assertTrue(corr3.compareTo(corr1)==+1); // corr3<corr1 perché 60<90
	}
	
	@Test 
	public void testTrovaCorrispondenze() {
		Cupido c = new MyCupido(iscritti, preferenze);
		SortedSet<Corrispondenza> corrispondenzeTrovate = c.trovaCorrispondenze("Roberto", preferenze.get("Roberto"));
		//System.out.println(corrispondenzeTrovate);
		Corrispondenza corr1 = c.calcolaCorrispondenza(iscritti.get("Anna"), preferenze.get("Roberto"),"Roberto");
		Corrispondenza corr2 = c.calcolaCorrispondenza(iscritti.get("Ludovica"), preferenze.get("Roberto"), "Roberto");
		Corrispondenza corr3 = c.calcolaCorrispondenza(iscritti.get("Elena"), preferenze.get("Roberto"), "Roberto");
		assertTrue(corrispondenzeTrovate.contains(corr1));
		assertTrue(corrispondenzeTrovate.contains(corr2));
		assertTrue(corrispondenzeTrovate.contains(corr3));
	}
	
	@Test 
	public void testTrovaCorrispondenze2() {
		Cupido c = new MyCupido(iscritti, preferenze);
		SortedSet<Corrispondenza> corrispondenzeTrovate = c.trovaCorrispondenze("Roberto", preferenze.get("Roberto"));
		//System.out.println(corrispondenzeTrovate);
		Corrispondenza corr1 = c.calcolaCorrispondenza(iscritti.get("Anna"), preferenze.get("Roberto"),"Roberto");
		Corrispondenza corr2 = c.calcolaCorrispondenza(iscritti.get("Ludovica"), preferenze.get("Roberto"), "Roberto");
		Corrispondenza corr3 = c.calcolaCorrispondenza(iscritti.get("Elena"), preferenze.get("Roberto"), "Roberto");
		assertEquals(corrispondenzeTrovate, Set.of(corr1,corr2,corr3)); // questo è ok comunque, ma.. 
		assertEquals(Set.of(corr1,corr2,corr3),corrispondenzeTrovate);  // ...questo funziona solo se Corrispondenza e Persona ridefinisco hashcode
	}
	
//	@Test 
//	public void testTrovaCorrispondenzeAlternativo() {
//		MyCupido c = new MyCupido(iscritti, preferenze);
//		SortedSet<Corrispondenza> corrispondenzeTrovate = c.trovaCorrispondenzeAlternativo("Roberto", preferenze.get("Roberto"));
//		//System.out.println(corrispondenzeTrovate);
//		Corrispondenza corr1 = c.calcolaCorrispondenza(iscritti.get("Anna"), preferenze.get("Roberto"),"Roberto");
//		Corrispondenza corr2 = c.calcolaCorrispondenza(iscritti.get("Ludovica"), preferenze.get("Roberto"), "Roberto");
//		Corrispondenza corr3 = c.calcolaCorrispondenza(iscritti.get("Elena"), preferenze.get("Roberto"), "Roberto");
//		assertTrue(corrispondenzeTrovate.contains(corr1));
//		assertTrue(corrispondenzeTrovate.contains(corr2));
//		assertTrue(corrispondenzeTrovate.contains(corr3));
//	}
	
	@Test 
	public void testTrovaCorrispondenzeDiUnaPersona() {
		Cupido c = new MyCupido(iscritti, preferenze);
		SortedSet<Corrispondenza> corrispondenzeTrovate = c.trovaCorrispondenze("Roberto");
		System.out.println("test: " + corrispondenzeTrovate);
	}

}
