package mobisure.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mobisure.project.dto.UserDto;
import mobisure.project.entity.RoleName;
import mobisure.project.entity.User;
import mobisure.project.request.changeMdpRequest;

/*
 * Service interface for managing User operations. 
 */
public interface UserService {

	/**
     * Retrieve all User entities and convert them to DTOs.
     *
     * @return a list of UserDto objects representing all users.
     */
	public List<UserDto> getAllUsers();
	
	/**
     * Retrieve a User by its ID and convert it to a DTO.
     *
     * @param id the ID of the User to retrieve.
     * @return an Optional containing the UserDto if found, or empty if not found.
     */
	public Optional<UserDto> getUserById(Long id);
	
	/**
     * Register a new User by converting a UserDto to an entity and saving it.
     *
     * @param userDto the UserDto object containing the data of the User to be registered.
     */
	void registerUser(UserDto userDto);
	
	/**
     * Convert a User entity to a UserDto.
     *
     * @param user the User entity to be converted.
     * @return the corresponding UserDto object.
     */
	UserDto convertToDto(User user);
	
	/**
     * Convert a UserDto to a User entity.
     *
     * @param userDto the UserDto object to be converted.
     * @return the corresponding User entity.
     */
	User convertToEntity(UserDto userDto);

	/**
     * Update the roles of a User by their ID.
     *
     * @param userId the ID of the User whose roles will be updated.
     * @param list a list of role names to assign to the User.
     */
	void updateRoleUser(long userId , List<String> list);

	/**
     * Delete a User by their ID.
     *
     * @param id the ID of the User to be deleted.
     */
	public void delete(Long id);

	public Optional<UserDto> getUserByEmail(String email);

	public void changeMdp(changeMdpRequest changeMdp);

	public void updateUser(Long id, UserDto userDto);
	
	public List<UserDto> getUsersByRole(RoleName role);
}
