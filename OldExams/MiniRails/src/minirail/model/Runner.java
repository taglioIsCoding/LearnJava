package minirail.model;

import java.util.Map;

public interface Runner {
	/* If the number of sections is less than N+2, where N is the number of circulating trains, deadlock may occur if the The clock period is too high.
	 * To prevent this risk, the period must be lowered according to the calculations below, OR the number of sections must be increased up to N+2 at least.
	 *  
	 * Reason:
	 * Let d be the length of the shortest section, and speed the speed of the fastest train. Then, in a period p, that trains covers a space s.
	 * Then, the shortest section d must be >= s+t, where t is the length of the longest train. It is not enough to merely require that d > t, 
	 * since the trains could not be at the rightmost end of the section, because that its advancement takes place at steps of length s.
	 * Because the step s depends on the sampling period, reducing the period reduces s - eventually, eliminating the deadlock risk. 
	 * The threshold depends on the fastest speed allowed on the line: for instance, if the max allowed speed is 160 km/h = 27.77 cm/s in gauge N, 
	 * a sampling period of 1.0 s calls for a shortest section of t+27.78 cm -- that is, about 96 cm if the train is, say, 68 cm long.
	 * However, reducing the period of 50 times, down to 0.02 seconds, allows for the shortest section just t+0.556 cm long (68.6 cm in the above case)
	 * 
	 * Alternatively, to prevent deadlock independently of the sampling period, the number of sections must be >= N+2, where N is the number of trains.
	 * This conditions ensures that at least a train as a free section in front of it, whatever its position.
	 * */
	public void clock(double period); 
	public Map<String, LineStatus> getLines();
	public Gauge getGauge();

}