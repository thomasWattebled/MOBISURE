package mobisure.project.sinistre.service;

import java.util.List;


import mobisure.project.sinistre.document.FileDocument;

public interface FileService {

	public FileDocument saveFile(FileDocument fileDocument);

	public List<FileDocument> findBySinistreId(Long sinistreId);
}
