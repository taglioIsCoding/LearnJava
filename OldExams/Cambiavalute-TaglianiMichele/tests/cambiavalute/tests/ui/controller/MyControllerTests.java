package cambiavalute.tests.ui.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import org.junit.Test;

import cambiavalute.model.*;
import cambiavalute.ui.controller.Controller;
import cambiavalute.ui.controller.MyController;
import cambiavalute.ui.controller.UserInteractor;

public class MyControllerTests {

	@Test
	public void ctorAndGetters() {
		UserInteractor userInteractor = new DummyUserInteractor();

		Map<String, TassoDiCambioUfficiale> cambiUfficiali = new HashMap<>();
		cambiUfficiali.put("XXX", new TassoDiCambioUfficiale("VVV", "NNN", "PPP", 1.234));

		List<CambiaValute> listaCambiaValute = new ArrayList<>();
		listaCambiaValute.add(new CambiaValute("AAA", new HashMap<>()));

		Controller c = new MyController(userInteractor, cambiUfficiali, listaCambiaValute);

		assertSame(userInteractor, c.getUserInteractor());

		assertEquals(1, c.getCambiUfficiali().size());
		assertEquals(cambiUfficiali.get("XXX"), c.getCambiUfficiali().get("XXX"));

		assertEquals(1, c.getListaCambiaValute().size());
		assertEquals(listaCambiaValute.get(0), c.getListaCambiaValute().get(0));
	}

	@Test
	public void changeTo() {
		UserInteractor userInteractor = new DummyUserInteractor();

		Map<String, TassoDiCambioUfficiale> cambiUfficiali = new HashMap<>();
		cambiUfficiali.put("XXX", new TassoDiCambioUfficiale("VVV", "NNN", "PPP", 1.234));

		Map<String, TassiDiCambio> tassiDiCambioMap;
		List<CambiaValute> listaCambiaValute = new ArrayList<>();
		
		tassiDiCambioMap = new HashMap<>();
		tassiDiCambioMap.put("VA1", new TassiDiCambio("VA1", 2, 1));
		listaCambiaValute.add(new CambiaValute("AG1", tassiDiCambioMap));
		
		tassiDiCambioMap = new HashMap<>();
		tassiDiCambioMap.put("VA2", new TassiDiCambio("VA2", 20, 10));
		listaCambiaValute.add(new CambiaValute("AG2", tassiDiCambioMap));

		Controller c = new MyController(userInteractor, cambiUfficiali, listaCambiaValute);
		Map<String, OptionalDouble> result = c.changeTo("VA1", 1);
		
		assertEquals(2, result.size());
		
		OptionalDouble value;
		
		value = result.get("AG1");
		assertTrue(value.isPresent());
		assertEquals(1, value.getAsDouble(), 0);
		
		value = result.get("AG2");
		assertFalse(value.isPresent());
	}

	@Test
	public void changeFrom() {
		UserInteractor userInteractor = new DummyUserInteractor();

		Map<String, TassoDiCambioUfficiale> cambiUfficiali = new HashMap<>();
		cambiUfficiali.put("XXX", new TassoDiCambioUfficiale("VVV", "NNN", "PPP", 1.234));

		Map<String, TassiDiCambio> tassiDiCambioMap;
		List<CambiaValute> listaCambiaValute = new ArrayList<>();
		
		tassiDiCambioMap = new HashMap<>();
		tassiDiCambioMap.put("VA1", new TassiDiCambio("VA1", 2, 1));
		listaCambiaValute.add(new CambiaValute("AG1", tassiDiCambioMap));
		
		tassiDiCambioMap = new HashMap<>();
		tassiDiCambioMap.put("VA2", new TassiDiCambio("VA2", 20, 10));
		listaCambiaValute.add(new CambiaValute("AG2", tassiDiCambioMap));

		Controller c = new MyController(userInteractor, cambiUfficiali, listaCambiaValute);
		Map<String, OptionalDouble> result = c.changeFrom("VA2", 1);
		
		assertEquals(2, result.size());
		
		OptionalDouble value;
		
		value = result.get("AG1");
		assertFalse(value.isPresent());
		
		value = result.get("AG2");
		assertTrue(value.isPresent());
		assertEquals(0.05, value.getAsDouble(), 0);
	}
}

class DummyUserInteractor implements UserInteractor {

	@Override
	public void showMessage(String message) {
	}

	@Override
	public void shutDownApplication() {
	}

}
