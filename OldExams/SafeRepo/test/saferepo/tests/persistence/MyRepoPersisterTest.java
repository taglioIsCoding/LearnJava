package saferepo.tests.persistence;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import saferepo.model.*;
import saferepo.persistence.MyRepoPersister;
import saferepo.persistence.RepoPersister;
import saferepo.persistence.SadPersisterException;

public class MyRepoPersisterTest
{

	VersionedDocument vdoc1_1, vdoc1_2, vdoc1_3, vdoc2_1, vdoc2_2, vdoc3_1, vdoc3_2, vdoc3_3;
	
	@Before
	public void setUp() {
		Document fakeDoc1 = new Document("doc1", Paths.get("testFiles/doc1"));
		Document fakeDoc2 = new Document("doc2", Paths.get("testFiles/doc2"));
		Document fakeDoc3 = new Document("doc3", Paths.get("testFiles/doc3"));
				
		vdoc1_1 = new VersionedDocument(fakeDoc1, LocalDateTime.parse("2018-12-31T15:39:02.154254"), 1);
		vdoc1_2 = new VersionedDocument(fakeDoc1, LocalDateTime.parse("2018-12-31T15:39:03.318918"), 2);
		vdoc1_3 = new VersionedDocument(fakeDoc1, LocalDateTime.parse("2018-12-31T15:39:17.487524"), 3);
		
		vdoc2_1 = new VersionedDocument(fakeDoc2, LocalDateTime.parse("2018-12-31T15:47:53.652415"), 1);
		vdoc2_2 = new VersionedDocument(fakeDoc2, LocalDateTime.parse("2018-12-31T15:48:03.252415"), 2);
		
		vdoc3_1 = new VersionedDocument(fakeDoc3, LocalDateTime.parse("2018-12-31T15:51:21.652415"), 1);
		vdoc3_2 = new VersionedDocument(fakeDoc3, LocalDateTime.parse("2018-12-31T15:51:22.652415"), 2);
		vdoc3_3 = new VersionedDocument(fakeDoc3, LocalDateTime.parse("2018-12-31T15:51:23.652415"), 3);
	}
	
	@Test
	public void testVersionedDocuments()
	{
		assertEquals(1, vdoc1_1.getVersion());
		assertEquals(2, vdoc1_2.getVersion());
		assertEquals(3, vdoc1_3.getVersion());
		assertEquals("2018-12-31T15:39:02.154254", vdoc1_1.getTimeStamp().toString());
		assertEquals("2018-12-31T15:39:03.318918", vdoc1_2.getTimeStamp().toString());
		assertEquals("2018-12-31T15:39:17.487524", vdoc1_3.getTimeStamp().toString());

		assertEquals(1, vdoc2_1.getVersion());
		assertEquals(2, vdoc2_2.getVersion());
		assertEquals("2018-12-31T15:47:53.652415", vdoc2_1.getTimeStamp().toString());
		assertEquals("2018-12-31T15:48:03.252415", vdoc2_2.getTimeStamp().toString());

		assertEquals(1, vdoc3_1.getVersion());
		assertEquals(2, vdoc3_2.getVersion());
		assertEquals(3, vdoc3_3.getVersion());
		assertEquals("2018-12-31T15:51:21.652415", vdoc3_1.getTimeStamp().toString());
		assertEquals("2018-12-31T15:51:22.652415", vdoc3_2.getTimeStamp().toString());
		assertEquals("2018-12-31T15:51:23.652415", vdoc3_3.getTimeStamp().toString());
	}
	
	@Test
	public void testStore() throws SadPersisterException, IOException
	{
		RepoPersister persister = new MyRepoPersister(Paths.get(RepoPersister.REPOPATH));
		persister.store(vdoc1_2);
		persister.store(vdoc2_1);
		persister.store(vdoc3_3);
		List<Path> listing = Files.list(Paths.get(RepoPersister.REPOPATH)).collect(Collectors.toList()); //forEach(System.out::println);
		assertTrue(listing.contains(Paths.get(RepoPersister.REPOPATH).resolve("doc1_2_2018-12-31T15_39_03.318918")));
		assertTrue(listing.contains(Paths.get(RepoPersister.REPOPATH).resolve("doc2_1_2018-12-31T15_47_53.652415")));
		assertTrue(listing.contains(Paths.get(RepoPersister.REPOPATH).resolve("doc3_3_2018-12-31T15_51_23.652415")));
	}
	
	@Test
	public void testStoreAndListAllDocs() throws SadPersisterException, IOException
	{
		RepoPersister persister = new MyRepoPersister(Paths.get(RepoPersister.REPOPATH));
		persister.store(vdoc1_2);
		persister.store(vdoc2_1);
		persister.store(vdoc3_3);
		List<Path> listing = persister.list(); // check metodo list/0, che si comporta esattamente come sopra
		assertTrue(listing.contains(Paths.get(RepoPersister.REPOPATH).resolve("doc1_2_2018-12-31T15_39_03.318918")));
		assertTrue(listing.contains(Paths.get(RepoPersister.REPOPATH).resolve("doc2_1_2018-12-31T15_47_53.652415")));
		assertTrue(listing.contains(Paths.get(RepoPersister.REPOPATH).resolve("doc3_3_2018-12-31T15_51_23.652415")));
	}

	@Test
	public void testStoreAndListByDoc() throws SadPersisterException, IOException
	{
		RepoPersister persister = new MyRepoPersister(Paths.get(RepoPersister.REPOPATH));
		persister.store(vdoc1_2);
		persister.store(vdoc2_1);
		persister.store(vdoc3_3);
		List<Path> listing = persister.list("doc1"); // check metodo list/1
		assertTrue(listing.contains(Paths.get(RepoPersister.REPOPATH).resolve("doc1_2_2018-12-31T15_39_03.318918")));
	}

	@Test
	public void testStoreAndRetrieve() throws SadPersisterException, IOException
	{
		RepoPersister persister = new MyRepoPersister(Paths.get(RepoPersister.REPOPATH));
		persister.store(vdoc1_2);
		persister.store(vdoc2_1);
		persister.store(vdoc3_3);
		persister.retrieve(vdoc1_2, Paths.get(RepoPersister.SAVEDFILESPATH));
		persister.retrieve(vdoc2_1, Paths.get(RepoPersister.SAVEDFILESPATH));
		persister.retrieve(vdoc3_3, Paths.get(RepoPersister.SAVEDFILESPATH));
	}
	
	@Test(expected=SadPersisterException.class)
	public void testStoreAndBadRetrieve() throws IOException, SadPersisterException
	{
		RepoPersister persister = new MyRepoPersister(Paths.get(RepoPersister.REPOPATH));
		persister.store(vdoc1_2);
		persister.store(vdoc2_1);
		persister.store(vdoc3_3);
		persister.retrieve(vdoc1_1, Paths.get(RepoPersister.SAVEDFILESPATH));
	}
}
