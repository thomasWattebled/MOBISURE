package mobisure.project.communication.controller;

import mobisure.project.communication.entity.AccessAssistance;
import mobisure.project.communication.entity.Assistance;
import mobisure.project.communication.entity.Status;
import mobisure.project.communication.entity.TypeAssistance;
import mobisure.project.communication.service.AccessAssistanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AccessAssistanceControllerTest {

	private MockMvc mockMvc;

    @Mock
    private AccessAssistanceService service;

    @InjectMocks
    private AccessAssistanceController controller;
    
    private Assistance assistance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        assistance = new Assistance(1L, Status.ATTENTE, new Date(), "Besoin d'aide", TypeAssistance.ACCIDENT, "Doe", "John", "john.doe@example.com", "password", "0123456789");

    }

    @Test
    void testMyFolder() throws Exception {
        List<Assistance> assistances = Arrays.asList(assistance);
        when(service.getMyFolder(1L)).thenReturn(assistances);

        mockMvc.perform(get("/access/myFolder")
                        .param("idUser", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(service, times(1)).getMyFolder(1L);
    }
    
    @Test
    void testAddAccess() throws Exception {
    	
        doNothing().when(service).addAccess("D-123", 1L);

        mockMvc.perform(post("/access/addAccess")
                        .param("idAssistance", "D-123")
                        .param("idUser", "1"))
                .andExpect(status().isOk());

        verify(service, times(1)).addAccess("D-123", 1L);
    }
    
    @Test
    void testRemoveAccess() throws Exception {
        doNothing().when(service).removeAccess("D-123", 1L);

        mockMvc.perform(post("/access/removeAccess")
                        .param("idAssistance", "D-123")
                        .param("idUser", "1"))
                .andExpect(status().isOk());

        verify(service, times(1)).removeAccess("D-123", 1L);
    }
 
    @Test
    void testGetAll() throws Exception {
        List<AccessAssistance> accessAssistances = Arrays.asList(new AccessAssistance("D-123",1L));
        when(service.getAll()).thenReturn(accessAssistances);

        mockMvc.perform(get("/access/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(service, times(1)).getAll();
    }
    
}
