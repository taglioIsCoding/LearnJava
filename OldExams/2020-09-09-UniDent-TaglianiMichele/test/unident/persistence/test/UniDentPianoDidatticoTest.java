package unident.persistence.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.Set;

import org.junit.jupiter.api.Test;

import unident.model.AttivitaFormativa;
import unident.persistence.BadFileFormatException;
import unident.persistence.UniDentPianoDidatticoReader;

public class UniDentPianoDidatticoTest {

	@Test
	void testOK() throws IOException {
		StringReader fakeReader = new StringReader(
				"27991	ANALISI MATEMATICA T-1			1	A	Mat/05	9\r\n" + 
				"28004	FONDAMENTI DI INFORMATICA T-1	1	A	Ing-Inf/05	12\r\n" + 
				"29228	GEOMETRIA E ALGEBRA T			1	A	Mat/03	6\r\n" + 
				"26337	IDONEITA' LINGUA INGLESE B - 2	1	E	6\r\n" + 
				"27993	ANALISI MATEMATICA T-2			2	A	Mat/05	6\r\n" + 
				"28006	FONDAMENTI DI INFORMATICA T-2	2	A	Ing-Inf/05	12\r\n" + 
				"28011	RETI LOGICHE T					2	B	Ing-Inf/05	6\r\n" + 
				"\r\n" + 
				"28012	CALCOLATORI ELETTRONICI T	1	B	Ing-Inf/05	6\r\n" + 
				"30780	FISICA GENERALE T			1	A	Fis/01	9\r\n" + 
				"28032	MATEMATICA APPLICATA T		1	C	Mat/07	6\r\n" + 
				"28027	SISTEMI INFORMATIVI T		1	B	Ing-Inf/05	9\r\n" + 
				"28030	ECONOMIA E ORGANIZZAZIONE AZIENDALE T	2	C	Ing-Ind/35	6\r\n" + 
				"28029	ELETTROTECNICA T					2	C	Ing-Ind/31	6\r\n" + 
				"28014	FONDAMENTI DI TELECOMUNICAZIONI T	2	B	Ing-Inf/03	9\r\n" + 
				"28020	SISTEMI OPERATIVI T					2	B	Ing-Inf/05	9\r\n" + 
				"\r\n" + 
				"28015	CONTROLLI AUTOMATICI T		1	B	Ing-Inf/04	9\r\n" + 
				"28016	ELETTRONICA T				1	B	Ing-Inf/01	6\r\n" + 
				"28024	RETI DI CALCOLATORI T		1	B	Ing-Inf/05	9\r\n" + 
				"28659	TECNOLOGIE WEB T			1	B	Ing-Inf/05	9\r\n" + 
				"28021	INGEGNERIA DEL SOFTWARE T	2	B	Ing-Inf/05	9\r\n" + 
				"17268	PROVA FINALE				O	E	3\r\n" + 
				"\r\n" + 
				"28072	LABORATORIO DI AMMINISTRAZIONE DI SISTEMI T		2	F	6\r\n" + 
				"28074	TIROCINIO T										2	F	6\r\n" + 
				"\r\n" + 
				"38378	AFFIDABILITÀ E CONTROLLO DELLA QUALITÀ T		2	D	Ing-Inf/07	6\r\n" + 
				"88326	AMMINISTRAZIONE DI SISTEMI E SICUREZZA INFORMATICA T C.I.	2	D	Ing-Inf/05	12		\r\n" + 
				"88324	AMMINISTRAZIONE DI SISTEMI T				2	D	Ing-Inf/05	6\r\n" + 
				"88325	LABORATORIO DI SICUREZZA INFORMATICA T		2	D	Ing-Inf/05	6\r\n" + 
				"32099	DIRITTO DELL'INFORMATICA T					2	D	Ius/20		6\r\n" + 
				"94442	PROGETTAZIONE DI APPLICAZIONI WEB T			2	D	Ing-Inf/05	6"
				);
		
		Set<AttivitaFormativa> piano = new UniDentPianoDidatticoReader().readAll(fakeReader);
		System.out.println(piano);
		assertEquals(29,piano.size());
		//Collections.sort(piano, Comparator.comparing(AttivitaFormativa::getNome));
		//System.out.println(piano);
	}

	@Test
	void testKO_noCodiceAF() throws IOException {
		StringReader fakeReader = new StringReader(
				"ANALISI MATEMATICA T-1			1	A	Mat/05	9\r\n" + 
				"28004	FONDAMENTI DI INFORMATICA T-1	1	A	Ing-Inf/05	12\r\n" + 
				"\r\n" + 
				"28012	CALCOLATORI ELETTRONICI T	1	B	Ing-Inf/05	6\r\n" 
				);
		assertThrows(BadFileFormatException.class, () -> new UniDentPianoDidatticoReader().readAll(fakeReader));
	}
	
	@Test
	void testKO_noTabBeforeNameAF() throws IOException {
		StringReader fakeReader = new StringReader(
				"27991 ANALISI MATEMATICA T-1			1	A	Mat/05	9\r\n" + 
				"28004	FONDAMENTI DI INFORMATICA T-1	1	A	Ing-Inf/05	12\r\n" + 
				"\r\n" + 
				"28012	CALCOLATORI ELETTRONICI T	1	B	Ing-Inf/05	6\r\n" 
				);
		assertThrows(BadFileFormatException.class, () -> new UniDentPianoDidatticoReader().readAll(fakeReader));
	}

