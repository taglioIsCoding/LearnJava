package crosswords.controller;


import crosswords.model.Game;



public interface Controller {
	int getSize();
	Game getGame();
	void updateMapping(int numericalValue, String letter);
}
