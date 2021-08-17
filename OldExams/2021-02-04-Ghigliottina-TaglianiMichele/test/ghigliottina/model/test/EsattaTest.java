package ghigliottina.model.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ghigliottina.model.Esatta;

class EsattaTest {

	@Test
	void test() {
		assertEquals(0, Esatta.FIRST.ordinal());
		assertEquals(1, Esatta.SECOND.ordinal());
		assertArrayEquals( new Esatta[]{Esatta.FIRST,Esatta.SECOND}, Esatta.values());
	}

}
