package mastermind.persistence;

import mastermind.model.Gioco;

public interface GameSaver {
	void save(Gioco gioco);
	void close();
}
