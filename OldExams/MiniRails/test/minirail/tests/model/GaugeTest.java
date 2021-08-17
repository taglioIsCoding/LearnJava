package minirail.tests.model;

import static org.junit.Assert.*;

import org.junit.Test;

import minirail.model.*;

public class GaugeTest {
	
	@Test
	public void testOK_ratio() {
		assertEquals(160,Gauge.N.getRatio());
		assertEquals(120,Gauge.TT.getRatio());
		assertEquals(87,Gauge.H0.getRatio());
		assertEquals(220,Gauge.Z.getRatio());
	}
	
	@Test
	public void testOK_speedConversion() {
		assertEquals(27.78,Gauge.N.kmhToCms(160), 0.01);
		assertEquals(51.08,Gauge.H0.kmhToCms(160), 0.01);
	}

}
