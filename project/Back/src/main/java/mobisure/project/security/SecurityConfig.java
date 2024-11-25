package mobisure.project.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception{
		http
		.csrf((csrf) -> csrf.disable())
	 	.cors(cors -> cors.configurationSource(corsConfigurationSource()))
	 	.authorizeHttpRequests((authz) -> 
	 		authz
	 			 .requestMatchers("/users/**").permitAll()
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
	
	
	@Bean
	 public UserDetailsService userDetailsService() {
		 
		 UserDetails theUser = User.withUsername("user")
				 .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
				 .password("user").roles("USER").build();
		 
		 InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		 userDetailsManager.createUser(theUser);
		 
		 return userDetailsManager;
		 
	 }
	 
	 
	 @Bean
	 public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder) {
		 DaoAuthenticationProvider authenticationProvider = new
		 DaoAuthenticationProvider();
		 authenticationProvider.setUserDetailsService(userDetailsService);
		 authenticationProvider.setPasswordEncoder(passwordEncoder);
	 		return new ProviderManager(authenticationProvider);
	 }
	 
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	 }
	 
	 
	 @Bean
	 public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", 
		"OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		configuration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	 }

}
