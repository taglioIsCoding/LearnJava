package minirail.ui.controller;

import java.util.List;
import java.util.Map;

import minirail.model.Gauge;
import minirail.model.Line;
import minirail.model.LineStatus;
import minirail.model.MyRunner;
import minirail.model.Runner;
import minirail.model.Train;
import minirail.model.TrainStatus;

public class MyController implements Controller {
	
	private Line line;
	private List<Train> trains;
	private LineStatus dir;
	private Runner runner; 

	public MyController(LineStatus dir, List<Train> trains) {
		this.dir=dir;
		this.line=dir.getLine();
		this.trains=trains;
		runner = new MyRunner(Map.of(this.getLine().getName(), this.getLineStatus()), Gauge.N);
	}
		
	@Override
	public void clock(double period) {
		try {
			runner.clock(period);
		}
		catch(RuntimeException e) {
			Controller.alert("Runner error", "Cannot move a stopped train", "Cannot move a stopped train");
		}
	}

	@Override
	public Line getLine() {
		return line;
	}

	@Override
	public List<Train> getTrains() {
		return trains;
	}

	@Override
	public LineStatus getLineStatus() {
		return dir;
	}

	@Override
	public void setMoving(Train t) {
		t.setStatus(TrainStatus.MOVING);		
	}

	@Override
	public void setStopped(Train t) {
		t.setStatus(TrainStatus.STOPPED);
	}

	@Override
	public String getLog() {
		return runner.toString();
	}
	
}
