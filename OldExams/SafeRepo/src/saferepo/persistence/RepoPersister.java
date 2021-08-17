package saferepo.persistence;

import java.nio.file.Path;
import java.util.List;

import saferepo.model.VersionedDocument;

public interface RepoPersister {
	
	final String MYPATH = System.getProperty("user.dir");
	final String REPOPATH = MYPATH + "/archivedFiles";
	final String SAVEDFILESPATH = MYPATH + "/retrievedFiles";
	
	void store(VersionedDocument vdoc) throws SadPersisterException;
	VersionedDocument retrieve(VersionedDocument vdoc, Path path) throws SadPersisterException;
	List<Path> list() throws SadPersisterException;
	List<Path> list(String documentID) throws SadPersisterException;
	String toString();
}
