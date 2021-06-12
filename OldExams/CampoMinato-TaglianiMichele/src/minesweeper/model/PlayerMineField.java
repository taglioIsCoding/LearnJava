package minesweeper.model;


public class PlayerMineField extends MineField{

	private int hiddenCellsNumber;
	
	public PlayerMineField(int size) {
		super(size);
		this.init();
	}

	@Override
	protected void init() {
		int dim = this.getSize()*this.getSize();
		this.hiddenCellsNumber = dim;
		for(int i = 0; i < dim; i++) {
			super.setCell(i /this.getSize(), i%this.getSize(), new Cell(CellType.HIDDEN));
		}
		
	}
	
	protected void setCell(int row, int col, Cell cell ) {
		if(cell.getType().equals(CellType.HIDDEN)) {
			throw new UnsupportedOperationException("Non sostituire hidden con hidden");
		}else {
			super.setCell(row, col, cell);
			this.hiddenCellsNumber--;
		}
	}
	
	public int getHiddenCellsNumber() {
		return hiddenCellsNumber;
	}

}
