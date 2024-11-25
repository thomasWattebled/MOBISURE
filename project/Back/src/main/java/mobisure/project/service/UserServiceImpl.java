package mobisure.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mobisure.project.dto.UserDto;
import mobisure.project.entity.User;
import mobisure.project.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = repo.findAll();
		List<UserDto> usersDto = new ArrayList<>();
		
		for(User user:users) {
			UserDto userDto = convertToDto(user);
			usersDto.add(userDto);
		}
		return usersDto;
	}

	@Override
	public Optional<UserDto> getUserById(Long id) {
		Optional<User> users = repo.findById(id);
		
		if(users.isPresent()) {
			UserDto userDto = convertToDto(users.get());
			return Optional.of(userDto);
		}
		
		return Optional.empty();
	}

	@Override
	public UserDto convertToDto(User user) {
		
		UserDto userDto = new UserDto();
		
		userDto.setId(user.getId());
		userDto.setMail(user.getMail());
		userDto.setMdp(user.getMdp());
		userDto.setNom(user.getNom());
		userDto.setPrenom(user.getPrenom());
		
		return userDto;
	}

	@Override
	public User convertToEntity(UserDto userDto) {
		
		User user = new User();
		
		user.setId(userDto.getId());
		user.setMail(userDto.getMail());
		user.setMdp(userDto.getMdp());
		user.setNom(userDto.getNom());
		user.setPrenom(userDto.getPrenom());
		
		return user;
	}

}
