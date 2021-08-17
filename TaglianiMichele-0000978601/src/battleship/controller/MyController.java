package battleship.controller;

import battleship.model.ShipBoard;
import battleship.model.ShipItem;

public class MyController extends AbstractController{

	public MyController(ShipBoard solutionBoard, ShipBoard playerBoard) {
		super(solutionBoard, playerBoard);
	}
	
	@Override
	public int verify() {
		int result = 0;
		int emptyCell = 0;
		for(int i = 0; i < this.getSize(); i++) {
			for(int j = 0; j < this.getSize(); j++) {
				if(this.getCurrentCellItem(i, j) == ShipItem.EMPTY) {
					emptyCell++;
				}else {
					if(this.getCurrentCellItem(i, j) != this.getSolutionCellItem(i, j)) {
						result++;
					}
					
				}	
			}
		}
		
		if(result == 0 && emptyCell == 0) {
			this.gameOver = true;
		}
			
		return result;
	}

}
