package mobisure.project.security;

import java.util.ArrayList;
import java.util.List;

public class UserAuthDto {
	
	private String username;
	private List<String> roles;
	
	
	
	public UserAuthDto(String username) {
		super();
		this.username = username;
		this.roles = new ArrayList<>();
	}

	public UserAuthDto() {}

	public String getUnsername() {
		return username;
	}
	
	public void setUsername(String unsername) {
		this.username = unsername;
	}
	
	public List<String> getRoles() {
		return roles;
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	
	
}
