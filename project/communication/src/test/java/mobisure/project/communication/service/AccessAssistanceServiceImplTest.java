package mobisure.project.communication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mobisure.project.communication.entity.AccessAssistance;
import mobisure.project.communication.entity.Assistance;
import mobisure.project.communication.entity.Status;
import mobisure.project.communication.entity.TypeAssistance;
import mobisure.project.communication.repository.AccessAssistanceRepository;
import mobisure.project.communication.repository.AssistanceRepository;

@ExtendWith(MockitoExtension.class)
public class AccessAssistanceServiceImplTest {

	@Mock
    private AccessAssistanceRepository accessAssistanceRepository;

    @Mock
    private AssistanceRepository assistanceRepository;
    
    @Mock
    private AccessAssistance accessAssistance;

    @Mock
    private Assistance assistance;

    @InjectMocks
    private AccessAssistanceServiceImpl accessAssistanceService;
	
    
    @BeforeEach
    public void setUp() {
        accessAssistance = new AccessAssistance("123", 1L);
    }
    
    @Test
    public void testGetPermissionOfUser_WithPermission() {
        // Simuler le comportement du repository pour trouver l'accès
        when(accessAssistanceRepository.findByIdAssistanceAndIdUser(anyString(), anyLong()))
                .thenReturn(accessAssistance);

        // Appeler la méthode à tester
        boolean permission = accessAssistanceService.getPermissionOfUser("123", 1L);

        // Vérifier le résultat
        assertFalse(permission);
    }
    
    @Test
    public void testGetPermissionOfUser_WithoutPermission() {
    	
        // Simuler le comportement du repository pour ne pas trouver l'accès
        when(accessAssistanceRepository.findByIdAssistanceAndIdUser(anyString(), anyLong()))
                .thenReturn(null);

        // Appeler la méthode à tester
        boolean permission = accessAssistanceService.getPermissionOfUser("123", 1L);

        // Vérifier le résultat
        assertTrue(permission);
    }
    
    @Test
    public void testAddAccess() {
        // Simuler le comportement du repository
        when(assistanceRepository.findByNumDossier(anyString())).thenReturn(assistance);
        when(accessAssistanceRepository.save(any(AccessAssistance.class))).thenReturn(accessAssistance);
        when(assistanceRepository.save(any(Assistance.class))).thenReturn(assistance);

        // Appeler la méthode à tester
        accessAssistanceService.addAccess("123", 1L);

        // Vérifier que le repository a bien enregistré l'accès et mis à jour l'assistance
        verify(assistanceRepository).save(any(Assistance.class));
        verify(accessAssistanceRepository).save(any(AccessAssistance.class));
    }
    
    @Test
    public void testGetAll() {
        // Simuler le comportement du repository
        when(accessAssistanceRepository.findAll()).thenReturn(List.of(accessAssistance));

        // Appeler la méthode à tester
        List<AccessAssistance> result = accessAssistanceService.getAll();

        // Vérifier que la méthode retourne la liste correcte
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(accessAssistance, result.get(0));
    }
    
    @Test
    public void testGetMyFolder() {
        // Simuler le comportement du repository
        when(accessAssistanceRepository.findByIdUser(anyLong())).thenReturn(List.of(accessAssistance));
        when(assistanceRepository.findByNumDossier(anyString())).thenReturn(assistance);

        // Appeler la méthode à tester
        List<Assistance> result = accessAssistanceService.getMyFolder(1L);

        // Vérifier que la méthode retourne la liste correcte
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(assistance, result.get(0));
    }
    
    @Test
    public void testRemoveAccess_DELETE_whenGererIsTrue() {
        // Simuler le comportement du repository
        when(accessAssistanceRepository.findByIdAssistanceAndIdUser(anyString(), anyLong()))
                .thenReturn(accessAssistance);
        when(assistanceRepository.findByNumDossier(anyString())).thenReturn(assistance);
        
        // Simuler le comportement de la méthode isGerer
        when(assistance.isGerer()).thenReturn(true);

        // Appeler la méthode à tester
        accessAssistanceService.removeAccess("123", 1L);

        // Vérifier que la méthode delete a été appelée
        verify(accessAssistanceRepository).delete(any(AccessAssistance.class));

        // Vérifier que l'assistance a été mise à jour avec setGerer(false)
        verify(assistanceRepository).save(assistance);
    }
    
    @Test
    public void testRemoveAccess_DELETE_whenGererIsFalse() {
        // Simuler le comportement du repository
        when(accessAssistanceRepository.findByIdAssistanceAndIdUser(anyString(), anyLong()))
                .thenReturn(accessAssistance);
        when(assistanceRepository.findByNumDossier(anyString())).thenReturn(assistance);
        
        // Simuler le comportement de la méthode isGerer pour retourner false
        when(assistance.isGerer()).thenReturn(false);

        // Appeler la méthode à tester
        accessAssistanceService.removeAccess("123", 1L);

        // Vérifier que l'assistance n'a pas été modifiée
        verify(assistanceRepository, never()).save(assistance);
    }

}

