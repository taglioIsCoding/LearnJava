package cupidonline.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.StringTokenizer;

import org.junit.jupiter.params.provider.NullEnum;

import cupidonline.model.Colore;
import cupidonline.model.Persona;
import cupidonline.model.Preferenza;
import cupidonline.model.SegnoZodiacale;
import cupidonline.model.Sesso;

public class MyIscrittiReader implements IscrittiReader{
	
	public Map<String, Persona> caricaIscritti(Reader reader) throws IOException, BadFileFormatException{
		
		Map<String, Persona> mappaIscritti = new HashMap<>();
		BufferedReader fin = null;
		String line;
		try {
			fin = new BufferedReader(reader);

			while ((line = fin.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				String id = tokenizer.nextToken().trim();
				Persona p = leggiIscritto(tokenizer, id);
				mappaIscritti.put(id, p);
			}
			fin.close();
		} catch (IOException | NoSuchElementException | NumberFormatException e) {
			throw new BadFileFormatException(e);
		}
		return mappaIscritti;
	}
	
	private Persona leggiIscritto(StringTokenizer tokenizer, String id) throws BadFileFormatException {
				// F, 20-25, Bilancia, capelli biondi, occhi -, 1.70, 58, Bologna, BO,
				// Emilia-Romagna
				String sSesso = tokenizer.nextToken().trim();

				Sesso sesso = null;
				try {
					sesso = Sesso.valueOfChar(sSesso);
				} catch (IllegalArgumentException e) {
					throw new BadFileFormatException("sesso inesistente: " + sSesso);
				}

				String sEta = tokenizer.nextToken().trim();
				String[] rangeEta = null;
				LocalDate dataNascita;
				try {
					dataNascita = LocalDate.parse(sEta);
				} catch (Exception e) {
					throw new BadFileFormatException("eta errato: " + sEta);
				}


				String[] chioma = tokenizer.nextToken().trim().split("\\s+");
				if (!chioma[0].equalsIgnoreCase("CAPELLI"))
					throw new BadFileFormatException("manca indicazione CAPELLI");
				Optional<Colore> capelli = null;
				try {
					capelli = Optional.of(Colore.valueOf(chioma[1].toUpperCase()));
				} catch (IllegalArgumentException e) {
					capelli = Optional.empty();
				}

				String[] sguardo = tokenizer.nextToken().trim().split("\\s+");
				if (!sguardo[0].equalsIgnoreCase("OCCHI"))
					throw new BadFileFormatException("manca indicazione OCCHI");
				Optional<Colore> occhi = null;
				try {
					occhi = Optional.of(Colore.valueOf(sguardo[1].toUpperCase()));
				} catch (IllegalArgumentException e) {
					occhi = Optional.empty();
				}

				String numAltezza = tokenizer.nextToken().trim();
				Optional<Float> altezza;
				try {
					altezza = Optional.of(Float.parseFloat(numAltezza + "F"));
				} catch (NumberFormatException e) {
					altezza = Optional.empty();
				}

				String numPeso = tokenizer.nextToken().trim();
				OptionalInt peso;
				try {
					peso = OptionalInt.of(Integer.parseInt(numPeso));
				} catch (NumberFormatException e) {
					peso = OptionalInt.empty();
				}
				
				SegnoZodiacale segnoPersona = SegnoZodiacale.ACQUARIO;
				for(SegnoZodiacale segno: SegnoZodiacale.values()) {
					if(segno.contains(dataNascita)) {
						segnoPersona = segno;
					}
				}

				String citta = tokenizer.nextToken().trim();
				String provincia = tokenizer.nextToken().trim();
				String regione = tokenizer.nextToken().trim();

				return new Persona(id, sesso, dataNascita, segnoPersona, capelli.get(), occhi.get(), altezza.get(), peso.getAsInt(), citta, provincia, regione);
			
	}
}
