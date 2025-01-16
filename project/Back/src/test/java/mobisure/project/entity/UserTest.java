package mobisure.project.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        assertNull(user.getRoles());
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
	public void testNumeroClient() {
		
	}
	
	@Test
	public void testAddRole() {
		assertNull(user.getRoles());
        user.addRole(RoleName.USER);
        assertFalse(user.getRoles().isEmpty());
        assertTrue(user.getRoles().contains(RoleName.USER));
        assertFalse(user.getRoles().contains(RoleName.ADMIN));
	}
	
	@Test
	public void testSetRoles() {
		assertNull(user.getRoles());
        Set<RoleName> roles = new HashSet<>();
        roles.add(RoleName.USER);
        user.setRoles(roles);
        assertFalse(user.getRoles().isEmpty());
        assertTrue(user.getRoles().contains(RoleName.USER));
        assertFalse(user.getRoles().contains(RoleName.ADMIN)); 
	}
	
	@Test
	public void testSexe() {
		assertNull(user.getSexe());
        user.setSexe("Monsieur");
        assertNotNull(user.getSexe());
        assertEquals(user.getSexe(),"Monsieur");
	}
	
	@Test
	public void testAdresse() {
		String adresse = "2 rue JeanBouin";
		assertNull( user.getAdresse());
		user.setAdresse(adresse);
		assertNotNull(user.getAdresse());
		assertEquals(adresse, user.getAdresse());
	}
	
	@Test
	public void testTelephone() {
		String telephone = "0607091011";
		assertNull( user.getTelephone());
		user.setTelephone(telephone);
		assertNotNull(user.getTelephone());
		assertEquals(telephone, user.getTelephone());
	}
	
	@Test
	public void testDateNaissance() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("15/08/1990");
		assertNull(user.getDateNaissance());
        user.setDateNaissance(dateNaissance);
        assertNotNull(user.getDateNaissance());
        assertEquals(user.getDateNaissance(),dateNaissance);
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
