package saferepo.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;

public interface Repository {
	public Optional<VersionedDocument> getCurrentVersion(String documentID);
	public Optional<VersionedDocument> getVersion(String documentID, int n);
	public Optional<VersionedDocument> getVersionAt(String documentID, LocalDateTime timestamp);
	public List<VersionedDocument> getVersions(String documentID);
	public SortedSet<String> getDocumentIDs();
	public boolean restoreVersion(String documentID, int n);
	public boolean restoreVersionAt(String documentID, LocalDateTime timestamp);
	public VersionedDocument add(Document document);
	public VersionedDocument add(Document document, LocalDateTime timestamp);
	public boolean delete(String documentID);
}
