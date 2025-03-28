package mobisure.project.sinistre.document;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class HealthDocumentTest {

    @Test
    void testHealthDocumentConstructorAndGetters() {
        Long sinistreId = 123L;
        Long contractId = 456L;
        String category = "Health";
        Date date = new Date();
        String nature = "Injury";
        String description = "Fracture of the leg";
        String hospital = "General Hospital";

        HealthDocument healthDocument = new HealthDocument(sinistreId, contractId, category, date, nature, description, hospital);

        assertEquals(sinistreId, healthDocument.getSinistreId());
        assertEquals(contractId, healthDocument.getContractId());
        assertEquals(category, healthDocument.getCategory());
        assertEquals(date, healthDocument.getDate());
        assertEquals(nature, healthDocument.getNature());
        assertEquals(description, healthDocument.getDescription());
        assertEquals(hospital, healthDocument.getHospital());
    }

    @Test
    void testSettersAndGetters() {
        HealthDocument healthDocument = new HealthDocument();

        healthDocument.setSinistreId(123L);
        healthDocument.setContractId(456L);
        healthDocument.setCategory("Health");
        healthDocument.setDate(new Date());
        healthDocument.setNature("Injury");
        healthDocument.setDescription("Fracture of the leg");
        healthDocument.setHospital("General Hospital");

        assertEquals(123L, healthDocument.getSinistreId());
        assertEquals(456L, healthDocument.getContractId());
        assertEquals("Health", healthDocument.getCategory());
        assertNotNull(healthDocument.getDate());
        assertEquals("Injury", healthDocument.getNature());
        assertEquals("Fracture of the leg", healthDocument.getDescription());
        assertEquals("General Hospital", healthDocument.getHospital());
    }
}
