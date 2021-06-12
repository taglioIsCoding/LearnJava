package dentinia.governor.persistence;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import dentinia.governor.model.Elezioni;

public class MySeggiWriter implements SeggiWriter{
	
	private String outPutFileName;
	
	public MySeggiWriter(String filename) {
		this.outPutFileName=filename;
	}

	@Override
	public void stampaSeggi(Elezioni elezioni, String msg) throws IOException {
		PrintWriter pWriter = new PrintWriter(new File("Report.txt"));
		DateTimeFormatter dFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.ITALY);
		pWriter.append(dFormatter.format(LocalDateTime.now())+ "\n");
		pWriter.append(msg+ "\n");
		pWriter.append(elezioni.toString()+ "\n");
		pWriter.flush();
	}

}
