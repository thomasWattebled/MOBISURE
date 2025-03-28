package mobisure.project.sinistre.service;

import mobisure.project.sinistre.document.FileDocument;
import mobisure.project.sinistre.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileServiceImplTest {

    @Mock
    private FileRepository fileRepo;

    @InjectMocks
    private FileServiceImpl fileService;

    @Test
    void testSaveFile() {
        String filename = "document.pdf";
        String contentType = "application/pdf";
        long size = 1024L;
        byte[] fileData = new byte[]{1, 2, 3, 4}; // Exemple de données binaires
        long sinistreId = 123L;

        FileDocument fileDocument = new FileDocument(filename, contentType, size, fileData, sinistreId);
        
        when(fileRepo.save(fileDocument)).thenReturn(fileDocument);

        FileDocument savedFile = fileService.saveFile(fileDocument);
        assertNotNull(savedFile);
        assertEquals(filename, savedFile.getFilename());
        assertEquals(contentType, savedFile.getContentType());
        assertArrayEquals(fileData, savedFile.getFileData());
    }

    @Test
    void testFindBySinistreId() {
        long sinistreId = 123L;
        FileDocument file1 = new FileDocument("document1.pdf", "application/pdf", 2048L, new byte[]{5, 6, 7, 8}, sinistreId);
        FileDocument file2 = new FileDocument("document2.jpg", "image/jpeg", 4096L, new byte[]{9, 10, 11, 12}, sinistreId);

        List<FileDocument> files = Arrays.asList(file1, file2);

        when(fileRepo.findBySinistreId(sinistreId)).thenReturn(files);

        List<FileDocument> result = fileService.findBySinistreId(sinistreId);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("document1.pdf", result.get(0).getFilename());
    }
}
