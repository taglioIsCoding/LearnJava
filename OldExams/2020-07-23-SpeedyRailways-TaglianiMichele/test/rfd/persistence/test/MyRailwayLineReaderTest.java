package rfd.persistence.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

import rfd.model.RailwayLine;
import rfd.persistence.MyRailwayLineReader;

public class MyRailwayLineReaderTest {

	@Test
	void testRailwayLineReaderOK() throws IOException {
		StringReader fakeReader = new StringReader(
				"  216,18  Milano Centrale                                     HUB    140\r\n" + 
				"  212,40  Milano Lambrate                                     HUB    140\r\n" + 
				"  208,75  Milano Rogoredo                                     HUB    140\r\n" + 
				"  206,61  San Donato Milanese                                        140\r\n" + 
				"  204,54  Borgolombardo                                              140\r\n" + 
				"  202,61  San Giuliano Milanese                                      140\r\n" + 
				"  197,91  Melegnano                                                  140\r\n" + 
				"  193,92  San Zenone al Lambro                                       140\r\n" + 
				"  190,41  Tavazzano                                                  140\r\n" + 
				"  182,69  Lodi                                                       140\r\n" + 
				"  170,78  Secugnago                                                  140\r\n" + 
				"  163,83  Casalpusterlengo                                           140\r\n" + 
				"  158,96  Codogno                                                    140\r\n" + 
				"  154,89  Santo Stefano Lodigiano                                    140\r\n" + 
				"  146,82  Piacenza                                                   140\r\n" + 
				"  138,00  Pontenure                                                  140\r\n" + 
				"  131,86  Cadeo                                                      140\r\n" + 
				"  125,21  Fiorenzuola                                                140\r\n" + 
				"  111,75  Fidenza                                                    140\r\n" + 
				"  102,15  Castelguelfo                                               140\r\n" + 
				"   99,17  Ponte Taro per Medesano                                    140\r\n" + 
				"   89,74  Parma                                               HUB    140\r\n" + 
				"   83,48  San Prospero Parmense                                      140\r\n" + 
				"   78,88  Sant'Ilario d'Enza                                         140\r\n" + 
				"   61,44  Reggio Emilia                                              140\r\n" + 
				"   49,59  Rubiera                                                    140\r\n" + 
				"   45,70  Marzaglia                                                  140\r\n" + 
				"   36,93  Modena                                              HUB    140\r\n" + 
				"   25,01  Castelfranco Emilia                                        140\r\n" + 
				"   17,13  Samoggia                                                   140\r\n" + 
				"   12,74  Anzola dell'Emilia                                         140\r\n" + 
				"    0,00  Bologna Centrale                                    HUB    140"
				);
		
		RailwayLine line = new MyRailwayLineReader().getRailwayLine(fakeReader);
		
		assertEquals(32,line.getStationNames().size());
		assertTrue(line.getStationNames().contains("San Prospero Parmense"));
		assertTrue(line.getStationNames().contains("Fidenza"));
		assertTrue(line.getStationNames().contains("Anzola dell'Emilia"));
		assertEquals(6,line.getHubNames().size());
		
		assertTrue(line.getHubNames().contains("Bologna Centrale"));
		assertTrue(line.getHubNames().contains("Milano Centrale"));
		assertTrue(line.getHubNames().contains("Milano Lambrate"));
		assertTrue(line.getHubNames().contains("Milano Rogoredo"));
		assertTrue(line.getHubNames().contains("Parma"));
		assertTrue(line.getHubNames().contains("Modena"));
		
		assertEquals(12.74, line.getStation("Anzola dell'Emilia").get().getKm());
		assertEquals(99.17, line.getStation("Ponte Taro per Medesano").get().getKm());
		assertEquals(138.0, line.getStation("Pontenure").get().getKm());
		assertEquals(0.0, line.getStation("Bologna Centrale").get().getKm());
	}
	
