package mobisure.project.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mobisure.project.security.AuthController.LoginRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

class AuthControllerTest {
	
	@InjectMocks
	private AuthController authController;
	
	@Mock
    private UserDetailsService userService;
	
	 @Mock
	 private AuthenticationManager authenticationManager;
	 
	 @Mock
	 private SecurityContextHolderStrategy securityContextHolderStrategy;
	 
	 @Mock
	 private SecurityContext securityContext;
	 
	 @Mock
	 private HttpServletRequest request;
	 
	 @Mock
	 private HttpServletResponse response;
	
	@BeforeEach
    void setUp() {
		MockitoAnnotations.openMocks(this);
    }
	
	@Test
	void testcreateUserAuthDto() {
		
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
    void testUserPrincipal_NoPrincipal() {
		 String result = authController.userPrincipal(null);
		 assertEquals("No principal",result);
	}
	
	@Test
    void testUserPrincipal_UserExists() {
		
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
	void testUserPrincipal_UserNotFound() {
		
		Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("unknownUser");

        when(userService.loadUserByUsername("unknownUser")).thenReturn(null);
        
        String res = authController.userPrincipal(principal);
        assertEquals("Can't find principal for unknownUser",res);
	}
	
	@Test
	void testAuthenticateUser_Success() {
		LoginRequest loginRequest = new LoginRequest("user1", "password1");
		UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated("user1", "password1");
		
		Authentication authResult = new UsernamePasswordAuthenticationToken(
	            "user1",
	            "password1",
	            List.of(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"))
	        );
		
		UserDetails userDetails = User.builder()
	            .username("user1")
	            .password("password1")
	            .authorities(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"))
	            .build();
		
		when(authenticationManager.authenticate(authRequest)).thenReturn(authResult);
		when(securityContextHolderStrategy.createEmptyContext()).thenReturn(securityContext);
		when(userService.loadUserByUsername("user1")).thenReturn(userDetails);
		
		 UserAuthDto userAuthDto = authController.authenticateUser(loginRequest, request, response);
	
		 assertNotNull(userAuthDto);
		 assertEquals("user1", userAuthDto.getUnsername());
		 assertEquals(2, userAuthDto.getRoles().size());
		 assertTrue(userAuthDto.getRoles().contains("USER"));
	     assertTrue(userAuthDto.getRoles().contains("ADMIN"));
	   	}
 	
	@Test
    void testAuthenticateUser_UserNotFound() {
		LoginRequest loginRequest = new LoginRequest("unknownUser", "password");
		UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated("unknownUser", "password");
		
		Authentication authResult = new UsernamePasswordAuthenticationToken(
	            "unknownUser",
	            "password",
	            List.of()
	        );
		
		when(authenticationManager.authenticate(authRequest)).thenReturn(authResult);
        when(securityContextHolderStrategy.createEmptyContext()).thenReturn(securityContext);
        when(userService.loadUserByUsername("unknownUser")).thenReturn(null);
        
        Exception exception = assertThrows(InternalAuthenticationServiceException.class, () -> {
            authController.authenticateUser(loginRequest, request, response);
        });
        
        assertEquals("Can't find user after authentication", exception.getMessage());
	}
	
}
