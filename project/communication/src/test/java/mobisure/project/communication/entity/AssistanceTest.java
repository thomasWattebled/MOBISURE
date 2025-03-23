package mobisure.project.communication.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AssistanceTest {

private Assistance assistance;
    
    @BeforeEach
    void setUp() {
        assistance = new Assistance(1L, Status.ATTENTE, new Date(), "Besoin d'aide", TypeAssistance.ACCIDENT, "Doe", "John", "john.doe@example.com", "password", "0123456789");
    }
	
    @Test
    void testConstructor() {
        assertNotNull(assistance.getNumDossier());
        assertEquals(1L, assistance.getIdClient());
        assertEquals(Status.ATTENTE, assistance.getStatus());
        assertEquals("Besoin d'aide", assistance.getMessage());
        assertEquals(TypeAssistance.ACCIDENT, assistance.getType());
        assertEquals("Doe", assistance.getNom());
        assertEquals("John", assistance.getPrenom());
        assertEquals("john.doe@example.com", assistance.getMail());
        assertEquals("password", assistance.getMdp());
        assertEquals("0123456789", assistance.getTelephone());
        assertFalse(assistance.isGerer());
    }
    
    @Test
    void testId() {
        assistance.setId("12345");
        assertEquals("12345", assistance.getId());
    }

    @Test
    void testIdClient() {
        assistance.setIdClient(2L);
        assertEquals(2L, assistance.getIdClient());
    }

    @Test
    void testStatus() {
        assistance.setStatus(Status.CLOTURER);
        assertEquals(Status.CLOTURER, assistance.getStatus());
    }

    @Test
    void testDate() {
        Date newDate = new Date();
        assistance.setDate(newDate);
        assertEquals(newDate, assistance.getDate());
    }
    
    @Test
    void testMessage() {
        assistance.setMessage("New message");
        assertEquals("New message", assistance.getMessage());
    }

    @Test
    void testType() {
        assistance.setType(TypeAssistance.AUTO);
        assertEquals(TypeAssistance.AUTO, assistance.getType());
    }

    @Test
    void testNom() {
        assistance.setNom("Smith");
        assertEquals("Smith", assistance.getNom());
    }

    @Test
    void testPrenom() {
        assistance.setPrenom("Alice");
        assertEquals("Alice", assistance.getPrenom());
    }
    
    @Test
    void testMail() {
        assistance.setMail("alice.smith@example.com");
        assertEquals("alice.smith@example.com", assistance.getMail());
    }

    @Test
    void testMdp() {
        assistance.setMdp("newpassword");
        assertEquals("newpassword", assistance.getMdp());
    }

    @Test
    void testTelephone() {
        assistance.setTelephone("0987654321");
        assertEquals("0987654321", assistance.getTelephone());
    }

    @Test
    void testGerer() {
        assistance.setGerer(true);
        assertTrue(assistance.isGerer());
    }
    
    @Test
    void testConstructeurVide() {
    	Assistance newAssistance = new Assistance();
    	assertThat(newAssistance.getNumDossier()).isNotNull();
    	assertFalse(newAssistance.isGerer());
    }
    
    @Test
    void testNumDossier() {
    	assistance.setNumDossier("D-1212");
    	assertEquals("D-1212",assistance.getNumDossier());
    }
}
