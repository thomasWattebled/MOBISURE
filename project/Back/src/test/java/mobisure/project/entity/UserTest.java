package mobisure.project.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

	private User user;
	
	@BeforeEach
	public void setUp() {
		user = new User("ALEXANDRE","Benjamin","benj@gmail.com","mdp", null, null, null, null, null, null, null);
	}
	
	@Test
	public void testConstructor() {
		assertEquals("ALEXANDRE", user.getNom());
        assertEquals("Benjamin", user.getPrenom());
        assertEquals("benj@gmail.com", user.getMail());
        assertEquals("mdp", user.getMdp());
        //assertNotNull(user.getRoles());
        //assertTrue(user.getRoles().isEmpty());
	}
	
	@Test
	public void testId() {
		user.setId((long) 2);
		assertEquals((long) 2, user.getId());
	}
	
	@Test
	public void testNom() {
		assertEquals("ALEXANDRE", user.getNom());
		user.setNom("Nom");
		assertEquals("Nom", user.getNom());
	}
	
	@Test
	public void testPrenom() {
		assertEquals("Benjamin", user.getPrenom());
		user.setPrenom("Prenom");
		assertEquals("Prenom", user.getPrenom());
	}
	
	@Test
	public void testMdp() {
		assertEquals("mdp", user.getMdp());
		user.setMdp("mdp2");
		assertEquals("mdp2", user.getMdp());
	}
	
	@Test
	public void testMail() {
		assertEquals("benj@gmail.com", user.getMail());
		user.setMail("benj2@gmail.com");
		assertEquals("benj2@gmail.com", user.getMail());
	}
	
	@Test
	public void testAddRole() {
		/*
		//assertNotNull(user.getRoles());
        assertTrue(user.getRoles().isEmpty());
        user.addRole(RoleName.USER);
        assertFalse(user.getRoles().isEmpty());
        assertTrue(user.getRoles().contains(RoleName.USER));
        assertFalse(user.getRoles().contains(RoleName.ADMIN));*/
	}
	
	@Test
	public void testSetRoles() {/*
		//assertNotNull(user.getRoles());
        assertTrue(user.getRoles().isEmpty());
        Set<RoleName> roles = new HashSet<>();
        roles.add(RoleName.USER);
        user.setRoles(roles);
        assertFalse(user.getRoles().isEmpty());
        assertTrue(user.getRoles().contains(RoleName.USER));
        assertFalse(user.getRoles().contains(RoleName.ADMIN)); */
	}
	
	@Test
	public void testEquals() {
		
		assertTrue(user.equals(user));
		User user2 = new User("ALEXANDRE","Benjamin","benj@gmail.com","mdp", null, null, null, null, null, null, null);
		assertTrue(user.equals(user2));
		
		assertFalse(user.equals(null));
		assertFalse(user.equals("user"));
		
	}
	
	@Test 
	public void testHashCode() {
		
		User user2 = new User("ALEXANDRE","Benjamin","benj@gmail.com","mdp", null, null, null, null, null, null, null);
		assertEquals(user.hashCode(),user2.hashCode());
		
	}
	
	
}
