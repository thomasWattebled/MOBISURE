package mobisure.project.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import mobisure.project.dto.UserDto;
import mobisure.project.entity.RoleName;
import mobisure.project.entity.User;
import mobisure.project.repository.UserRepository;
import mobisure.project.request.changeMdpRequest;

class UserServiceImplTest {
	
	@Mock
    private UserRepository repoUser;
	
	@Mock
    private PasswordEncoder passwordEncoder;
	
	@InjectMocks
    private UserServiceImpl userService;
	
	private UserDto userDto;
    private User user;
    
    @BeforeEach
    void setUp() throws ParseException {
    	MockitoAnnotations.openMocks(this);
    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("12/08/1990");
		Date dateCreation = dateFormat.parse("16/01/2025");
		Set<RoleName> roles = new HashSet<>();
		user = new User("ALEXANDRE","Benjamin","benj@gmail.com","mdp", "Homme", dateNaissance, "39 rue de Lille", null, "0612234556", dateCreation, roles);
    	user.setId(1L);
		
		userDto = new UserDto("ALEXANDRE","Benjamin","benj@gmail.com","mdp", "Homme", dateNaissance, "39 rue de Lille","0612234556", dateCreation);
    	userDto.setId(1L);
    }
    
    @Test
    void testRegisterUser_MailExisting() {
    	
    	when(repoUser.findByMail(user.getMail())).thenReturn(Optional.of(user));
    	
    	Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(userDto);
        });
    	
    	assertEquals("L'utilisateur avec cet email existe déjà.", exception.getMessage());
    	
    }
	
    @Test
    void testRegisterUser_Success() {
        
        when(repoUser.findByMail(user.getMail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(userDto.getMdp())).thenReturn("encodedPassword");
        
        userService.registerUser(userDto);
        
        verify(repoUser, times(1)).save(any(User.class)); 
    }

    @Test
    void testGetUserById_Found() {
    	
    	when(repoUser.findById(user.getId())).thenReturn(Optional.of(user));
    	
    	Optional<UserDto> result = userService.getUserById(user.getId());
    	
    	assertTrue(result.isPresent());
    	assertEquals(userDto.getId(),result.get().getId());
    }
    
    @Test
    void testGetUserById_NotFound() {
    	
    	when(repoUser.findById(user.getId())).thenReturn(Optional.empty());
    	
    	Optional<UserDto> result = userService.getUserById(user.getId());
    	
    	assertFalse(result.isPresent());
    }
    
    @Test
    void testGetAllUser() {
    	
    	when(repoUser.findAll()).thenReturn(List.of(user));
    	
    	userService.registerUser(userDto);
    	
    	List<UserDto> users = userService.getAllUsers();
    	users.get(0).addRole(RoleName.USER);
    	assertFalse(users.isEmpty());
    	assertEquals(1,users.size());
    	System.out.println(users.get(0));
    	assertEquals(userDto,users.get(0));
    }
    
    @Test
    void testconvertToDto() {
    	
    	UserDto dto = userService.convertToDto(user);
    	
    	assertEquals(userDto,dto);
    	
    }
    
    @Test
    void convertToEntity() {
    	User entity = userService.convertToEntity(userDto);
    	assertEquals(entity,user);
    }
    
    @Test
    void testUpdateRoleUser_UserExists_ADMIN(){
    	
    	long userId = 1L;
        List<String> roles = List.of("ADMIN");

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setRoles(new HashSet<>());
        
        when(repoUser.findById(userId)).thenReturn(Optional.of(existingUser));
        
        userService.updateRoleUser(userId, roles);
            	
        assertTrue(existingUser.getRoles().contains(RoleName.ADMIN));
        assertEquals(1, existingUser.getRoles().size());
    }
    
    @Test
    void testUpdateRoleUser_UserExists_PARTENAIRE(){
    	
    	long userId = 1L;
        List<String> roles = List.of("PARTENAIRE");

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setRoles(new HashSet<>());
        
        when(repoUser.findById(userId)).thenReturn(Optional.of(existingUser));
        
        userService.updateRoleUser(userId, roles);
            	
        assertTrue(existingUser.getRoles().contains(RoleName.PARTENAIRE));
        assertEquals(1, existingUser.getRoles().size());
    }
    
    @Test
    void testUpdateRoleUser_UserExists_MEDECIN(){
    	
    	long userId = 1L;
        List<String> roles = List.of("MEDECIN");

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setRoles(new HashSet<>());
        
        when(repoUser.findById(userId)).thenReturn(Optional.of(existingUser));
        
        userService.updateRoleUser(userId, roles);
            	
        assertTrue(existingUser.getRoles().contains(RoleName.MEDECIN));
        assertEquals(1, existingUser.getRoles().size());
    }
    
    @Test
    void testUpdateRoleUser_UserExists_Conseiller(){
    	
    	long userId = 1L;
        List<String> roles = List.of("CONSEILLER");

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setRoles(new HashSet<>());
        
        when(repoUser.findById(userId)).thenReturn(Optional.of(existingUser));
        
        userService.updateRoleUser(userId, roles);
            	
        assertTrue(existingUser.getRoles().contains(RoleName.CONSEILLER));
        assertEquals(1, existingUser.getRoles().size());
    }
    
    
    @Test
    void testUpdateRoleUser_UserNotFound() {
        long userId = 2L;
        List<String> roles = List.of("USER");

        when(repoUser.findById(userId)).thenReturn(Optional.empty());

        userService.updateRoleUser(userId, roles);

        verify(repoUser, times(0)).save(any(User.class)); // Vérifie que save() n'est jamais appelé
    }
    
    @Test
    void testUpdateRoleUser_UserExists_USER(){
    	
    	long userId = 1L;
        List<String> roles = List.of("USER");

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setRoles(new HashSet<>());
        
        when(repoUser.findById(userId)).thenReturn(Optional.of(existingUser));
        
        userService.updateRoleUser(userId, roles);
            	
        assertTrue(existingUser.getRoles().contains(RoleName.USER));
        assertEquals(1, existingUser.getRoles().size());
    }
    
    @Test
    void testDelete() {
    	Long userId = 1L;
        userService.delete(userId);
        verify(repoUser, times(1)).deleteById(userId);
    }
    
    @Test
    void testGetUserByEmail_Found() {
    	String email = "benj@mail.com";
    	when(repoUser.findByMail(email)).thenReturn(Optional.of(user));
    	
    	Optional<UserDto> result = userService.getUserByEmail(email);
    	assertTrue(result.isPresent());
    	assertEquals(userDto.getMail(), result.get().getMail());
    
    	verify(repoUser, times(1)).findByMail(email);	
    }
    
    @Test
    void testGetUserByEmail_NotFound() {
        String email = "unknown@mail.com";
        when(repoUser.findByMail(email)).thenReturn(Optional.empty());

        Optional<UserDto> result = userService.getUserByEmail(email);
        assertFalse(result.isPresent());

        verify(repoUser, times(1)).findByMail(email);
    }

    @Test
    void testChangeMdp_Success() throws ParseException {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("12/08/1990");
        changeMdpRequest request = new changeMdpRequest();
        request.setMail("benj@mail.com");
        request.setDate(date);
        request.setMdp("newpassword");

        when(repoUser.findByMailAndDateNaissance(request.getMail(), request.getDate()))
            .thenReturn(Optional.of(user));
        when(passwordEncoder.encode(request.getMdp())).thenReturn("encodedPassword");

        userService.changeMdp(request);

        verify(repoUser, times(1)).findByMailAndDateNaissance(request.getMail(), request.getDate());
        verify(passwordEncoder, times(1)).encode(request.getMdp());
        verify(repoUser, times(1)).save(user);

        assertEquals("encodedPassword", user.getMdp());
    }
    
    @Test
    void testChangeMdp_UserNotFound() throws ParseException {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("12/08/1990");
        changeMdpRequest request = new changeMdpRequest();
        request.setMail("unknown@mail.com");
        request.setDate(date);
        request.setMdp("newpassword");

        when(repoUser.findByMailAndDateNaissance(request.getMail(), request.getDate()))
            .thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.changeMdp(request);
        });

        assertEquals("Ce compte n'existe pas.", exception.getMessage());

        verify(repoUser, times(1)).findByMailAndDateNaissance(request.getMail(), request.getDate());
        verify(passwordEncoder, times(0)).encode(any());
        verify(repoUser, times(0)).save(any());
    }
    
    @Test
    void testGetUsersByRole_success() {
    	user.addRole(RoleName.USER);
        when(repoUser.findAll()).thenReturn(List.of(user));

        List<UserDto> result = userService.getUsersByRole(RoleName.USER);

        assertEquals(1, result.size());
        assertEquals(userDto.getMail(), result.get(0).getMail());
    }
    
    @Test
    void testGetUsersByRole_failed() {
        when(repoUser.findAll()).thenReturn(List.of(user));

        List<UserDto> result = userService.getUsersByRole(RoleName.USER);

        assertEquals(0, result.size());
    }
    
    @Test
    void testUpdateUser_Success_AllFields() throws ParseException {
        when(repoUser.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date newDateNaissance = dateFormat.parse("15/08/2002");

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setMail("updated@example.com");
        updatedUserDto.setNom("UpdatedName");
        updatedUserDto.setPrenom("UpdatedPrenom");
        updatedUserDto.setDateNaissance(newDateNaissance);
        updatedUserDto.setAdresse("Updated Address");
        updatedUserDto.setTelephone("1234567890");
        updatedUserDto.setSexe("M");
        updatedUserDto.setMdp("newPassword");

        userService.updateUser(1L, updatedUserDto);

        verify(repoUser, times(1)).save(any(User.class));
        
        assertEquals("updated@example.com", user.getMail());
        assertEquals("UpdatedName", user.getNom());
        assertEquals("UpdatedPrenom", user.getPrenom());
        assertEquals("Updated Address", user.getAdresse());
        assertEquals("1234567890", user.getTelephone());
        assertEquals("M", user.getSexe());
        assertEquals(newDateNaissance,user.getDateNaissance());
        assertEquals("encodedNewPassword", user.getMdp());
    }
    
    @Test
    void testUpdateUser_Success_Mdp_null() {
        when(repoUser.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setMail("updated@example.com");
        updatedUserDto.setNom("UpdatedName");
        updatedUserDto.setPrenom("UpdatedPrenom");
        
        updatedUserDto.setAdresse("Updated Address");
        updatedUserDto.setTelephone("1234567890");
        updatedUserDto.setSexe("M");
        updatedUserDto.setMdp(null);

        userService.updateUser(1L, updatedUserDto);

        verify(repoUser, times(1)).save(any(User.class));
        
        assertEquals("updated@example.com", user.getMail());
        assertEquals("UpdatedName", user.getNom());
        assertEquals("UpdatedPrenom", user.getPrenom());
        assertEquals("Updated Address", user.getAdresse());
        assertEquals("1234567890", user.getTelephone());
        assertEquals("M", user.getSexe());
        assertEquals("mdp", user.getMdp());
    }
    
    @Test
    void testUpdateUser_Success_Mdp_empty() {
        when(repoUser.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setMail("updated@example.com");
        updatedUserDto.setNom("UpdatedName");
        updatedUserDto.setPrenom("UpdatedPrenom");
        
        updatedUserDto.setAdresse("Updated Address");
        updatedUserDto.setTelephone("1234567890");
        updatedUserDto.setSexe("M");
        updatedUserDto.setMdp("");

        userService.updateUser(1L, updatedUserDto);

        verify(repoUser, times(1)).save(any(User.class));
        
        assertEquals("updated@example.com", user.getMail());
        assertEquals("UpdatedName", user.getNom());
        assertEquals("UpdatedPrenom", user.getPrenom());
        assertEquals("Updated Address", user.getAdresse());
        assertEquals("1234567890", user.getTelephone());
        assertEquals("M", user.getSexe());
        assertEquals("mdp", user.getMdp());
    }
    
    @Test
    void testUpdateUser_Success_Sexe_null() {
        when(repoUser.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setMail("updated@example.com");
        updatedUserDto.setNom("UpdatedName");
        updatedUserDto.setPrenom("UpdatedPrenom");
        
        updatedUserDto.setAdresse("Updated Address");
        updatedUserDto.setTelephone("1234567890");
        updatedUserDto.setSexe(null);
        updatedUserDto.setMdp("newPassword");

        userService.updateUser(1L, updatedUserDto);

        verify(repoUser, times(1)).save(any(User.class));
        
        assertEquals("updated@example.com", user.getMail());
        assertEquals("UpdatedName", user.getNom());
        assertEquals("UpdatedPrenom", user.getPrenom());
        assertEquals("Updated Address", user.getAdresse());
        assertEquals("1234567890", user.getTelephone());
        assertEquals("Homme", user.getSexe());
        assertEquals("encodedNewPassword", user.getMdp());
    }
    
    @Test
    void testUpdateUser_Success_Sexe_empty() {
        when(repoUser.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setMail("updated@example.com");
        updatedUserDto.setNom("UpdatedName");
        updatedUserDto.setPrenom("UpdatedPrenom");
        
        updatedUserDto.setAdresse("Updated Address");
        updatedUserDto.setTelephone("1234567890");
        updatedUserDto.setSexe("");
        updatedUserDto.setMdp("newPassword");

        userService.updateUser(1L, updatedUserDto);

        verify(repoUser, times(1)).save(any(User.class));
        
        assertEquals("updated@example.com", user.getMail());
        assertEquals("UpdatedName", user.getNom());
        assertEquals("UpdatedPrenom", user.getPrenom());
        assertEquals("Updated Address", user.getAdresse());
        assertEquals("1234567890", user.getTelephone());
        assertEquals("Homme", user.getSexe());
        assertEquals("encodedNewPassword", user.getMdp());
    }
    
    @Test
    void testUpdateUser_Success_Phone_null() {
        when(repoUser.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setMail("updated@example.com");
        updatedUserDto.setNom("UpdatedName");
        updatedUserDto.setPrenom("UpdatedPrenom");
        
        updatedUserDto.setAdresse("Updated Address");
        updatedUserDto.setTelephone(null);
        updatedUserDto.setSexe("M");
        updatedUserDto.setMdp("newPassword");

        userService.updateUser(1L, updatedUserDto);

        verify(repoUser, times(1)).save(any(User.class));
        
        assertEquals("updated@example.com", user.getMail());
        assertEquals("UpdatedName", user.getNom());
        assertEquals("UpdatedPrenom", user.getPrenom());
        assertEquals("Updated Address", user.getAdresse());
        assertEquals("0612234556", user.getTelephone());
        assertEquals("M", user.getSexe());
        assertEquals("encodedNewPassword", user.getMdp());
    }
    
    @Test
    void testUpdateUser_Success_Phone_empty() {
        when(repoUser.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setMail("updated@example.com");
        updatedUserDto.setNom("UpdatedName");
        updatedUserDto.setPrenom("UpdatedPrenom");
        
        updatedUserDto.setAdresse("Updated Address");
        updatedUserDto.setTelephone("");
        updatedUserDto.setSexe("M");
        updatedUserDto.setMdp("newPassword");

        userService.updateUser(1L, updatedUserDto);

        verify(repoUser, times(1)).save(any(User.class));
        
        assertEquals("updated@example.com", user.getMail());
        assertEquals("UpdatedName", user.getNom());
        assertEquals("UpdatedPrenom", user.getPrenom());
        assertEquals("Updated Address", user.getAdresse());
        assertEquals("0612234556", user.getTelephone());
        assertEquals("M", user.getSexe());
        assertEquals("encodedNewPassword", user.getMdp());
    }
    
    @Test
    void testUpdateUser_Success_Adress_null() {
        when(repoUser.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setMail("updated@example.com");
        updatedUserDto.setNom("UpdatedName");
        updatedUserDto.setPrenom("UpdatedPrenom");
        
        updatedUserDto.setAdresse(null);
        updatedUserDto.setTelephone("1234567890");
        updatedUserDto.setSexe("M");
        updatedUserDto.setMdp("newPassword");

        userService.updateUser(1L, updatedUserDto);

        verify(repoUser, times(1)).save(any(User.class));
        
        assertEquals("updated@example.com", user.getMail());
        assertEquals("UpdatedName", user.getNom());
        assertEquals("UpdatedPrenom", user.getPrenom());
        assertEquals("39 rue de Lille", user.getAdresse());
        assertEquals("1234567890", user.getTelephone());
        assertEquals("M", user.getSexe());
        assertEquals("encodedNewPassword", user.getMdp());
    }
    
    @Test
    void testUpdateUser_Success_Adress_empty() {
        when(repoUser.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setMail("updated@example.com");
        updatedUserDto.setNom("UpdatedName");
        updatedUserDto.setPrenom("UpdatedPrenom");
        
        updatedUserDto.setAdresse("");
        updatedUserDto.setTelephone("1234567890");
        updatedUserDto.setSexe("M");
        updatedUserDto.setMdp("newPassword");

        userService.updateUser(1L, updatedUserDto);

        verify(repoUser, times(1)).save(any(User.class));
        
        assertEquals("updated@example.com", user.getMail());
        assertEquals("UpdatedName", user.getNom());
        assertEquals("UpdatedPrenom", user.getPrenom());
        assertEquals("39 rue de Lille", user.getAdresse());
        assertEquals("1234567890", user.getTelephone());
        assertEquals("M", user.getSexe());
        assertEquals("encodedNewPassword", user.getMdp());
    }
    
    @Test
    void testUpdateUser_Success_Mail_Null() {
    	when(repoUser.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setMail(null);
        updatedUserDto.setNom("UpdatedName");
        updatedUserDto.setPrenom("UpdatedPrenom");
        
        updatedUserDto.setAdresse("Updated Address");
        updatedUserDto.setTelephone("1234567890");
        updatedUserDto.setSexe("M");
        updatedUserDto.setMdp("newPassword");

        userService.updateUser(1L, updatedUserDto);

        verify(repoUser, times(1)).save(any(User.class));
        
        assertEquals("benj@gmail.com", user.getMail());
        assertEquals("UpdatedName", user.getNom());
        assertEquals("UpdatedPrenom", user.getPrenom());
        assertEquals("Updated Address", user.getAdresse());
        assertEquals("1234567890", user.getTelephone());
        assertEquals("M", user.getSexe());
        assertEquals("encodedNewPassword", user.getMdp());
    }
    
    @Test
    void testUpdateUser_Success_Mail_empty() {
    	when(repoUser.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setMail("");
        updatedUserDto.setNom("UpdatedName");
        updatedUserDto.setPrenom("UpdatedPrenom");
        
        updatedUserDto.setAdresse("Updated Address");
        updatedUserDto.setTelephone("1234567890");
        updatedUserDto.setSexe("M");
        updatedUserDto.setMdp("newPassword");

        userService.updateUser(1L, updatedUserDto);

        verify(repoUser, times(1)).save(any(User.class));
        
        assertEquals("benj@gmail.com", user.getMail());
        assertEquals("UpdatedName", user.getNom());
        assertEquals("UpdatedPrenom", user.getPrenom());
        assertEquals("Updated Address", user.getAdresse());
        assertEquals("1234567890", user.getTelephone());
        assertEquals("M", user.getSexe());
        assertEquals("encodedNewPassword", user.getMdp());
    }

    @Test
    void testUpdateUser_Success_Nom_Null() {
        when(repoUser.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setMail("updated@example.com");
        updatedUserDto.setNom(null);
        updatedUserDto.setPrenom("UpdatedPrenom");
        
        updatedUserDto.setAdresse("Updated Address");
        updatedUserDto.setTelephone("1234567890");
        updatedUserDto.setSexe("M");
        updatedUserDto.setMdp("newPassword");

        userService.updateUser(1L, updatedUserDto);

        verify(repoUser, times(1)).save(any(User.class));
        
        assertEquals("updated@example.com", user.getMail());
        assertEquals("ALEXANDRE", user.getNom());
        assertEquals("UpdatedPrenom", user.getPrenom());
        assertEquals("Updated Address", user.getAdresse());
        assertEquals("1234567890", user.getTelephone());
        assertEquals("M", user.getSexe());
        assertEquals("encodedNewPassword", user.getMdp());
    }
    
    @Test
    void testUpdateUser_Success_Nom_Empty() {
        when(repoUser.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setMail("updated@example.com");
        updatedUserDto.setNom("");
        updatedUserDto.setPrenom("UpdatedPrenom");
        
        updatedUserDto.setAdresse("Updated Address");
        updatedUserDto.setTelephone("1234567890");
        updatedUserDto.setSexe("M");
        updatedUserDto.setMdp("newPassword");

        userService.updateUser(1L, updatedUserDto);

        verify(repoUser, times(1)).save(any(User.class));
        
        assertEquals("updated@example.com", user.getMail());
        assertEquals("ALEXANDRE", user.getNom());
        assertEquals("UpdatedPrenom", user.getPrenom());
        assertEquals("Updated Address", user.getAdresse());
        assertEquals("1234567890", user.getTelephone());
        assertEquals("M", user.getSexe());
        assertEquals("encodedNewPassword", user.getMdp());
    }
    
    @Test
    void testUpdateUser_Success_Prenom_null() {
        when(repoUser.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setMail("updated@example.com");
        updatedUserDto.setNom("UpdatedName");
        updatedUserDto.setPrenom(null);
        
        updatedUserDto.setAdresse("Updated Address");
        updatedUserDto.setTelephone("1234567890");
        updatedUserDto.setSexe("M");
        updatedUserDto.setMdp("newPassword");

        userService.updateUser(1L, updatedUserDto);

        verify(repoUser, times(1)).save(any(User.class));
        
        assertEquals("updated@example.com", user.getMail());
        assertEquals("UpdatedName", user.getNom());
        assertEquals("Benjamin", user.getPrenom());
        assertEquals("Updated Address", user.getAdresse());
        assertEquals("1234567890", user.getTelephone());
        assertEquals("M", user.getSexe());
        assertEquals("encodedNewPassword", user.getMdp());
    }
    
    @Test
    void testUpdateUser_Success_Prenom_empty() {
        when(repoUser.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setMail("updated@example.com");
        updatedUserDto.setNom("UpdatedName");
        updatedUserDto.setPrenom("");
        
        updatedUserDto.setAdresse("Updated Address");
        updatedUserDto.setTelephone("1234567890");
        updatedUserDto.setSexe("M");
        updatedUserDto.setMdp("newPassword");

        userService.updateUser(1L, updatedUserDto);

        verify(repoUser, times(1)).save(any(User.class));
        
        assertEquals("updated@example.com", user.getMail());
        assertEquals("UpdatedName", user.getNom());
        assertEquals("Benjamin", user.getPrenom());
        assertEquals("Updated Address", user.getAdresse());
        assertEquals("1234567890", user.getTelephone());
        assertEquals("M", user.getSexe());
        assertEquals("encodedNewPassword", user.getMdp());
    }
    
    @Test
    void testUpdateUser_Success_DateNaissance_null() {
        when(repoUser.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setMail("updated@example.com");
        updatedUserDto.setNom("UpdatedName");
        updatedUserDto.setPrenom("UpdatedPrenom");
        updatedUserDto.setDateNaissance(null);
        updatedUserDto.setAdresse("Updated Address");
        updatedUserDto.setTelephone("1234567890");
        updatedUserDto.setSexe("M");
        updatedUserDto.setMdp("newPassword");

        userService.updateUser(1L, updatedUserDto);

        verify(repoUser, times(1)).save(any(User.class));
        
        assertEquals("updated@example.com", user.getMail());
        assertEquals("UpdatedName", user.getNom());
        assertEquals("UpdatedPrenom", user.getPrenom());
        assertEquals("Updated Address", user.getAdresse());
        assertEquals("1234567890", user.getTelephone());
        assertEquals("M", user.getSexe());
        assertEquals("encodedNewPassword", user.getMdp());
        assertEquals(userDto.getDateNaissance(),user.getDateNaissance());
    }
    
	
    
    @Test
    void testUpdateUser_UserNotFound() {
        when(repoUser.findById(2L)).thenReturn(Optional.empty());

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setMail("new@example.com");

        Exception exception = assertThrows(RuntimeException.class, () -> userService.updateUser(2L, updatedUserDto));

        assertEquals("Utilisateur non trouvé", exception.getMessage());
    }


}
