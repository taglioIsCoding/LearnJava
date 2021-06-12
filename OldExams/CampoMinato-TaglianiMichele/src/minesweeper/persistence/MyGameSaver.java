package minesweeper.persistence;

import java.io.PrintWriter;

import minesweeper.model.Game;

public class MyGameSaver implements GameSaver{
	
	private PrintWriter pw;
	
	public MyGameSaver(PrintWriter pw) {
		this.pw = pw;
	}

	@Override
	public void print(Game match) {
		pw.print(match.toString());
		pw.flush();
	}

	@Override
	public void close() {
		this.pw.close();
	}

}
