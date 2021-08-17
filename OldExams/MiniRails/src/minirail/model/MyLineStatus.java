package minirail.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyLineStatus implements LineStatus {

	private Line line;
	private Map<Train,Double> trainLocations;
	private Map<Train,List<Integer>> sectionOccupancy;
	
	public MyLineStatus(Line line) {
		if (line==null) throw new IllegalArgumentException("Null line for Line Status");
		this.line = line;
		trainLocations = new HashMap<>();
		sectionOccupancy = new HashMap<>();
	}
	
	public Line getLine() {
		return this.line;
	}
	
	public List<Integer> getSectionOccupancy(Train t) {
		List<Integer> l = sectionOccupancy.get(t);
		Collections.sort(l);
		return l;
	}

	
	public String toString() {
		return "Line " + line.getName() +" (length: " + line.getLength() + "), made of " + line.getSections().size() + " sections, currently holds " + getAllTrains().size() + " trains, as follows: " + trainLocations;
	}

	public boolean moveTrain(Train t, double pos) {
		if (t.getStatus()==TrainStatus.STOPPED) throw new RuntimeException("Cannot move a stopped train");
		return putTrain(t,pos);
	}

	public boolean putTrain(Train t, double pos) {
		List<Integer> occupancy = new ArrayList<>();
		double trainLength = t.getLength();
		// check basic preconditions
			//System.err.println("pos = " + pos + ", line length = " + line.getLength() + ", train length=" + trainLength + ", minSectionLen=" + line.getMinimumSectionLength());
		if (pos<0 || pos >= line.getLength() || trainLength>=line.getMinimumSectionLength()) return false;
			//System.out.println("starting checks");
		// calculate section(s) to be occupied
		int sectionID = this.posToSectionID(pos);
		if (occupyingTrain(sectionID)!=null && occupyingTrain(sectionID)!=t) return false;
			System.out.println("Train " + t.getName() + " should occupy section " + sectionID);
		// check if previous section is also affected
		int previousSectionID = sectionID>0 ? sectionID-1 : line.getSections().size()-1;
		if (posToSectionID(pos-trainLength) != sectionID) { // tail of train is on previous section
				System.out.println("Train " + t.getName() + " crosses the border between previous section " + previousSectionID + " and section " + sectionID);
			if (occupyingTrain(previousSectionID)!=null && occupyingTrain(previousSectionID)!=t) return false;
			//	System.out.println("Previous section " + previousSectionID + " is " + (line.isSectionOccupied(previousSectionID) ? "occupied by " + t.getName() : "free"));
			occupancy.add(previousSectionID);
				System.out.println("Previous section " + previousSectionID + " set to occupied by " + t.getName());
		}
		else {
				System.out.println("Train " + t.getName() + " does not cross previous section " + previousSectionID + " border");
				System.out.println("Previous section " + previousSectionID + " set to free");
		} 
		occupancy.add(sectionID);
			System.out.println("Section " + sectionID + " now occupied by " + t.getName());
		sectionOccupancy.put(t,occupancy);
			System.out.println("Overall section occupancy for " + t.getName() + ": " + occupancy);
		trainLocations.put(t,pos);
		return true;
	}
	
	public Set<Train> getAllTrains() {
		return this.trainLocations.keySet();
	}
	
	public double getTrainLocation(Train t) {
		return this.trainLocations.get(t);
	}

	public int posToSectionID(double pos) {
		int i = 0;
		if (pos<0) pos+= line.getLength();
		for(double sectionsLength=line.getSection(0).getLength(); sectionsLength<pos; i++,sectionsLength+=line.getSection(i).getLength());
		return i;
	}
	
	public Train occupyingTrain(int sectionID) {
		for (Train t : sectionOccupancy.keySet()) 
			if (sectionOccupancy.get(t).contains(sectionID)) return t;
		return null;
	}
	
	public boolean isSectionOccupied(int sectionID) {
		return occupyingTrain(sectionID)!=null;
	}

	@Override
	public boolean removeTrain(Train t) {
		Double pos = trainLocations.remove(t);
		List<Integer> sections = sectionOccupancy.remove(t);
		if (pos==null && sections!=null || pos!=null && sections==null) throw new RuntimeException("Inconsistent line status");
		return (pos!=null && sections!=null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((line == null) ? 0 : line.hashCode());
		result = prime * result + ((sectionOccupancy == null) ? 0 : sectionOccupancy.hashCode());
		result = prime * result + ((trainLocations == null) ? 0 : trainLocations.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		MyLineStatus other = (MyLineStatus) obj;
		if (line == null) {
			if (other.line != null) return false;
		} else if (!line.equals(other.line)) return false;
		if (sectionOccupancy == null) {
			if (other.sectionOccupancy != null) return false;
		} else if (!sectionOccupancy.equals(other.sectionOccupancy)) return false;
		if (trainLocations == null) {
			if (other.trainLocations != null) return false;
		} else if (!trainLocations.equals(other.trainLocations)) return false;
		return true;
	}

	
	
}

