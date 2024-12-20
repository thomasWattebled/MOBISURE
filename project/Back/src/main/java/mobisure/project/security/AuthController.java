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
	 
	 
	 public record LoginRequest(String username, String password) {
		}

	 
	 public UserAuthDto createUserAuthDto( UserDetails userDetails ) {
		   
		   UserAuthDto user = new UserAuthDto();
		   
		   user.setUsername(userDetails.getUsername());
		   user.setRoles(userDetails.getAuthorities().stream()
		            .map(authority -> authority.getAuthority().replace("ROLE_", ""))
		            .collect(Collectors.toList()));
		   
		   return user;
	   }


	 
	
}
