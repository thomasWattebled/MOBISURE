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
	public void setUp() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		user = new User("ALEXANDRE","Benjamin","benj@gmail.com","mdp", "Homme", dateNaissance, "39 rue de Lille", "12230604", "0612234556", dateCreation, null);
	}
	
	@Test
	void testConstructor() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		
		assertEquals("ALEXANDRE", user.getNom());
        assertEquals("Benjamin", user.getPrenom());
        assertEquals("benj@gmail.com", user.getMail());
        assertEquals("mdp", user.getMdp());
        assertEquals(dateNaissance,user.getDateNaissance());
        assertEquals("39 rue de Lille",user.getAdresse());
        assertEquals("12230604",user.getNumero_client());
        assertEquals("0612234556",user.getTelephone());
        assertEquals(dateCreation,user.getDate_creation());
        assertNull(user.getRoles());
	}
	
	@Test
	void testId() {
		user.setId((long) 2);
		assertEquals((long) 2, user.getId());
	}
	
	@Test
	void testNom() {
		assertEquals("ALEXANDRE", user.getNom());
		user.setNom("Nom");
		assertEquals("Nom", user.getNom());
	}
	
	@Test
	void testPrenom() {
		assertEquals("Benjamin", user.getPrenom());
		user.setPrenom("Prenom");
		assertEquals("Prenom", user.getPrenom());
	}
	
	@Test
	void testMdp() {
		assertEquals("mdp", user.getMdp());
		user.setMdp("mdp2");
		assertEquals("mdp2", user.getMdp());
	}
	
	@Test
	void testMail() {
		assertEquals("benj@gmail.com", user.getMail());
		user.setMail("benj2@gmail.com");
		assertEquals("benj2@gmail.com", user.getMail());
	}
	
	@Test
	void testNumeroClient() {
		assertEquals("12230604",user.getNumero_client());
		user.setNumero_client("1234567");
		assertEquals("1234567",user.getNumero_client());
	}
	
	@Test
	void testAddRole() {
		assertNull(user.getRoles());
        user.addRole(RoleName.USER);
        assertFalse(user.getRoles().isEmpty());
        assertTrue(user.getRoles().contains(RoleName.USER));
        assertFalse(user.getRoles().contains(RoleName.ADMIN));
	}
	
	@Test
	void testSetRoles() {
		assertNull(user.getRoles());
        Set<RoleName> roles = new HashSet<>();
        roles.add(RoleName.USER);
        user.setRoles(roles);
        assertFalse(user.getRoles().isEmpty());
        assertTrue(user.getRoles().contains(RoleName.USER));
        assertFalse(user.getRoles().contains(RoleName.ADMIN)); 
	}
	
	@Test
	void testSexe() {
		assertEquals("Homme",user.getSexe());
        user.setSexe("Monsieur");
        assertNotNull(user.getSexe());
        assertEquals(user.getSexe(),"Monsieur");
	}
	
	@Test
	void testAdresse() {
		String adresse = "2 rue JeanBouin";
		assertEquals("39 rue de Lille",user.getAdresse());
		user.setAdresse(adresse);
		assertNotNull(user.getAdresse());
		assertEquals(adresse, user.getAdresse());
	}
	
	@Test
	void testTelephone() {
		String telephone = "0607091011";
		assertEquals("0612234556",user.getTelephone());
		user.setTelephone(telephone);
		assertNotNull(user.getTelephone());
		assertEquals(telephone, user.getTelephone());
	}
	
	@Test
	void testDateNaissance() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		assertEquals(dateNaissance,user.getDateNaissance());
        user.setDateNaissance(dateNaissance);
        assertNotNull(user.getDateNaissance());
        assertEquals(user.getDateNaissance(),dateNaissance);
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
		
		User user2 = new User("ALEXANDRE","Benjamin","benj@gmail.com","mdp", "Homme", dateNaissance, "39 rue de Lille", "12230604", "0612234556", dateCreation, null);
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
		User user2 = new User("ALEXANDRE", "Benjamin", "benj@gmail.com", "mdp", "Homme", dateNaissance, 
                "DIFFERENT", "12230604", "0612234556", dateCreation, null);
		
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
		User user2 = new User("ALEXANDRE", "Benjamin", "benj@gmail.com", "mdp", "Homme", dateNaissance2, 
				"39 rue de Lille", "12230604", "0612234556", dateCreation, null);
		
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
		User user2 = new User("ALEXANDRE", "Benjamin", "benj@gmail.com", "mdp", "Homme", dateNaissance, 
				"39 rue de Lille", "12230604", "0612234556", dateCreation, null);
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
		User user2 = new User("ALEXANDRE", "Benjamin", "benj@gmail.com", "mdp", "Homme", dateNaissance, 
				"39 rue de Lille", "12230604", "0612234556", dateCreation2, null);
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
		User user2 = new User("ALEXANDRE", "Benjamin", "ben@gmail.com", "mdp", "Homme", dateNaissance, 
				"39 rue de Lille", "12230604", "0612234556", dateCreation, null);
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
		User user2 = new User("ALEXANDRE", "Benjamin", "benj@gmail.com", "test", "Homme", dateNaissance, 
				"39 rue de Lille", "12230604", "0612234556", dateCreation, null);
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
		User user2 = new User("TEST", "Benjamin", "benj@gmail.com", "mdp", "Homme", dateNaissance, 
				"39 rue de Lille", "12230604", "0612234556", dateCreation, null);
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
		User user2 = new User("ALEXANDRE", "Test", "benj@gmail.com", "mdp", "Homme", dateNaissance, 
				"39 rue de Lille", "12230604", "0612234556", dateCreation, null);
		assertNotEquals(user,user2);
		
		//La même 
		user2.setPrenom("Benjamin");
		assertEquals(user,user2);
	}
	
	@Test
	void testEqualsNumClient() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		
		//Différent :
		User user2 = new User("ALEXANDRE", "Benjamin", "benj@gmail.com", "mdp", "Homme", dateNaissance, 
				"39 rue de Lille", "12230605", "0612234556", dateCreation, null);
		assertNotEquals(user,user2);
		
		//La même
		user2.setNumero_client("12230604");
		assertEquals(user,user2);
	}
	
	@Test
	void testEqualsSexe() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		
		//Différent :
		User user2 = new User("ALEXANDRE", "Benjamin", "benj@gmail.com", "mdp", "Femme", dateNaissance, 
				"39 rue de Lille", "12230604", "0612234556", dateCreation, null);
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
		User user2 = new User("ALEXANDRE", "Benjamin", "benj@gmail.com", "mdp", "Homme", dateNaissance, 
				"39 rue de Lille", "12230604", "0612234557", dateCreation, null);
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
        
        User user2 = new User("ALEXANDRE", "Benjamin", "benj@gmail.com", "mdp", "Homme", dateNaissance, 
				"39 rue de Lille", "12230604", "0612234556", dateCreation, roles);
	    assertNotEquals(user,user2);
	    
	    //La même
	    user2.setRoles(null);
	    assertEquals(user,user2);
	}
	
	@Test 
	void testHashCode() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		
		User user2 = new User("ALEXANDRE","Benjamin","benj@gmail.com","mdp", "Homme", dateNaissance, "39 rue de Lille", "12230604", "0612234556", dateCreation, null);
		assertEquals(user.hashCode(),user2.hashCode());
		
	}
	
}
