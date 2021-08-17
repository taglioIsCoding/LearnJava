package minirail.model;

public class Train {

	private String name;
	private double length;
	private TrainStatus status;
	private double speed;
	
	public Train(String name, double len, double speed) {
		if (len<=0) throw new IllegalArgumentException("Illegal Train Length " + len);
		if (speed<=0) throw new IllegalArgumentException("Illegal Train Speed " + speed);
		this.name=name;
		this.status=TrainStatus.STOPPED;
		this.length=len;
		this.speed=speed;
	}
	
	public TrainStatus getStatus() {
		return this.status;
	}

	public void setStatus(TrainStatus status) {
		this.status = status;
	}
	
	public TrainStatus changeStatus() {
		return this.status=status.getOther();
	}
	
	public double getLength() {
		return length;
	}

	public double getSpeed() {
		return speed;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return "Train " + name +" (length: " + length + ") is " + status.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(length);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		temp = Double.doubleToLongBits(speed);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Train other = (Train) obj;
		if (Double.doubleToLongBits(length) != Double.doubleToLongBits(other.length)) return false;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		if (Double.doubleToLongBits(speed) != Double.doubleToLongBits(other.speed)) return false;
		return true;
	}
	
}

