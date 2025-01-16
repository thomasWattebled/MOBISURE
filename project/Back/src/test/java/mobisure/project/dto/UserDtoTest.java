package mobisure.project.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mobisure.project.entity.RoleName;
import mobisure.project.entity.User;


public class UserDtoTest {

private UserDto user;
	
	@BeforeEach
	public void setUp() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		
		user = new UserDto("ALEXANDRE","Benjamin","benj@gmail.com","mdp", "Homme", dateNaissance, "39 rue de Lille", "0612234556", dateCreation);
	}
	
	@Test
	public void testConstructor() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		Set<RoleName> roles = new HashSet<>();
		
		assertEquals("ALEXANDRE", user.getNom());
        assertEquals("Benjamin", user.getPrenom());
        assertEquals("benj@gmail.com", user.getMail());
        assertEquals("mdp", user.getMdp());
        assertEquals(dateNaissance,user.getDateNaissance());
        assertEquals("39 rue de Lille",user.getAdresse());
        assertEquals("0612234556",user.getTelephone());
        assertEquals(dateCreation,user.getDate_creation());
        assertEquals(roles,user.getRoles());
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
	public void testHashCode() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		
		UserDto user2 = new UserDto("ALEXANDRE","Benjamin","benj@gmail.com","mdp", "Homme", dateNaissance, "39 rue de Lille", "0612234556", dateCreation);
		assertEquals(user.hashCode(),user2.hashCode());
		
	}
	
	@Test
	void testEqualsSameObject() {
		assertTrue(user.equals(user));
	}
	
	@Test
	void testEqualsTwoSameUser() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		
		UserDto user2 = new UserDto("ALEXANDRE","Benjamin","benj@gmail.com","mdp", "Homme", dateNaissance, "39 rue de Lille", "0612234556", dateCreation);
		assertTrue(user.equals(user2));
		
	}
	
	@Test
	void testEqualsWithNull() {
		assertFalse(user.equals(null));
	}
	
	@Test
	void testEqualsWithTypeDifferent() {
		assertFalse(user.equals("user"));
	}
	
	@Test
	void testEqualsAdresse() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		
		//Différent :
		UserDto user2 = new UserDto("ALEXANDRE","Benjamin","benj@gmail.com","mdp", "Homme", dateNaissance, "38 rue de Lille", "0612234556", dateCreation);
		
	    assertNotEquals(user,user2);
		
		//La même :
		user2.setAdresse("39 rue de Lille");
		assertEquals(user,user2);
	    
	}
	
	@Test
	void testEqualsDateNaissance() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateNaissance2 = dateFormat.parse("11/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		
		//Différent :
		UserDto user2 = new UserDto("ALEXANDRE","Benjamin","benj@gmail.com","mdp", "Homme", dateNaissance2, "39 rue de Lille", "0612234556", dateCreation);

		
		assertNotEquals(user,user2);
		
		//La même
		user2.setDateNaissance(dateNaissance);
		assertEquals(user,user2);
	}
	
	@Test
	void testEqualsId() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		
		//Différent :
		UserDto user2 = new UserDto("ALEXANDRE","Benjamin","benj@gmail.com","mdp", "Homme", dateNaissance, "39 rue de Lille", "0612234556", dateCreation);

		user2.setId(999L);
		assertNotEquals(user,user2);
		
		//La même
		user2.setId(null);
		assertEquals(user,user2);
	}
	
	@Test
	void testEqualsDateCreation() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		Date dateCreation2 = dateFormat.parse("11/08/1990");
		
		//Différent :
		UserDto user2 = new UserDto("ALEXANDRE","Benjamin","benj@gmail.com","mdp", "Homme", dateNaissance, "39 rue de Lille", "0612234556", dateCreation2);

		assertNotEquals(user,user2);
		
		//La même
		user2.setDate_creation(dateCreation);
		assertEquals(user,user2);	
	}
	
	@Test
	void testEqualsMail() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		
		//Différent :
		UserDto user2 = new UserDto("ALEXANDRE","Benjamin","ben@gmail.com","mdp", "Homme", dateNaissance, "39 rue de Lille", "0612234556", dateCreation);

		assertNotEquals(user,user2);
		
		//La même 
		user2.setMail("benj@gmail.com");
		assertEquals(user,user2);	
	}
	
	@Test
	void testEqualsMdp() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		
		//Différent :
		UserDto user2 = new UserDto("ALEXANDRE","Benjamin","benj@gmail.com","test", "Homme", dateNaissance, "39 rue de Lille", "0612234556", dateCreation);

		assertNotEquals(user,user2);
		
		//La même
		user2.setMdp("mdp");
		assertEquals(user,user2);	
	}
	
	@Test
	void testEqualsNom() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		
		//Différent :
		UserDto user2 = new UserDto("TEST","Benjamin","benj@gmail.com","mdp", "Homme", dateNaissance, "39 rue de Lille", "0612234556", dateCreation);

		assertNotEquals(user,user2);
		
		//La même
		user2.setNom("ALEXANDRE");
		assertEquals(user,user2);
	}
	
	@Test
	void testEqualsPrenom() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		
		//Différent :
		UserDto user2 = new UserDto("ALEXANDRE","test","benj@gmail.com","mdp", "Homme", dateNaissance, "39 rue de Lille", "0612234556", dateCreation);

		assertNotEquals(user,user2);
		
		//La même 
		user2.setPrenom("Benjamin");
		assertEquals(user,user2);
	}
	
	@Test
	void testEqualsSexe() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		
		//Différent :
		UserDto user2 = new UserDto("ALEXANDRE","Benjamin","benj@gmail.com","mdp", "Femme", dateNaissance, "39 rue de Lille", "0612234556", dateCreation);

		assertNotEquals(user,user2);
		
		//La même
		user2.setSexe("Homme");
		assertEquals(user,user2);
	}
	
	@Test
	void testEqualsTelephone() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		
		//Différent :
		UserDto user2 = new UserDto("ALEXANDRE","Benjamin","benj@gmail.com","mdp", "Homme", dateNaissance, "39 rue de Lille", "0612234557", dateCreation);

		assertNotEquals(user,user2);
		
		//La même
		user2.setTelephone("0612234556");
		assertEquals(user,user2);
	}
	
	@Test
	void testEqualsRole() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		
		//Différent :
		Set<RoleName> roles = new HashSet<>();
        roles.add(RoleName.USER);
        
		UserDto user2 = new UserDto("ALEXANDRE","Benjamin","benj@gmail.com","mdp", "Homme", dateNaissance, "39 rue de Lille", "0612234556", dateCreation);
		user2.setRoles(roles);
	    assertNotEquals(user,user2);
	    
	    //La même
	    Set<RoleName> roles2 = new HashSet<>();
	    user2.setRoles(roles2);
	    assertEquals(user,user2);
	}
	
}
