package mobisure.project.communication.controller;

import mobisure.project.communication.entity.Document;
import mobisure.project.communication.service.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class DocumentControllerTest {

	
	private MockMvc mockMvc;

    @Mock
    private DocumentService documentService;

    @InjectMocks
    private DocumentController documentController;

    private Document document;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(documentController).build();
        byte[] sampleData = {1, 2, 3, 4, 5};
        document = new Document("12345", "test.pdf", "application/pdf", sampleData);
    }
    
    
    @Test
    void testUploadFile_Success() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.pdf", "application/pdf", "Mock file content".getBytes());
        document.setId(1L);
        
        when(documentService.storeFile("12345", file)).thenReturn(document);

        mockMvc.perform(multipart("/documents/upload")
                        .file(file)
                        .param("numDossier", "12345"))
                .andExpect(status().isOk())
                .andExpect(content().string("Fichier enregistré avec succès, ID : 1"));

        verify(documentService, times(1)).storeFile("12345", file);
    }
    
    @Test
    void testUploadFile_Failure() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.pdf", "application/pdf", "Mock file content".getBytes());

        when(documentService.storeFile(anyString(), any())).thenThrow(new IOException("Erreur"));

        mockMvc.perform(multipart("/documents/upload")
                        .file(file)
                        .param("numDossier", "12345"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Erreur lors de l'enregistrement du fichier"));

        verify(documentService, times(1)).storeFile("12345", file);
    }
	
    @Test
    void testGetFile_Success() throws Exception {

    	document.setId(1L);
        when(documentService.getFile(1L)).thenReturn(Optional.of(document));

        mockMvc.perform(get("/documents/1"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=\"test.pdf\""));

        verify(documentService, times(1)).getFile(1L);
    }
    
    @Test
    void testGetFile_NotFound() throws Exception {
        when(documentService.getFile(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/documents/1"))
                .andExpect(status().isNotFound());

        verify(documentService, times(1)).getFile(1L);
    }
    
    @Test
    void testGetFilesByDossier() throws Exception {
        document.setNum_dossier("12345");
        List<Document> mockDocuments = Arrays.asList(document);
        
        when(documentService.getFilesByNumDossier("12345")).thenReturn(mockDocuments);

        mockMvc.perform(get("/documents/by-dossier/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(documentService, times(1)).getFilesByNumDossier("12345");
    }
    
    @Test
    void testDownloadDocument() throws Exception {
        byte[] fileContent = "file".getBytes();
        when(documentService.download(1L)).thenReturn(ResponseEntity.ok(fileContent));

        mockMvc.perform(get("/documents/download/1"))
                .andExpect(status().isOk())
                .andExpect(content().bytes(fileContent));

        verify(documentService, times(1)).download(1L);
    }
    
}
