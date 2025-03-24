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
    void setUp() {
    	
    	MockitoAnnotations.openMocks(this);
    	
    	userDto = new UserDto();
        userDto.setId(1L);
        userDto.setMail("benj@mail.com");
        userDto.setMdp("mdp");
        userDto.setNom("ALEXANDRE");
        userDto.setPrenom("Benjamin");
        user = new User();
        user.setId(1L);
        user.setMail("benj@mail.com");
        user.setMdp("mdp");
        user.setNom("ALEXANDRE");
        user.setPrenom("Benjamin");
    	
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
    void testUpdateRoleUser_UserExists(){
    	
    	long userId = 1L;
        List<String> roles = List.of("USER", "ADMIN");

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setRoles(new HashSet<>());
        
        when(repoUser.findById(userId)).thenReturn(Optional.of(existingUser));
        
        userService.updateRoleUser(userId, roles);
            	
        assertTrue(existingUser.getRoles().contains(RoleName.USER));
        assertTrue(existingUser.getRoles().contains(RoleName.ADMIN));
        assertEquals(2, existingUser.getRoles().size());
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

}
