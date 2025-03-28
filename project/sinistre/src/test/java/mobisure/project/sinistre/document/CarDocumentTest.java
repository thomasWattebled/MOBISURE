package mobisure.project.sinistre.document;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class CarDocumentTest {

    @Test
    void testCarDocumentConstructorAndGetters() {
        Long sinistreId = 123L;
        Long contractId = 456L;
        String category = "Accident";
        Date date = new Date();
        String immatriculation = "123ABC";
        String brand = "Toyota";
        String modele = "Corolla";
        String description = "Collision with another car";
        String responsable = "John Doe";

        CarDocument carDocument = new CarDocument(sinistreId, contractId, category, date, immatriculation, brand, modele, description, responsable);

        assertEquals(sinistreId, carDocument.getSinistreId());
        assertEquals(contractId, carDocument.getContractId());
        assertEquals(category, carDocument.getCategory());
        assertEquals(date, carDocument.getDate());
        assertEquals(immatriculation, carDocument.getImmatriculation());
        assertEquals(brand, carDocument.getBrand());
        assertEquals(modele, carDocument.getModele());
        assertEquals(description, carDocument.getDescription());
        assertEquals(responsable, carDocument.getResponsable());
    }

    @Test
    void testSettersAndGetters() {
        CarDocument carDocument = new CarDocument();
        
        carDocument.setSinistreId(123L);
        carDocument.setContractId(456L);
        carDocument.setCategory("Accident");
        carDocument.setDate(new Date());
        carDocument.setImmatriculation("123ABC");
        carDocument.setBrand("Toyota");
        carDocument.setModele("Corolla");
        carDocument.setDescription("Collision with another car");
        carDocument.setResponsable("John Doe");

        assertEquals(123L, carDocument.getSinistreId());
        assertEquals(456L, carDocument.getContractId());
        assertEquals("Accident", carDocument.getCategory());
        assertNotNull(carDocument.getDate());
        assertEquals("123ABC", carDocument.getImmatriculation());
        assertEquals("Toyota", carDocument.getBrand());
        assertEquals("Corolla", carDocument.getModele());
        assertEquals("Collision with another car", carDocument.getDescription());
        assertEquals("John Doe", carDocument.getResponsable());
    }
}
