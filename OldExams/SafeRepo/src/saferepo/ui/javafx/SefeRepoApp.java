package saferepo.ui.javafx;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import saferepo.persistence.SadPersisterException;
import saferepo.model.MyRepository;
import saferepo.model.Repository;
import saferepo.persistence.MyRepoPersister;
import saferepo.persistence.RepoPersister;
import saferepo.ui.controller.Controller;
import saferepo.ui.controller.MyController;

public class SefeRepoApp extends Application {

	private Controller controller;

	public void start(Stage stage) {
		controller = createController();
		if (controller == null) {
			Controller.alert("Errore irrecuperabile", "Errore nella creazione del controller", "Addio");
			System.exit(1);
		}
		stage.setTitle("Safe Repo - File versioning");
		SefeRepoPane root = new SefeRepoPane(controller, stage);
		
		Scene scene = new Scene(root, 500, 200, Color.ALICEBLUE);
		stage.setScene(scene);
		stage.show();
	}

	protected Controller createController() {
		RepoPersister persister;
		try {
			Repository repository = new MyRepository();
			persister = new MyRepoPersister(Paths.get(RepoPersister.REPOPATH));
			return new MyController(repository, persister);
		} catch (SadPersisterException e) {
			Controller.alert("Errore irrecuperabile", "Errore nella creazione del persister @ " + RepoPersister.REPOPATH, "Addio");
			System.exit(1);
		}
		return null; // fake, codice irraggiungibile
	}

	public static void main(String[] args) {
		launch(args);
	}

}
