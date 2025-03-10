package mobisure.project.communication.controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


import mobisure.project.communication.entity.Document;
import mobisure.project.communication.service.DocumentService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/documents")
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
        @RequestParam("numDossier") String numDossier,
        @RequestParam("file") MultipartFile file
    ) {
        try {
            Document doc = documentService.storeFile(numDossier, file);
            return ResponseEntity.ok("Fichier enregistré avec succès, ID : " + doc.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'enregistrement du fichier");
        }
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<Document> document = documentService.getFile(id);
        if (document.isPresent()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.get().getName() + "\"")
                    .body(document.get().getData());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    @GetMapping("/by-dossier/{numDossier}")
    public ResponseEntity<List<Document>> getFilesByDossier(@PathVariable String numDossier) {
        List<Document> documents = documentService.getFilesByNumDossier(numDossier);
        if (documents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(documents);
    }
    
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long id) {
        return documentService.download(id);
    }

    
}
