package mobisure.project.communication.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import mobisure.project.communication.entity.Document;
import mobisure.project.communication.repository.DocumentRepository;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceImplTest {

	@Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private DocumentServiceImpl documentService;
    
    private MultipartFile file;
    private Document document;
    
    @BeforeEach
    public void setUp() {
        file = mock(MultipartFile.class);
        document = new Document("123", "testFile.txt", "text/plain", new byte[]{1, 2, 3, 4});
    }
    
    @Test
    public void testStoreFile() throws IOException {
    	
        // Simuler le comportement de MultipartFile
        when(file.getOriginalFilename()).thenReturn("testFile.txt");
        when(file.getContentType()).thenReturn("text/plain");
        when(file.getBytes()).thenReturn(new byte[]{1, 2, 3, 4});
        
        // Simuler le comportement du repository
        when(documentRepository.save(any(Document.class))).thenReturn(document);

        // Appeler la méthode à tester
        Document storedDocument = documentService.storeFile("123", file);

        // Vérifier que le repository a été appelé
        verify(documentRepository).save(any(Document.class));

        // Vérifier les assertions
        assertNotNull(storedDocument);
        assertEquals("testFile.txt", storedDocument.getName());
        assertEquals("text/plain", storedDocument.getType());
    }
    
    @Test
    public void testGetFile() {
    	
        // Simuler le comportement du repository
        when(documentRepository.findById(anyLong())).thenReturn(Optional.of(document));

        // Appeler la méthode à tester
        Optional<Document> foundDocument = documentService.getFile(1L);

        // Vérifier les assertions
        assertTrue(foundDocument.isPresent());
        assertEquals("testFile.txt", foundDocument.get().getName());
    }
    
    @Test
    public void testGetFilesByNumDossier() {
    	
        // Simuler le comportement du repository
        when(documentRepository.findByNumDossier(anyString())).thenReturn(List.of(document));

        // Appeler la méthode à tester
        List<Document> documents = documentService.getFilesByNumDossier("123");

        // Vérifier les assertions
        assertNotNull(documents);
        assertFalse(documents.isEmpty());
        assertEquals(1, documents.size());
    }
    
    @Test
    public void testDownloadFileFound() {
        // Simuler le comportement du repository
        when(documentRepository.findById(anyLong())).thenReturn(Optional.of(document));

        // Appeler la méthode à tester
        ResponseEntity<byte[]> response = documentService.download(1L);

        // Vérifier que la réponse est correcte
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertArrayEquals(new byte[]{1, 2, 3, 4}, response.getBody());
        assertTrue(response.getHeaders().containsKey(HttpHeaders.CONTENT_DISPOSITION));
    }
    
    @Test
    public void testDownloadFileNotFound() {
        // Simuler le comportement du repository
        when(documentRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Appeler la méthode à tester
        ResponseEntity<byte[]> response = documentService.download(1L);

        // Vérifier que la réponse est une erreur 404
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
    }
	
}
