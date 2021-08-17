package ghigliottina.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import ghigliottina.model.Esatta;
import ghigliottina.model.Ghigliottina;
import ghigliottina.model.Terna;

public class MyGhigliottineReader implements GhigliottineReader{

	private List<Ghigliottina> gigliottine;
	
	public MyGhigliottineReader() {
		this.gigliottine = new ArrayList<>();
	}
	
	
	
	@Override
	public List<Ghigliottina> getGhigliottine() {
		return this.gigliottine;
	}

	@Override
	public List<Ghigliottina> readAll(BufferedReader reader) throws IOException, BadFileFormatException {
		
		Ghigliottina toAddGhigliottina;
		while((toAddGhigliottina = this.parseOne(reader)) != null) {
			
				this.gigliottine.add(toAddGhigliottina);
			
		}
		
		return this.getGhigliottine();
	}

	private Ghigliottina parseOne(BufferedReader reader) throws IOException, BadFileFormatException {
		
		String line;
		ArrayList<Terna> terne= new ArrayList<>();
		String rispostaEsattaString = "";
		boolean lettoTutto = false;
		Ghigliottina toSend;
		while((line = reader.readLine())!= null) {
			
			if(lettoTutto == true && !line.contains("------")) {
				throw new BadFileFormatException("trattini mancanti");
			}
			
			if(line.contains("------")) {
				//System.out.println(terne);
				//System.out.println(rispostaEsattaString);
				try {
					toSend = new Ghigliottina(terne, rispostaEsattaString);
				} catch (Exception e) {
					throw new BadFileFormatException("Errore di lettura");
				}
				return toSend;
			}
			
			
			StringTokenizer sTokenizer = new StringTokenizer(line, "/=");
			if(sTokenizer.countTokens() > 3 || sTokenizer.countTokens() < 2) {
				throw new BadFileFormatException("Non abbastanza elementi");
			}
			
			if(sTokenizer.countTokens() == 3) {
				String parola1 = sTokenizer.nextToken().trim();
				String parola2 = sTokenizer.nextToken().trim();
				Esatta rightEsatta ;
				try {
					rightEsatta = Esatta.valueOf(sTokenizer.nextToken().trim());
				} catch (Exception e) {
					throw new BadFileFormatException("Non so la parola corretta");
				}
				
				terne.add(new Terna(parola1, parola2, rightEsatta));
				
			}else {
				String rispostaEsatta = sTokenizer.nextToken().trim();
				if(!rispostaEsatta.equals("Risposta esatta")) {
					throw new BadFileFormatException("Parola chiave non valida");
				}
				String parolachiave = sTokenizer.nextToken().trim();
				if(parolachiave == null || parolachiave.isEmpty()) {
					throw new BadFileFormatException("risposta non valida");
				}
				
				lettoTutto = true;
				rispostaEsattaString = parolachiave;
			}
			
		}
		
		if(lettoTutto == true) {
			throw new BadFileFormatException("trattini mancanti");
		}
		return null;
	}
	
}
