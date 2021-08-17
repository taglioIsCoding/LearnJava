package saferepo.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;


public abstract class AbstractRepoPersister implements RepoPersister {
	
	protected Path repoPath;
	
	public AbstractRepoPersister(Path repoPath) throws SadPersisterException {
		this.repoPath=repoPath;
		try {
			if (!Files.exists(repoPath)) Files.createDirectory(repoPath);
		} catch (IOException e) {
			throw new SadPersisterException("Errore nella creazione della cartella del persister");
		}
	}
	
	@Override
	public String toString() {
		try {
			return "Persister @ " + this.repoPath + ", currently holding " + Files.list(this.repoPath).count() + " files";
		} catch (IOException e) {
			return "ERROR";
		}
		//return this.list().toString();
	}
	
	@Override
	public List<Path> list() throws SadPersisterException {
		try {
			return Files.list(this.repoPath).collect(Collectors.toList());
		} catch (IOException e) {
			throw new SadPersisterException("Errore di accesso alla cartella del persister");
		}
	}
	
	@Override
	public List<Path> list(String documentID) throws SadPersisterException {
		try {
			return Files.list(this.repoPath).filter(p -> p.getFileName().toString().startsWith(documentID)).collect(Collectors.toList());
		} catch (IOException e) {
			throw new SadPersisterException("Errore di accesso alla cartella del persister");
		}
	}
	
}
