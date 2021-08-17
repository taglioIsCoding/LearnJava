package saferepo.persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import saferepo.model.Document;
import saferepo.model.VersionedDocument;

public class MyRepoPersister extends AbstractRepoPersister{
	
	public MyRepoPersister(Path repoPath) throws SadPersisterException {
		super(repoPath);
	}

	@Override
	public void store(VersionedDocument vdoc) throws SadPersisterException {
		//copia in repoPAth
		Path copiedFile;
		String dateString = vdoc.getTimeStamp().toString().substring(0, 10).replaceAll(":", "-");
		String hour = vdoc.getTimeStamp().toString().substring(10).replaceAll(":", "_");
		String name = vdoc.getID()+"_"+vdoc.getVersion()+"_"+dateString+hour;
		
		
		try {
			File newFolrder = Path.of(this.REPOPATH).toFile();
			newFolrder.mkdirs();
			copiedFile = Files.copy(vdoc.getPath(), newFolrder.toPath().resolve(name), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new SadPersisterException("Impossible store" + e);
		}	
		
	}

	@Override
	public VersionedDocument retrieve(VersionedDocument vdoc, Path path) throws SadPersisterException {
		VersionedDocument toSenDocument;
		String name = vdoc.getID()+"_"+vdoc.getVersion()+"_"+vdoc.getTimeStamp().toString().replaceAll(":", "_");
		Path copiedFile;
		Path sourcePath = Path.of(this.REPOPATH).resolve(name);
		try {
			File newFolrder = Path.of(this.SAVEDFILESPATH).resolve(path).toFile();
			copiedFile = Files.copy(sourcePath,path.resolve(vdoc.getPath().getFileName()), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new SadPersisterException("Impossible retrive "+ e);
		}
		
		Document doc = new Document(vdoc.getID(), copiedFile);
		
		
		return new VersionedDocument(doc, vdoc.getTimeStamp(), vdoc.getVersion());
	}
}
