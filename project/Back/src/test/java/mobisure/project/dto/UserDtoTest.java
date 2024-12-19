package mobisure.project.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mobisure.project.entity.RoleName;


public class UserDtoTest {

private UserDto user;
	
	@BeforeEach
	public void setUp() {
		user = new UserDto("ALEXANDRE","Benjamin","benj@gmail.com","mdp");
	}
	
	@Test
	public void testConstructor() {
		assertEquals("ALEXANDRE", user.getNom());
        assertEquals("Benjamin", user.getPrenom());
        assertEquals("benj@gmail.com", user.getMail());
        assertEquals("mdp", user.getMdp());
        assertNotNull(user.getRoles());
        assertTrue(user.getRoles().isEmpty());
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
		assertNotNull(user.getRoles());
        assertTrue(user.getRoles().isEmpty());
        user.addRole(RoleName.USER);
        assertFalse(user.getRoles().isEmpty());
        assertTrue(user.getRoles().contains(RoleName.USER));
        assertFalse(user.getRoles().contains(RoleName.ADMIN));
	}
	
	@Test
	public void testSetRoles() {
		assertNotNull(user.getRoles());
        assertTrue(user.getRoles().isEmpty());
        Set<RoleName> roles = new HashSet<>();
        roles.add(RoleName.USER);
        user.setRoles(roles);
        assertFalse(user.getRoles().isEmpty());
        assertTrue(user.getRoles().contains(RoleName.USER));
        assertFalse(user.getRoles().contains(RoleName.ADMIN)); 
	}
	
	@Test
	public void testEquals() {
		
		assertTrue(user.equals(user));
		UserDto user2 = new UserDto("ALEXANDRE","Benjamin","benj@gmail.com","mdp");
		assertTrue(user.equals(user2));
		
		assertFalse(user.equals(null));
		assertFalse(user.equals("user"));
		
	}
	
	@Test 
	public void testHashCode() {
		
		UserDto user2 = new UserDto("ALEXANDRE","Benjamin","benj@gmail.com","mdp");
		assertEquals(user.hashCode(),user2.hashCode());
		
	}
	
}
