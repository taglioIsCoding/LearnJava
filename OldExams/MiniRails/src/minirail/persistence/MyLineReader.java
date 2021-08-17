package minirail.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import minirail.model.Line;
import minirail.model.Section;

public class MyLineReader implements LineReader{
	
	private String lineKwrd;
	private String lineName;
	private Line line;
	private List<Section> sections;
	
	public MyLineReader(Reader reader) throws IOException, BadFileFormatException {
		BufferedReader rdr = new BufferedReader(reader);
		sections = new ArrayList<>();
		String firstLine = rdr.readLine();
		String line;
		
		try {
			if(!firstLine.split(" ")[0].equalsIgnoreCase("line")) {
				throw new BadFileFormatException("line errato kwrd");
			}
			
			if(firstLine.split(" ").length > 2) {
				throw new BadFileFormatException("first line errato kwrd");
			}
			this.lineName = firstLine.split(" ")[1];
		} catch (Exception e) {
			throw new BadFileFormatException("lineName errato");
		}
		
		while((line = rdr.readLine())!= null) {
			StringTokenizer stk = new StringTokenizer(line, " ");
			
			if(stk.countTokens() != 3) {
				throw new BadFileFormatException("manca un campo");
			}
			
			if(!stk.nextToken().trim().equals("Section")) {
				throw new BadFileFormatException("section errato kwrd");
			}
			
			String sectionId = stk.nextToken().trim();
			
			double lunghezza;
			try {
				lunghezza = Double.parseDouble(stk.nextToken().trim());
			} catch (Exception e) {
				throw new BadFileFormatException("lunghezza errato");
			}
			
			sections.add(new Section(sectionId, lunghezza));
		}
		
		this.line = new Line(lineName, sections);
		
	}
	
	@Override
	public Line getLine() {
		return this.line;
	}

}
