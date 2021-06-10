package cambiavalute;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import cambiavalute.model.CambiaValute;
import cambiavalute.model.TassiDiCambio;
import cambiavalute.model.TassoDiCambioUfficiale;
import cambiavalute.persistence.CambiReader;
import cambiavalute.persistence.CambiUfficialiReader;
import cambiavalute.persistence.MyCambiReader;
import cambiavalute.persistence.MyCambiUfficialiReader;
import cambiavalute.ui.controller.Controller;
import cambiavalute.ui.controller.MainPane;
import cambiavalute.ui.controller.MyController;
import cambiavalute.ui.controller.SwingUserInteractor;
import cambiavalute.ui.controller.UserInteractor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import cambiavalute.ui.*;

public class CambiaValuteApplication extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		Controller controller = null;
		UserInteractor userInteractor = new SwingUserInteractor();
		List<CambiaValute> listaCambiaValute = new ArrayList<>();
		try {
			CambiUfficialiReader reader1 = new MyCambiUfficialiReader();
			Map<String,TassoDiCambioUfficiale> cambiUfficiali;
			try (FileReader cambiUfficialiFile = new FileReader("CambiUfficiali.csv")) {
				cambiUfficiali = reader1.leggiTabellaCambiUfficiali(cambiUfficialiFile);
			}
			System.err.println(cambiUfficiali);

			CambiReader reader2 = new MyCambiReader();
			List<Path> daAprire = Files.list(Paths.get("."))
					.filter(n->n.toString().endsWith("txt"))
					.collect(Collectors.toList());
			for (Path p : daAprire) {
				String agenzia = p.getFileName().toString().split("\\.")[0];
				System.err.println("Processing " + agenzia);
				Map<String,TassiDiCambio> cambiQuestaAgenzia;
				try (FileReader cambiQuestaAgenziaFile = new FileReader(p.toFile())) {
					cambiQuestaAgenzia = reader2.leggiTabellaCambiApplicati(cambiQuestaAgenziaFile);
				}
				listaCambiaValute.add(new CambiaValute(agenzia,cambiQuestaAgenzia));
			}
			
			System.out.println("100 EURO in USD secondo cambio ufficiale = " + 100*cambiUfficiali.get("USD").getTassoDiCambio());
			System.out.println("100 EURO in CHF secondo cambio ufficiale = " + 100*cambiUfficiali.get("CHF").getTassoDiCambio());
			System.out.println("100 USD in EURO secondo cambio ufficiale = " + 100/cambiUfficiali.get("USD").getTassoDiCambio());
			System.out.println("100 CHF in EURO secondo cambio ufficiale = " + 100/cambiUfficiali.get("CHF").getTassoDiCambio());
			for (CambiaValute cv : listaCambiaValute){
				System.out.println("100 EURO in USD con " + cv.getNomeAgenzia() + " = " + cv.vendita("USD", 100.0));
				System.out.println("100 EURO in CHF con " + cv.getNomeAgenzia() + " = " + cv.vendita("CHF", 100.0));
				System.out.println("100 USD in EURO con " + cv.getNomeAgenzia() + " = " + cv.acquisto("USD", 100.0));
				System.out.println("100 CHF in EURO con " + cv.getNomeAgenzia() + " = " + cv.acquisto("CHF", 100.0));
			}
			
			controller = new MyController(userInteractor, cambiUfficiali, listaCambiaValute);
			System.out.println(controller.getListaValuteEstere());
			System.out.println(controller.getNomeCompletoValutaEstera("CAD"));
			
			Map<String,OptionalDouble> cambi100USD = controller.changeFrom("USD", 100);
			System.err.println(cambi100USD);
			Map<String,OptionalDouble> cambi100CHF = controller.changeFrom("CHF", 100);
			System.err.println(cambi100CHF);
			Map<String,OptionalDouble> cambi100EURinUSD = controller.changeTo("USD", 100);
			System.err.println(cambi100EURinUSD);
			Map<String,OptionalDouble> cambi100EURinCHF = controller.changeTo("CHF", 100);
			System.err.println(cambi100EURinCHF);

			MainPane mainPanel = new MainPane(controller);

			Scene scene = new Scene(mainPanel, 980, 480, Color.GRAY);
			stage.setScene(scene);
			stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void showAlert(String text) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Errore");
		alert.setHeaderText("Impossibile leggere i dati");
		alert.setContentText(text);
		alert.showAndWait();
	}
	
	public static void main(String[] args) {
		System.setProperty("java.locale.providers", "JRE");
		launch(args);
	}

}
