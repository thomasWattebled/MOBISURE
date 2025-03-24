package mobisure.project.communication.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DocumentTest {

	private Document document;
    
    @BeforeEach
    void setUp() {
        byte[] sampleData = {1, 2, 3, 4, 5};
        document = new Document("12345", "test.pdf", "application/pdf", sampleData);
    }
	
    @Test
    void testConstructeur() {
    	assertEquals("12345",document.getNum_dossier());
    	assertEquals("test.pdf",document.getName());
    	assertEquals("application/pdf",document.getType());
    	assertThat(document.getData()).isNotNull().hasSize(5);
    }
    
    @Test
    void testNumDossier() {
    	assertEquals("12345",document.getNum_dossier());
    	document.setNum_dossier("6789");
    	assertFalse(document.getNum_dossier().equals("12345"));
    	assertEquals("6789",document.getNum_dossier());
    }
    
    @Test
    void testName() {
    	assertEquals("test.pdf",document.getName());
    	document.setName("projet.pdf");
    	assertFalse(document.getName().equals("test.pdf"));
    	assertEquals("projet.pdf",document.getName());
    }
    
    @Test
    void testType() {
    	assertEquals("application/pdf",document.getType());
    	document.setType("png");
    	assertFalse(document.getType().equals("application/pdf"));
    	assertEquals("png",document.getType());
    }
    
    @Test
    void testData() {
    	byte[] sampleData = {6, 8, 9};
    	assertThat(document.getData()).isNotNull().hasSize(5);
    	document.setData(sampleData);
    	assertThat(document.getData()).isNotNull().hasSize(3);
    }
    
    @Test
    void testId() {
    	document.setId(5L);
    	assertEquals(5L,document.getId());
    }
    
}
