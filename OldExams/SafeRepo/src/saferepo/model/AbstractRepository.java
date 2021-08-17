package saferepo.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeMap;

public abstract class AbstractRepository implements Repository {

	protected Map<String,List<VersionedDocument>> repomap;
	
	public AbstractRepository() {
		repomap = new TreeMap<>();
	}
		
	@Override
	public VersionedDocument add(Document document) {
		return add(document, LocalDateTime.now());
	}
	
	@Override
	public List<VersionedDocument> getVersions(String documentID) {
		return repomap.get(documentID);
	}
	
	@Override
	public SortedSet<String> getDocumentIDs() {
		return (SortedSet<String>) repomap.keySet();
	}


	@Override
	public boolean restoreVersion(String documentID, int n) {
		Optional<VersionedDocument> doc = getVersion(documentID, n);
		if (doc.isPresent()) {
			add(doc.get().getDocument());
			return true;
		}
		else return false;
	}

	@Override
	public boolean restoreVersionAt(String documentID, LocalDateTime timestamp) {
		Optional<VersionedDocument> doc = getVersionAt(documentID, timestamp);
		if (doc.isPresent()) {
			add(doc.get().getDocument());
			return true;
		}
		else return false;
	}

}
