/**
 * Classe di test
 * @author Fondamenti di Informatica T-2
 * @version Feb 2021
 */
public class MainFrazione
{
	private static String getAsString(Frazione f) {
		String str = "";
		int num = f.getNum();
		int den = f.getDen();

		str += f.getDen() == 1 ? num : num + "/" + den;
		
		return str;
	}
	
	public static void main(String[] args) {
		
		
		//test costruzione frazione
		//test funzionamento metodi accessor e toString
		Frazione frazione1 = new Frazione(3, 12);
		System.out.println("Creata la frazione " + getAsString(frazione1));

		Frazione frazione2 = new Frazione(1, 4);
		System.out.println("Creata la frazione " + getAsString(frazione2));

		Frazione frazione3 = new Frazione(1, 8);
		System.out.println("Creata la frazione " + getAsString(frazione3));

		Frazione frazione4 = new Frazione(4, 1);
		System.out.println("Creata la frazione " + getAsString(frazione4));
		
		//test valori negativi
		Frazione frazione5 = new Frazione(-1, 8);
		assert(frazione5.getNum() == -1 && frazione5.getDen() == 8);
		System.out.println("Creata la frazione " + getAsString(frazione5));
		
		Frazione frazione6 = new Frazione(2, -3);
		System.out.println("Creata la frazione " + getAsString(frazione6));
		
		Frazione frazione7 = new Frazione(-5, -7);
		System.out.println("Creata la frazione " + getAsString(frazione7) + "\n");

		//test funzionamento equals
		
		if (frazione1.equals(frazione2)) // true
			System.out.println("Le frazioni " + getAsString(frazione1) + " e " + getAsString(frazione2) + " sono equivalenti");
		else
			System.out.println("Le frazioni " + getAsString(frazione1) + " e " + getAsString(frazione2) + " non sono equivalenti");

		
		if (frazione1.equals(frazione3)) // false
			System.out.println("Le frazioni " + getAsString(frazione1) + " e " + getAsString(frazione3) + " sono equivalenti");
		else
			System.out.println("Le frazioni " + getAsString(frazione1) + " e " + getAsString(frazione3) + " non sono equivalenti");
		
		
		if (frazione1.equals(frazione6)) // false
			System.out.println("Le frazioni " + getAsString(frazione1) + " e " + getAsString(frazione6) + " sono equivalenti");
		else
			System.out.println("Le frazioni " + getAsString(frazione1) + " e " + getAsString(frazione6) + " non sono equivalenti" + "\n");

		//test funzionamento riduzioneMinimiTermini
		Frazione frazioneRid = frazione1.minTerm();
		
		System.out.println("La frazione " + getAsString(frazione1) + " ridotta ai minimi termini diventa " + getAsString(frazioneRid) + "\n"); // 1/4
		
		frazioneRid = frazione6.minTerm();
		System.out.println("La frazione " + getAsString(frazione6) + " ridotta ai minimi termini diventa " + getAsString(frazioneRid) + "\n"); // -2/3
		
		//test funzionamento somma fra due frazioni
		Frazione sum = frazione6.sum(frazione7);
		System.out.println("-2/3 + 5/7 = " + getAsString(sum)); // 1/21
		
		//test funzionamento somma con mcm
		Frazione sum2 = frazione2.sumWithMcm(frazione3);
		System.out.println("1/4 + 1/8 = " + getAsString(sum2)); //3/8
		
		sum2 = new Frazione(-1, 4).sumWithMcm(frazione3);
		System.out.println("-1/4 + 2/16 = " + getAsString(sum2)+ "\n"); //-1/8
		
		//test uguaglianza somma fra due frazioni con mcm e senza
		Frazione sumWithMcm = frazione6.sumWithMcm(frazione7);
		System.out.println("La somma ottenuta con mcm e' " + sumWithMcm+ " senza e' "+ sum+ "\n");
		
		//test funzionamento prodotto fra due frazioni
		Frazione mul = frazione4.mul(frazione5);
		System.out.println("4 * -1/8 = " + getAsString(mul)); // -1/2
		
		//test funzionamento divisione fra due frazioni
		Frazione div = frazione6.div(frazione2);
		System.out.println("-2/3 : 1/4 = " + getAsString(div)); // -8/3
		
		//test funzionamento compareTo
	    System.out.println("il confronto tra 3/12 e 1/4 è: "+ frazione1.compareTo(frazione2));
	    
	  //test funzionamento getDouble
	    System.out.println("valore reale associato a : "+ frazione1.toString() + " è "+frazione1.getDouble());
	    

	
	    
		
	}
}
