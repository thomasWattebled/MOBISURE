package mobisure.project.security;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UserAuthDtoTest {

	private UserAuthDto user;
	
	@BeforeEach
	public void setUp() {
		user = new UserAuthDto("Benjamin", null);
	}
	
	@Test
	public void testUserName() {
		assertEquals("Benjamin",user.getUnsername());
		user.setUsername("Alexandre");
		assertEquals("Alexandre",user.getUnsername());
	}
	
	@Test
	public void testRoles() {
		assertNotNull(user.getRoles());
		assertTrue(user.getRoles().isEmpty());
		List<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		user.setRoles(roles);
		assertEquals(1,user.getRoles().size());
		assertTrue(user.getRoles().contains("ADMIN"));
		assertEquals(roles,user.getRoles());
	}
	
}
