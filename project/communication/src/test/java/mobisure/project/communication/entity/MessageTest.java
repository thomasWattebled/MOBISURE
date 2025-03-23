package mobisure.project.communication.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageTest {
	
	private Message message;
	
	@BeforeEach
	public void setUp() {
		
		message = new Message((long)1,(long)2,"Bonjour");
		
	}

	@Test
	void testConstructor(){
		assertEquals((long)1,message.getExpediteurId());
		assertEquals((long)2,message.getReceveurId());
		assertEquals("Bonjour",message.getContenu());	
	}
	
	@Test
	void testId() {
		message.setId((long)3);
		assertEquals((long)3,message.getId());
		message.setId((long)4);
		assertFalse(message.getId().equals((long)3));
		assertTrue(message.getId().equals((long)4));
	}
	
	@Test
	void testExpediteurId() {
		assertEquals((long)1,message.getExpediteurId());
		message.setExpediteurId((long)5);
		assertFalse(message.getExpediteurId().equals((long)1));
		assertTrue(message.getExpediteurId().equals((long)5));
	}
	
	@Test
	void testReceveurId() {
		assertEquals((long)2,message.getReceveurId());
		message.setReceveurId((long)5);
		assertFalse(message.getReceveurId().equals((long)2));
		assertTrue(message.getReceveurId().equals((long)5));
	}
	
	@Test
	void testContenue() {
		assertEquals("Bonjour",message.getContenu());
		message.setContenue("Salut");
		assertFalse(message.getContenu().equals("Bonjour"));
		assertTrue(message.getContenu().equals("Salut"));
	}
	
}
