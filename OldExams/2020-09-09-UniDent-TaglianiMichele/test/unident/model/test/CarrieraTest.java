package unident.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.Test;

import unident.model.AttivitaFormativa;
import unident.model.Carriera;
import unident.model.Esame;
import unident.model.Ssd;
import unident.model.Tipologia;
import unident.model.Voto;

class CarrieraTest {

	@Test
	void testOK_esameStandard() {
		AttivitaFormativa af1 = new AttivitaFormativa("Fondamenti di Informatica T-1", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af2 = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);

		Carriera carriera = new Carriera(Set.of(
				af1,af2
				));

		LocalDate dataEsame = LocalDate.of(2020, 8, 10);
		Esame e0 = new Esame(af1,Voto.RITIRATO,dataEsame.minusDays(15));
		Esame e2 = new Esame(af1,Voto.V25,dataEsame.plusMonths(1));
		
		carriera.registra(e0);
		carriera.registra(e2);
		assertEquals(2, carriera.istanzeDi(af1).size());
		assertEquals(e0, carriera.istanzeDi(af1).get(0));
		assertEquals(e2, carriera.istanzeDi(af1).get(1));
	}

	@Test
	void testKO_esameStandard_DateInvertite() {
		AttivitaFormativa af1 = new AttivitaFormativa("Fondamenti di Informatica T-1", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af2 = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);

		Carriera carriera = new Carriera(Set.of(
				af1,af2
				));

		LocalDate dataEsame = LocalDate.of(2020, 8, 10);
		Esame e0 = new Esame(af1,Voto.RITIRATO,dataEsame.plusDays(15));
		Esame e2 = new Esame(af1,Voto.V25,dataEsame);
		
		carriera.registra(e0);
		assertThrows(IllegalArgumentException.class, () -> carriera.registra(e2));
	}

	@Test
	void testKO_esameStandard_DateIdentiche() {
		AttivitaFormativa af1 = new AttivitaFormativa("Fondamenti di Informatica T-1", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af2 = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);

		Carriera carriera = new Carriera(Set.of(
				af1,af2
				));

		LocalDate dataEsame = LocalDate.of(2020, 8, 10);
		Esame e0 = new Esame(af1,Voto.RITIRATO,dataEsame);
		Esame e2 = new Esame(af1,Voto.V25,dataEsame);
		
		carriera.registra(e0);
		assertThrows(IllegalArgumentException.class, () -> carriera.registra(e2));
	}

	@Test
	void testKO_esameGiaSostenutoConEsitoPositivo_Risostenuto() {
		AttivitaFormativa af1 = new AttivitaFormativa("Fondamenti di Informatica T-1", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af2 = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);

		Carriera carriera = new Carriera(Set.of(
				af1,af2
				));

		LocalDate dataEsame = LocalDate.of(2020, 8, 10);
		Esame e1 = new Esame(af1,Voto.V18,dataEsame);
		Esame e2 = new Esame(af1,Voto.V25,dataEsame.plusMonths(1));
		
		carriera.registra(e1);
		assertThrows(IllegalArgumentException.class, () -> carriera.registra(e2));
	}

	@Test
	void testKO_esameGiaSostenutoConEsitoPositivo_PoiRitirato() {
		AttivitaFormativa af1 = new AttivitaFormativa("Fondamenti di Informatica T-1", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af2 = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);

		Carriera carriera = new Carriera(Set.of(
				af1,af2
				));

		LocalDate dataEsame = LocalDate.of(2020, 8, 10);
		Esame e0 = new Esame(af1,Voto.RITIRATO,dataEsame.plusDays(15));
		Esame e1 = new Esame(af1,Voto.V18,dataEsame);
		
		carriera.registra(e1);
		assertThrows(IllegalArgumentException.class, () -> carriera.registra(e0));
	}

