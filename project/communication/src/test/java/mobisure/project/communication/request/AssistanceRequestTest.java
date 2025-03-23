package mobisure.project.communication.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AssistanceRequestTest {

	private AssistanceRequest assistanceRequest;

    @BeforeEach
    public void setUp() {
        assistanceRequest = new AssistanceRequest(
            1L, 
            "ATTENTE", 
            "2025-03-23", 
            "Test message", 
            "AUTO", 
            "Doe", 
            "John", 
            "john.doe@example.com", 
            "password123", 
            "0123456789", 
            "Paris", 
            "Rue de Rivoli", 
            2, 
            200.0f, 
            "Technical issue"
        );
    }
    
    @Test
    void testConstructeurVide() {
    	AssistanceRequest newRequest = new AssistanceRequest();
    	assertNotNull(newRequest);
    }
    
    @Test
    void testConstructor() {
        assertEquals(1L, assistanceRequest.getId_client());
        assertEquals("ATTENTE", assistanceRequest.getStatus());
        assertNotNull(assistanceRequest.getDate());
        assertEquals("Test message", assistanceRequest.getMessage());
        assertEquals("AUTO", assistanceRequest.getType());
        assertEquals("Doe", assistanceRequest.getNom());
        assertEquals("John", assistanceRequest.getPrenom());
        assertEquals("john.doe@example.com", assistanceRequest.getMail());
        assertEquals("password123", assistanceRequest.getMdp());
        assertEquals("0123456789", assistanceRequest.getTelephone());
        assertEquals("Paris", assistanceRequest.getVille());
        assertEquals("Rue de Rivoli", assistanceRequest.getRue());
        assertEquals(2, assistanceRequest.getNbBlesse());
        assertEquals(200.0f, assistanceRequest.getMontant());
        assertEquals("Technical issue", assistanceRequest.getMotif());
    }
    
    @Test
    void testStatus() {
        assistanceRequest.setStatus("CLOTURER");
        assertEquals("CLOTURER", assistanceRequest.getStatus());
    }

    @Test
    void testVille() {
        assistanceRequest.setVille("Lyon");
        assertEquals("Lyon", assistanceRequest.getVille());
    }

    @Test
    void testRue() {
        assistanceRequest.setRue("Rue de la République");
        assertEquals("Rue de la République", assistanceRequest.getRue());
    }

    @Test
    void testNbBlesse() {
        assistanceRequest.setNbBlesse(5);
        assertEquals(5, assistanceRequest.getNbBlesse());
    }
    
    @Test
    void testMontant() {
        assistanceRequest.setMontant(300.5f);
        assertEquals(300.5f, assistanceRequest.getMontant());
    }

    @Test
    void testMotif() {
        assistanceRequest.setMotif("Accident");
        assertEquals("Accident", assistanceRequest.getMotif());
    }
    
    @Test
    void testIdClient() {
    	assistanceRequest.setId_client(4L);
    	assertEquals(4L,assistanceRequest.getId_client());
    }
    
    @Test
    void testDate() {
    	assistanceRequest.setDate("30-04-2003");
    	assertEquals("30-04-2003",assistanceRequest.getDate());
    }
    
    @Test
    void testMessage() {
    	assistanceRequest.setMessage("test");
    	assertEquals("test",assistanceRequest.getMessage());
    }
    
     @Test
     void testType() {
    	 assistanceRequest.setType("MEDICAL");
    	 assertEquals("MEDICAL",assistanceRequest.getType());
     }
     
     @Test
     void testNom() {
    	 assistanceRequest.setNom("Ben");
    	 assertEquals("Ben",assistanceRequest.getNom());
     }
     
     @Test
     void testPrenom() {
    	 assistanceRequest.setPrenom("Ben");
    	 assertEquals("Ben",assistanceRequest.getPrenom());
     }
     
     @Test
     void testMail() {
    	 assistanceRequest.setMail("ben@gmail.com");
    	 assertEquals("ben@gmail.com",assistanceRequest.getMail());
     }
	
     @Test
     void testTelephone() {
    	 assistanceRequest.setTelephone("0619485632");
    	 assertEquals("0619485632",assistanceRequest.getTelephone());
     }
     
     @Test
     void testMdp() {
    	 assistanceRequest.setMdp("mdptest");
    	 assertEquals("mdptest",assistanceRequest.getMdp());
     }
}
