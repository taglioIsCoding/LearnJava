package flights.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import flights.model.Aircraft;
import flights.model.Airport;
import flights.model.FlightSchedule;

public class MyFlightScheduleReader implements FlightScheduleReader{
	
	private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY);
	private static final String SEPARATOR1 = ",";
	private static final String SEPARATOR2 = "-";
	private static final String SEPARATOR3 = ":";

	@Override
	public Collection<FlightSchedule> read(Reader reader, Map<String, Airport> airportMap,
			Map<String, Aircraft> aircraftMap) throws IOException, BadFileFormatException{
		
		BufferedReader bufferedReader = new BufferedReader(reader);
		ArrayList<FlightSchedule> flightScheduleList = new ArrayList<FlightSchedule>();
		FlightSchedule flightSchedule;
		
		while(( flightSchedule = readShedule(bufferedReader,airportMap,aircraftMap )) != null) {
			flightScheduleList.add(flightSchedule);
		}
		
		
		return flightScheduleList;
	}
	
	
	private FlightSchedule readShedule(BufferedReader reader, Map<String, Airport> airportMap, Map<String, Aircraft> aircraftMap) throws IOException, BadFileFormatException  {
		
		FlightSchedule f;
		String line = reader.readLine();
		
		if(line == null || line.isEmpty()) {
			return null;
		}
//		System.out.println("-------------------------------");
//		System.out.println(line);
		
		try {
			StringTokenizer stk = new StringTokenizer(line);
			String code = stk.nextToken(SEPARATOR1).trim();
			String cityCodeDep = stk.nextToken(SEPARATOR1).trim();
			String hour = stk.nextToken(SEPARATOR1).trim();
			String cityCodeArr = stk.nextToken(SEPARATOR1).trim();
			String hourArr = stk.nextToken(SEPARATOR1).trim();
			String dayOffset = stk.nextToken(SEPARATOR1).trim();
			String days = stk.nextToken(SEPARATOR1).trim();
			String aircraftCode = stk.nextToken("\n\r").trim().substring(1);
			
			
			
		
			Set<DayOfWeek> daysOfFly = new TreeSet<DayOfWeek>(readDays(days));
//			System.out.println( daysOfFly);
			LocalTime timeArr = LocalTime.parse(hourArr, timeFormatter);
			LocalTime timeDep = LocalTime.parse(hour, timeFormatter);
			Aircraft airc = aircraftMap.get(aircraftCode);
			Airport portArr =  airportMap.get(cityCodeArr);
			Airport portDep =  airportMap.get(cityCodeDep);
				if(airc == null || portArr == null || portDep == null) {
					System.out.println(airc+portDep.toString());
					throw new BadFileFormatException();
				}
			
			f = new FlightSchedule(code, portDep, timeDep,  portArr, timeArr, 
					Integer.parseInt(dayOffset), daysOfFly, airc);
			
			
		} catch(IOException e) {
			throw new IOException(e);
		} catch(Exception e) {
			throw new BadFileFormatException(e);
		}
		
		return f;
	}
	
	private Collection<DayOfWeek>  readDays(String stringDays) throws IOException, BadFileFormatException {
		Set<DayOfWeek> days = new TreeSet<DayOfWeek>();
		
		
		
		for(int i=0; i< 7; i++) {
			int dayInt = stringDays.charAt(i);
			if(!Character.isDigit(stringDays.charAt(i)) && stringDays.charAt(i)!= SEPARATOR2.charAt(0)) {
				throw new BadFileFormatException();
			}else if((dayInt != -3 && dayInt > 57) || dayInt-49 > i) {
				throw new BadFileFormatException();
			}else if(stringDays.charAt(i)!= SEPARATOR2.charAt(0)) {
				days.add(DayOfWeek.of(i+1));	
			}
		}
		
		return days;
		
	}


}
