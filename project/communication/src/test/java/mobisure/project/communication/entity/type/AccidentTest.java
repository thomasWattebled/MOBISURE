package mobisure.project.communication.entity.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mobisure.project.communication.entity.Status;
import mobisure.project.communication.entity.TypeAssistance;

public class AccidentTest {

    private Accident accident;

    @BeforeEach
    public void setUp() {
        accident = new Accident(2L, Status.ATTENTE, new Date(), "Accident message", TypeAssistance.ACCIDENT, "Smith", "Alice", "alice.smith@example.com", "securePass", "0987654321", "Paris", "Rue de Rivoli", 3);
    }

    @Test
    void testAccident() {
        assertEquals(2L, accident.getIdClient());
        assertEquals(Status.ATTENTE, accident.getStatus());
        assertNotNull(accident.getDate());
        assertEquals("Accident message", accident.getMessage());
        assertEquals(TypeAssistance.ACCIDENT, accident.getType());
        assertEquals("Smith", accident.getNom());
        assertEquals("Alice", accident.getPrenom());
        assertEquals("alice.smith@example.com", accident.getMail());
        assertEquals("securePass", accident.getMdp());
        assertEquals("0987654321", accident.getTelephone());
        assertEquals("Paris", accident.getVille());
        assertEquals("Rue de Rivoli", accident.getRue());
        assertEquals(3, accident.getNbBlesse());
    }
    
    @Test
    void testVille() {
        accident.setVille("Lyon");
        assertEquals("Lyon", accident.getVille());
    }

    @Test
    void testRue() {
        accident.setRue("Rue de Lyon");
        assertEquals("Rue de Lyon", accident.getRue());
    }

    @Test
    void testNbBlesse() {
        accident.setNbBlesse(5);
        assertEquals(5, accident.getNbBlesse());
    }
	
}
