package minesweeper.model;

import java.util.Random;

public class RealMineField extends MineField{

	private int mines;
	
	public RealMineField(int size) {
		super(size);
		this.mines = 10;
		this.init();
	}
	
	public RealMineField(int size, int mines) {
		super(size);
		this.mines = mines;
		this.init();
	}
	
	public int getMinesNumber() {
		return mines;
	}

	@Override
	protected void init() {
		Random random = new Random();
		
		for(int i = 1; i <= mines; i++) {
			int x = random.nextInt(this.getSize());
			int y = random.nextInt(this.getSize());
			
			if(this.getCell(x, y) == null) {
				System.out.println("Messa mina in" + x+" "+y);
				this.setCell(x, y, new Cell(CellType.MINE));
			}else {
				i--;
			}
		}
		
		System.out.println("STAMPO IL TUO SOLOMINE");
		System.out.println(this.toString());
		
		for(int i = 0; i < this.getSize()*this.getSize(); i++) {
			if(this.getCell(i/this.getSize(), i%this.getSize()) != null) {
				continue;
			}else {
				this.setCell(i/this.getSize(), i%this.getSize(),
					new Cell(CellType.NUM, this.calcAdjacentMines(i/this.getSize(), i%this.getSize())));
			}
		}
		System.out.println("STAMPO IL TUO CAMPO");
		System.out.println(this.toString());
	}
	
	private int calcAdjacentMines(int row, int col) {
		int nMines = 0;
		
		for(int dx = -1; dx <= 1; dx++) {
			for(int dy = -1; dy <= 1; dy++) {
				if(row + dx < 0 || col + dy < 0
						|| row + dx > this.getSize()-1 || col + dy > this.getSize()-1
						|| this.getCell(row + dx, col + dy) == null
						|| (dx == 0 && dy == 0)) {
					continue;
				}else {
					if(this.getCell(row + dx, col + dy).getType().equals(CellType.MINE)) {
						nMines++;
					}
				}
			}
		}
		
		return nMines;
	}

}
