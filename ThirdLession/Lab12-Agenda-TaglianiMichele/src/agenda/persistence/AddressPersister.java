package agenda.persistence;

import java.util.StringTokenizer;

import agenda.model.Address;
import agenda.model.Detail;
import agenda.model.DetailFactory;

public class AddressPersister implements DetailPersister{
	
	private static final String SEPARATOR = ";";

	@Override
	public Detail load(StringTokenizer source) throws BadFileFormatException {
		Address d = new Address();
		
		d.setDescription(source.nextToken(SEPARATOR));
		d.setStreet(source.nextToken(SEPARATOR));
		d.setNumber(source.nextToken(SEPARATOR));
		d.setZipCode(source.nextToken(SEPARATOR));
		d.setCity(source.nextToken(SEPARATOR));
		d.setState(source.nextToken(SEPARATOR));
		
		return d;
	}

	@Override
	public void save(Detail d, StringBuilder sb) {
		 Address a = (Address) d;
		 	sb.append(a.getName() + SEPARATOR);
	        sb.append(a.getDescription() + SEPARATOR);
	        sb.append(a.getStreet() + SEPARATOR);
	        sb.append(a.getNumber() + SEPARATOR);
	        sb.append(a.getZipCode() + SEPARATOR);
	        sb.append(a.getCity() + SEPARATOR);
	        sb.append(a.getState());
	        sb.append("\n");
		
	}

}
