package electriclife.tests.model;

import static org.junit.Assert.fail;

import electriclife.model.Bolletta;
import electriclife.model.VoceBolletta;

public class BollettaTestUtils
{	
	public static void mustHave(Bolletta b, double valore)
	{
		for (VoceBolletta linea : b.getLineeBolletta())
		{
			if (valore - 0.01 < linea.getValore() && linea.getValore() < valore + 0.01)
			{
				return;
			}
		}
		fail("LineaBolletta con valore " + valore + " non trovata.");
	}
	
}
