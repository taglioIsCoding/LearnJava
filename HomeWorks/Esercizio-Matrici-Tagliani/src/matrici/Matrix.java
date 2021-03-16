package matrici;

public class Matrix {
	
	private double[][] values;
	
	public Matrix(double [][] values) {
		this.values = values;
	}
	
	private Matrix(int rows, int cols) {
		this.values = new double[rows][cols];
	}
	
	public int getRows(){
		return this.values.length;
	}
	
	public int getCols() {
		return this.values[0].length;
	}
	
	public double getValue(int row, int col) {
		return this.values[row][col];
	}
	
	private void setValue(int row, int col, double value) {
		this.values[row][col] = value; 
	}
	
	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i < this.getRows(); i++) {
			for (int j=0; j < this.getCols(); j++) {
				s = s + Double.toString(getValue(i, j)) + "\t";
			}
			s = s + "\n";
		}
		return s;
	}
	
	public boolean isSquared() {
		return getRows() == getCols() ? true:false; 
	}
	
	public Matrix sum(Matrix m) {
		if((this.getRows() == m.getRows()) && (this.getCols() == m.getCols())) {
			Matrix c = new Matrix(this.getRows(), this.getCols());
			for (int i=0; i < this.getRows(); i++) {
				for (int j=0; j < this.getCols(); j++) {
					c.setValue(i, j, this.getValue(i, j) + m.getValue(i, j));
				}
			}
			return c;	
		}
		return null;
	}
	
	public  Matrix mul(Matrix m){
		if(this.getCols() == m.getRows()) {
			Matrix c = new Matrix(this.getRows(), m.getCols());
			for(int i = 0; i < this.getRows(); i++) {
				for (int j=0; j < m.getCols(); j++) {
					for (int k = 0; k < this.getCols(); k++) {
	                    c.values[i][j] += this.getValue(i, k) * m.getValue(k, j);
	            }
			}
		}
			return c;
		}else {
			return null;
		}
	}
	
	public Matrix extractMinor(int row, int col) {
//		
		int newRow = 0;
		int newCol = 0;
		int j;
		Matrix c = new Matrix(this.getRows()-1, this.getCols()-1);
		if(this.isSquared()) {
			for(int i =0; i < this.getRows(); i++) {
				for( j = 0; j< this.getCols(); j++) {
					if(i == row || j == col) {
					}else {
						c.setValue(newRow, newCol, this.getValue(i, j));
						if(c.getCols() -1 == newCol ) {
							newRow++;
							newCol = 0;
						}else {
							newCol++;
						}
					}
				}
			  j=0;
			}
			return c;
		}
		return null;
		
	}
	
	public double det() {
		return this.isSquared() ? calcDet(): Double.NaN;
	}
	
	private double calcDet() {
		double detTot = 0;
		if (this.getCols()==1 && this.getRows() == 1) {
			return this.getValue(0, 0);
		}
		int j = 0;
		for(int i =0; i < this.getRows(); i++) {
				detTot += (this.getValue(i, j) * Math.pow(-1, (double) i+j) * this.extractMinor(i, j).calcDet());
		}
		return detTot;
	}
}
