package mobisure.project.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import mobisure.project.dto.UserDto;
import mobisure.project.service.UserService;

public class UserControllerTest {
	
	 private MockMvc mockMvc;

	    @InjectMocks
	    private UserController userController;

	    @Mock
	    private UserService userService;

	    private ObjectMapper objectMapper = new ObjectMapper();

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	    }

	    @Test
	    void testGetAllUser_Success() throws Exception {
	        // Arrange
	        UserDto user1 = new UserDto("John", "Doe", "johndoe@example.com","mdp");
	        UserDto user2 = new UserDto("Jane", "Doe", "janedoe@example.com","mdp2");
	        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

	      
	        mockMvc.perform(get("/users"))
	                .andExpect(status().isOk())
			        .andExpect(jsonPath("$[0].nom").value("John"))
		            .andExpect(jsonPath("$[1].nom").value("Jane"));
	                
	    }
	    
	    @Test
	    void testGetUserById_Found() throws Exception {
	        // Arrange
	        UserDto user = new UserDto("John", "Doe", "johndoe@example.com","mdp");
	        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

	        // Act & Assert
	        mockMvc.perform(get("/users/1"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.nom").value("John"));
	    }
	    
	    @Test
	    void testRegisterUser_Success() throws Exception {
	        // Arrange
	        UserDto user = new UserDto(null, "John", "Doe", "johndoe@example.com");
	        doNothing().when(userService).registerUser(any(UserDto.class));

	        // Act & Assert
	        mockMvc.perform(post("/users/register")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(user)))
	                .andExpect(status().isCreated())
	                .andExpect(content().string("Utilisateur créé avec succès."));
	    }
	    
	    @Test
	    void testRegisterUser_Failure() throws Exception {
	        // Arrange
	        UserDto user = new UserDto(null, "John", "Doe", "johndoe@example.com");
	        doThrow(new RuntimeException("Email already in use")).when(userService).registerUser(any(UserDto.class));

	        // Act & Assert
	        mockMvc.perform(post("/users/register")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(user)))
	                .andExpect(status().isBadRequest())
	                .andExpect(content().string("Email already in use"));
	    }


}
