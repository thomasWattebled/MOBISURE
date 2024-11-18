package mobisure.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import mobisure.project.dto.UserDto;
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
	
}