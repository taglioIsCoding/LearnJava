package frazione;
import util.MyMath;

/**
 * Frazione come tipo di dato astratto (ADT)
 * 
 * @author Fondamenti di Informatica T-2
 * @version Feb 2021
 */
public class Frazione {
	private int num, den;

	/**
	 * Costruttore della Frazione
	 * 
	 * @param num
	 *            Numeratore
	 * @param den
	 *            Denominatore
	 */
	public Frazione(int num, int den) {
		boolean negativo = num * den < 0;
		this.num = negativo ? -Math.abs(num) : Math.abs(num);
		this.den = Math.abs(den);
	}

	/**
	 * Costruttore della Frazione
	 * 
	 * @param num
	 *            Numeratore
	 */
	public Frazione(int num) {
		this(num, 1);
	}

	/**
	 * Recupera il numeratore
	 * 
	 * @return Numeratore della frazione
	 */
	public int getNum() {
		return num;
	}

	/**
	 * Recupera il denominatore
	 * 
	 * @return Denominatore della frazione
	 */
	public int getDen() {
		return den;
	}
	
	public double getDouble() {
		double res = this.num/ (double) this.den;
		return res;
	}

	/**
	 * Calcola la funzione ridotta ai minimi termini.
	 * 
	 * @return Una nuova funzione equivalente all'attuale, ridotta ai minimi
	 *         termini.
	 */
	public Frazione minTerm() {
		if (getNum()==0) return new Frazione(getNum(), getDen());
		int mcd = MyMath.mcd(Math.abs(getNum()), getDen());
		int n = getNum() / mcd;
		int d = getDen() / mcd;
		return new Frazione(n, d);
	}

	
	public boolean equals(Frazione f) {
		return f.getNum() * getDen() == f.getDen() * getNum();
	}
	
	@Override
	public String toString() {
		return getNum() + "/" + getDen();
	}
	
	public int compareTo(Frazione f) {
		if(this.num/this.den > f.getNum()/f.getDen()) {
			return 1;
		} else if(this.num/this.den < f.getNum()/f.getDen()) {
			return -1;
		}else {
			return 0;
		}
	}
	
	public Frazione sum(Frazione f ) {
		int n = num * f.den + den * f.num;
		int d = den * f.den;
		Frazione fSum = new Frazione(n, d);
		return fSum.minTerm();
	}
	
	public Frazione sumWithMcm(Frazione f ) {
		int mcm = MyMath.mcm(this.den, f.getDen());
		int newNum = (mcm/this.den)*this.num + (mcm/f.getDen())*f.getNum();
		return new Frazione(newNum, mcm);
	}
	
	public Frazione sub(Frazione f ) {
		int mcm = MyMath.mcm(this.den, f.getDen());
		int newNum = (mcm/this.den)*this.num - (mcm/f.getDen())*f.getNum();
		return new Frazione(newNum, mcm);
	}
	
	public Frazione mul(Frazione f ) {
		Frazione res = new Frazione(this.num * f.getNum(), this.den * f.getDen());
		return res.minTerm();
	}
	
	public Frazione reciprocal() {
		Frazione recip = new Frazione(this.den, this.num);
		return recip.minTerm();
	}
	
	public Frazione div(Frazione f) {
		return this.mul(f.reciprocal());
	}
	
	public static Frazione sum(Frazione[] tutte) {
		Frazione tot = new Frazione(1, 1);
		for(Frazione frazione: tutte) {
			tot = tot.sum(frazione);
		}
	 return tot.sub(new Frazione(1,1));
	}
	
	public static Frazione mul(Frazione[] tutte) {
		Frazione tot = new Frazione(1 , 1);
		for(Frazione frazione: tutte) {
			tot = tot.mul(frazione);
		}
	 return tot;
	}
	
	public static String convertToString(Frazione[] tutte) {
		String res = "[";
		for (int k=0; k<tutte.length && tutte[k]!= null; k++){
		res += tutte[k].toString() + ", "; }
		res += "]";
		return res;
	}
	
	public static Frazione[] sum(Frazione[] setA, Frazione[] setB) { 
		if (size(setA) != size(setB)) 
			return null; 
		Frazione[] result = new Frazione[size(setB)]; 
		for (int k=0; k<result.length; k++){
			  result[k] = setA[k].sum(setB[k]);
		}
		return result;
	}
	
	public static Frazione[] mul(Frazione[] setA, Frazione[] setB) { 
		if (size(setA) != size(setB)) 
			return null; 
		Frazione[] result = new Frazione[size(setB)]; 
		for (int k=0; k<result.length; k++){
			  result[k] = setA[k].mul(setB[k]);
		}
		return result;
	}
	
	
	public static int size(Frazione[] tutte) {
		int i;
		for(i=0; i< tutte.length ;i++) {
			if(tutte[i]==null) {
				return i;
			}
		}
		return tutte.length;
	}
	
}
