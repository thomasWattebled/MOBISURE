package mobisure.project.sinistre.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mobisure.project.sinistre.document.FileDocument;
import mobisure.project.sinistre.repository.FileRepository;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	FileRepository fileRepo;

	public FileDocument saveFile(FileDocument fileDocument) {
		return this.fileRepo.save(fileDocument);
	}

	public List<FileDocument> findBySinistreId(Long sinistreId) {
		return this.fileRepo.findBySinistreId(sinistreId);
	}
}
