package cupidonline.ui.javafx;

import java.io.IOException;
import java.util.Map;

import cupidonline.model.Persona;
import cupidonline.model.Preferenza;
import cupidonline.persistence.BadFileFormatException;
import cupidonline.persistence.IscrittiReader;
import cupidonline.persistence.PreferenzeReader;
import cupidonline.tests.persistence.IscrittiReaderMock;
import cupidonline.tests.persistence.PreferenzeReaderMock;
import cupidonline.ui.controller.Controller;
import cupidonline.ui.controller.MyController;

public class CupidoApplicationMock extends CupidoApplication {

	@Override
	protected Controller createController() {
		IscrittiReader iscrittiReader = new IscrittiReaderMock();
		PreferenzeReader prefReader = new PreferenzeReaderMock();
		try {
			Map<String, Persona> iscritti = iscrittiReader.caricaIscritti(null);
			Map<String,Preferenza> preferenze = prefReader.caricaPreferenze(null);
			return new MyController(iscritti, preferenze);
		}
		catch (IOException | BadFileFormatException e) {
			Controller.alert("Errore", "Impossibile aprire file iscritti o preferenze", "Errore o nell'apertura del file, o nella lettura, o nel formato");
			return null;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
