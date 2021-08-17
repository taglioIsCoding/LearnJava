package saferepo.ui.controller;

import java.nio.file.Path;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import saferepo.model.VersionedDocument;
import saferepo.persistence.RepoPersister;
import saferepo.persistence.SadPersisterException;
import saferepo.model.Document;
import saferepo.model.Repository;

public class MyController implements Controller {
	private RepoPersister persister;
	private Repository repository;
		
	public MyController(Repository repository, RepoPersister persister) {
		this.repository=repository; this.persister=persister;
	}

	@Override
	public ObservableList<VersionedDocument> getVersions(String documentID) {
		return FXCollections.observableArrayList(repository.getVersions(documentID));
	}

	@Override
	public ObservableList<String> getDocumentIDs() {
		return FXCollections.observableArrayList(repository.getDocumentIDs());
	}

	@Override
	public VersionedDocument retrieve(VersionedDocument vdoc, Path path) throws SadPersisterException {
		return persister.retrieve(vdoc, path);
	}
	
	@Override
	public VersionedDocument store(String documentID, Path path) throws SadPersisterException {
		VersionedDocument vdoc = repository.add(new Document(documentID,path)); 
		persister.store(vdoc);
		return vdoc;
	}

	@Override
	public boolean delete(String documentID) throws SadPersisterException {
		return repository.delete(documentID);
	}
	
}
