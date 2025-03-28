package mobisure.project.calculemission.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
class CalculEmissionControllerTest {

    @Autowired
    private CalculEmissionController calculEmissionController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(calculEmissionController).build();
    }

    @Test
    void testGetEmission() throws Exception {
        String gpsStart = "2.3522,48.8566"; // Paris
        String gpsEnd = "3.0586,50.6292"; // Lille
        int transport = 1;  // Example transport value

        // Mock d'un retour de l'API
        mockMvc.perform(MockMvcRequestBuilders.get("/calculemission/getemission")
                .param("gpsStart", gpsStart)
                .param("gpsEnd", gpsEnd)
                .param("transport", String.valueOf(transport)))
                .andExpect(MockMvcResultMatchers.status().isOk()); // Vérifie le code 200 OK
    }
    
    @Test
    void testGetDistance() throws Exception {
        String gpsStart = "2.3522,48.8566"; // Paris
        String gpsEnd = "3.0586,50.6292"; // Lille
        int transport = 1;  // Exemple de transport (1 pour la distance à vol d'oiseau)

        // Mock d'un retour de l'API ou du calcul
        mockMvc.perform(MockMvcRequestBuilders.get("/calculemission/getdistance")
                .param("gpsStart", gpsStart)
                .param("gpsEnd", gpsEnd)
                .param("transport", String.valueOf(transport)))
                .andExpect(MockMvcResultMatchers.status().isOk()); // Vérifie le code 200 OK
    }
}
