package frazlib;

import frazione.Frazione;

public class FrazLib {
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
}
