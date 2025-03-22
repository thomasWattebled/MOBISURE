package mobisure.project.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRoleUpdateRequestTest {

	private UserRoleUpdateRequest request;
	
	@BeforeEach
	public void setUp() {
		
		List<String> roles = new ArrayList<>();
		
		request = new UserRoleUpdateRequest();
		request.setUserId((long) 1);
		request.setRoles(roles);
	}
	
	@Test
	void testUserId() {
		assertEquals((long) 1,request.getUserId());
		request.setUserId((long)2);
		assertEquals((long) 2,request.getUserId());
	}
	
	@Test
	void testRoles() {
		List<String> roles = new ArrayList<>();
		assertEquals(roles,request.getRoles());
		roles.add("Role1");
		request.setRoles(roles);
		assertEquals(roles,request.getRoles());
		assertTrue(request.getRoles().contains("Role1"));
	}
}
