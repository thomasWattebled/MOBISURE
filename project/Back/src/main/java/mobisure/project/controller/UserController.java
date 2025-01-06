package mobisure.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mobisure.project.dto.UserDto;
import mobisure.project.request.UserRoleUpdateRequest;
import mobisure.project.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public List<UserDto> getAllUser(){
		return service.getAllUsers();
	}
	
	@GetMapping("users/{id}")
	public Optional<UserDto> getUserById(@PathVariable Long id){
		return service.getUserById(id);
	}
	
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
	
	@PostMapping("/users/updateRole")
	public void updateRole(@RequestBody UserRoleUpdateRequest request){
		service.updateRoleUser(request.getUserId(),request.getRoles());
	}
	
	@DeleteMapping("users/delete/{id}")
	public void deleteUser(@PathVariable Long id) {
		service.delete(id);
	}
	
	
	
}
