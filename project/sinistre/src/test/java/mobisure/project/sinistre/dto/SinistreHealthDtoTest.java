package mobisure.project.sinistre.dto;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SinistreHealthDtoTest {

    @Test
    void testId() {
        SinistreHealthDto sinistreHealthDto = new SinistreHealthDto(123L, 456L, "Health", new Date(), "Accident", "Description", "Hospital");
        sinistreHealthDto.setId(1L);
        assertEquals(1L, sinistreHealthDto.getId());
    }

    @Test
    void testContractId() {
        SinistreHealthDto sinistreHealthDto = new SinistreHealthDto(123L, 456L, "Health", new Date(), "Accident", "Description", "Hospital");
        sinistreHealthDto.setContractId(789L);
        assertEquals(789L, sinistreHealthDto.getContractId());
    }

    @Test
    void testUserId() {
        SinistreHealthDto sinistreHealthDto = new SinistreHealthDto(123L, 456L, "Health", new Date(), "Accident", "Description", "Hospital");
        sinistreHealthDto.setUserId(321L);
        assertEquals(321L, sinistreHealthDto.getUserId());
    }

    @Test
    void testCategory() {
        SinistreHealthDto sinistreHealthDto = new SinistreHealthDto(123L, 456L, "Health", new Date(), "Accident", "Description", "Hospital");
        sinistreHealthDto.setCategory("Emergency");
        assertEquals("Emergency", sinistreHealthDto.getCategory());
    }

    @Test
    void testDate() {
        Date currentDate = new Date();
        SinistreHealthDto sinistreHealthDto = new SinistreHealthDto(123L, 456L, "Health", new Date(), "Accident", "Description", "Hospital");
        sinistreHealthDto.setDate(currentDate);
        assertEquals(currentDate, sinistreHealthDto.getDate());
    }

    @Test
    void testNature() {
        SinistreHealthDto sinistreHealthDto = new SinistreHealthDto(123L, 456L, "Health", new Date(), "Accident", "Description", "Hospital");
        sinistreHealthDto.setNature("Fracture");
        assertEquals("Fracture", sinistreHealthDto.getNature());
    }

    @Test
    void testHospital() {
        SinistreHealthDto sinistreHealthDto = new SinistreHealthDto(123L, 456L, "Health", new Date(), "Accident", "Description", "Hospital");
        sinistreHealthDto.setHospital("City Hospital");
        assertEquals("City Hospital", sinistreHealthDto.getHospital());
    }

    @Test
    void testDescription() {
        SinistreHealthDto sinistreHealthDto = new SinistreHealthDto(123L, 456L, "Health", new Date(), "Accident", "Description", "Hospital");
        sinistreHealthDto.setDescription("Injury caused by accident");
        assertEquals("Injury caused by accident", sinistreHealthDto.getDescription());
    }
}
