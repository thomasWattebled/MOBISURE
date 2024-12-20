package mobisure.project.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class AuthControllerTest {
	
	@InjectMocks
	private AuthController authController;
	
	@Mock
    private UserDetailsService userService;
	
	@BeforeEach
    void setUp() {
		MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void testcreateUserAuthDto() {
		
		UserDetails userDetails = User.builder()
	            .username("user1")
	            .password("password1")
	            .authorities(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"))
	            .build();
		
		UserAuthDto userAuthDto = authController.createUserAuthDto(userDetails);
		
		assertEquals("user1", userAuthDto.getUnsername());
		assertEquals(2, userAuthDto.getRoles().size());
		assertTrue(userAuthDto.getRoles().contains("USER"));
		assertTrue(userAuthDto.getRoles().contains("ADMIN"));
	}

	@Test
    public void testUserPrincipal_NoPrincipal() {
		 String result = authController.userPrincipal(null);
		 assertEquals("No principal",result);
	}
	
	@Test
    public void testUserPrincipal_UserExists() {
		
		Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("user1");

        UserDetails userDetails = User.builder()
                .username("user1")
                .password("password1")
                .authorities(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"))
                .build();

        when(userService.loadUserByUsername("user1")).thenReturn(userDetails);
        
        String res = authController.userPrincipal(principal);
        assertEquals("Current user: user1, roles: ROLE_ADMIN, ROLE_USER, ",res);
	}
	
	@Test
	public void testUserPrincipal_UserNotFound() {
		
		Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("unknownUser");

        when(userService.loadUserByUsername("unknownUser")).thenReturn(null);
        
        String res = authController.userPrincipal(principal);
        assertEquals("Can't find principal for unknownUser",res);
	}
	
	
}
