package saferepo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MyRepository extends AbstractRepository{
	
	public MyRepository() {
		super();
	}

	@Override
	public Optional<VersionedDocument> getCurrentVersion(String documentID) {
		return Optional.of(this.repomap.get(documentID).get(this.repomap.get(documentID).size()-1));
	}

	@Override
	public Optional<VersionedDocument> getVersion(String documentID, int n) {
		if(n < 0) {
			throw new IllegalArgumentException("version number < 0");
		} else if (n > this.repomap.get(documentID).size()) {
			return Optional.empty();
		}
		return Optional.of(this.repomap.get(documentID).get(n));
	}

	@Override
	public Optional<VersionedDocument> getVersionAt(String documentID, LocalDateTime timestamp) {
		int i = this.repomap.get(documentID).size()-1;
		
		for(; i > 0; i--) {
			if(this.repomap.get(documentID).get(i).getTimeStamp().isBefore(timestamp) || this.repomap.get(documentID).get(i).getTimeStamp().equals(timestamp)) {
				return Optional.of(this.repomap.get(documentID).get(i));
			}
		}
		return Optional.empty();
	}

	@Override
	public VersionedDocument add(Document document, LocalDateTime timestamp) {
		if(this.repomap.get(document.getID()) == null) {
			VersionedDocument firstElement = new VersionedDocument(document, timestamp, 0);
			List<VersionedDocument> docList = new ArrayList<>();
			docList.add(firstElement);
			this.repomap.put(document.getID(), docList);
			return firstElement;
		}else {
			VersionedDocument precVer = this.repomap.get(document.getID()).get(this.repomap.get(document.getID()).size()-1);
			VersionedDocument newElement = new VersionedDocument(document, timestamp, precVer.getVersion()+1);
			this.repomap.get(document.getID()).add(newElement);
			return newElement;
		}
	}

	@Override
	public boolean delete(String documentID) {
		if(this.repomap.get(documentID) == null) {
			return false;
		}
		VersionedDocument versionedDocument = new VersionedDocument(new Document(documentID, null), LocalDateTime.now(), this.repomap.get(documentID).size());
		this.repomap.get(documentID).add(versionedDocument);
		return true;
	}

}
