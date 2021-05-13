package agenda.persistence;

import java.util.StringTokenizer;

import agenda.model.Address;
import agenda.model.Detail;
import agenda.model.Phone;

public class PhonePersister implements DetailPersister{
	
	private static String SEPARATOR = ";";
	
	@Override
	public Detail load(StringTokenizer source) throws BadFileFormatException {
		Phone d = new Phone();
		
		d.setDescription(source.nextToken(SEPARATOR).trim());
		d.setValue(source.nextToken("\n\r").trim().substring(1));
		
		return d;
	}

	@Override
	public void save(Detail d, StringBuilder sb) {
		sb.append(d.getName()+SEPARATOR+d.getDescription()+SEPARATOR+d.getValues()+"\n");
	}

}
