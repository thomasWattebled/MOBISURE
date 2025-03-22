package mobisure.project.communication.service;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mobisure.project.communication.entity.Document;
import mobisure.project.communication.repository.DocumentRepository;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository repo;

	@Override
	public Document storeFile(String numDossier, MultipartFile file) throws IOException {
		Document document = new Document(
	            numDossier,
	            file.getOriginalFilename(),
	            file.getContentType(),
	            file.getBytes()
	        );
		
		return repo.save(document);
	}

	@Override
	public Optional<Document> getFile(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Document> getFilesByNumDossier(String numDossier) {
		return repo.findByNumDossier(numDossier);
	}
	
	public ResponseEntity<byte[]> download(Long id) {
		Optional<Document> documentOptional = repo.findById(id);
        
        if (documentOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Document document = documentOptional.get();
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
                .body(document.getData());
	}

}
