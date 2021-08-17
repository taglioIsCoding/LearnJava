package tris.model;

import java.util.List;

public interface Match {
	public Board getCurrentState();
	public Board getState(int n);
	public List<Board> getHistory();
	public void add(Board board);
	public String toString();
}
