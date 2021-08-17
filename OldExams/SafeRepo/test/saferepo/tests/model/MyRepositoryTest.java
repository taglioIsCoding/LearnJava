package saferepo.tests.model;

import static org.junit.Assert.*;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import saferepo.model.*;

public class MyRepositoryTest
{
	Document fakeDoc1, fakeDoc2, fakeDoc3;
	Repository repo;
	
	@Before
	public void setUp() {
		fakeDoc1 = new Document("doc1", Paths.get("testFiles/doc1"));
		fakeDoc2 = new Document("doc2", Paths.get("testFiles/doc2"));
		fakeDoc3 = new Document("doc3", Paths.get("testFiles/doc3"));
		
		repo = new MyRepository();
		
		repo.add(fakeDoc1, LocalDateTime.parse("2018-12-30T09:39:02"));
		repo.add(fakeDoc1, LocalDateTime.parse("2018-12-30T09:39:03"));
		repo.add(fakeDoc1, LocalDateTime.parse("2018-12-30T09:40:04"));
		repo.add(fakeDoc1, LocalDateTime.parse("2018-12-30T09:40:05"));
		repo.add(fakeDoc1, LocalDateTime.parse("2018-12-30T09:42:06"));
		repo.add(fakeDoc1, LocalDateTime.parse("2018-12-30T09:42:07"));
		
		repo.add(fakeDoc1, LocalDateTime.parse("2018-12-30T09:44:08"));
		repo.add(fakeDoc2, LocalDateTime.parse("2018-12-30T09:39:04"));
		repo.add(fakeDoc2, LocalDateTime.parse("2018-12-30T09:39:05"));
		repo.add(fakeDoc2, LocalDateTime.parse("2018-12-30T09:40:04"));
		repo.add(fakeDoc2, LocalDateTime.parse("2018-12-30T09:40:08"));
		repo.add(fakeDoc2, LocalDateTime.parse("2018-12-30T09:41:06"));
		repo.add(fakeDoc2, LocalDateTime.parse("2018-12-30T09:41:07"));
		repo.add(fakeDoc2, LocalDateTime.parse("2018-12-30T09:43:08"));
		
		repo.add(fakeDoc3, LocalDateTime.parse("2018-12-30T09:39:01"));
		repo.add(fakeDoc3, LocalDateTime.parse("2018-12-30T09:39:05"));
		repo.add(fakeDoc3, LocalDateTime.parse("2018-12-30T09:40:06"));
		repo.add(fakeDoc3, LocalDateTime.parse("2018-12-30T09:40:08"));
		repo.add(fakeDoc3, LocalDateTime.parse("2018-12-30T09:40:09"));
		repo.add(fakeDoc3, LocalDateTime.parse("2018-12-30T09:41:10"));
		repo.add(fakeDoc3, LocalDateTime.parse("2018-12-30T09:44:09"));
	}
	
	@Test
	public void testCurrentVersion_Doc1()
	{
		// System.out.println("Current version of doc1: " + repo.getCurrentVersion("doc1").get());
		// version=6, timestamp=2018-12-30T09:44:08				
		assertEquals(6, repo.getCurrentVersion("doc1").get().getVersion());
		assertEquals("2018-12-30T09:44:08", repo.getCurrentVersion("doc1").get().getTimeStamp().toString());
	}
	
	@Test
	public void testCurrentVersion_Doc3()
	{
		// System.out.println("Current version of doc3: " + repo.getCurrentVersion("doc3").get());
		// version=6, timestamp=2018-12-30T09:44:09		
		assertEquals(6, repo.getCurrentVersion("doc3").get().getVersion());
		assertEquals("2018-12-30T09:44:09", repo.getCurrentVersion("doc3").get().getTimeStamp().toString());
	}

	@Test
	public void testPreviousVersion2_Doc3()
	{
		// System.out.println("Previous version #2 of doc3: " +  repo.getVersion("doc3", 2) );
		// version=2, timestamp=2018-12-30T09:40:06		
		assertEquals("doc3", repo.getVersion("doc3", 2).get().getID());
		assertEquals("testFiles/doc3", repo.getVersion("doc3", 2).get().getPath().toString());
		assertEquals(2,                     repo.getVersion("doc3", 2).get().getVersion());
		assertEquals("2018-12-30T09:40:06", repo.getVersion("doc3", 2).get().getTimeStamp().toString());
	}
	
