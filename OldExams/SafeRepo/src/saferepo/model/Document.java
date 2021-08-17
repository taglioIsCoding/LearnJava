package saferepo.model;

import java.nio.file.Path;

public class Document {
	
	private String id;
	private Path path;
	
	public Document(String id, Path path) {
		this.id=id; this.path=path; 
	}
	
	public String getID() { return id;}
	public Path getPath() { return path;}

	@Override
	public String toString() {
		return "Document [id=" + id + ", path=" + path + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

	
}
