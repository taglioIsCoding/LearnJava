package minirail.persistence;

import java.util.List;

import minirail.model.Gauge;
import minirail.model.Train;

public interface ConfigReader {
	List<Train> getTrains();
	Gauge getGauge();
}
