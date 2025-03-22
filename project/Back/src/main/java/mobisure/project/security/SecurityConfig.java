package mobisure.project.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import mobisure.project.repository.UserRepository;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Configuration class for Spring Security settings.
 */
@Configuration
public class SecurityConfig {

	@Autowired
	private UserRepository repoUser;

	/**
     * Configures the security filter chain for the application.
     *
     * @param http the {@link HttpSecurity} object used to configure security rules.
     * @return a {@link SecurityFilterChain} object.
     * @throws Exception if there is a problem with the configuration.
     */
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception{
		http
		.csrf((csrf) -> csrf.disable())
	 	.cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .authorizeHttpRequests(authz ->
        authz
            .requestMatchers(
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/swagger-ui.html",
                "/login",
                "/",
					"/login",
					"/auth/login",
					"/users/register",  // 🔥 Permet l'enregistrement sans authentification
					"/users/**","/devis/**").permitAll()
            .anyRequest().authenticated()
      )
	 .httpBasic(withDefaults())
	 .formLogin( formLogin-> formLogin
			 .permitAll()
			 .loginPage("http://localhost:3000/")
			 .loginPage("/auth/login")
			 )

	 .logout(withDefaults());

	return http.build();

	}

	/**
     * Configures the user details service used for authentication.
     *
     * @return a {@link UserDetailsService} implementation that retrieves user information from the database.
     */
	@Bean
	 public UserDetailsService userDetailsService() {

		return username -> {
	        mobisure.project.entity.User user = repoUser.findByMail(username)
	                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));


	        return new org.springframework.security.core.userdetails.User(
	                user.getMail(),
	                user.getMdp(),
	                user.getRoles().stream()
	                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))  // Ajout du préfixe "ROLE_" pour Spring Security
	                    .collect(Collectors.toList())
	        );
	    };

	 }

	/**
     * Configures the authentication manager for the application.
     *
     * @param userDetailsService the {@link UserDetailsService} used to load user data.
     * @param passwordEncoder the {@link PasswordEncoder} used for encoding and matching passwords.
     * @return an {@link AuthenticationManager} object.
     */
	 @Bean
	 public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder) {
		 DaoAuthenticationProvider authenticationProvider = new
		 DaoAuthenticationProvider();
		 authenticationProvider.setUserDetailsService(userDetailsService);
		 authenticationProvider.setPasswordEncoder(passwordEncoder);
	 		return new ProviderManager(authenticationProvider);
	 }

	 /**
	  * Configures the password encoder used to securely hash passwords.
	  *
	  * @return a {@link PasswordEncoder} object.
	 */
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	 }

	 /**
	  * Configures Cross-Origin Resource Sharing (CORS) settings.
	  *
	  * @return a {@link CorsConfigurationSource} object with the specified configuration.
	 */
	 @Bean
	 public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		 configuration.setAllowedOrigins(Collections.singletonList("*")); // 🔥 Autorise toutes les origines (Boomerang inclus)

		 configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE",
		"OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	 }

}
