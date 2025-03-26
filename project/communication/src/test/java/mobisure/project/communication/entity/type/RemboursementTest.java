package mobisure.project.communication.entity.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mobisure.project.communication.entity.Status;
import mobisure.project.communication.entity.TypeAssistance;

public class RemboursementTest {

	private Remboursement remboursement;
	
	@BeforeEach
    public void setUp() {
        remboursement = new Remboursement(5L, Status.ATTENTE, new Date(), "Remboursement message", TypeAssistance.REMBOURSEMENT, "Davis", "Olivia", "olivia.davis@example.com", "refundPass", "5566778899", 250.75f, "Frais médicaux");
    }
	
	@Test
    void testRemboursementConstructor() {
        assertEquals(5L, remboursement.getIdClient());
        assertEquals(Status.ATTENTE, remboursement.getStatus());
        assertNotNull(remboursement.getDate());
        assertEquals("Remboursement message", remboursement.getMessage());
        assertEquals(TypeAssistance.REMBOURSEMENT, remboursement.getType());
        assertEquals("Davis", remboursement.getNom());
        assertEquals("Olivia", remboursement.getPrenom());
        assertEquals("olivia.davis@example.com", remboursement.getMail());
        assertEquals("refundPass", remboursement.getMdp());
        assertEquals("5566778899", remboursement.getTelephone());
        assertEquals(250.75f, remboursement.getMontant());
        assertEquals("Frais médicaux", remboursement.getMotif());
    }

    @Test
    void testRemboursementMontant() {
        remboursement.setMontant(300.50f);
        assertEquals(300.50f, remboursement.getMontant());
    }

    @Test
    void testRemboursementMotif() {
        remboursement.setMotif("Autres frais");
        assertEquals("Autres frais", remboursement.getMotif());
    }
	
}