	@Test
	void testRailwayLineReaderOK_UncapitalizedHub() throws IOException {
		StringReader fakeReader = new StringReader(
				"  216,18  Milano Centrale                                     hUB    140\r\n" + 
				"  212,40  Milano Lambrate                                     HUB    140\r\n" + 
				"  208,75  Milano Rogoredo                                     HUb    140\r\n" + 
				"  206,61  San Donato Milanese                                        140\r\n" + 
				"  204,54  Borgolombardo                                              140\r\n" + 
				"  202,61  San Giuliano Milanese                                      140\r\n" + 
				"  197,91  Melegnano                                                  140\r\n" + 
				"  193,92  San Zenone al Lambro                                       140\r\n" + 
				"  190,41  Tavazzano                                                  140\r\n" + 
				"  182,69  Lodi                                                       140\r\n" + 
				"  170,78  Secugnago                                                  140\r\n" + 
				"  163,83  Casalpusterlengo                                           140\r\n" + 
				"  158,96  Codogno                                                    140\r\n" + 
				"  154,89  Santo Stefano Lodigiano                                    140\r\n" + 
				"  146,82  Piacenza                                                   140\r\n" + 
				"  138,00  Pontenure                                                  140\r\n" + 
				"  131,86  Cadeo                                                      140\r\n" + 
				"  125,21  Fiorenzuola                                                140\r\n" + 
				"  111,75  Fidenza                                                    140\r\n" + 
				"  102,15  Castelguelfo                                               140\r\n" + 
				"   99,17  Ponte Taro per Medesano                                    140\r\n" + 
				"   89,74  Parma                                               hub    140\r\n" + 
				"   83,48  San Prospero Parmense                                      140\r\n" + 
				"   78,88  Sant'Ilario d'Enza                                         140\r\n" + 
				"   61,44  Reggio Emilia                                              140\r\n" + 
				"   49,59  Rubiera                                                    140\r\n" + 
				"   45,70  Marzaglia                                                  140\r\n" + 
				"   36,93  Modena                                              HUB    140\r\n" + 
				"   25,01  Castelfranco Emilia                                        140\r\n" + 
				"   17,13  Samoggia                                                   140\r\n" + 
				"   12,74  Anzola dell'Emilia                                         140\r\n" + 
				"    0,00  Bologna Centrale                                    HUB    140"
				);
		
		RailwayLine line = new MyRailwayLineReader().getRailwayLine(fakeReader);
		
		assertTrue(line.getStation("Milano Centrale").isPresent());
		assertTrue(line.getStation("Milano Rogoredo").isPresent());
		assertTrue(line.getStation("Parma").isPresent());
		assertTrue(line.getStation("Bologna Centrale").isPresent());
		
		assertTrue(line.getHubNames().contains("Bologna Centrale"));
		assertTrue(line.getHubNames().contains("Milano Centrale"));
		assertTrue(line.getHubNames().contains("Milano Lambrate"));
		assertTrue(line.getHubNames().contains("Milano Rogoredo"));
		assertTrue(line.getHubNames().contains("Parma"));
		assertTrue(line.getHubNames().contains("Modena"));
	}
	
