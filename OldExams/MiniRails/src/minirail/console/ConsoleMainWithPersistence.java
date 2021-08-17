package minirail.console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import minirail.model.*;
import minirail.persistence.BadFileFormatException;
import minirail.persistence.ConfigReader;
import minirail.persistence.LineReader;
import minirail.persistence.MyConfigReader;
import minirail.persistence.MyLineReader;

public class ConsoleMainWithPersistence {
	
	public static void main(String[] args) throws BadFileFormatException, IOException {
		// run just ONE of these!
		ConfigReader configReader = new MyConfigReader(new BufferedReader(new FileReader("config.txt")));
		Gauge gauge = configReader.getGauge();
		List<Train> trains = configReader.getTrains();
		//
		LineReader linesReader = new MyLineReader(new BufferedReader(new FileReader("lines.txt")));
		Line line = linesReader.getLine();
		//
		LineStatus dir = new MyLineStatus(line);
		//
		Train t0 = trains.get(0);
		Train t1 = trains.get(1);
		dir.putTrain(t0, 85);
		//
		Map<String, LineStatus> linesMap = Map.of(line.getName(), dir);
		Runner runner = new MyRunner(linesMap, gauge);
		//		
		System.out.println("==============TEST #1==============");
		t0.changeStatus(); // now MOVING
		System.out.println(runner);
		while (dir.getTrainLocation(t0)<249) {
			runner.clock(1.0);
			System.out.println(runner);
		}
		runner.clock(1.0);
		System.out.println(runner);
		t0.changeStatus(); // now STOPPED
		//
		System.out.println("==============TEST #2==============");
		dir.putTrain(t0, 85);
		dir.putTrain(t1, 160);
		t0.changeStatus(); // now MOVING
		t1.changeStatus(); // now MOVING
		System.out.println(runner);
		while (dir.getTrainLocation(t1)<290) {
			runner.clock(1.0);
			System.out.println(runner);
		}
		t0.changeStatus(); // now STOPPED
		t1.changeStatus(); // now STOPPED
	}

}
