package battleship.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;

import battleship.model.ShipBoard;

public class MyBattleshipReader implements BattleshipReader {
	
	private ShipBoard playerBoard;
	private ShipBoard solutionBoard;
	
	public MyBattleshipReader() {
		
	}

	@Override
	public ShipBoard getSolutionBoard(Reader reader) throws BadFileFormatException, IOException {
		//se e' gia stata effettuata una lettura ritorno il dato memorizzato
		if(this.solutionBoard != null) {
			return this.solutionBoard;
		}
		
		//delego la lettura al metodo readBorad passandogli i caratteri accettabili
		this.solutionBoard = readBoard(reader, "<>^vxo~");
		
		return solutionBoard;
	}

	@Override
	public ShipBoard getPlayerBoard(Reader reader) throws BadFileFormatException, IOException {
		//se e' gia stata effettuata una lettura ritorno il dato memorizzato
		if(this.playerBoard != null) {
			return this.playerBoard;
		}
		

		//delego la lettura al metodo readBorad passandogli i caratteri accettabili
		this.playerBoard = readBoard(reader, "<>^vxo#");
		
		return playerBoard;
	}
	
	protected ShipBoard readBoard(Reader reader, String admissibleChars) throws IOException, BadFileFormatException {
		BufferedReader rdr = new BufferedReader(reader);
		
		StringBuilder sBuilder = new StringBuilder();
		String line;
		ShipBoard toSendBoard;
		
		int lines = 0;
		while((line = rdr.readLine())!= null) {
			
			//uso stringtokenizer, si potrebbe anche unare line.split()
			StringTokenizer stk = new StringTokenizer(line, " ");
			if(stk.countTokens() != 8) {
				throw new BadFileFormatException("Wrong number of elemnts");
			}
			
			//controllo la correttezza dei caratteri
			for(int i = 0; i < 8; i++) {
				String elemntString = stk.nextToken();
				if(!admissibleChars.contains(elemntString)) {
					throw new BadFileFormatException("Wrong char");
				}
			}
			
			//aggiungo la linea appena letta allo stringbuilder
			sBuilder.append(line+"\n");
			lines++;
			
		}
		
		//controllo l'eventuale mancanza di righe tramite la variabile ausiliaria lines
		if(lines != 8) {
			throw new BadFileFormatException("Wrong number of lines");
		}else {
			toSendBoard = new ShipBoard(sBuilder.toString());
		}
		
		//ritorno la ShipBoard
		return toSendBoard;
	}

}
