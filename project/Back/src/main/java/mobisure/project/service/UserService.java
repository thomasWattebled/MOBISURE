package mobisure.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mobisure.project.dto.UserDto;
import mobisure.project.entity.User;

@Service
public interface UserService {

	public List<UserDto> getAllUsers();
	
	public Optional<UserDto> getUserById(Long id);
	
	UserDto convertToDto(User user);
	
	User convertToEntity(UserDto userDto);
}
