package mobisure.project.sinistre.entity;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SinistreTest {

    @Test
    void testId() {
        Sinistre sinistre = new Sinistre();
        sinistre.setId(1L);
        assertEquals(1L, sinistre.getId());
    }

    @Test
    void testContractId() {
        Sinistre sinistre = new Sinistre();
        sinistre.setContractId(123L);
        assertEquals(123L, sinistre.getContractId());
    }

    @Test
    void testUserId() {
        Sinistre sinistre = new Sinistre();
        sinistre.setUserId(456L);
        assertEquals(456L, sinistre.getUserId());
    }

    @Test
    void testCategory() {
        Sinistre sinistre = new Sinistre();
        sinistre.setCategory("Theft");
        assertEquals("Theft", sinistre.getCategory());
    }

    @Test
    void testDate() {
        Date currentDate = new Date();
        Sinistre sinistre = new Sinistre();
        sinistre.setDate(currentDate);
        assertEquals(currentDate, sinistre.getDate());
    }

    @Test
    void testHashCode() {
        Sinistre sinistre = new Sinistre(123L, 456L, "Theft", new Date());
        Sinistre sinistre2 = new Sinistre(123L, 456L, "Theft", new Date());
        assertEquals(sinistre.hashCode(), sinistre2.hashCode());
    }

    @Test
    void testToString() {
        Sinistre sinistre = new Sinistre(123L, 456L, "Theft", new Date());
        assertTrue(sinistre.toString().contains("Theft"));
    }

    @Test
    void testEqualsSame() {
        Sinistre sinistre1 = new Sinistre(123L, 456L, "Theft", new Date());
        assertEquals(sinistre1, sinistre1);
    }
    
    @Test
    void testNullEquals() {
        Sinistre sinistre1 = new Sinistre(123L, 456L, "Theft", new Date());
        assertFalse(sinistre1.equals(null));
    }
}

