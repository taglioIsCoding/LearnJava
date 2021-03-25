package clock;

import sevensegmentdisplay.Display;
import java.time.Clock;


public class ClockDisplay {

	public static void main(String[] args) {
		Display d = new Display(6);
		Clock c = Clock.systemDefaultZone();
		
		while(true) {
			//System.out.println(c.instant());
			int hours =  Integer.parseInt((c.instant().toString().substring(11, 13)));
			hours++;
			int minutes =  Integer.parseInt((c.instant().toString().substring(14, 16)));
			int seconds =  Integer.parseInt((c.instant().toString().substring(17, 19)));
			//System.out.println(hours + " " + minutes + " " + seconds);
			d.setValue(hours * 10000 + minutes * 100  + seconds);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
