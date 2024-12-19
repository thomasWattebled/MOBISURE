package mobisure.project.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import mobisure.project.dto.UserDto;
import mobisure.project.entity.User;
import mobisure.project.repository.UserRepository;

public class UserServiceImplTest {
	
	@Mock
    private UserRepository repoUser;
	
	@Mock
    private PasswordEncoder passwordEncoder;
	
	@InjectMocks
    private UserServiceImpl userService;
	
	private UserDto userDto;
    private User user;
    
    @BeforeEach
    public void setUp() {
    	
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
    public void testRegisterUser_MailExisting() {
    	
    	when(repoUser.findByMail(user.getMail())).thenReturn(Optional.of(user));
    	
    	Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(userDto);
        });
    	
    	assertEquals("L'utilisateur avec cet email existe déjà.", exception.getMessage());
    	
    }
	
    @Test
    public void testRegisterUser_Success() {
        
        when(repoUser.findByMail(user.getMail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(userDto.getMdp())).thenReturn("encodedPassword");
        
        userService.registerUser(userDto);
        
        verify(repoUser, times(1)).save(any(User.class)); 
    }

    @Test
    public void testGetUserById_Found() {
    	
    	when(repoUser.findById(user.getId())).thenReturn(Optional.of(user));
    	
    	Optional<UserDto> result = userService.getUserById(user.getId());
    	
    	assertTrue(result.isPresent());
    	assertEquals(userDto.getId(),result.get().getId());
    }
    
    @Test
    public void testGetUserById_NotFound() {
    	
    	when(repoUser.findById(user.getId())).thenReturn(Optional.empty());
    	
    	Optional<UserDto> result = userService.getUserById(user.getId());
    	
    	assertFalse(result.isPresent());
    }
    
    @Test
    public void testGetAllUser() {
    	
    	when(repoUser.findAll()).thenReturn(List.of(user));
    	
    	userService.registerUser(userDto);
    	
    	List<UserDto> users = userService.getAllUsers();
    	
    	
    	assertFalse(users.isEmpty());
    	assertEquals(1,users.size());
    	assertTrue(users.contains(userDto));
    }
    
    @Test
    public void testconvertToDto() {
    	
    	UserDto dto = userService.convertToDto(user);
    	
    	assertEquals(userDto,dto);
    	
    }
    
    @Test
    public void convertToEntity() {
    	
    	
    	User entity = userService.convertToEntity(userDto);
    	
    	assertEquals(entity,user);
    }
    
}
