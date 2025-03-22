package mobisure.project.request;

import java.util.List;

/*
 * Class to handle requests for changes in a user’s role
 */
public class UserRoleUpdateRequest {

	private Long userId;
	private List<String> roles;
	
	/**
	 * @return the user's ID
	 */
	public Long getUserId() {
        return userId;
    }

	/**
	 * Set the user's ID
	 * 
	 * @param userId : the new id of user
	 */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the list of user's role
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Modify the list of user's role
     * @param roles : the new list of role
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
	
}
