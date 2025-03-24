package mobisure.project.communication.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.text.SimpleDateFormat;
import java.util.*;

import mobisure.project.communication.entity.Assistance;
import mobisure.project.communication.entity.Status;
import mobisure.project.communication.entity.TypeAssistance;
import mobisure.project.communication.request.AssistanceRequest;
import mobisure.project.communication.service.AssistanceService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AssistanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AssistanceService assistanceService;

    @InjectMocks
    private AssistanceController assistanceController;

    private Assistance assistance;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(assistanceController).build();
        assistance = new Assistance(1L, Status.ATTENTE, new Date(), "Besoin d'aide", TypeAssistance.ACCIDENT, "Doe", "John", "john.doe@example.com", "password", "0123456789");
        assistance.setId("123");
    }

    @Test
    void testGetAllAssistance() throws Exception {
        List<Assistance> assistances = Arrays.asList(assistance);
        when(assistanceService.getAllAssistance()).thenReturn(assistances);

        mockMvc.perform(get("/assistance/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value("123"));
    }

    @Test
    void testGetAssistanceDisponible() throws Exception {
        List<Assistance> assistances = Arrays.asList(assistance);
        when(assistanceService.getAssistanceDisponnible()).thenReturn(assistances);

        mockMvc.perform(get("/assistance/disponnible"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value("123"));
    }

    @Test
    void testUpdateStatus() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("status", "ATTENTE");

        mockMvc.perform(put("/assistance/updateStatus/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andExpect(status().isOk());

        verify(assistanceService, times(1)).updateAssistance("123", Status.ATTENTE);
    }

    @Test
    void testGetMyAssistance() throws Exception {
        List<Assistance> assistances = Arrays.asList(assistance);
        when(assistanceService.getMyAssistance(1L)).thenReturn(assistances);

        mockMvc.perform(get("/assistance/getMyAssistance/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value("123"));
    }

    @Test
    void testGetAssistanceByNumDossier() throws Exception {
        when(assistanceService.getByNumDossier("D-123")).thenReturn(assistance);

        mockMvc.perform(get("/assistance/getByNumDossier/D-123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"));
    }

    @Test
    void testAddAssistanceAuto() throws Exception {
        AssistanceRequest request = new AssistanceRequest();
        request.setId_client(1L);
        request.setStatus("TRAITEMENT");
        request.setType("AUTO");
        request.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        request.setMessage("Message");
        request.setNom("ALEXANDRE");
        request.setPrenom("Ben");
        request.setMail("ben@example.com");
        request.setMdp("password");
        request.setTelephone("0123456789");
        request.setVille("Paris");
        request.setRue("Rue de la Paix");

        mockMvc.perform(post("/assistance/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
    
    @Test
    void testAddAssistanceAccident() throws Exception {
        AssistanceRequest request = new AssistanceRequest();
        request.setId_client(1L);
        request.setStatus("TRAITEMENT");
        request.setType("ACCIDENT");
        request.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        request.setMessage("Message");
        request.setNom("ALEXANDRE");
        request.setPrenom("Ben");
        request.setMail("ben@example.com");
        request.setMdp("password");
        request.setTelephone("0123456789");
        request.setVille("Paris");
        request.setRue("Rue de la Paix");

        mockMvc.perform(post("/assistance/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
    
    @Test
    void testAddAssistanceRemboursemment() throws Exception {
        AssistanceRequest request = new AssistanceRequest();
        request.setId_client(1L);
        request.setStatus("TRAITEMENT");
        request.setType("REMBOURSEMENT");
        request.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        request.setMessage("Message");
        request.setNom("ALEXANDRE");
        request.setPrenom("Ben");
        request.setMail("ben@example.com");
        request.setMdp("password");
        request.setTelephone("0123456789");
        request.setVille("Paris");
        request.setRue("Rue de la Paix");

        mockMvc.perform(post("/assistance/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
    
    @Test
    void testAddAssistanceMedical() throws Exception {
        AssistanceRequest request = new AssistanceRequest();
        request.setId_client(1L);
        request.setStatus("TRAITEMENT");
        request.setType("MEDICAL");
        request.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        request.setMessage("Message");
        request.setNom("ALEXANDRE");
        request.setPrenom("Ben");
        request.setMail("ben@example.com");
        request.setMdp("password");
        request.setTelephone("0123456789");
        request.setVille("Paris");
        request.setRue("Rue de la Paix");

        mockMvc.perform(post("/assistance/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
    
    @Test
    void testAddAssistanceAutres() throws Exception {
        AssistanceRequest request = new AssistanceRequest();
        request.setId_client(1L);
        request.setStatus("TRAITEMENT");
        request.setType("AUTRE");
        request.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        request.setMessage("Message");
        request.setNom("ALEXANDRE");
        request.setPrenom("Ben");
        request.setMail("ben@example.com");
        request.setMdp("password");
        request.setTelephone("0123456789");
        request.setVille("Paris");
        request.setRue("Rue de la Paix");

        mockMvc.perform(post("/assistance/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
    
    @Test
    void testAddAssistanceServeurError() throws Exception {
        AssistanceRequest request = new AssistanceRequest();

        mockMvc.perform(post("/assistance/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Erreur lors de l'ajout de la demande d'assistance"));
    }
    
    @Test
    void testAddAssistanceBadRequest() throws Exception {
    	AssistanceRequest request = new AssistanceRequest();
        request.setId_client(1L);
        request.setStatus("TRAITEMENT");
        request.setType("INVALIDE");
        request.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        request.setMessage("Message");
        request.setNom("ALEXANDRE");
        request.setPrenom("Ben");
        request.setMail("ben@example.com");
        request.setMdp("password");
        request.setTelephone("0123456789");
        request.setVille("Paris");
        request.setRue("Rue de la Paix");

        mockMvc.perform(post("/assistance/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}