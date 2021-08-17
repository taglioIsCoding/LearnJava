package minirail.tests.model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import minirail.model.*;

public class LineTest {
	
	@Test
	public void testOK_1() {
		Section[] sections = new Section[] {
				new Section("Casarsa-Piana", 90), 
				new Section("Piana-Colle dell'Orso", 90),
				new Section("Colle dell'Orso-Dolmeno", 70)
		};
		Line line = new Line("Casarsa-Dolmeno", Arrays.asList(sections));
		assertEquals("Casarsa-Dolmeno",line.getName());
		assertEquals(3,line.getSections().size());
		assertEquals(250,line.getLength(),0.01);
		assertEquals(sections[0],line.getSection(0));
		assertEquals(sections[1],line.getSection(1));
		assertEquals(sections[2],line.getSection(2));
		assertEquals(70,line.getMinimumSectionLength(), 0.01);		
	}
	
	@Test
	public void testKO_NoVoidName() {
		Section[] sections = new Section[] {
				new Section("Casarsa-Piana", 90), 
				new Section("Piana-Colle dell'Orso", 90),
				new Section("Colle dell'Orso-Dolmeno", 70)
		};
		assertThrows(IllegalArgumentException.class, () -> new Line(null, Arrays.asList(sections)));
		assertThrows(IllegalArgumentException.class, () -> new Line("", Arrays.asList(sections)));
	}

	@Test
	public void testKO_NoVoidSectionList() {
		assertThrows(IllegalArgumentException.class, () -> new Line("uh", null));
		assertThrows(IllegalArgumentException.class, () -> new Line("uh", new ArrayList<>()));
	}

}
