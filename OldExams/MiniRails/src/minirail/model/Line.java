package minirail.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Line {

	private String name;
	private List<Section> sections;
	private double length;
	
	public Line(String name, List<Section> sections) {
		if (name==null || name.isEmpty()) throw new IllegalArgumentException("Illegal line - empty name");
		this.name=name;
		if (sections==null || sections.isEmpty()) throw new IllegalArgumentException("Illegal line - empty section list");
		this.sections = new ArrayList<>();
		this.length=sections.stream().collect(Collectors.summingDouble(Section::getLength));
		this.sections=sections;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Section> getSections() {
		return this.sections;
	}

	public Section getSection(int index) {
		return this.sections.get(index);
	}
	
	public double getMinimumSectionLength() {
		return sections.stream().min(Comparator.comparingDouble(Section::getLength)).map(Section::getLength).orElse(0.0);
	}
			
	public double getLength() {
		return length;
	}

	public String toString() {
		return "Line " + name +" (length: " + length + ") is made of " + sections.size() + " sections";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(length);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sections == null) ? 0 : sections.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Line other = (Line) obj;
		if (Double.doubleToLongBits(length) != Double.doubleToLongBits(other.length)) return false;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		if (sections == null) {
			if (other.sections != null) return false;
		} else if (!sections.equals(other.sections)) return false;
		return true;
	}

	
	
}