	@Test
	public void testPreviousVersion3_Doc3()
	{
		// System.out.println("Previous version #3 of doc3: " +  repo.getVersion("doc3", 3) );
		// version=2, timestamp=2018-12-30T09:40:08		
		assertEquals("doc3", repo.getVersion("doc3", 2).get().getID());
		assertEquals("testFiles/doc3", repo.getVersion("doc3", 2).get().getPath().toString());
		assertEquals(3,                     repo.getVersion("doc3", 3).get().getVersion());
		assertEquals("2018-12-30T09:40:08", repo.getVersion("doc3", 3).get().getTimeStamp().toString());
	}
	
	@Test
	public void testPreviousVersionBad_Doc3()
	{
		assertEquals(Optional.empty(), repo.getVersion("doc3", 8)); // non esiste v8 in doc3
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPreviousVersionNegative_Doc3()
	{
		repo.getVersion("doc3", -2); // inaccettabili numeri di versione negativi!
	}
	
	@Test
	public void testPreviousVersionAtEpochBeforeFirstTimestamp_Doc3()
	{
		assertEquals(Optional.empty(), repo.getVersionAt("doc3", LocalDateTime.parse("2016-01-01T01:40:00"))); // questo invece ha perfettamente senso, se non c'è restituirà empty
	}

	@Test
	public void testPreviousVersionAt6_Doc3()
	{
		// System.out.println("Version of doc3 valid at 2018-12-30T09:40:06: " + repo.getVersionAt("doc3", LocalDateTime.parse("2018-12-30T09:40:06")) );
		// version=2, timestamp=2018-12-30T09:40:06		
		assertEquals("doc3", repo.getVersion("doc3", 2).get().getID());
		assertEquals("testFiles/doc3", repo.getVersion("doc3", 2).get().getPath().toString());
		assertEquals(2,repo.getVersionAt("doc3", LocalDateTime.parse("2018-12-30T09:40:06")).get().getVersion());
		assertEquals("2018-12-30T09:40:06", repo.getVersionAt("doc3", LocalDateTime.parse("2018-12-30T09:40:06")).get().getTimeStamp().toString());
	}
	
	@Test
	public void testPreviousVersionAt7_Doc3()
	{
		// System.out.println("Version of doc3 valid at 2018-12-30T09:40:07: " + repo.getVersionAt("doc3", LocalDateTime.parse("2018-12-30T09:40:07")) );
		// version=2, timestamp=2018-12-30T09:40:07
		assertEquals("doc3", repo.getVersion("doc3", 2).get().getID());
		assertEquals("testFiles/doc3", repo.getVersion("doc3", 2).get().getPath().toString());
		assertEquals(2,                     repo.getVersionAt("doc3", LocalDateTime.parse("2018-12-30T09:40:07")).get().getVersion());
		assertEquals("2018-12-30T09:40:06", repo.getVersionAt("doc3", LocalDateTime.parse("2018-12-30T09:40:07")).get().getTimeStamp().toString());
	}
	
	@Test
	public void testPreviousVersion4_Doc1()
	{
		//System.out.println("Previous version #4 of doc1: " +  repo.getVersion("doc1", 4) );
		//Previous version #4 of doc1: Optional[VersionedDocument [timestamp=2018-12-30T09:42:06, version=4, doc=Document [id=doc1, path=\temp\doc1]]]
		assertEquals("doc1", repo.getVersion("doc1", 4).get().getID());
		assertEquals("testFiles/doc1", repo.getVersion("doc1", 4).get().getPath().toString());
		assertEquals(4,                     repo.getVersion("doc1", 4).get().getVersion());
		assertEquals("2018-12-30T09:42:06", repo.getVersion("doc1", 4).get().getTimeStamp().toString());
	}
	
	@Test
	public void testRestorePreviousVersion4_Doc1()
	{
		//System.out.println("Restoring version #4 of doc1: " +  repo.restoreVersion("doc1", 4) ); // true, ma produce la v7
		//Restoring version #4 of doc1: true
		//System.out.println("Current version of doc1: " + repo.getCurrentVersion("doc1").get());  // v7
		//Current version of doc1: VersionedDocument [timestamp=2019-01-05T18:18:13.447242100, version=7, doc=Document [id=doc1, path=\temp\doc1]]
		assertTrue(repo.restoreVersion("doc1", 4)); // genera v7
		assertEquals(7,repo.getCurrentVersion("doc1").get().getVersion());
		assertEquals("doc1", repo.getVersion("doc1", 7).get().getID());
		assertEquals("testFiles/doc1", repo.getVersion("doc1", 7).get().getPath().toString());
		//System.out.print(  "Checking doc1 contents @v4 vs. v7: " );
		//System.out.println(repo.getVersion("doc1", 4).get().getDocument()==repo.getVersion("doc1", 7).get().getDocument());	// true, perché v7 e v4 incapsulano lo stesso doc
		//Checking doc1 contents @v4 vs. v7: true
		assertEquals(repo.getVersion("doc1", 4).get().getDocument(), repo.getVersion("doc1", 7).get().getDocument());
	}
	
	@Test
	public void testDelete_Doc2()
	{
		//System.out.println("TestDelete-Current version of doc2: " + repo.getCurrentVersion("doc2").get());
		// version=6, timestamp=2018-12-30T09:43:08		
		assertEquals(6, repo.getCurrentVersion("doc2").get().getVersion());
		assertEquals("2018-12-30T09:43:08", repo.getCurrentVersion("doc2").get().getTimeStamp().toString());
		
		//System.out.println("Deleting doc2: " + repo.delete("doc2"));
		//System.out.println("Version of doc2 valid now: " + repo.getVersionAt("doc2", LocalDateTime.now()) );     // inner doc ==null
		//Deleting doc2: true
		assertTrue(repo.delete("doc2")); // genera v7 vuota
		
		//Version of doc2 valid now: Optional[VersionedDocument [timestamp=2019-01-05T18:27:00.700770200, version=7, doc=null]]
		assertEquals(7, repo.getVersionAt("doc2", LocalDateTime.now()).get().getVersion());
		assertNull(repo.getVersionAt("doc2", LocalDateTime.now()).get().getDocument().getPath());
	}
	
	@Test
	public void testDeleteRestore_Doc2()
	{
		//System.out.println("TestDeleteRestore-Current version of doc2: " + repo.getCurrentVersion("doc2").get());
		// version=6, timestamp=2018-12-30T09:43:08		
		assertEquals(6, repo.getCurrentVersion("doc2").get().getVersion());
		assertEquals("2018-12-30T09:43:08", repo.getCurrentVersion("doc2").get().getTimeStamp().toString());
		
		//System.out.println("Deleting doc2: " + repo.delete("doc2"));
		//System.out.println("Version of doc2 valid now: " + repo.getVersionAt("doc2", LocalDateTime.now()) );     // inner doc ==null
		//Deleting doc2: true
		assertTrue(repo.delete("doc2")); // genera v7 vuota
		
		//Version of doc2 valid now: Optional[VersionedDocument [timestamp=2019-01-05T18:27:00.700770200, version=7, doc=null]]
		assertEquals(7, repo.getVersionAt("doc2", LocalDateTime.now()).get().getVersion());
		assertNull(repo.getVersionAt("doc2", LocalDateTime.now()).get().getDocument().getPath());
		
		//System.out.println("Restoring doc2 valid 1ms ago: " +  repo.restoreVersionAt("doc2", LocalDateTime.now().minusNanos(1000)) ); // giustamente non lo fa
		//Restoring doc2 valid 1ms ago: true
		assertEquals(7, repo.getCurrentVersion("doc2").get().getVersion());
		assertTrue( repo.restoreVersionAt("doc2", LocalDateTime.now().minusNanos(1000))); // crea nuova versione v8
		assertEquals(8, repo.getCurrentVersion("doc2").get().getVersion());
		
		//System.out.println("Restoring doc2 valid 1 min ago: "  +  repo.restoreVersionAt("doc2", LocalDateTime.now().minusMinutes(1)) );  // ora invece lo fa, e trova la v7
		//Restoring doc2 valid 1 min ago: true
		assertTrue(repo.restoreVersionAt("doc2", LocalDateTime.now().minusMinutes(1))); // ripristina versione valida 1 minuto --> crea v9
		// System.out.println("Current version of doc2: " + repo.getCurrentVersion("doc2").get());
		// Current version of doc2: VersionedDocument [timestamp=2019-01-05T18:27:00.700770200, version=9, doc=Document [id=doc2, path=\temp\doc2]]
		assertEquals(9, repo.getCurrentVersion("doc2").get().getVersion());	
		assertEquals("doc2",         repo.getVersion("doc2", 9).get().getID());
		assertEquals("testFiles/doc2", repo.getVersion("doc2", 9).get().getPath().toString());
	}
	
}
