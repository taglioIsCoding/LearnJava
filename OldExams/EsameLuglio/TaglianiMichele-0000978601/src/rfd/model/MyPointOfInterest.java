package rfd.model;

import java.util.StringTokenizer;

public class MyPointOfInterest extends PointOfInterest{
	
	public MyPointOfInterest(String name, String progressivaKM) {
		
		super(name, progressivaKM);
		StringTokenizer stkStringTokenizer = new StringTokenizer(progressivaKM, "+");
		if(stkStringTokenizer.countTokens() != 2) {
			throw new IllegalArgumentException("Formato non valido");
		}
		
		String kmString = stkStringTokenizer.nextToken().trim();
		String mString = stkStringTokenizer.nextToken().trim();
		
		try {
			int kmInt = Integer.parseInt(kmString);
			if(kmString.length() < 1) {
				throw new IllegalArgumentException("KM non validi");
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("KM non validi");
		}
		
		try {
			double mInt = Double.parseDouble(kmString);
			if(mString.length() != 3) {
				throw new IllegalArgumentException("M non validi");
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("M non validi");
		}
		
	}

	@Override
	public double getKmAsNum() {
		StringTokenizer stkStringTokenizer = new StringTokenizer(this.getKm(), "+");
		if(stkStringTokenizer.countTokens() != 2) {
			throw new IllegalArgumentException("Formato non valido");
		}
		
		String kmString = stkStringTokenizer.nextToken().trim();
		String mString = stkStringTokenizer.nextToken().trim();
		
		double kmInt = 0;
		double mInt = 0;
		try {
			 kmInt =  Double.parseDouble(kmString);
			if(kmString.length() < 1) {
				throw new IllegalArgumentException("KM non validi");
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("KM non validi");
		}
		
		try {
			 mInt = Double.parseDouble(mString)/1000;
			if(mString.length() != 3) {
				throw new IllegalArgumentException("M non validi");
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("M non validi");
		}
		return kmInt+mInt;
	}

}
