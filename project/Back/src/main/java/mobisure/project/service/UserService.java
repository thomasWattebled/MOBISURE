package mobisure.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mobisure.project.dto.UserDto;
import mobisure.project.entity.User;

public interface UserService {

	public List<UserDto> getAllUsers();
	
	public Optional<UserDto> getUserById(Long id);
	
	void registerUser(UserDto userDto);
	
	UserDto convertToDto(User user);
	
	User convertToEntity(UserDto userDto);

}
