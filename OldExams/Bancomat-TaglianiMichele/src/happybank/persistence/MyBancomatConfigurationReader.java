package happybank.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import happybank.model.AbstractBancomat;
import happybank.model.TaglioBanconota;

public class MyBancomatConfigurationReader implements BancomatConfigurationReader{

	@Override
	public void configura(Reader reader, AbstractBancomat bancomat) throws IOException, BadFileFormatException {
		if(reader == null) {
			throw new IOException("Reader Null");
		}
		BufferedReader rdr= new BufferedReader(reader);
		
		String line = rdr.readLine().trim();
		if (line == null) {
			throw new BadFileFormatException("Linea Vuota");
		}
		
		String[] prod;
		try {
			prod = line.split("\\+");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BadFileFormatException("Errore split");
		}
		
		
		if(prod.length != 2 || prod == null) {
			throw new BadFileFormatException("Termini non suff");
		}
		
		String piccoloString = prod[0].trim();
		String grandeString = prod[1].trim();
		
		int nPiccolo;
		int valoreP;
		int nGrande;
		int valoreG;
		
		if(piccoloString.split("x").length != 2 || grandeString.split("x").length != 2) {
			throw new BadFileFormatException("1");
		}
		
		System.out.println(piccoloString+" "+grandeString);
		
		try {
			valoreP = Integer.parseInt(piccoloString.split("x")[0].trim());
		} catch (Exception e) {
			throw new BadFileFormatException("1");
		}
		try {
			nPiccolo= Integer.parseInt(piccoloString.split("x")[1].trim());
		} catch (Exception e) {
			throw new BadFileFormatException("2");
		}
		try {
			valoreG = Integer.parseInt(grandeString.split("x")[0].trim());
		} catch (Exception e) {
			throw new BadFileFormatException("3");
		}
		try {
			nGrande = Integer.parseInt(grandeString.split("x")[1].trim());
		} catch (Exception e) {
			throw new BadFileFormatException("4");
		}
		
		TaglioBanconota piccolo = null, grande = null;

        for (TaglioBanconota t : TaglioBanconota.values()) {
            if (t.getValore() == valoreP)
                piccolo = t;
            if (t.getValore() == valoreG)
                grande = t;
        }

        if(piccolo == null || grande == null) {
            throw new BadFileFormatException("Tagli non validi");
        }
		
		bancomat.caricaBanconote(piccolo, nPiccolo, grande,nGrande);
		
		
	}

}
