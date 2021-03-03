/**
 * Classe di test
 * @author Fondamenti di Informatica T-2
 * @version Jan 2021
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
	
	public static void main(String[] args) 	{	
		
		//costruzione frazione
		//funzionamento e toString
		Frazione frazione1 = new Frazione(3, 12);
		
		System.out.println("Creata la frazione " + frazione1);

		Frazione frazione2 = new Frazione(1, 4);
		
		System.out.println("Creata la frazione " + frazione2);

		Frazione frazione3 = new Frazione(1, 8);
		
		System.out.println("Creata la frazione " + frazione3);

		Frazione frazione4 = new Frazione(4, 1);
		System.out.println("Creata la frazione " + frazione4);
		
		//test valori negativi
		Frazione frazione5 = new Frazione(-1, 8);
		System.out.println("Creata la frazione " + frazione5);
		
		Frazione frazione6 = new Frazione(2, -3);
		System.out.println("Creata la frazione " + frazione6);
		
		Frazione frazione7 = new Frazione(-5, -7);
		System.out.println("Creata la frazione " + frazione7 + "\n");

		//test funzionamento equals
		if (frazione1.equals(frazione2)) // true
			System.out.println("Le frazioni " + frazione1 + " e " + frazione2 + " sono equivalenti");
		else
			System.out.println("Le frazioni " + frazione1 + " e " + frazione2 + " non sono equivalenti");

		
		if (frazione1.equals(frazione3)) // false
			System.out.println("Le frazioni " + frazione1+ " e " + frazione3 + " sono equivalenti");
		else
			System.out.println("Le frazioni " + frazione1 + " e " + frazione3 + " non sono equivalenti");
		
		
		if (frazione1.equals(frazione6)) // false
			System.out.println("Le frazioni " + frazione1 + " e " +frazione6 + " sono equivalenti");
		else
			System.out.println("Le frazioni " + frazione1 + " e " + frazione6 + " non sono equivalenti" + "\n");

		//test funzionamento riduzioneMinimiTermini
		Frazione frazioneRid = frazione1.minTerm();
		
		System.out.println("La frazione " + frazione1 + " ridotta ai minimi termini diventa " + frazioneRid + "\n"); // 1/4
		
		frazioneRid = frazione6.minTerm();
		
		System.out.println("La frazione " + frazione6 + " ridotta ai minimi termini diventa " + frazioneRid + "\n"); // -2/3
			
	}
}
