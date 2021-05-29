package oroscopo.ui;

public enum Mesi {
		Gennaio("GENNAIO", 0),
		Febbraio("FEBBRAIO", 1), 
		Marzo("MARZO", 2), 
		Aprile("APRILE", 3),
		Maggio("MAGGIO", 4),
		Giugno("GIUGNO", 5),
		Luglio("LUGLIO", 6),
		Agosto("AGOSTO", 7),
		Settembre("SETTEMBRE", 8),
		Ottobre("OTTOBRE", 9),
		Novembre("NOVEMBRE", 10),
		Dicembre("DICEMBRE", 11);
		private String name;
		private int code;
		Mesi(String string, int code) {
			this.name = string;
			this.code = code;
		}
		
}
