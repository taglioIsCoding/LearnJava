package mastermind.tests.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import java.util.StringJoiner;
import mastermind.model.*;

public class MasterMindTest1 {

	private static String coloriPioli;
	
	@BeforeAll
	public static void coloriPioli() {
		StringJoiner sb = new StringJoiner(",");
		for(PioloDiGioco c : PioloDiGioco.values()) {
			sb.add(c.toString());
		}
		coloriPioli = sb.toString();
	}
	
	//@Test
	@RepeatedTest(5)
	public void testSorteggioIniziale(TestInfo testInfo, RepetitionInfo repetitionInfo) {
		MasterMind gioco = new MasterMind(10,4);
		assertEquals(10, gioco.maxTentativi());
		assertEquals(10, gioco.tentativiRimasti());
		assertEquals(0, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());
		assertEquals(Status.IN_CORSO, gioco.stato());
		String combinazione = gioco.combinazioneSegreta();
		//	System.out.println("Running testSorteggioIniziale #" + repetitionInfo.getCurrentRepetition() + " -> " + combinazione);
		String[] colori  = combinazione.split(",");
		for (String colore: colori) assertTrue(coloriPioli.contains(colore));
		assertTrue(gioco.toString().contains("Situazione:"+ System.lineSeparator()));
		assertTrue(gioco.toString().contains("Gioco: "+ Status.IN_CORSO));
		assertTrue(gioco.ultimoTentativo().isEmpty());
		assertTrue(gioco.ultimaRisposta().isEmpty());
	}
	
	
	@Test
	public void testDopo1Tentativo() {
		MasterMind gioco = new MasterMind(10,4) {
			protected void sorteggiaCombinazione(Combinazione segreta) {
				segreta.setPiolo(0, PioloDiGioco.GIALLO);
				segreta.setPiolo(1, PioloDiGioco.ROSSO);
				segreta.setPiolo(2, PioloDiGioco.VERDE);
				segreta.setPiolo(3, PioloDiGioco.BLU);
			}
		};
		assertEquals(10, gioco.maxTentativi());
		assertEquals(10, gioco.tentativiRimasti());
		assertEquals(0, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());
		assertEquals(Status.IN_CORSO, gioco.stato());
		assertEquals("GIALLO,ROSSO,VERDE,BLU",gioco.combinazioneSegreta());
		Combinazione c = new Combinazione(4);
		c.setPiolo(0, PioloDiGioco.ROSSO);
		c.setPiolo(1, PioloDiGioco.GIALLO);
		c.setPiolo(2, PioloDiGioco.VERDE);
		c.setPiolo(3, PioloDiGioco.BLU);
		Status status = gioco.tenta(c);
		assertEquals(Status.IN_CORSO, status);
		// System.out.println("testDopo1Tentativo-ultimarisposta: " + gioco.ultimaRisposta());
		assertEquals(10, gioco.maxTentativi());
		assertEquals(9, gioco.tentativiRimasti());
		assertEquals(1, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());		
		assertTrue(gioco.toString().contains("Situazione:"+ System.lineSeparator()));
		assertTrue(gioco.toString().contains("Gioco: "+ Status.IN_CORSO));
		assertTrue(gioco.toString().contains("ROSSO,GIALLO,VERDE,BLU\t\tNERO,NERO,BIANCO,BIANCO"+ System.lineSeparator()));
		//System.out.println(gioco);
		assertEquals(c,gioco.ultimoTentativo().get());
		Risposta r = new Risposta(4);
		r.setPiolo(0, PioloRisposta.NERO);
		r.setPiolo(1, PioloRisposta.NERO);
		r.setPiolo(2, PioloRisposta.BIANCO);
		r.setPiolo(3, PioloRisposta.BIANCO);
		assertEquals(r,gioco.ultimaRisposta().get());
	}
	
