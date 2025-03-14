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
import mobisure.project.request.changeMdpRequest;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repoUser;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
	 /**
	  * Retrieve all User entities and convert them to DTOs.
	  *
	  * @return a list of UserDto objects representing all users.
	  */
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

	/**
     * Retrieve a User by its ID and convert it to a DTO.
     *
     * @param id the ID of the User to retrieve.
     * @return an Optional containing the UserDto if found, or empty if not found.
     */
	@Override
	public Optional<UserDto> getUserById(Long id) {
		Optional<User> users = repoUser.findById(id);
		
		if(users.isPresent()) {
			UserDto userDto = convertToDto(users.get());
			return Optional.of(userDto);
		}
		
		return Optional.empty();
	}

	/**
     * Convert a User entity to a UserDto.
     *
     * @param user the User entity to be converted.
     * @return the corresponding UserDto object.
     */
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

	/**
     * Convert a UserDto to a User entity.
     *
     * @param userDto the UserDto object to be converted.
     * @return the corresponding User entity.
     */
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

	/**
     * Register a new User by converting a UserDto to an entity and saving it.
     *
     * @param userDto the UserDto object containing the data of the User to be registered.
     */
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

	/**
     * Update the roles of a User by their ID.
     *
     * @param userId the ID of the User whose roles will be updated.
     * @param list a list of role names to assign to the User.
     */
	@Override
	public void updateRoleUser(long userId, List<String> role) {
		System.out.println(userId);
		System.out.println(role);
		Set<RoleName> roles = new HashSet<>();
		
		if(role.contains("USER")) {roles.add(RoleName.USER);}
		if(role.contains("ADMIN")) {roles.add(RoleName.ADMIN);}
		if(role.contains("PARTENAIRE")) {roles.add(RoleName.PARTENAIRE);}
		if(role.contains("MEDECIN")) {roles.add(RoleName.MEDECIN);}
		if(role.contains("CONSEILLER")) {roles.add(RoleName.CONSEILLER);}
		
		
		System.out.println(roles);
		
		Optional<User> user = repoUser.findById(userId);
		
		if(user.isPresent()) {
			user.get().setRoles(roles);
			repoUser.save(user.get());
		}
		
	}

	/**
     * Delete a User by their ID.
     *
     * @param id the ID of the User to be deleted.
     */
	@Override
	public void delete(Long id) {
		repoUser.deleteById(id);
	}

	@Override
	public Optional<UserDto> getUserByEmail(String email) {
		
		Optional<User> user = repoUser.findByMail(email);
		
		if(user.isPresent()) {
			UserDto userDto = convertToDto(user.get());
			return Optional.of(userDto);
		}
		
		return Optional.empty();
	}

	@Override
	public void changeMdp(changeMdpRequest changeMdp) {
		System.out.println(changeMdp.getMail());
		System.out.println(changeMdp.getDate());
		System.out.println(changeMdp.getMdp());
		Optional<User> user = repoUser.findByMailAndDateNaissance(changeMdp.getMail(), changeMdp.getDate()); 
		if(user.isPresent()) {
			user.get().setMdp(passwordEncoder.encode(changeMdp.getMdp()));
			repoUser.save(user.get());
		}
		else {
			throw new RuntimeException("Ce compte n'existe pas.");
		}
		
	}

	@Override
	public void updateUser(Long id, UserDto userDto) {
	    // Vérifier si l'utilisateur existe
	    Optional<User> userOptional = repoUser.findById(id);
	    
	    if (userOptional.isPresent()) {
	        User user = userOptional.get();

	        // Mettre à jour les informations de l'utilisateur
	        if (userDto.getMail() != null && !userDto.getMail().isEmpty()) {
	            user.setMail(userDto.getMail());
	        }
	        if (userDto.getNom() != null && !userDto.getNom().isEmpty()) {
	            user.setNom(userDto.getNom());
	        }
	        if (userDto.getPrenom() != null && !userDto.getPrenom().isEmpty()) {
	            user.setPrenom(userDto.getPrenom());
	        }
	        if (userDto.getDateNaissance() != null) {
	            user.setDateNaissance(userDto.getDateNaissance());
	        }
	        if (userDto.getAdresse() != null && !userDto.getAdresse().isEmpty()) {
	            user.setAdresse(userDto.getAdresse());
	        }
	        if (userDto.getTelephone() != null && !userDto.getTelephone().isEmpty()) {
	            user.setTelephone(userDto.getTelephone());
	        }
	        if (userDto.getSexe() != null && !userDto.getSexe().isEmpty()) {
	            user.setSexe(userDto.getSexe());
	        }
	        
	        // Si le mot de passe est fourni, on le met à jour aussi
	        if (userDto.getMdp() != null && !userDto.getMdp().isEmpty()) {
	            user.setMdp(passwordEncoder.encode(userDto.getMdp()));
	        }
	        // Sauvegarder l'utilisateur mis à jour
	        repoUser.save(user);
	    } else {
	        throw new RuntimeException("Utilisateur non trouvé");
	    }
	}
	
	public List<UserDto> getUsersByRole(RoleName role) {
		
		List<User> users = repoUser.findAll();
		List<UserDto> res = new ArrayList<>();
		users.forEach( u -> {
			if(u.getRoles().contains(role)) {
				UserDto us = convertToDto(u);
				res.add(us); 
			}
		});
		
		return res;
	}

}
