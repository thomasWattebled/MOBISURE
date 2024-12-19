package mobisure.project.security;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import mobisure.project.entity.RoleName;
import mobisure.project.entity.User;
import mobisure.project.repository.UserRepository;

public class SecurityConfigTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Mock
    private UserRepository repoUser;

    @InjectMocks
    private SecurityConfig securityConfig;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testUserDetailsService_UserFound() {
    	User user = new User();
    	user.setMail("benj@gmail.com");
    	user.setMdp("mdp");
    	Set<RoleName> roles = new HashSet<>();
    	roles.add(RoleName.ADMIN);
    	user.setRoles(roles);
    	
    	when(repoUser.findByMail("benj@gmail.com")).thenReturn(Optional.of(user));
    	UserDetails userDetails = securityConfig.userDetailsService().loadUserByUsername("benj@gmail.com");
    	
    	assertNotNull(userDetails);
    	assertEquals("benj@gmail.com",userDetails.getUsername());
    	assertEquals("mdp",userDetails.getPassword());
    }
    
    @Test
    public void testUserDetailsService_UserNotFound() {
    	when(repoUser.findByMail("benj@mail.com")).thenReturn(Optional.empty());
    	assertThrows(RuntimeException.class, 
                () -> securityConfig.userDetailsService().loadUserByUsername("benj@mail.com")
            );
    }
    
    @Test
    public void testPasswordEncoder() {
    	PasswordEncoder encoder = securityConfig.passwordEncoder();
    	assertNotNull(encoder);
    	String test = encoder.encode("mdp");
    	assertNotNull(test);
    	assertTrue(encoder.matches("mdp", test));
    }

    @Test
    public void testAuthenticationManager() {
    	UserDetailsService userDetailsService = securityConfig.userDetailsService();
    	PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
    	AuthenticationManager test = securityConfig.authenticationManager(userDetailsService, passwordEncoder);
    	assertNotNull(test);
    }
    
}
