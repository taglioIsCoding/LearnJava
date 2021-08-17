package mastermind.tests.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mastermind.model.*;

public class GiocoTest {

	@Test
	public void testSituazioneIniziale() {
		Gioco gioco = new Gioco(10,4) {
			protected void sorteggiaCombinazione(Combinazione segreta) {}
			protected Risposta calcolaRisposta(Combinazione tentativo) {return new Risposta(4);}
		};
		assertEquals(10, gioco.maxTentativi());
		assertEquals(10, gioco.tentativiRimasti());
		assertEquals(0, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());
		assertEquals(Status.IN_CORSO, gioco.stato());
		assertEquals("VUOTO,VUOTO,VUOTO,VUOTO",gioco.combinazioneSegreta());
		assertTrue(gioco.toString().contains("Situazione:"+ System.lineSeparator()));
		assertTrue(gioco.toString().contains("Gioco: "+ Status.IN_CORSO));
	}
	
	@Test
	public void testDopo1Tentativo() {
		Gioco gioco = new Gioco(10,4) {
			protected void sorteggiaCombinazione(Combinazione segreta) {
				segreta.setPiolo(0, PioloDiGioco.GIALLO);
				segreta.setPiolo(1, PioloDiGioco.ROSSO);
				segreta.setPiolo(2, PioloDiGioco.VERDE);
				segreta.setPiolo(3, PioloDiGioco.BLU);
			}
			protected Risposta calcolaRisposta(Combinazione tentativo) {
				Risposta r = new Risposta(4);
				r.setPiolo(0, PioloRisposta.BIANCO);
				r.setPiolo(1, PioloRisposta.BIANCO);
				r.setPiolo(2, PioloRisposta.NERO);
				r.setPiolo(3, PioloRisposta.NERO);
				return r;
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
		Status s = gioco.tenta(c);
		assertEquals(Status.IN_CORSO, s);
		assertEquals(10, gioco.maxTentativi());
		assertEquals(9, gioco.tentativiRimasti());
		assertEquals(1, gioco.tentativiEffettuati());
		assertEquals(4, gioco.dimensione());
		assertTrue(gioco.toString().contains("Situazione:"+ System.lineSeparator()));
		assertTrue(gioco.toString().contains("Gioco: "+ Status.IN_CORSO));
		assertTrue(gioco.toString().contains("ROSSO,GIALLO,VERDE,BLU\t\tBIANCO,BIANCO,NERO,NERO"+ System.lineSeparator()));
		//System.out.println("testDopo1Tentativo-ultimo tentativo: " + gioco.ultimoTentativo());
		//System.out.println("testDopo1Tentativo-ultima risposta:  " + gioco.ultimaRisposta());
		assertEquals(c,gioco.ultimoTentativo().get());
		Risposta r = new Risposta(4);
		r.setPiolo(0, PioloRisposta.BIANCO);
		r.setPiolo(1, PioloRisposta.BIANCO);
		r.setPiolo(2, PioloRisposta.NERO);
		r.setPiolo(3, PioloRisposta.NERO);
		assertEquals(r,gioco.ultimaRisposta().get());
	}
	

	@Test
	public void testDopo2Tentativi() {
		Gioco gioco = new Gioco(10,4) {
			protected void sorteggiaCombinazione(Combinazione segreta) {
				segreta.setPiolo(0, PioloDiGioco.GIALLO);
				segreta.setPiolo(1, PioloDiGioco.ROSSO);
				segreta.setPiolo(2, PioloDiGioco.VERDE);
				segreta.setPiolo(3, PioloDiGioco.BLU);
			}
			protected Risposta calcolaRisposta(Combinazione tentativo) {
				Risposta r= new Risposta(4);
				r.setPiolo(0, PioloRisposta.BIANCO);
				r.setPiolo(1, PioloRisposta.BIANCO);
				r.setPiolo(2, PioloRisposta.NERO);
				r.setPiolo(3, PioloRisposta.NERO);
				return r;
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
		gioco.tenta(c1);
		assertEquals(10, gioco.maxTentativi());
		assertEquals(9, gioco.tentativiRimasti());
		assertEquals(1, gioco.tentativiEffettuati());
		assertTrue(gioco.toString().contains("Situazione:"+ System.lineSeparator()));
		assertTrue(gioco.toString().contains("Gioco: "+ Status.IN_CORSO));
		assertTrue(gioco.toString().contains("ROSSO,GIALLO,VERDE,BLU\t\tBIANCO,BIANCO,NERO,NERO"+ System.lineSeparator()));
		//System.out.println("testDopo1Tentativo-ultimo tentativo: " + gioco.ultimoTentativo());
		//System.out.println("testDopo1Tentativo-ultima risposta:  " + gioco.ultimaRisposta());
		assertEquals(c1,gioco.ultimoTentativo().get());
		Risposta r1 = new Risposta(4);
		r1.setPiolo(0, PioloRisposta.BIANCO);
		r1.setPiolo(1, PioloRisposta.BIANCO);
		r1.setPiolo(2, PioloRisposta.NERO);
		r1.setPiolo(3, PioloRisposta.NERO);
		assertEquals(r1,gioco.ultimaRisposta().get());
		//--------------
		Combinazione c2 = new Combinazione(4);
		c2.setPiolo(0, PioloDiGioco.ROSSO);
		c2.setPiolo(1, PioloDiGioco.BLU);
		c2.setPiolo(2, PioloDiGioco.VERDE);
		c2.setPiolo(3, PioloDiGioco.GIALLO);
		gioco.tenta(c2);
		assertEquals(10, gioco.maxTentativi());
		assertEquals(8, gioco.tentativiRimasti());
		assertEquals(2, gioco.tentativiEffettuati());
		//System.out.println(gioco);
		assertTrue(gioco.toString().contains("Situazione:"+ System.lineSeparator()));
		assertTrue(gioco.toString().contains("Gioco: "+ Status.IN_CORSO));
		assertTrue(gioco.toString().contains("1) ROSSO,GIALLO,VERDE,BLU\t\tBIANCO,BIANCO,NERO,NERO"+ System.lineSeparator()));
		assertTrue(gioco.toString().contains("2) ROSSO,BLU,VERDE,GIALLO\t\tBIANCO,BIANCO,NERO,NERO"+ System.lineSeparator()));
		assertEquals(c2,gioco.ultimoTentativo().get());
		Risposta r2 = new Risposta(4);
		r2.setPiolo(0, PioloRisposta.BIANCO);
		r2.setPiolo(1, PioloRisposta.BIANCO);
		r2.setPiolo(2, PioloRisposta.NERO);
		r2.setPiolo(3, PioloRisposta.NERO);
		assertEquals(r2,gioco.ultimaRisposta().get());
	}

}