	@Test
	void testRailwayLineReaderKO_MissingNewLine() throws IOException {
		StringReader fakeReader = new StringReader(
				"  216,18  Milano Centrale                                     HUB    140 " + 
				"  212,40  Milano Lambrate                                     HUB    140\r\n" + 
				"  208,75  Milano Rogoredo                                     HUB    140\r\n" + 
				"  206,61  San Donato Milanese                                        140\r\n" + 
				"  204,54  Borgolombardo                                              140\r\n" + 
				"  202,61  San Giuliano Milanese                                      140\r\n" + 
				"  197,91  Melegnano                                                  140\r\n" + 
				"  193,92  San Zenone al Lambro                                       140\r\n" + 
				"  190,41  Tavazzano                                                  140\r\n" + 
				"  182,69  Lodi                                                       140\r\n" + 
				"  170,78  Secugnago                                                  140\r\n" + 
				"  163,83  Casalpusterlengo                                           140\r\n" + 
				"  158,96  Codogno                                                    140\r\n" + 
				"  154,89  Santo Stefano Lodigiano                                    140\r\n" + 
				"  146,82  Piacenza                                                   140\r\n" + 
				"  138,00  Pontenure                                                  140\r\n" + 
				"  131,86  Cadeo                                                      140\r\n" + 
				"  125,21  Fiorenzuola                                                140\r\n" + 
				"  111,75  Fidenza                                                    140\r\n" + 
				"  102,15  Castelguelfo                                               140\r\n" + 
				"   99,17  Ponte Taro per Medesano                                    140\r\n" + 
				"   89,74  Parma                                               HUB    140\r\n" + 
				"   83,48  San Prospero Parmense                                      140\r\n" + 
				"   78,88  Sant'Ilario d'Enza                                         140\r\n" + 
				"   61,44  Reggio Emilia                                              140\r\n" + 
				"   49,59  Rubiera                                                    140\r\n" + 
				"   45,70  Marzaglia                                                  140\r\n" + 
				"   36,93  Modena                                              HUB    140\r\n" + 
				"   25,01  Castelfranco Emilia                                        140\r\n" + 
				"   17,13  Samoggia                                                   140\r\n" + 
				"   12,74  Anzola dell'Emilia                                         140\r\n" + 
				"    0,00  Bologna Centrale                                    HUB    140"
				);
		assertThrows(IllegalArgumentException.class, () -> new MyRailwayLineReader().getRailwayLine(fakeReader));
	}
	
	@Test
	void testRailwayLineReaderKO_MissingStationName() throws IOException {
		StringReader fakeReader = new StringReader(
				"  216,18                                                      HUB    140\r\n" + 
				"  212,40  Milano Lambrate                                     HUB    140\r\n" + 
				"  208,75  Milano Rogoredo                                     HUB    140\r\n" + 
				"  206,61  San Donato Milanese                                        140\r\n" + 
				"  204,54  Borgolombardo                                              140\r\n" + 
				"  202,61  San Giuliano Milanese                                      140\r\n" + 
				"  197,91  Melegnano                                                  140\r\n" + 
				"  193,92  San Zenone al Lambro                                       140\r\n" + 
				"  190,41  Tavazzano                                                  140\r\n" + 
				"  182,69  Lodi                                                       140\r\n" + 
				"  170,78  Secugnago                                                  140\r\n" + 
				"  163,83  Casalpusterlengo                                           140\r\n" + 
				"  158,96  Codogno                                                    140\r\n" + 
				"  154,89  Santo Stefano Lodigiano                                    140\r\n" + 
				"  146,82  Piacenza                                                   140\r\n" + 
				"  138,00  Pontenure                                                  140\r\n" + 
				"  131,86  Cadeo                                                      140\r\n" + 
				"  125,21  Fiorenzuola                                                140\r\n" + 
				"  111,75  Fidenza                                                    140\r\n" + 
				"  102,15  Castelguelfo                                               140\r\n" + 
				"   99,17  Ponte Taro per Medesano                                    140\r\n" + 
				"   89,74  Parma                                               HUB    140\r\n" + 
				"   83,48  San Prospero Parmense                                      140\r\n" + 
				"   78,88  Sant'Ilario d'Enza                                         140\r\n" + 
				"   61,44  Reggio Emilia                                              140\r\n" + 
				"   49,59  Rubiera                                                    140\r\n" + 
				"   45,70  Marzaglia                                                  140\r\n" + 
				"   36,93  Modena                                              HUB    140\r\n" + 
				"   25,01  Castelfranco Emilia                                        140\r\n" + 
				"   17,13  Samoggia                                                   140\r\n" + 
				"   12,74  Anzola dell'Emilia                                         140\r\n" + 
				"    0,00  Bologna Centrale                                    HUB    140"
				);
		assertThrows(IllegalArgumentException.class, () -> new MyRailwayLineReader().getRailwayLine(fakeReader));
	}
	