	@Test
	public void testDopo2Tentativi() {
		MasterMind gioco = new MasterMind(10,4) {
			protected void sorteggiaCombinazione(Combinazione segreta) {
				segreta.setPiolo(0, PioloDiGioco.GIALLO);
				segreta.setPiolo(1, PioloDiGioco.ROSSO);
				segreta.setPiolo(2, PioloDiGioco.VERDE);
				segreta.setPiolo(3, PioloDiGioco.BLU);
			}
		};
		assertEquals(10, gioco.maxTentativi());
		assertEquals(10, gioco.tentativiRimasti());
		assertEquals(0, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());
		assertEquals(Status.IN_CORSO, gioco.stato());
		assertEquals("GIALLO,ROSSO,VERDE,BLU",gioco.combinazioneSegreta());
		Combinazione c1 = new Combinazione(4);
		c1.setPiolo(0, PioloDiGioco.ROSSO);
		c1.setPiolo(1, PioloDiGioco.GIALLO);
		c1.setPiolo(2, PioloDiGioco.VERDE);
		c1.setPiolo(3, PioloDiGioco.BLU);
		Status status1 = gioco.tenta(c1);
		assertEquals(Status.IN_CORSO, status1);
		//	System.out.println("testDopo2Tentativi-ultimarisposta: " + gioco.ultimaRisposta());
		assertEquals(10, gioco.maxTentativi());
		assertEquals(9, gioco.tentativiRimasti());
		assertEquals(1, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());		
		assertTrue(gioco.toString().contains("Situazione:"+ System.lineSeparator()));
		assertTrue(gioco.toString().contains("Gioco: "+ Status.IN_CORSO));
		assertTrue(gioco.toString().contains("ROSSO,GIALLO,VERDE,BLU\t\tNERO,NERO,BIANCO,BIANCO"+ System.lineSeparator()));
		//	System.out.println(gioco);
		assertEquals(c1,gioco.ultimoTentativo().get());
		Risposta r1 = new Risposta(4);
		r1.setPiolo(0, PioloRisposta.NERO);
		r1.setPiolo(1, PioloRisposta.NERO);
		r1.setPiolo(2, PioloRisposta.BIANCO);
		r1.setPiolo(3, PioloRisposta.BIANCO);
		assertEquals(r1,gioco.ultimaRisposta().get());
		//------------
		Combinazione c2 = new Combinazione(4);
		c2.setPiolo(0, PioloDiGioco.ROSSO);
		c2.setPiolo(1, PioloDiGioco.GIALLO);
		c2.setPiolo(2, PioloDiGioco.BLU);
		c2.setPiolo(3, PioloDiGioco.VERDE);
		Status status2 = gioco.tenta(c2);
		assertEquals(Status.IN_CORSO, status2);
		//	System.out.println("testDopo2Tentativi-ultimarisposta: " + gioco.ultimaRisposta());
		assertEquals(10, gioco.maxTentativi());
		assertEquals(8, gioco.tentativiRimasti());
		assertEquals(2, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());
		assertEquals(c2,gioco.ultimoTentativo().get());
		Risposta r2 = new Risposta(4);
		r2.setPiolo(0, PioloRisposta.BIANCO);
		r2.setPiolo(1, PioloRisposta.BIANCO);
		r2.setPiolo(2, PioloRisposta.BIANCO);
		r2.setPiolo(3, PioloRisposta.BIANCO);
		assertEquals(r2,gioco.ultimaRisposta().get());		
	}
	
