package tris.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MyMatch implements Match{
	
	private LocalDateTime MatchID;
	private List<Board> states;
	
	public MyMatch() {
		states = new ArrayList<>();
		Board initBoard = new MyBoard("         ");
		this.states.add(initBoard);
	}
	
	public MyMatch(LocalDateTime dateTime) {
		this.MatchID = dateTime;
		states = new ArrayList<>();
		Board initBoard = new MyBoard("         ");
		this.states.add(initBoard);
	}
	
	@Override
	public Board getCurrentState() {
		return this.states.get(this.states.size()-1);
	}

	@Override
	public Board getState(int n) {
		if(this.states.size()-1 < n) {
			throw new IllegalArgumentException("Index error");
		}
		
		
		return this.states.get(n);
	}

	@Override
	public List<Board> getHistory() {
		return this.states;
	}

	@Override
	public void add(Board board) {
		if(this.getCurrentState().adjacent(board)) {
			this.states.add(board);	
		}else {
			throw new IllegalArgumentException("Non adiacente");
		}
	}

}
