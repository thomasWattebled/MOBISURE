package mobisure.project.communication.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccessAssistanceTest {

	private AccessAssistance access;
	
	@BeforeEach
	public void setUp() {
		access = new AccessAssistance("123",(long)1);
	}
	
	@Test
	void testConstructeur() {
		assertEquals("123",access.getIdAssistance());
		assertEquals((long)1,access.getIdUser());
	}
	
	@Test
	void testId() {
		access.setId((long)3);
		assertTrue(access.getId().equals((long)3));
		access.setId((long)4);
		assertFalse(access.getId().equals((long)3));
		assertTrue(access.getId().equals((long)4));
	}
	
	@Test 
	void testIdAssistance() {
		assertEquals("123",access.getIdAssistance());
		access.setIdAssistance("456");
		assertFalse(access.getIdAssistance().equals("123"));
		assertTrue(access.getIdAssistance().equals("456"));
	}
	
	@Test
	void testIdUser() {
		assertEquals((long)1,access.getIdUser());
		access.setIdUser((long)2);
		assertFalse(access.getIdUser().equals((long)1));
		assertTrue(access.getIdUser().equals((long)2));
	}
	
}
