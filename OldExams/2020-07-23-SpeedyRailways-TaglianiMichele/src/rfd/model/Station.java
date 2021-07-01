package rfd.model;

public class Station {
	private String stationName;
	private double progressivaKm;
	private int speed;

	public Station(String name, double progressivaKm, int speed) {
		this.stationName = name;
		this.progressivaKm = progressivaKm;
		this.speed=speed;
	}

	public String getStationName() {
		return stationName;
	}

	public double getKm() {
		return progressivaKm;
	}
	
	public int getSpeed() {
		return speed;
	}

	@Override
	public String toString() {
		return getKm() + "\t" + getStationName() + '\t' + speed + "km/h";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(progressivaKm);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + speed;
		result = prime * result + ((stationName == null) ? 0 : stationName.hashCode());
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
		Station other = (Station) obj;
		if (Double.doubleToLongBits(progressivaKm) != Double.doubleToLongBits(other.progressivaKm))
			return false;
		if (speed != other.speed)
			return false;
		if (stationName == null) {
			if (other.stationName != null)
				return false;
		} else if (!stationName.equals(other.stationName))
			return false;
		return true;
	}
	
}
