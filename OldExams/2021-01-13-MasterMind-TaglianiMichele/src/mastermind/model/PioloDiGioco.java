package mastermind.model;

public enum PioloDiGioco {
	VUOTO("#808080"), ROSSO("#FF0000"), GIALLO("#FFFF00"), VERDE("#00FF00"), BLU("#00FFFF"), GRIGIO("#C0C0C0"), MARRONE("#800000");
	
	private String rgbColor;

	private PioloDiGioco(String rgbColor) {
		this.rgbColor=rgbColor;
	}
	
	public String rgb() {
		return rgbColor;
	}
}
