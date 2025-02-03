package mobisure.project.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
import mobisure.project.request.UserRoleUpdateRequest;
import mobisure.project.request.changeMdpRequest;
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
	        UserDto user1 = new UserDto("John", "Doe", "johndoe@example.com","mdp", null, null, null, null, null);
	        UserDto user2 = new UserDto("Jane", "Doe", "janedoe@example.com","mdp2", null, null, null, null, null);
	        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

	      
	        mockMvc.perform(get("/users"))
	                .andExpect(status().isOk())
			        .andExpect(jsonPath("$[0].nom").value("John"))
		            .andExpect(jsonPath("$[1].nom").value("Jane"));
	                
	    }
	    
	    @Test
	    void testGetUserById_Found() throws Exception {
	        // Arrange
	        UserDto user = new UserDto("John", "Doe", "johndoe@example.com","mdp", null, null, null, null, null);
	        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

	        // Act & Assert
	        mockMvc.perform(get("/users/1"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.nom").value("John"));
	    }
	    
	    @Test
	    void testRegisterUser_Success() throws Exception {
	        // Arrange
	        UserDto user = new UserDto(null, "John", "Doe", "johndoe@example.com", null, null, null, null, null);
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
	        UserDto user = new UserDto(null, "John", "Doe", "johndoe@example.com", null, null, null, null, null);
	        doThrow(new RuntimeException("Email already in use")).when(userService).registerUser(any(UserDto.class));

	        // Act & Assert
	        mockMvc.perform(post("/users/register")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(user)))
	                .andExpect(status().isBadRequest())
	                .andExpect(content().string("Email already in use"));
	    }


	    @Test
	    void testUpdateRole_Success() throws Exception {
	    	ArrayList<String> roles = new ArrayList<>();
	    	roles.add("ADMIN");
	    	roles.add("USER");
	    	UserRoleUpdateRequest request = new UserRoleUpdateRequest();
	    	request.setRoles(roles);
	    	request.setUserId(1l);
	    	
	    	doNothing().when(userService).updateRoleUser(anyLong(), anyList());
	    	
	    	mockMvc.perform(post("/users/updateRole")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(request)))
	                .andExpect(status().isOk());
	    	
	    	verify(userService, times(1)).updateRoleUser(1L, Arrays.asList("ADMIN", "USER"));
	    	
	    }
	    
	    @Test
	    void testDeleteUser_Success() throws Exception {
	        Long userId = 1L;
	        doNothing().when(userService).delete(userId);

	        mockMvc.perform(delete("/users/delete/{id}", userId))
	                .andExpect(status().isOk());

	        verify(userService, times(1)).delete(userId);
	    }
	    
	    @Test
	    void testGetUserByEmail_Success() throws Exception {
	        String email = "ben@gmail.com";
	        UserDto userDto = new UserDto("Ben", "ALEXANDRE", email, "mdp", null, null, null, null, null);
	        
	        when(userService.getUserByEmail(email)).thenReturn(Optional.of(userDto));

	        Map<String, String> requestBody = new HashMap<>();
	        requestBody.put("email", email);

	        mockMvc.perform(post("/getUserEmail")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(requestBody)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.nom").value("Ben"))
	                .andExpect(jsonPath("$.mail").value(email));

	        verify(userService, times(2)).getUserByEmail(email);
	    }
	    
	    @Test
	    void testChangeMdp_Success() throws Exception {
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = dateFormat.parse("12/08/1990");
	    	
	        changeMdpRequest request = new changeMdpRequest();
	        request.setMail("ben@gmail.com");
	        request.setMdp("mdp");
	        request.setDate(date);
	        
	        doNothing().when(userService).changeMdp(any(changeMdpRequest.class));

	        // Act & Assert
	        mockMvc.perform(post("/users/changeMdp")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(request)))
	                .andExpect(status().isCreated())
	                .andExpect(content().string("changement du mot de passe réussie"));

	        verify(userService, times(1)).changeMdp(any(changeMdpRequest.class));
	    }
	    
	    @Test
	    void testChangeMdp_Failure() throws Exception {
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = dateFormat.parse("12/08/1990");
	    	
	        changeMdpRequest request = new changeMdpRequest();
	        request.setMail("unknown@example.com");
	        request.setMdp("mdp");
	        request.setDate(date);

	        doThrow(new RuntimeException("Ce compte n'existe pas.")).when(userService).changeMdp(any(changeMdpRequest.class));

	        // Act & Assert
	        mockMvc.perform(post("/users/changeMdp")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(request)))
	                .andExpect(status().isBadRequest())
	                .andExpect(content().string("Ce compte n'existe pas."));

	        verify(userService, times(1)).changeMdp(any(changeMdpRequest.class));
	    }
}

