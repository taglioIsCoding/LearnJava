package mastermind.persistence;

import java.io.PrintWriter;

import mastermind.model.Gioco;

public class MyGameSaver implements GameSaver{
	
	private PrintWriter pw;
	
	public MyGameSaver(PrintWriter pw) {
		this.pw = pw;
	}

	@Override
	public void save(Gioco gioco) {
		pw.print(gioco);
		this.close();
	}

	@Override
	public void close() {
		pw.flush();
	}
	
	

}
