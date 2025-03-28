package mobisure.project.sinistre.dto;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SinistreCarDtoTest {

    @Test
    void testId() {
        SinistreCarDto sinistreCarDto = new SinistreCarDto(123L, 456L, "Theft", new Date(), "ABC123", "Toyota", "Corolla", "Description", "John Doe");
        sinistreCarDto.setId(1L);
        assertEquals(1L, sinistreCarDto.getId());
    }

    @Test
    void testContractId() {
        SinistreCarDto sinistreCarDto = new SinistreCarDto(123L, 456L, "Theft", new Date(), "ABC123", "Toyota", "Corolla", "Description", "John Doe");
        sinistreCarDto.setContractId(789L);
        assertEquals(789L, sinistreCarDto.getContractId());
    }

    @Test
    void testUserId() {
        SinistreCarDto sinistreCarDto = new SinistreCarDto(123L, 456L, "Theft", new Date(), "ABC123", "Toyota", "Corolla", "Description", "John Doe");
        sinistreCarDto.setUserId(321L);
        assertEquals(321L, sinistreCarDto.getUserId());
    }

    @Test
    void testCategory() {
        SinistreCarDto sinistreCarDto = new SinistreCarDto(123L, 456L, "Theft", new Date(), "ABC123", "Toyota", "Corolla", "Description", "John Doe");
        sinistreCarDto.setCategory("Accident");
        assertEquals("Accident", sinistreCarDto.getCategory());
    }

    @Test
    void testDate() {
        Date currentDate = new Date();
        SinistreCarDto sinistreCarDto = new SinistreCarDto(123L, 456L, "Theft", new Date(), "ABC123", "Toyota", "Corolla", "Description", "John Doe");
        sinistreCarDto.setDate(currentDate);
        assertEquals(currentDate, sinistreCarDto.getDate());
    }

    @Test
    void testImmatriculation() {
        SinistreCarDto sinistreCarDto = new SinistreCarDto(123L, 456L, "Theft", new Date(), "ABC123", "Toyota", "Corolla", "Description", "John Doe");
        sinistreCarDto.setImmatriculation("XYZ987");
        assertEquals("XYZ987", sinistreCarDto.getImmatriculation());
    }

    @Test
    void testBrand() {
        SinistreCarDto sinistreCarDto = new SinistreCarDto(123L, 456L, "Theft", new Date(), "ABC123", "Toyota", "Corolla", "Description", "John Doe");
        sinistreCarDto.setBrand("Honda");
        assertEquals("Honda", sinistreCarDto.getBrand());
    }

    @Test
    void testModele() {
        SinistreCarDto sinistreCarDto = new SinistreCarDto(123L, 456L, "Theft", new Date(), "ABC123", "Toyota", "Corolla", "Description", "John Doe");
        sinistreCarDto.setModele("Civic");
        assertEquals("Civic", sinistreCarDto.getModele());
    }

    @Test
    void testDescription() {
        SinistreCarDto sinistreCarDto = new SinistreCarDto(123L, 456L, "Theft", new Date(), "ABC123", "Toyota", "Corolla", "Description", "John Doe");
        sinistreCarDto.setDescription("Car damage");
        assertEquals("Car damage", sinistreCarDto.getDescription());
    }

    @Test
    void testResponsable() {
        SinistreCarDto sinistreCarDto = new SinistreCarDto(123L, 456L, "Theft", new Date(), "ABC123", "Toyota", "Corolla", "Description", "John Doe");
        sinistreCarDto.setResponsable("Jane Doe");
        assertEquals("Jane Doe", sinistreCarDto.getResponsable());
    }
}