	@Test
	public void testDopo3Tentativi() {
		MasterMind gioco = new MasterMind(10,4) {
			protected void sorteggiaCombinazione(Combinazione segreta) {
				segreta.setPiolo(0, PioloDiGioco.GIALLO);
				segreta.setPiolo(1, PioloDiGioco.ROSSO);
				segreta.setPiolo(2, PioloDiGioco.VERDE);
				segreta.setPiolo(3, PioloDiGioco.BLU);
			}
		};
		assertEquals(10, gioco.maxTentativi());
		assertEquals(10, gioco.tentativiRimasti());
		assertEquals(0, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());
		assertEquals(Status.IN_CORSO, gioco.stato());
		assertEquals("GIALLO,ROSSO,VERDE,BLU",gioco.combinazioneSegreta());
		Combinazione c1 = new Combinazione(4);
		c1.setPiolo(0, PioloDiGioco.ROSSO);
		c1.setPiolo(1, PioloDiGioco.GIALLO);
		c1.setPiolo(2, PioloDiGioco.VERDE);
		c1.setPiolo(3, PioloDiGioco.BLU);
		Status status1 = gioco.tenta(c1);
		assertEquals(Status.IN_CORSO, status1);
		//	System.out.println("testDopo3Tentativi-ultimarisposta: " + gioco.ultimaRisposta());
		assertEquals(10, gioco.maxTentativi());
		assertEquals(9, gioco.tentativiRimasti());
		assertEquals(1, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());		
		assertTrue(gioco.toString().contains("Situazione:"+ System.lineSeparator()));
		assertTrue(gioco.toString().contains("Gioco: "+ Status.IN_CORSO));
		assertTrue(gioco.toString().contains("ROSSO,GIALLO,VERDE,BLU\t\tNERO,NERO,BIANCO,BIANCO"+ System.lineSeparator()));
		//	System.out.println(gioco);
		assertEquals(c1,gioco.ultimoTentativo().get());
		Risposta r1 = new Risposta(4);
		r1.setPiolo(0, PioloRisposta.NERO);
		r1.setPiolo(1, PioloRisposta.NERO);
		r1.setPiolo(2, PioloRisposta.BIANCO);
		r1.setPiolo(3, PioloRisposta.BIANCO);
		assertEquals(r1,gioco.ultimaRisposta().get());
		//------------
		Combinazione c2 = new Combinazione(4);
		c2.setPiolo(0, PioloDiGioco.ROSSO);
		c2.setPiolo(1, PioloDiGioco.GIALLO);
		c2.setPiolo(2, PioloDiGioco.BLU);
		c2.setPiolo(3, PioloDiGioco.VERDE);
		Status status2 = gioco.tenta(c2);
		assertEquals(Status.IN_CORSO, status2);
		//	System.out.println("testDopo3Tentativi-ultimarisposta: " + gioco.ultimaRisposta());
		assertEquals(10, gioco.maxTentativi());
		assertEquals(8, gioco.tentativiRimasti());
		assertEquals(2, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());
		assertEquals(c2,gioco.ultimoTentativo().get());
		Risposta r2 = new Risposta(4);
		r2.setPiolo(0, PioloRisposta.BIANCO);
		r2.setPiolo(1, PioloRisposta.BIANCO);
		r2.setPiolo(2, PioloRisposta.BIANCO);
		r2.setPiolo(3, PioloRisposta.BIANCO);
		assertEquals(r2,gioco.ultimaRisposta().get());		
		//------------
		Combinazione c3 = new Combinazione(4);
		c3.setPiolo(0, PioloDiGioco.GIALLO);
		c3.setPiolo(1, PioloDiGioco.ROSSO);
		c3.setPiolo(2, PioloDiGioco.BLU);
		c3.setPiolo(3, PioloDiGioco.VERDE);
		Status status3 = gioco.tenta(c3);
		assertEquals(Status.IN_CORSO, status3);
		//	System.out.println("testDopo3Tentativi-ultimarisposta: " + gioco.ultimaRisposta());
		assertEquals(10, gioco.maxTentativi());
		assertEquals(7, gioco.tentativiRimasti());
		assertEquals(3, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());
		assertEquals(c3,gioco.ultimoTentativo().get());
		Risposta r3 = new Risposta(4);
		r3.setPiolo(0, PioloRisposta.NERO);
		r3.setPiolo(1, PioloRisposta.NERO);
		r3.setPiolo(2, PioloRisposta.BIANCO);
		r3.setPiolo(3, PioloRisposta.BIANCO);
		assertEquals(r3,gioco.ultimaRisposta().get());		
	}
	
