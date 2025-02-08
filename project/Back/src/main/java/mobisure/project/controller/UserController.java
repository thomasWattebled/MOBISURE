package mobisure.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mobisure.project.dto.UserDto;
import mobisure.project.request.UserRoleUpdateRequest;
import mobisure.project.request.changeMdpRequest;
import mobisure.project.service.UserService;

/*
 * Controller to manage routes for users
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	/**
     * Retrieves the list of all users.
     *
     * @return a list containing all users as DTOs.
     */
	@GetMapping("/users")
	public List<UserDto> getAllUser(){
		return service.getAllUsers();
	}
	
	/**
     * Retrieves information of a specific user by its ID.
     *
     * @param id the user ID to retrieve.
     * @return an “Optional” object containing the user’s DTO if found.
     */
	@GetMapping("users/{id}")
	public Optional<UserDto> getUserById(@PathVariable Long id){
		return service.getUserById(id);
	}
	
	/**
     * Register a new user.
     *
     * @param userDto an object containing the user information to be saved.
     * @return an HTTP response indicating the status of the creation.
     */
	@PostMapping("users/register")
	public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
		System.out.println(userDto);
		try {
			service.registerUser(userDto);
			return ResponseEntity.status(HttpStatus.CREATED).body("Utilisateur créé avec succès.");
		}
		catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	/**
     * Updates a user’s roles.
     *
     * @param request an object containing the user ID and list of new roles.
     */
	@PostMapping("/users/updateRole")
	public void updateRole(@RequestBody UserRoleUpdateRequest request){
		service.updateRoleUser(request.getUserId(),request.getRoles());
	}
	
	/**
     * Remove a user by their ID.
     *
     * @param id the user ID to be deleted.
     */
	@DeleteMapping("users/delete/{id}")
	public void deleteUser(@PathVariable Long id) {
		service.delete(id);
	}
	
	@PostMapping("getUserEmail")
	public Optional<UserDto> getUserByEmail(@RequestBody Map<String, String> body){
		String email = body.get("email");
		Optional<UserDto> test = service.getUserByEmail(email);
		return service.getUserByEmail(email);
	}
	
	
	@PostMapping("users/changeMdp")
	public ResponseEntity<String> changeMdp(@RequestBody changeMdpRequest changeMdp) {
		try {
			service.changeMdp(changeMdp);
			return ResponseEntity.status(HttpStatus.CREATED).body("changement du mot de passe réussie");
		}
		catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@PutMapping("users/{id}")
	public ResponseEntity<Map<String,String>> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
	    try {
	        // On appelle le service pour mettre à jour l'utilisateur avec les nouvelles informations
	        service.updateUser(id, userDto);
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Utilisateur mis à jour avec succès.");
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    } catch (RuntimeException e) {
	    	Map<String, String> errorResponse = new HashMap<>();
	    	errorResponse.put("error", e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	    }
	}
	
	
}
