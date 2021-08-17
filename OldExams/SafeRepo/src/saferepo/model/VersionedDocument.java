package saferepo.model;

import java.nio.file.Path;
import java.time.LocalDateTime;

public class VersionedDocument {
	
	private LocalDateTime timestamp;
	private int version;
	private Document doc;
	
	public VersionedDocument(Document doc, LocalDateTime timestamp, int n) {
		this.doc=doc; this.timestamp=timestamp; this.version=n;
	}

	public Document getDocument() { return doc; }
	public String getID() { return doc.getID();}
	public Path getPath() { return doc.getPath();}
	
	public LocalDateTime getTimeStamp() {return timestamp; }
	//public void setTimeStamp(LocalDateTime timestamp) { this.timestamp=timestamp; }
	
	public int getVersion() {return version; }
	//public void setVersion(int version) { this.version=version; }

	@Override
	public String toString() {
		return "VersionedDocument [timestamp=" + timestamp + ", version=" + version + ", doc=" + doc + "]";
	}
		
}
