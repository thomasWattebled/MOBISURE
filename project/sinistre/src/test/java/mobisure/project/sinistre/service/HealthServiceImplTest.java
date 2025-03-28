package mobisure.project.sinistre.service;

import mobisure.project.sinistre.document.HealthDocument;
import mobisure.project.sinistre.repository.HealthRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HealthServiceImplTest {

    @Mock
    private HealthRepository healthRepo;

    @InjectMocks
    private HealthServiceImpl healthService;

    @Test
    void testSaveHealth() {
        Long sinistreId = 123L;
        Long contractId = 456L;
        String category = "Accident";
        Date date = new Date();
        String nature = "Fracture";
        String description = "Fracture du bras";
        String hospital = "Hôpital Général";

        HealthDocument healthDocument = new HealthDocument(sinistreId, contractId, category, date, nature, description, hospital);
        
        when(healthRepo.save(healthDocument)).thenReturn(healthDocument);

        HealthDocument savedHealth = healthService.saveHealth(healthDocument);
        assertNotNull(savedHealth);
        assertEquals(sinistreId, savedHealth.getSinistreId());
        assertEquals(contractId, savedHealth.getContractId());
        assertEquals(category, savedHealth.getCategory());
        assertEquals(nature, savedHealth.getNature());
        assertEquals(description, savedHealth.getDescription());
        assertEquals(hospital, savedHealth.getHospital());
    }

    @Test
    void testGetBySinistreId() {
        long sinistreId = 123L;
        HealthDocument health1 = new HealthDocument(sinistreId, 456L, "Accident", new Date(), "Fracture", "Fracture du bras", "Hôpital Général");
        HealthDocument health2 = new HealthDocument(sinistreId, 789L, "Accident", new Date(), "Entorse", "Entorse du genou", "Clinique Saint-Luc");

        List<HealthDocument> healthList = Arrays.asList(health1, health2);

        when(healthRepo.findBySinistreId(sinistreId)).thenReturn(healthList);

        List<HealthDocument> result = healthService.getBySinistreId(sinistreId);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Accident", result.get(0).getCategory());
        assertEquals("Fracture du bras", result.get(0).getDescription());
    }
}
