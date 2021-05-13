package agenda.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import agenda.model.Contact;
import agenda.model.Detail;
import agenda.model.DetailFactory;

public class TextContactsPersister implements ContactsPersister{
	
	private static final String SEPARATOR = ";";

	@Override
	public List<Contact> load(Reader reader) throws IOException, BadFileFormatException {
		if(reader == null) {
			throw new IOException("reader null");
		}
		BufferedReader rdr = new BufferedReader(reader);
		String line;
		List<Contact> toLoad = new ArrayList<Contact>();
		while(( line = rdr.readLine()) != null) {
			if(!line.equals("StartContact")) {
				throw new BadFileFormatException("StartContact expected");
			}
			
			Optional<Contact> contactToAdd = readContact(rdr);
			if(contactToAdd.isPresent()){
				toLoad.add(contactToAdd.get());
			}
			
			
		}
		return toLoad;
	}
	
	private Optional<Contact> readContact(BufferedReader rdr) throws IOException, BadFileFormatException {

		StringTokenizer stk = new StringTokenizer(rdr.readLine());
		
		String name = stk.nextToken(SEPARATOR).trim();
		String surname = stk.nextToken("\n\r").trim().substring(1);
		
		if(name == null || surname == null) {
			throw new BadFileFormatException();
		}
		
		Contact c = new Contact(name, surname);
		
		readDetails( c,  rdr);
		
		return Optional.of(c);
	}
	
	private void readDetails(Contact c, BufferedReader rdr) throws IOException, BadFileFormatException {
		String line;
		line = rdr.readLine();
		if(line == null) {
			throw new BadFileFormatException("Detail or EndContact expected");
		}
		
		while(!(line.contains("EndContact"))){
			StringTokenizer stk = new StringTokenizer(line);
			String detailType = stk.nextToken(";");
			DetailPersister detP = DetailPersister.of(detailType);
			if(detP == null) {
				throw new BadFileFormatException("Unknown Detail Type");
			}
			
			
			Detail toInsert = detP.load(stk);
			c.getDetailList().add(toInsert);
			
			try {
				line = rdr.readLine();
			} catch (NullPointerException e) {
				throw new BadFileFormatException("Detail or EndContact expected");
			}
		}
		
	}

	@Override
	public void save(List<Contact> contacts, Writer writer) throws IOException {
		if(writer == null) {
			throw new IOException("writer null");
		}
		
		for(Contact c : contacts) {
			saveCotact(c, writer);
		}
		
	}
	
	private void saveCotact(Contact c, Writer writer) throws IOException {
		PrintWriter wrt = new PrintWriter(writer);
		StringBuilder stb = new StringBuilder();
		wrt.println("StartContact");
		
		wrt.println(c.getName()+SEPARATOR+c.getSurname());
		try {
			saveDetail(c.getDetailList(), stb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadFileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wrt.print(stb);
		wrt.print("EndContact\n");
		
	}
	
	private void saveDetail(List<Detail> details, StringBuilder stb) throws IOException, BadFileFormatException {
		
		
		for(Detail d : details) {
			String detailType = d.getName();
			DetailPersister detP = DetailPersister.of(detailType);
			if(detP == null) {
				throw new BadFileFormatException("Unknown Detail Type");
			}
			
			detP.save(d, stb);
		}
		
		
	}

}
