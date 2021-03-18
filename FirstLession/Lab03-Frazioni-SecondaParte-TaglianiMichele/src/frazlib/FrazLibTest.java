package frazlib;

import frazione.Frazione;

public class FrazLibTest {

	public static void main(String[] args) {
		Frazione tutte[] = new Frazione[4];
		Frazione sumTot = new Frazione(0,0);
		Frazione mulTot = new Frazione(0,0);
		
		tutte[0] = new Frazione(1,2);
		tutte[1] = new Frazione(3,2);
		tutte[2] = new Frazione(6,2);
		tutte[3] = new Frazione(7,2);
		
		sumTot = FrazLib.sum(tutte);
		mulTot = FrazLib.mul(tutte);
		
		System.out.println(sumTot);
		System.out.println(mulTot);
	}

}