	@Test
	public void testDopo4Tentativi() {
		MasterMind gioco = new MasterMind(10,4) {
			protected void sorteggiaCombinazione(Combinazione segreta) {
				segreta.setPiolo(0, PioloDiGioco.GIALLO);
				segreta.setPiolo(1, PioloDiGioco.ROSSO);
				segreta.setPiolo(2, PioloDiGioco.VERDE);
				segreta.setPiolo(3, PioloDiGioco.BLU);
			}
		};
		assertEquals(10, gioco.maxTentativi());
		assertEquals(10, gioco.tentativiRimasti());
		assertEquals(0, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());
		assertEquals(Status.IN_CORSO, gioco.stato());
		assertEquals("GIALLO,ROSSO,VERDE,BLU",gioco.combinazioneSegreta());
		Combinazione c1 = new Combinazione(4);
		c1.setPiolo(0, PioloDiGioco.ROSSO);
		c1.setPiolo(1, PioloDiGioco.GIALLO);
		c1.setPiolo(2, PioloDiGioco.VERDE);
		c1.setPiolo(3, PioloDiGioco.BLU);
		Status status1 = gioco.tenta(c1);
		assertEquals(Status.IN_CORSO, status1);
		//	System.out.println("testDopo4Tentativi-risposta#1: " + gioco.ultimaRisposta());
		assertEquals(10, gioco.maxTentativi());
		assertEquals(9, gioco.tentativiRimasti());
		assertEquals(1, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());		
		assertTrue(gioco.toString().contains("Situazione:"+ System.lineSeparator()));
		assertTrue(gioco.toString().contains("Gioco: "+ Status.IN_CORSO));
		assertTrue(gioco.toString().contains("ROSSO,GIALLO,VERDE,BLU\t\tNERO,NERO,BIANCO,BIANCO"+ System.lineSeparator()));
		//System.out.println(gioco);
		assertEquals(c1,gioco.ultimoTentativo().get());
		Risposta r1 = new Risposta(4);
		r1.setPiolo(0, PioloRisposta.NERO);
		r1.setPiolo(1, PioloRisposta.NERO);
		r1.setPiolo(2, PioloRisposta.BIANCO);
		r1.setPiolo(3, PioloRisposta.BIANCO);
		assertEquals(r1,gioco.ultimaRisposta().get());
		//------------
		Combinazione c2 = new Combinazione(4);
		c2.setPiolo(0, PioloDiGioco.ROSSO);
		c2.setPiolo(1, PioloDiGioco.GIALLO);
		c2.setPiolo(2, PioloDiGioco.BLU);
		c2.setPiolo(3, PioloDiGioco.VERDE);
		Status status2 = gioco.tenta(c2);
		assertEquals(Status.IN_CORSO, status2);
		//	System.out.println("testDopo4Tentativi-risposta#2: " + gioco.ultimaRisposta());
		assertEquals(10, gioco.maxTentativi());
		assertEquals(8, gioco.tentativiRimasti());
		assertEquals(2, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());
		assertEquals(c2,gioco.ultimoTentativo().get());
		Risposta r2 = new Risposta(4);
		r2.setPiolo(0, PioloRisposta.BIANCO);
		r2.setPiolo(1, PioloRisposta.BIANCO);
		r2.setPiolo(2, PioloRisposta.BIANCO);
		r2.setPiolo(3, PioloRisposta.BIANCO);
		assertEquals(r2,gioco.ultimaRisposta().get());		
		//------------
		Combinazione c3 = new Combinazione(4);
		c3.setPiolo(0, PioloDiGioco.GIALLO);
		c3.setPiolo(1, PioloDiGioco.ROSSO);
		c3.setPiolo(2, PioloDiGioco.BLU);
		c3.setPiolo(3, PioloDiGioco.VERDE);
		Status status3 = gioco.tenta(c3);
		assertEquals(Status.IN_CORSO, status3);
		//	System.out.println("testDopo4Tentativi-risposta#3: " + gioco.ultimaRisposta());
		assertEquals(10, gioco.maxTentativi());
		assertEquals(7, gioco.tentativiRimasti());
		assertEquals(3, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());
		assertEquals(c3,gioco.ultimoTentativo().get());
		Risposta r3 = new Risposta(4);
		r3.setPiolo(0, PioloRisposta.NERO);
		r3.setPiolo(1, PioloRisposta.NERO);
		r3.setPiolo(2, PioloRisposta.BIANCO);
		r3.setPiolo(3, PioloRisposta.BIANCO);
		assertEquals(r3,gioco.ultimaRisposta().get());		
		//------------
		Combinazione c4 = new Combinazione(4);
		c4.setPiolo(0, PioloDiGioco.GIALLO);
		c4.setPiolo(1, PioloDiGioco.ROSSO);
		c4.setPiolo(2, PioloDiGioco.VERDE);
		c4.setPiolo(3, PioloDiGioco.BLU);
		Status status4 = gioco.tenta(c4);
		assertEquals(Status.VITTORIA, status4);
		//	System.out.println("testDopo4Tentativi-risposta#4: " + gioco.ultimaRisposta());
		assertEquals(10, gioco.maxTentativi());
		assertEquals(6, gioco.tentativiRimasti());
		assertEquals(4, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());
		assertEquals(c4,gioco.ultimoTentativo().get());
		Risposta r4 = new Risposta(4);
		r4.setPiolo(0, PioloRisposta.NERO);
		r4.setPiolo(1, PioloRisposta.NERO);
		r4.setPiolo(2, PioloRisposta.NERO);
		r4.setPiolo(3, PioloRisposta.NERO);
		assertEquals(r4,gioco.ultimaRisposta().get());		
	}
}
