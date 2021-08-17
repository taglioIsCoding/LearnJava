package tris.ui.controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import tris.model.BoardValue;
import tris.model.Match;
import tris.model.MyBoard;
import tris.model.MyMatch;
import tris.persistence.MatchPrinter;
import tris.persistence.MyMatchPrinter;
import tris.persistence.SadPrinterException;

public class MyController implements Controller {
	private String filename;
	private MatchPrinter printer;
	private BoardValue currentPlayer;
	private Match match;
		
	public MyController(String filename) {
		this.filename=filename;
		this.currentPlayer=BoardValue.X;
		this.match=new MyMatch();
		this.printer = new MyMatchPrinter();
	}
	
	public void print() { // saves the (just-terminated) match to the default repo
		try {
			printer.print(new PrintWriter(filename),match);
		} catch (FileNotFoundException | SadPrinterException e) {
			Controller.alert("Errore", "Stampa su file fallita", e.getMessage());
		}
	}
	
	@Override
	public String getCurrentPlayer() {
		return currentPlayer.get();
	}
	
	@Override
	public String getNextPlayer() {
		currentPlayer = currentPlayer.other();
		return getCurrentPlayer();
	}
	
	@Override
	public boolean storeStatus(String status) {
		match.add(new MyBoard(status));
		return match.getCurrentState().winning();
	}
	
	@Override
	public boolean winningStatus() {
		return match.getCurrentState().winning();
	}
	
}
