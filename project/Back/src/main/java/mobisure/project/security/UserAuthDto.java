package mobisure.project.security;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object (DTO) representing authentication information for a user.
 */
public class UserAuthDto {
	
	private String username;
	private List<String> roles;
	
	/**
     * Constructs a UserAuthDto with a specific username.
     * Initializes the roles list as an empty ArrayList.
     * 
     * @param username the username of the user.
     */
	public UserAuthDto(String username) {
		super();
		this.username = username;
		this.roles = new ArrayList<>();
	}

	/**
     * Default constructor for UserAuthDto.
     */
	public UserAuthDto() {}

	/**
     * Retrieves the username of the user.
     * 
     * @return the username as a {@link String}.
     */
	public String getUnsername() {
		return username;
	}
	
	/**
     * Sets the username of the user.
     * 
     * @param username the new username to set.
     */
	public void setUsername(String unsername) {
		this.username = unsername;
	}
	
	/**
     * Retrieves the roles assigned to the user.
     * 
     * @return a {@link List} of roles as strings.
     */
	public List<String> getRoles() {
		return roles;
	}
	
	/**
     * Sets the roles for the user.
     * 
     * @param roles a {@link List} of roles to assign to the user.
     */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	
	
}
