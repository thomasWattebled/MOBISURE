package mobisure.project.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mobisure.project.dto.UserDto;
import mobisure.project.entity.RoleName;
import mobisure.project.entity.User;
import mobisure.project.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repoUser;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = repoUser.findAll();
		List<UserDto> usersDto = new ArrayList<>();
		
		for(User user:users) {
			UserDto userDto = convertToDto(user);
			usersDto.add(userDto);
		}
		return usersDto;
	}

	@Override
	public Optional<UserDto> getUserById(Long id) {
		Optional<User> users = repoUser.findById(id);
		
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
		userDto.setAdresse(user.getAdresse());
		userDto.setDate_creation(user.getDate_creation());
		userDto.setDateNaissance(user.getDateNaissance());
		userDto.setSexe(user.getSexe());
		userDto.setTelephone(user.getTelephone());
		userDto.setRoles(user.getRoles());
		
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
		user.setAdresse(userDto.getAdresse());
		user.setDate_creation(userDto.getDate_creation());
		user.setDateNaissance(userDto.getDateNaissance());
		user.setSexe(userDto.getSexe());
		user.setTelephone(userDto.getTelephone());
		user.setRoles(userDto.getRoles());
		
		return user;
	}

	@Override
	public void registerUser(UserDto userDto) {
		
		User user = convertToEntity(userDto);
		System.out.print(user.toString());
		if(repoUser.findByMail(user.getMail()).isPresent()) {
			throw new RuntimeException("L'utilisateur avec cet email existe déjà.");
		}
		
		user.setMdp(passwordEncoder.encode(userDto.getMdp()));
		
		user.addRole(RoleName.USER);
		
		repoUser.save(user);
		
	}

	@Override
	public void updateRoleUser(long userId, List<String> role) {
		System.out.println(userId);
		System.out.println(role);
		Set<RoleName> roles = new HashSet<>();
		
		if(role.contains("USER")) {roles.add(RoleName.USER);}
		if(role.contains("ADMIN")) {roles.add(RoleName.ADMIN);}
		if(role.contains("PARTENAIRE")) {roles.add(RoleName.PARTENAIRE);}
		if(role.contains("MEDECIN")) {roles.add(RoleName.MEDECIN);}
		
		System.out.println(roles);
		
		Optional<User> user = repoUser.findById(userId);
		
		if(user.isPresent()) {
			user.get().setRoles(roles);
			repoUser.save(user.get());
		}
		
	}

	@Override
	public void delete(Long id) {
		repoUser.deleteById(id);
	}

}
