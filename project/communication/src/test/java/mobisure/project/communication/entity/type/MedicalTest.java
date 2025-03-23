package mobisure.project.communication.entity.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mobisure.project.communication.entity.Status;
import mobisure.project.communication.entity.TypeAssistance;

public class MedicalTest {

	private Medical medical;
	
	 @BeforeEach
	    public void setUp() {
	        medical = new Medical(4L, Status.CLOTURER, new Date(), "Medical message", TypeAssistance.MEDICAL, "White", "Emma", "emma.white@example.com", "medicalPass", "1122334455", "Urgence");
	    }
	
	 @Test
	    void testMedicalConstructor() {
	        assertEquals(4L, medical.getIdClient());
	        assertEquals(Status.CLOTURER, medical.getStatus());
	        assertNotNull(medical.getDate());
	        assertEquals("Medical message", medical.getMessage());
	        assertEquals(TypeAssistance.MEDICAL, medical.getType());
	        assertEquals("White", medical.getNom());
	        assertEquals("Emma", medical.getPrenom());
	        assertEquals("emma.white@example.com", medical.getMail());
	        assertEquals("medicalPass", medical.getMdp());
	        assertEquals("1122334455", medical.getTelephone());
	        assertEquals("Urgence", medical.getMotif());
	    }

	    @Test
	    void testMedicalMotif() {
	        medical.setMotif("Consultation");
	        assertEquals("Consultation", medical.getMotif());
	    }
	 
}
