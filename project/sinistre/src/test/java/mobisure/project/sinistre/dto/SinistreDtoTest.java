package mobisure.project.sinistre.dto;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SinistreDtoTest {

    @Test
    void testId() {
        SinistreDto sinistreDto = new SinistreDto(123L, 456L, "Theft", new Date());
        sinistreDto.setId(1L);
        assertEquals(1L, sinistreDto.getId());
    }

    @Test
    void testContractId() {
        SinistreDto sinistreDto = new SinistreDto(123L, 456L, "Theft", new Date());
        sinistreDto.setContractId(789L);
        assertEquals(789L, sinistreDto.getContractId());
    }

    @Test
    void testUserId() {
        SinistreDto sinistreDto = new SinistreDto(123L, 456L, "Theft", new Date());
        sinistreDto.setUserId(321L);
        assertEquals(321L, sinistreDto.getUserId());
    }

    @Test
    void testCategory() {
        SinistreDto sinistreDto = new SinistreDto(123L, 456L, "Theft", new Date());
        sinistreDto.setCategory("Accident");
        assertEquals("Accident", sinistreDto.getCategory());
    }

    @Test
    void testDate() {
        Date currentDate = new Date();
        SinistreDto sinistreDto = new SinistreDto(123L, 456L, "Theft", new Date());
        sinistreDto.setDate(currentDate);
        assertEquals(currentDate, sinistreDto.getDate());
    }

    @Test
    void testEqualsSame() {
        SinistreDto sinistreDto1 = new SinistreDto(123L, 456L, "Theft", new Date());
        assertTrue(sinistreDto1.equals(sinistreDto1));
    }
    
    @Test
    void testNullEquals() {
        SinistreDto sinistreDto1 = new SinistreDto(123L, 456L, "Theft", new Date());
        assertFalse(sinistreDto1.equals(null));
    }
    
    @Test
    void testEquals() {
    	Date date = new Date();
        SinistreDto sinistreDto1 = new SinistreDto(123L, 456L, "Theft", date);
        SinistreDto sinistreDto2 = new SinistreDto(123L, 456L, "Theft", date);
        assertFalse(sinistreDto1.equals(sinistreDto2));
    }

    @Test
    void testHashCode() {
        SinistreDto sinistreDto1 = new SinistreDto(123L, 456L, "Theft", new Date());
        SinistreDto sinistreDto2 = new SinistreDto(123L, 456L, "Theft", new Date());
        assertEquals(sinistreDto1.hashCode(), sinistreDto2.hashCode());
    }

    @Test
    void testToString() {
        SinistreDto sinistreDto = new SinistreDto(123L, 456L, "Theft", new Date());
        assertNotNull(sinistreDto.toString());
    }
}
