package mobisure.project.communication.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import mobisure.project.communication.entity.Document;

public interface DocumentService {

	public Document storeFile(String numDossier, MultipartFile file) throws IOException;
	
	public Optional<Document> getFile(Long id);
	
	public List<Document> getFilesByNumDossier(String numDossier);
	
	public ResponseEntity<byte[]> download(Long id);
}
