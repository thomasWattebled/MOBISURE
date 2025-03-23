package mobisure.project.communication.entity.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mobisure.project.communication.entity.Status;
import mobisure.project.communication.entity.TypeAssistance;

public class DepannageTest {

	private Depannage depannage;
	
	@BeforeEach
    public void setUp() {
		depannage = new Depannage(3L, Status.TRAITEMENT, new Date(), "Depannage message", TypeAssistance.AUTO, "Brown", "Charlie", "charlie.brown@example.com", "strongPass", "1234567890", "Lyon", "Rue Victor Hugo");
    }
	
	@Test
    void testDepannageConstructor() {
        assertEquals(3L, depannage.getIdClient());
        assertEquals(Status.TRAITEMENT, depannage.getStatus());
        assertNotNull(depannage.getDate());
        assertEquals("Depannage message", depannage.getMessage());
        assertEquals(TypeAssistance.AUTO, depannage.getType());
        assertEquals("Brown", depannage.getNom());
        assertEquals("Charlie", depannage.getPrenom());
        assertEquals("charlie.brown@example.com", depannage.getMail());
        assertEquals("strongPass", depannage.getMdp());
        assertEquals("1234567890", depannage.getTelephone());
        assertEquals("Lyon", depannage.getVille());
        assertEquals("Rue Victor Hugo", depannage.getRue());
    }
	
	@Test
    void testDepannageVille() {
        depannage.setVille("Marseille");
        assertEquals("Marseille", depannage.getVille());
    }

    @Test
    void testDepannageRue() {
        depannage.setRue("Rue de la République");
        assertEquals("Rue de la République", depannage.getRue());
    }
	
}
