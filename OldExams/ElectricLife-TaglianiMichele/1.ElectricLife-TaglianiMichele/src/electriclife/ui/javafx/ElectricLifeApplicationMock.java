package electriclife.ui.javafx;

import java.io.IOException;

import electriclife.persistence.BadFileFormatException;
import electriclife.persistence.TariffeReader;
import electriclife.tests.persistence.TariffaReaderMock;
import electriclife.ui.controller.Controller;
import electriclife.ui.controller.MyController;

public class ElectricLifeApplicationMock extends ElectricLifeApplication {

	@Override
	protected Controller createController() {
		TariffeReader tariffeReader = new TariffaReaderMock();	
		Controller c = new MyController();
		try {
			c.leggiTariffe(tariffeReader);
			return c;
		} catch (IOException | BadFileFormatException e) {
			// ignored
			return null;
		}
	}

	public static void main(String[] args) {
		System.setProperty("java.locale.providers", "JRE");
		launch(args);
	}
}
