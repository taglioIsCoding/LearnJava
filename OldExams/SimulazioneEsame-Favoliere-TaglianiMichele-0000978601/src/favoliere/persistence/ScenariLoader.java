package favoliere.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import favoliere.model.Azione;
import favoliere.model.Personaggio;
import favoliere.model.Scenario;
import favoliere.model.Tipologia;

public class ScenariLoader implements SectionLoader<Scenario> {

	private List<Scenario> scenari = new ArrayList<>();
	
	@Override
	public List<Scenario> getItems() {
		return this.scenari;
	}

	@Override
	public void loadAllItems(Reader baseReader) throws IOException, BadFileFormatException {
		String line;
		BufferedReader rdr = new BufferedReader(baseReader);
		
		while ((line = rdr.readLine()) != null) {
			StringTokenizer stk = new StringTokenizer(line, "#");
			String desc = stk.nextToken().trim();
			if (desc == null || desc.isBlank() || ("012345").contains(desc)) {
				throw new BadFileFormatException("desc errato");
			}
			
			int durezza;
			try {
				durezza = Integer.parseInt(stk.nextToken());
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("durezza non presente");
			}
			if (durezza < 0) {
				throw new BadFileFormatException("durezza negativo");
			}
			
			this.scenari.add(supply(desc, durezza));
			
		}
	}
	
	protected Scenario supply(String desc, int dur) {
		return new Scenario(desc, dur);
	}

}