	@Test
	void testRailwayLineReaderKO_MissingProgressiva() throws IOException {
		StringReader fakeReader = new StringReader(
				"          Milano Centrale                                     HUB    140\r\n" + 
				"  212,40  Milano Lambrate                                     HUB    140\r\n" + 
				"  208,75  Milano Rogoredo                                     HUB    140\r\n" + 
				"  206,61  San Donato Milanese                                        140\r\n" + 
				"  204,54  Borgolombardo                                              140\r\n" + 
				"  202,61  San Giuliano Milanese                                      140\r\n" + 
				"  197,91  Melegnano                                                  140\r\n" + 
				"  193,92  San Zenone al Lambro                                       140\r\n" + 
				"  190,41  Tavazzano                                                  140\r\n" + 
				"  182,69  Lodi                                                       140\r\n" + 
				"  170,78  Secugnago                                                  140\r\n" + 
				"  163,83  Casalpusterlengo                                           140\r\n" + 
				"  158,96  Codogno                                                    140\r\n" + 
				"  154,89  Santo Stefano Lodigiano                                    140\r\n" + 
				"  146,82  Piacenza                                                   140\r\n" + 
				"  138,00  Pontenure                                                  140\r\n" + 
				"  131,86  Cadeo                                                      140\r\n" + 
				"  125,21  Fiorenzuola                                                140\r\n" + 
				"  111,75  Fidenza                                                    140\r\n" + 
				"  102,15  Castelguelfo                                               140\r\n" + 
				"   99,17  Ponte Taro per Medesano                                    140\r\n" + 
				"   89,74  Parma                                               HUB    140\r\n" + 
				"   83,48  San Prospero Parmense                                      140\r\n" + 
				"   78,88  Sant'Ilario d'Enza                                         140\r\n" + 
				"   61,44  Reggio Emilia                                              140\r\n" + 
				"   49,59  Rubiera                                                    140\r\n" + 
				"   45,70  Marzaglia                                                  140\r\n" + 
				"   36,93  Modena                                              HUB    140\r\n" + 
				"   25,01  Castelfranco Emilia                                        140\r\n" + 
				"   17,13  Samoggia                                                   140\r\n" + 
				"   12,74  Anzola dell'Emilia                                         140\r\n" + 
				"    0,00  Bologna Centrale                                    HUB    140"
				);
		assertThrows(IllegalArgumentException.class, () -> new MyRailwayLineReader().getRailwayLine(fakeReader));
	}
	
