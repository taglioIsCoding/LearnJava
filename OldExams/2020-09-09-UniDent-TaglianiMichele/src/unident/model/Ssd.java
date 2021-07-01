package unident.model;

public enum Ssd {
	INGINF05("Ing-Inf/05"), INGINF01("Ing-Inf/01"), INGINF03("Ing-Inf/03"), INGINF04("Ing-Inf/04"),
	INGINF07("Ing-Inf/07"), INGIND31("Ing-Ind/31"), INGIND35("Ing-Ind/35"), FIS01("Fis/01"), 
	MAT05("Mat/05"), MAT03("Mat/03"), MAT07("Mat/07"), IUS20("Ius/20"), SENZASETTORE;
	
	private Ssd(String s) {
		value=s;
	}

	private Ssd() {
		value="\t\t";
	}
	
	private String value;
	
	public String getValue() {
		return value;
	}
	
	public static Ssd of(String s) {
		return Ssd.valueOf(s.replace("-", "").replace("/", "").toUpperCase());
	}
}