	@Test
	void testKO_noNameAF() throws IOException {
		StringReader fakeReader = new StringReader(
				"27991	1	A	Mat/05	9\r\n" + 
				"28004	FONDAMENTI DI INFORMATICA T-1	1	A	Ing-Inf/05	12\r\n" + 
				"\r\n" + 
				"28012	CALCOLATORI ELETTRONICI T	1	B	Ing-Inf/05	6\r\n" 
				);
		assertThrows(BadFileFormatException.class, () -> new UniDentPianoDidatticoReader().readAll(fakeReader));
	}

	@Test
	void testKO_noPeriodo() throws IOException {
		StringReader fakeReader = new StringReader(
				"27991	ANALISI MATEMATICA T-1	A	Mat/05	9\r\n" + 
				"28004	FONDAMENTI DI INFORMATICA T-1	1	A	Ing-Inf/05	12\r\n" + 
				"\r\n" + 
				"28012	CALCOLATORI ELETTRONICI T	1	B	Ing-Inf/05	6\r\n" 
				);
		assertThrows(BadFileFormatException.class, () -> new UniDentPianoDidatticoReader().readAll(fakeReader));
	}

	@Test
	void testKO_noTipologia() throws IOException {
		StringReader fakeReader = new StringReader(
				"27991	ANALISI MATEMATICA T-1	1 Mat/05	9\r\n" + 
				"28004	FONDAMENTI DI INFORMATICA T-1	1	A	Ing-Inf/05	12\r\n" + 
				"\r\n" + 
				"28012	CALCOLATORI ELETTRONICI T	1	B	Ing-Inf/05	6\r\n" 
				);
		assertThrows(BadFileFormatException.class, () -> new UniDentPianoDidatticoReader().readAll(fakeReader));
	}

	@Test
	void testKO_badTipologia() throws IOException {
		StringReader fakeReader = new StringReader(
				"27991	ANALISI MATEMATICA T-1	1 X	Mat/05	9\r\n" + 
				"28004	FONDAMENTI DI INFORMATICA T-1	1	A	Ing-Inf/05	12\r\n" + 
				"\r\n" + 
				"28012	CALCOLATORI ELETTRONICI T	1	B	Ing-Inf/05	6\r\n" 
				);
		assertThrows(BadFileFormatException.class, () -> new UniDentPianoDidatticoReader().readAll(fakeReader));
	}

	@Test
	void testKO_noSsd() throws IOException {
		StringReader fakeReader = new StringReader(
				"27991	ANALISI MATEMATICA T-1	1 A	9\r\n" + 
				"28004	FONDAMENTI DI INFORMATICA T-1	1	A	Ing-Inf/05	12\r\n" + 
				"\r\n" + 
				"28012	CALCOLATORI ELETTRONICI T	1	B	Ing-Inf/05	6\r\n" 
				);
		assertThrows(BadFileFormatException.class, () -> new UniDentPianoDidatticoReader().readAll(fakeReader));
	}

	@Test
	void testKO_badSsd() throws IOException {
		StringReader fakeReader = new StringReader(
				"27991	ANALISI MATEMATICA T-1	1 A	Puppy/00	9\r\n" + 
				"28004	FONDAMENTI DI INFORMATICA T-1	1	A	Ing-Inf/05	12\r\n" + 
				"\r\n" + 
				"28012	CALCOLATORI ELETTRONICI T	1	B	Ing-Inf/05	6\r\n" 
				);
		assertThrows(BadFileFormatException.class, () -> new UniDentPianoDidatticoReader().readAll(fakeReader));
	}

	@Test
	void testKO_noCfu() throws IOException {
		StringReader fakeReader = new StringReader(
				"28004	FONDAMENTI DI INFORMATICA T-1	1	A	Ing-Inf/05\r\n" + 
				"\r\n" + 
				"28012	CALCOLATORI ELETTRONICI T	1	B	Ing-Inf/05	6\r\n" 
				);
		assertThrows(BadFileFormatException.class, () -> new UniDentPianoDidatticoReader().readAll(fakeReader));
	}

	@Test
	void testKO_badCfuXyz() throws IOException {
		StringReader fakeReader = new StringReader(
				"28004	FONDAMENTI DI INFORMATICA T-1	1	A	Ing-Inf/05	xyz\r\n" + 
				"\r\n" + 
				"28012	CALCOLATORI ELETTRONICI T	1	B	Ing-Inf/05	6\r\n" 
				);
		assertThrows(BadFileFormatException.class, () -> new UniDentPianoDidatticoReader().readAll(fakeReader));
	}

	@Test
	void testKO_badCfu6yz() throws IOException {
		StringReader fakeReader = new StringReader(
				"28004	FONDAMENTI DI INFORMATICA T-1	1	A	Ing-Inf/05	6yz\r\n" + 
				"\r\n" + 
				"28012	CALCOLATORI ELETTRONICI T	1	B	Ing-Inf/05	6\r\n" 
				);
		assertThrows(BadFileFormatException.class, () -> new UniDentPianoDidatticoReader().readAll(fakeReader));
	}

	@Test 
	void testKO_duplicateAF() throws IOException {
	StringReader fakeReader = new StringReader(
	"28004 FONDAMENTI DI INFORMATICA T-1 1 A Ing-Inf/05 6\r\n" + 
	"\r\n" + 
	"28004 FONDAMENTI DI INFORMATICA T-1 1 A Ing-Inf/05 6\r\n"
	);
	assertThrows(BadFileFormatException.class, () -> new UniDentPianoDidatticoReader().readAll(fakeReader));
	}

}
