package mobisure.project.service;

import java.util.List;

import mobisure.project.document.FileDocument;

public interface FileService {
	
	public FileDocument saveFile (FileDocument fileDocument);
	public List<FileDocument> findBySinistreId (Long sinistreId);
}
