package rfd.persistence.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import rfd.persistence.RailwayLineReader;


public class AllLineNamesTest {

	@Test
	void testFilesIntegrity() throws IOException {
		List<String> lineNames = RailwayLineReader.getAllLineNames(Path.of(".")).get();
		assertEquals(7, lineNames.size());
		assertTrue(lineNames.contains("PR-BS.line"));
		assertTrue(lineNames.contains("BO-PD.line"));
		assertTrue(lineNames.contains("BO-LE.line"));
		assertTrue(lineNames.contains("BO-MI.line"));
		assertTrue(lineNames.contains("BO-VR.line"));
		assertTrue(lineNames.contains("MI-VE.line"));
		assertTrue(lineNames.contains("MO-VR.line"));
	}

}
