package mobisure.project.sinistre.document;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class FileDocumentTest {

    @Test
    void testFileDocumentConstructorAndGetters() {
        String filename = "testFile.txt";
        String contentType = "text/plain";
        long size = 1024;
        byte[] fileData = new byte[] {1, 2, 3, 4};
        long sinistreId = 789L;

        FileDocument fileDocument = new FileDocument(filename, contentType, size, fileData, sinistreId);

        assertEquals(filename, fileDocument.getFilename());
        assertEquals(contentType, fileDocument.getContentType());
        assertEquals(size, fileDocument.getSize());
        assertNotNull(fileDocument.getUploadDate());
        assertArrayEquals(fileData, fileDocument.getFileData());
        assertEquals(sinistreId, fileDocument.getSinistreId());
    }

    @Test
    void testSettersAndGetters() {
        FileDocument fileDocument = new FileDocument();

        fileDocument.setFilename("testFile.txt");
        fileDocument.setContentType("text/plain");
        fileDocument.setSize(1024);
        fileDocument.setFileData(new byte[] {1, 2, 3, 4});
        fileDocument.setUploadDate(new Date());
        fileDocument.setSinistreId(789L);

        assertEquals("testFile.txt", fileDocument.getFilename());
        assertEquals("text/plain", fileDocument.getContentType());
        assertEquals(1024, fileDocument.getSize());
        assertNotNull(fileDocument.getUploadDate());
        assertArrayEquals(new byte[] {1, 2, 3, 4}, fileDocument.getFileData());
        assertEquals(789L, fileDocument.getSinistreId());
    }
}
