package minirail.model;

import java.util.List;
import java.util.Set;

public interface LineStatus {

	public Line getLine();
	public boolean putTrain(Train t, double pos);
	public boolean moveTrain(Train t, double pos);
	public boolean removeTrain(Train t);
	public Set<Train> getAllTrains();
	public double getTrainLocation(Train t);
	public int posToSectionID(double pos);
	public boolean isSectionOccupied(int sectionID);
	public Train occupyingTrain(int sectionID);
	public List<Integer> getSectionOccupancy(Train t);
}