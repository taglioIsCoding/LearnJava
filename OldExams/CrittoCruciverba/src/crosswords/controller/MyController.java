package crosswords.controller;


import crosswords.model.Game;
import crosswords.model.MyGame;
import crosswords.model.Scheme;


public class MyController implements Controller {
	private MyGame game;

	

	
	public MyController(int size) {
		this.game=new MyGame(size);
	}

	public MyController(Scheme s)
	{
		this.game= new MyGame(s.getSize());
		for (int i = 0;i<s.getSize();i++)
			game.setCellRow(i,s.getCellRow(i));
				
	}

	@Override
	public int getSize(){
		return game.getSize();
	}
	
	@Override
	public Game getGame() {
		return game;
	}


	@Override
	 public	void updateMapping(int numericalValue, String letter)
	{
		game.setMapping(numericalValue, letter);
	}
	
}