	@Test
	void testKO_esameGiaSostenutoConEsitoPositivo_PoiRespinto() {
		AttivitaFormativa af1 = new AttivitaFormativa("Fondamenti di Informatica T-1", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af2 = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);

		Carriera carriera = new Carriera(Set.of(
				af1,af2
				));

		LocalDate dataEsame = LocalDate.of(2020, 8, 10);
		Esame e0 = new Esame(af1,Voto.RESPINTO,dataEsame.plusDays(15));
		Esame e1 = new Esame(af1,Voto.V18,dataEsame);
		
		carriera.registra(e1);
		assertThrows(IllegalArgumentException.class, () -> carriera.registra(e0));
	}

	@Test
	void testKO_esameGiaSostenutoConEsitoPositivo_PoiAssente() {
		AttivitaFormativa af1 = new AttivitaFormativa("Fondamenti di Informatica T-1", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af2 = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);

		Carriera carriera = new Carriera(Set.of(
				af1,af2
				));

		LocalDate dataEsame = LocalDate.of(2020, 8, 10);
		Esame e0 = new Esame(af1,Voto.ASSENTE,dataEsame.plusDays(15));
		Esame e1 = new Esame(af1,Voto.V18,dataEsame);
		
		carriera.registra(e1);
		assertThrows(IllegalArgumentException.class, () -> carriera.registra(e0));
	}

