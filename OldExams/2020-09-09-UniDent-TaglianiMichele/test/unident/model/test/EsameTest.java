package unident.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import unident.model.AttivitaFormativa;
import unident.model.Esame;
import unident.model.Ssd;
import unident.model.Tipologia;
import unident.model.Voto;

class EsameTest {

	@Test
	void testOK_esameStandard() {
		AttivitaFormativa af = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);
		LocalDate dataEsame = LocalDate.of(2020, 8, 10);
		Esame e = new Esame(af,Voto.V18,dataEsame);
		assertEquals(af, e.getAf());
		assertEquals(18, e.getVoto().getValue().getAsInt());
		assertEquals(dataEsame, e.getDate());
	}
	
	@Test
	void testKO_noAf() {
		LocalDate dataEsame = LocalDate.of(2020, 8, 10);
		assertThrows(IllegalArgumentException.class, () -> new Esame(null,Voto.V18,dataEsame));
	}

	@Test
	void testKO_noVoto() {
		AttivitaFormativa af = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);
		LocalDate dataEsame = LocalDate.of(2020, 8, 10);
		assertThrows(IllegalArgumentException.class, () -> new Esame(af,null,dataEsame));
	}

	@Test
	void testKO_noData() {
		AttivitaFormativa af = new AttivitaFormativa("Fondamenti di Informatica T-2", Tipologia.B, Ssd.INGINF05, 12);
		assertThrows(IllegalArgumentException.class, () -> new Esame(af, Voto.V19, null));
	}
}
