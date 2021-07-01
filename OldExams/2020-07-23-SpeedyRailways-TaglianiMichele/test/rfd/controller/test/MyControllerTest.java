package rfd.controller.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import rfd.controller.Controller;
import rfd.controller.MyController;
import rfd.model.Station;
import rfd.model.RailwayLine;
import rfd.model.Route;
import rfd.model.Segment;

public class MyControllerTest {

	@Test
	void testOK() {
		RailwayLine lineaBoLe = new RailwayLine(Map.of(
				"Santarcangelo di Romagna", new Station("Santarcangelo di Romagna", 101.27, 120),
				"RiminiFiera", new Station("RiminiFiera", 106.97, 120),
				"Bologna Centrale", new Station("Bologna Centrale", 0, 120),
				"Giulianova", new Station("Giulianova", 312.36, 120)
				),
				new TreeSet<>(Set.of("Bologna Centrale")
				));
		RailwayLine lineaBoMi = new RailwayLine(Map.of(
				"Modena", new Station("Modena", 36.93, 160),
				"Reggio Emilia", new Station("Reggio Emilia", 61.44, 160),
				"Parma", new Station("Parma", 89.74, 160),
				"Bologna Centrale", new Station("Bologna Centrale", 0.0, 160),
				"Piacenza", new Station("Piacenza", 146.82, 160),
				"Milano Lambrate", new Station("Milano Lambrate", 212.40, 60),
				"Milano Centrale", new Station("Milano Centrale", 216.18, 60)
				),
				new TreeSet<>(Set.of("Bologna Centrale", "Parma", "Modena", "Milano Centrale")
				));
		RailwayLine lineaBoVr = new RailwayLine(Map.of(
				"Verona Porta Nuova", new Station("Verona Porta Nuova", 114.95, 200),
				"Bologna Centrale", new Station("Bologna Centrale", 0.0, 200)
				),
				new TreeSet<>(Set.of("Bologna Centrale", "Verona Porta Nuova")
				));
		RailwayLine lineaMoVr = new RailwayLine(Map.of(
				"Verona Porta Nuova", new Station("Verona Porta Nuova", 97.86, 110),
				"Modena", new Station("Modena", 0.0, 110)
				),
				new TreeSet<>(Set.of("Modena", "Verona Porta Nuova")
				));
		RailwayLine lineaMiVr = new RailwayLine(Map.of(
				"Milano Centrale", new Station("Milano Centrale", 0.0, 60),
				"Milano Lambrate", new Station("Milano Lambrate", 3.8, 90),
				"Pioltello-Limito", new Station("Pioltello-Limito", 12.31, 140),
				"Verona Porta Nuova", new Station("Verona Porta Nuova", 147.48, 70)
				),
				new TreeSet<>(Set.of("Milano Centrale", "Verona Porta Nuova")
				));
				
		Controller controller = new MyController(Set.of(lineaBoMi,lineaBoLe,lineaBoVr,lineaMoVr,lineaMiVr));
	
		assertEquals(List.of(new Route(
					new Segment(lineaBoLe, new Station("Giulianova", 312.36, 120), new Station("Bologna Centrale", 0.00, 120))
					)),
				controller.getRoutes("Giulianova", "Bologna Centrale"));
		
		assertEquals(List.of(new Route(
				new Segment(lineaBoMi, new Station("Modena", 36.93, 160), new Station("Bologna Centrale", 0.0, 160)),
				new Segment(lineaBoLe, new Station("Bologna Centrale", 0.0, 120), new Station("Giulianova", 312.36, 120))
				)),
				controller.getRoutes("Modena","Giulianova"));

		assertEquals(List.of(new Route(
				new Segment(lineaBoMi, new Station("Reggio Emilia", 61.44, 160), new Station("Bologna Centrale", 0.0, 160)),
				new Segment(lineaBoLe, new Station("Bologna Centrale", 0.0, 120), new Station("Giulianova", 312.36, 120))
				)),
				controller.getRoutes("Reggio Emilia","Giulianova"));
		
		assertTrue(List.of(
				new Route(
							new Segment(lineaBoMi, new Station("Reggio Emilia", 61.44, 160), new Station("Modena", 36.93, 160)),
							new Segment(lineaMoVr, new Station("Modena", 0.000, 110), new Station("Verona Porta Nuova", 97.86, 110))
				),
				new Route(
						new Segment(lineaBoMi, new Station("Reggio Emilia", 61.44, 160), new Station("Bologna Centrale", 0.00, 160)),
						new Segment(lineaBoVr, new Station("Bologna Centrale", 0.0, 200), new Station("Verona Porta Nuova", 114.95, 200))
				),
				new Route(
							new Segment(lineaBoMi, new Station("Reggio Emilia", 61.44, 160), new Station("Milano Centrale", 216.18, 60)),
							new Segment(lineaMiVr, new Station("Milano Centrale", 0.0, 60), new Station("Verona Porta Nuova", 147.48, 70))
				)).containsAll(
				controller.getRoutes("Reggio Emilia","Verona Porta Nuova")));
		
		assertEquals(Collections.emptyList(),
				controller.getRoutes("Pioltello-Limito","Giulianova"));
	}
	
	@Test
	void testOKStationNames() {
		RailwayLine lineaBoVr = new RailwayLine(Map.of(
				"Verona Porta Nuova", new Station("Verona Porta Nuova", 114.95, 200),
				"Bologna Centrale", new Station("Bologna Centrale", 0.0, 200)
				),
				new TreeSet<>(Set.of("Bologna Centrale", "Verona Porta Nuova")
				));
		RailwayLine lineaMoVr = new RailwayLine(Map.of(
				"Verona Porta Nuova", new Station("Verona Porta Nuova", 97.86, 110),
				"Modena", new Station("Modena", 0.0, 110)
				),
				new TreeSet<>(Set.of("Modena", "Verona Porta Nuova")
				));
		RailwayLine lineaBoMi = new RailwayLine(Map.of(
				"Modena", new Station("Modena", 36.93, 160),
				"Reggio Emilia", new Station("Reggio Emilia", 61.44, 160),
				"Parma", new Station("Parma", 89.74, 160),
				"Bologna Centrale", new Station("Bologna Centrale", 0.0, 160),
				"Piacenza", new Station("Piacenza", 146.82, 160)
				),
				new TreeSet<>(Set.of("Bologna Centrale", "Parma", "Modena")
				));
		
		Controller controller = new MyController(Set.of(lineaBoMi,lineaBoVr,lineaMoVr));
		
		assertEquals(List.of("Bologna Centrale", "Modena", "Parma", "Piacenza", "Reggio Emilia", "Verona Porta Nuova" ),
					controller.getStationNames().stream().sorted().collect(Collectors.toList()));
	}

}