	@Test
	void testKO_AfNonPresenteInCarriera() {
		AttivitaFormativa af1 = new AttivitaFormativa("Fondamenti di Informatica T-1", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af2 = new AttivitaFormativa("Fondamenti di Informatica T-2",Tipologia.B,  Ssd.INGINF05, 12);
		AttivitaFormativa af3 = new AttivitaFormativa("Fisica T", Tipologia.A, Ssd.FIS01, 9);
		
		Carriera carriera = new Carriera(Set.of(
				af1,af2
				));

		LocalDate dataEsame = LocalDate.of(2020, 8, 10);
		Esame e1 = new Esame(af3,Voto.V30L,dataEsame);
		assertThrows(IllegalArgumentException.class, () -> carriera.registra(e1));
	}

	@Test
	void testOK_mediaFraDueConStessoPeso() {
		AttivitaFormativa af1 = new AttivitaFormativa("Fondamenti di Informatica T-1", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af2 = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);

		Carriera carriera = new Carriera(Set.of(
				af1,af2
				));

		LocalDate dataEsame = LocalDate.of(2020, 8, 10);
		Esame e1 = new Esame(af1,Voto.V18,dataEsame);
		Esame e2 = new Esame(af2,Voto.V25,dataEsame);
		
		carriera.registra(e1);
		carriera.registra(e2);
		assertEquals(1, carriera.istanzeDi(af1).size());
		assertEquals(1, carriera.istanzeDi(af2).size());
		assertEquals(21.5,carriera.mediaPesata(), 0.01);
	}

	@Test
	void testOK_mediaFraTreConRitirato() {
		AttivitaFormativa af1 = new AttivitaFormativa("Fondamenti di Informatica T-1", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af2 = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);

		Carriera carriera = new Carriera(Set.of(
				af1,af2
				));

		LocalDate dataEsame = LocalDate.of(2020, 8, 10);
		Esame e0 = new Esame(af1,Voto.RITIRATO,dataEsame.minusMonths(1));
		Esame e1 = new Esame(af1,Voto.V18,dataEsame);
		Esame e2 = new Esame(af2,Voto.V25,dataEsame);
		
		carriera.registra(e0);
		carriera.registra(e1);
		carriera.registra(e2);
		assertEquals(2, carriera.istanzeDi(af1).size());
		assertEquals(1, carriera.istanzeDi(af2).size());
		assertEquals(21.5,carriera.mediaPesata(), 0.01);
	}

	@Test
	void testOK_mediaFraTreConPesiDiversi() {
		AttivitaFormativa af1 = new AttivitaFormativa("Fondamenti di Informatica T-1", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af2 = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af3 = new AttivitaFormativa("Fisica T", Tipologia.A, Ssd.FIS01, 9);

		Carriera carriera = new Carriera(Set.of(
				af1,af2,af3
				));

		LocalDate dataEsame = LocalDate.of(2020, 8, 10);
		Esame e1 = new Esame(af1,Voto.V18,dataEsame);
		Esame e2 = new Esame(af2,Voto.V25,dataEsame);
		Esame e3 = new Esame(af3,Voto.V30,dataEsame.plusMonths(1));
		
		carriera.registra(e3);
		carriera.registra(e1);
		carriera.registra(e2);
		assertEquals(1, carriera.istanzeDi(af1).size());
		assertEquals(1, carriera.istanzeDi(af2).size());
		assertEquals(1, carriera.istanzeDi(af3).size());
		assertEquals(23.82,carriera.mediaPesata(), 0.01);
	}

	@Test
	void testOK_mediaFraDueConPesiDiversi() {
		AttivitaFormativa af1 = new AttivitaFormativa("Fondamenti di Informatica T-1", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af2 = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af3 = new AttivitaFormativa("Fisica T", Tipologia.A, Ssd.FIS01, 9);

		Carriera carriera = new Carriera(Set.of(
				af1,af2,af3
				));

		LocalDate dataEsame = LocalDate.of(2020, 8, 10);
		Esame e2 = new Esame(af2,Voto.V25,dataEsame);
		Esame e3 = new Esame(af3,Voto.V30,dataEsame.plusMonths(1));
		
		carriera.registra(e3);
		carriera.registra(e2);
		assertEquals(1, carriera.istanzeDi(af2).size());
		assertEquals(1, carriera.istanzeDi(af3).size());
		assertEquals(27.14,carriera.mediaPesata(), 0.01);
	}

	@Test
	void testOK_mediaComplessaFraNEsamiConPesiDiversi() {
		AttivitaFormativa af1 = new AttivitaFormativa("Fondamenti di Informatica T-1", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af2 = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af3 = new AttivitaFormativa("Fisica T", Tipologia.A, Ssd.FIS01, 9);
		AttivitaFormativa af4 = new AttivitaFormativa("Reti Logiche T", Tipologia.B, Ssd.INGINF05, 6);

		Carriera carriera = new Carriera(Set.of(
				af1,af2,af3,af4
				));

		LocalDate dataEsame = LocalDate.of(2020, 8, 10);
		Esame e0 = new Esame(af4,Voto.RITIRATO,dataEsame.minusMonths(1));
		Esame e1 = new Esame(af4,Voto.V18,dataEsame);
		Esame e2 = new Esame(af1,Voto.V25,dataEsame);
		Esame e3 = new Esame(af2,Voto.ASSENTE,dataEsame);
		Esame e4 = new Esame(af2,Voto.V30,dataEsame.plusMonths(1));
		Esame e5 = new Esame(af3,Voto.RESPINTO,dataEsame);
		Esame e6 = new Esame(af3,Voto.V22,dataEsame.plusMonths(1));
		
		carriera.registra(e0);
		carriera.registra(e1);
		carriera.registra(e2);
		carriera.registra(e3);
		carriera.registra(e4);
		carriera.registra(e5);
		carriera.registra(e6);

		assertEquals(1, carriera.istanzeDi(af1).size());
		assertEquals(2, carriera.istanzeDi(af2).size());
		assertEquals(2, carriera.istanzeDi(af3).size());
		assertEquals(2, carriera.istanzeDi(af4).size());
		assertEquals(24.77,carriera.mediaPesata(), 0.01);
	}
	
	@Test
	void testOK_toString() {
		AttivitaFormativa af1 = new AttivitaFormativa("Fondamenti di Informatica T-1", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af2 = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);
		AttivitaFormativa af3 = new AttivitaFormativa("Fisica T", Tipologia.A, Ssd.FIS01, 9);
		AttivitaFormativa af4 = new AttivitaFormativa("Reti Logiche T", Tipologia.B, Ssd.INGINF05, 6);

		Carriera carriera = new Carriera(Set.of(
				af1,af2,af3,af4
				));

		LocalDate dataEsame = LocalDate.of(2020, 8, 10);
		Esame e0 = new Esame(af4,Voto.RITIRATO,dataEsame.minusMonths(1));
		Esame e1 = new Esame(af4,Voto.V18,dataEsame);
		Esame e2 = new Esame(af1,Voto.V25,dataEsame);
		Esame e3 = new Esame(af2,Voto.ASSENTE,dataEsame);
		Esame e4 = new Esame(af2,Voto.V30L,dataEsame.plusMonths(1));
		Esame e5 = new Esame(af3,Voto.RESPINTO,dataEsame);
		Esame e6 = new Esame(af3,Voto.V22,dataEsame.plusMonths(1));
		
		carriera.registra(e0);
		carriera.registra(e1);
		carriera.registra(e2);
		carriera.registra(e3);
		carriera.registra(e4);
		carriera.registra(e5);
		carriera.registra(e6);

		String[] output = carriera.toString().split("((\\r?)\\n)+");

		assertTrue(output[0].trim().equalsIgnoreCase("Esami sostenuti:"));
		assertTrue(output[1].contains(af3.getNome()));
		assertTrue(output[2].contains(af3.getNome()));
		assertTrue(output[3].contains(af1.getNome()));
		assertTrue(output[4].contains(af2.getNome()));
		assertTrue(output[5].contains(af2.getNome()));
		assertTrue(output[6].contains(af4.getNome()));
		assertTrue(output[7].contains(af4.getNome()));
		//non controlliamo riga vuota intermedia perché mangiata da split
		assertTrue(output[8].trim().startsWith("Media pesata:"));
		assertTrue(output[8].trim().endsWith("24,77/30"));
		
		assertTrue(output[1].contains("CFU:"));
		assertTrue(output[2].contains("CFU:"));
		assertTrue(output[3].contains("CFU:"));
		assertTrue(output[4].contains("CFU:"));
		assertTrue(output[5].contains("CFU:"));
		assertTrue(output[6].contains("CFU:"));
		assertTrue(output[7].contains("CFU:"));

		assertTrue(output[1].contains(""+af3.getCfu()));
		assertTrue(output[2].contains(""+af3.getCfu()));
		assertTrue(output[3].contains(""+af1.getCfu()));
		assertTrue(output[4].contains(""+af2.getCfu()));
		assertTrue(output[5].contains(""+af2.getCfu()));
		assertTrue(output[6].contains(""+af4.getCfu()));
		assertTrue(output[7].contains(""+af4.getCfu()));

		assertTrue(output[1].contains("Data:"));
		assertTrue(output[2].contains("Data:"));
		assertTrue(output[3].contains("Data:"));
		assertTrue(output[4].contains("Data:"));
		assertTrue(output[5].contains("Data:"));
		assertTrue(output[6].contains("Data:"));
		assertTrue(output[7].contains("Data:"));

		assertTrue(output[1].contains("10/08/20"));
		assertTrue(output[2].contains("10/09/20"));
		assertTrue(output[3].contains("10/08/20"));
		assertTrue(output[4].contains("10/08/20"));
		assertTrue(output[5].contains("10/09/20"));
		assertTrue(output[6].contains("10/07/20"));
		assertTrue(output[7].contains("10/08/20"));

		System.out.println(carriera.toString());

		assertTrue(output[1].contains("Voto:"));
		assertTrue(output[2].contains("Voto:"));
		assertTrue(output[3].contains("Voto:"));
		assertTrue(output[4].contains("Voto:"));
		assertTrue(output[5].contains("Voto:"));
		assertTrue(output[6].contains("Voto:"));
		assertTrue(output[7].contains("Voto:"));

		assertTrue(output[1].contains(e5.getVoto().toString()));
		assertTrue(output[2].contains(e6.getVoto().toString()));
		assertTrue(output[3].contains(e2.getVoto().toString()));
		assertTrue(output[4].contains(e3.getVoto().toString()));
		assertTrue(output[5].contains(e4.getVoto().toString()));
		assertTrue(output[6].contains(e0.getVoto().toString()));
		assertTrue(output[7].contains(e1.getVoto().toString()));
	}

}
