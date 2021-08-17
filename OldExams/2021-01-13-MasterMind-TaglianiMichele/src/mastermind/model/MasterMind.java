package mastermind.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MasterMind extends Gioco{
	
	public MasterMind(int MaxTentativi , int dim) {
		super(MaxTentativi, dim);
	}

	@Override
	protected void sorteggiaCombinazione(Combinazione segreta) {
		PioloDiGioco arrayPioli[] = PioloDiGioco.values();
		Random random = new Random();
		
		for (int i = 0; i < dimensione(); i++) {
			PioloDiGioco pioloDiGioco = arrayPioli[random.nextInt(arrayPioli.length)];
			while (pioloDiGioco.name().equals("VUOTO")) {
				 pioloDiGioco = arrayPioli[random.nextInt(arrayPioli.length)];
			}
			
			segreta.setPiolo(i,pioloDiGioco );
		}
		
		
	}

	@Override
	protected Risposta calcolaRisposta(Combinazione tentativo) {
		Risposta risposta = new Risposta(dimensione());
		int neri = 0;
		int bianchi = 0;
		int rispostaLen = 0;
		//PioloDiGioco arrayPioli[] = new PioloDiGioco[segreta.dim()];
		System.out.println("-------------------------");
		
		ArrayList<PioloDiGioco> arrayPioliSegreti = new ArrayList<>();
		ArrayList<PioloDiGioco> arrayPioliTentativo = new ArrayList<>();
		
		
		for (int i = 0; i < segreta.dim(); i++) {
			arrayPioliSegreti.add(segreta.getPiolo(i));
			arrayPioliTentativo.add(tentativo.getPiolo(i));
		}
		
		System.out.println(arrayPioliSegreti);
		System.out.println(arrayPioliTentativo);
		
		for(int i = 0; i < tentativo.dim(); i++) {
			if(tentativo.getPiolo(i).name().equals(segreta.getPiolo(i).name())) {
				arrayPioliSegreti.remove(i-rispostaLen);
				arrayPioliTentativo.remove(i-rispostaLen);
				System.out.println("Nero "+ tentativo.getPiolo(i).name());
				System.out.println(arrayPioliSegreti);
				System.out.println(arrayPioliTentativo);
				risposta.setPiolo(rispostaLen, PioloRisposta.NERO);
				rispostaLen++;
				
			}else {
				System.out.println("Falso "+ tentativo.getPiolo(i).name());
			}
		}
		
		
		for(PioloDiGioco pioloSegreto : arrayPioliSegreti) {
			if(arrayPioliTentativo.contains(pioloSegreto)) {
				risposta.setPiolo(rispostaLen, PioloRisposta.BIANCO);
				rispostaLen++;
				arrayPioliTentativo.remove(pioloSegreto);
			}
		}
		
		return risposta;
	}

}