	@Test
	void testRailwayLineReaderKO_BadFormatInKm() throws IOException {
		StringReader fakeReader = new StringReader(
				"  AAA.18  Milano Centrale                                     HUB    140\r\n" + 
				"  212,40  Milano Lambrate                                     HUB    140\r\n" + 
				"  208,75  Milano Rogoredo                                     HUB    140\r\n" + 
				"  206,61  San Donato Milanese                                        140\r\n" + 
				"  204,54  Borgolombardo                                              140\r\n" + 
				"  202,61  San Giuliano Milanese                                      140\r\n" + 
				"  197,91  Melegnano                                                  140\r\n" + 
				"  193,92  San Zenone al Lambro                                       140\r\n" + 
				"  190,41  Tavazzano                                                  140\r\n" + 
				"  182,69  Lodi                                                       140\r\n" + 
				"  170,78  Secugnago                                                  140\r\n" + 
				"  163,83  Casalpusterlengo                                           140\r\n" + 
				"  158,96  Codogno                                                    140\r\n" + 
				"  154,89  Santo Stefano Lodigiano                                    140\r\n" + 
				"  146,82  Piacenza                                                   140\r\n" + 
				"  138,00  Pontenure                                                  140\r\n" + 
				"  131,86  Cadeo                                                      140\r\n" + 
				"  125,21  Fiorenzuola                                                140\r\n" + 
				"  111,75  Fidenza                                                    140\r\n" + 
				"  102,15  Castelguelfo                                               140\r\n" + 
				"   99,17  Ponte Taro per Medesano                                    140\r\n" + 
				"   89,74  Parma                                               HUB    140\r\n" + 
				"   83,48  San Prospero Parmense                                      140\r\n" + 
				"   78,88  Sant'Ilario d'Enza                                         140\r\n" + 
				"   61,44  Reggio Emilia                                              140\r\n" + 
				"   49,59  Rubiera                                                    140\r\n" + 
				"   45,70  Marzaglia                                                  140\r\n" + 
				"   36,93  Modena                                              HUB    140\r\n" + 
				"   25,01  Castelfranco Emilia                                        140\r\n" + 
				"   17,13  Samoggia                                                   140\r\n" + 
				"   12,74  Anzola dell'Emilia                                         140\r\n" + 
				"    0,00  Bologna Centrale                                    HUB    140"
				);
		assertThrows(IllegalArgumentException.class, () -> new MyRailwayLineReader().getRailwayLine(fakeReader));
	}
	
	@Test
	void testRailwayLineReaderKO_BadSeparatorInKm() throws IOException {
		StringReader fakeReader = new StringReader(
				"  216.18  Milano Centrale                                     HUB    140\r\n" + 
				"  212,40  Milano Lambrate                                     HUB    140\r\n" + 
				"  208,75  Milano Rogoredo                                     HUB    140\r\n" + 
				"  206,61  San Donato Milanese                                        140\r\n" + 
				"  204,54  Borgolombardo                                              140\r\n" + 
				"  202,61  San Giuliano Milanese                                      140\r\n" + 
				"  197,91  Melegnano                                                  140\r\n" + 
				"  193,92  San Zenone al Lambro                                       140\r\n" + 
				"  190,41  Tavazzano                                                  140\r\n" + 
				"  182,69  Lodi                                                       140\r\n" + 
				"  170,78  Secugnago                                                  140\r\n" + 
				"  163,83  Casalpusterlengo                                           140\r\n" + 
				"  158,96  Codogno                                                    140\r\n" + 
				"  154,89  Santo Stefano Lodigiano                                    140\r\n" + 
				"  146,82  Piacenza                                                   140\r\n" + 
				"  138,00  Pontenure                                                  140\r\n" + 
				"  131,86  Cadeo                                                      140\r\n" + 
				"  125,21  Fiorenzuola                                                140\r\n" + 
				"  111,75  Fidenza                                                    140\r\n" + 
				"  102,15  Castelguelfo                                               140\r\n" + 
				"   99,17  Ponte Taro per Medesano                                    140\r\n" + 
				"   89,74  Parma                                               HUB    140\r\n" + 
				"   83,48  San Prospero Parmense                                      140\r\n" + 
				"   78,88  Sant'Ilario d'Enza                                         140\r\n" + 
				"   61,44  Reggio Emilia                                              140\r\n" + 
				"   49,59  Rubiera                                                    140\r\n" + 
				"   45,70  Marzaglia                                                  140\r\n" + 
				"   36,93  Modena                                              HUB    140\r\n" + 
				"   25,01  Castelfranco Emilia                                        140\r\n" + 
				"   17,13  Samoggia                                                   140\r\n" + 
				"   12,74  Anzola dell'Emilia                                         140\r\n" + 
				"    0,00  Bologna Centrale                                    HUB    140"
				);
		assertThrows(IllegalArgumentException.class, () -> new MyRailwayLineReader().getRailwayLine(fakeReader));
	}

}
