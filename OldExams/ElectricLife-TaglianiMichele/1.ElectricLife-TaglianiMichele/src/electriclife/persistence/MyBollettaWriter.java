package electriclife.persistence;

import java.io.PrintWriter;

import electriclife.model.Bolletta;

public class MyBollettaWriter implements BollettaWriter {

	PrintWriter pw;
	
	public MyBollettaWriter(PrintWriter pw) {
		this.pw=pw;
	}

	@Override
	public void stampaBolletta(Bolletta b) {
		pw.print(b);		
		pw.flush();
	}

}
