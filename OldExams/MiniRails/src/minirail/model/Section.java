package minirail.model;

public class Section {
	private double length;
	private String id;
	
	public Section(String id, double len) {
		if (id==null || id.isEmpty()) throw new IllegalArgumentException("Illegal Section ID " + id);
		this.id=id;
		if (len<=0) throw new IllegalArgumentException("Illegal Section Length " + len);
		this.length=len;
	}
	
	public String getID() {
		return id;
	}
	
	public double getLength() {
		return length;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(length);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Section)) return false; // DO NOT compare getClass: we need it works also with subclasses!
		Section other = (Section) obj;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		if (Double.doubleToLongBits(length) != Double.doubleToLongBits(other.length)) return false;
		return true;
	}

	public String toString() {
		return "Section " + id;
	}
	
}
