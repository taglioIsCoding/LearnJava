package agenda.persistence;

import java.util.StringTokenizer;

import agenda.model.Detail;
import agenda.model.DetailFactory;
import agenda.model.EMail;

public class EMailPersister implements DetailPersister{
	
	private static String SEPARATOR = ";";
	
	public EMailPersister() {
		
	}
	@Override
	public Detail load(StringTokenizer source) throws BadFileFormatException {
		EMail d = new EMail();
		d.setDescription(source.nextToken(SEPARATOR).trim());
		d.setValue(source.nextToken("\n\r").trim().substring(1));
		return d;
	}

	@Override
	public void save(Detail d, StringBuilder sb) {
		sb.append(d.getName()+SEPARATOR+d.getDescription()+SEPARATOR+d.getValues()+"\n");
	}

}
