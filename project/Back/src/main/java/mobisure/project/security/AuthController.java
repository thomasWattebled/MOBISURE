package mobisure.project.security;

import java.security.Principal;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * Controller to manage routes for the identification
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	 private UserDetailsService userService;
	 
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	 private AuthenticationManager authenticationManager;
	
	 private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

	 private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

	 public AuthController() {}
	 
	 
	 /**
	  *	Authenticates a user using their username and password.
	  * If authentication is successful, a security token is created and the security context is updated.
	  *
	  * @param loginRequest Login information (username and password).
	  * @param request the HTTP request to save the security context.
	  * @param response the HTTP response to record the security context.
	  * @return a UserAuthDto object containing the authenticated user information.
	  * @throws InternalAuthenticationServiceException if the user is not found after authentication.
	 */
	 @PostMapping("/login")
	 public UserAuthDto authenticateUser(@RequestBody LoginRequest loginRequest, 
			 HttpServletRequest request, HttpServletResponse response) {
		 
		 UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
				 loginRequest.username(), loginRequest.password());
		 
		 Authentication authentication = authenticationManager.authenticate(token); 
		 
		 SecurityContext context = securityContextHolderStrategy.createEmptyContext();

		 context.setAuthentication(authentication); 

		 securityContextHolderStrategy.setContext(context);

		 securityContextRepository.saveContext(context, request, response); 

		 UserDetails existingUser = userService.loadUserByUsername(authentication.getName() );

		 System.out.println(existingUser);
		 
		 if (existingUser == null ) {
			 throw new InternalAuthenticationServiceException("Can't find user after authentication");
		}
		 
		return  createUserAuthDto(existingUser);

	 }

	 /**
	  * Gets information about the currently authenticated user.
	  * 
	  * @param principal the “Main” object representing the currently authenticated user.
	  * @return a string containing user information (name and roles).
	 */
	 @GetMapping("/user")
	 public String userPrincipal(Principal principal) {
		 
		 if( principal == null) {
			 return "No principal";
			 }
		 
		 UserDetails existingUser=userService.loadUserByUsername(principal.getName() );
		 
		 if (existingUser != null ) {
			 return "Current user: "
			 + existingUser.getUsername()
			 +", roles: "
			 + existingUser.getAuthorities().stream()
			 	.map( authority-> authority.getAuthority() + ", ")
			 	.collect( Collectors.joining() ) ;
		 } 
		 else {
			 return "Can't find principal for " + principal.getName();
		 }
	 }
	 
	 /**
	  * Represents the user’s login information (username and password).
	 */
	 public record LoginRequest(String username, String password) {
		}

	 
	 /**
	  * Creates a UserAuthDto object containing the user name and roles.
	  *
	  * @param userDetails the details of the authenticated user.
	  * @return a ‘UserAuthDto’ object containing the user’s information.
	 */
	 public UserAuthDto createUserAuthDto( UserDetails userDetails ) {
		   
		   UserAuthDto user = new UserAuthDto();
		   
		   user.setUsername(userDetails.getUsername());
		   user.setRoles(userDetails.getAuthorities().stream()
		            .map(authority -> authority.getAuthority().replace("ROLE_", ""))
		            .collect(Collectors.toList()));
		   
		   return user;
	   }


	 
	
}
