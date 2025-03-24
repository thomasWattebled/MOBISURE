	package mobisure.project.communication.service;
	
	import static org.junit.jupiter.api.Assertions.assertEquals;
	import static org.mockito.ArgumentMatchers.any;
	import static org.mockito.Mockito.times;
	import static org.mockito.Mockito.verify;
	import static org.mockito.Mockito.when;
	
	import java.util.Date;
import java.util.List;
import java.util.Optional;
	
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.mockito.InjectMocks;
	import org.mockito.Mock;
	import org.mockito.MockitoAnnotations;
	
	import mobisure.project.communication.entity.Assistance;
	import mobisure.project.communication.entity.Status;
	import mobisure.project.communication.entity.TypeAssistance;
	import mobisure.project.communication.entity.type.Accident;
	import mobisure.project.communication.entity.type.Depannage;
	import mobisure.project.communication.entity.type.Medical;
	import mobisure.project.communication.entity.type.Remboursement;
	import mobisure.project.communication.repository.AssistanceRepository;
	
	public class AssistanceServiceImplTest {
	
		@Mock
	    private AssistanceRepository repo;
	
	    @InjectMocks
	    private AssistanceServiceImpl assistanceService;
	
	    private Assistance assistance;
	    
	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	        assistance = new Assistance(1L, Status.ATTENTE, new Date(), "Besoin d'aide", TypeAssistance.ACCIDENT, "Doe", "John", "john.doe@example.com", "password", "0123456789");
	        assistance.setNumDossier("123");
	    }
		
	    @Test
	    public void testUpdateAssistance() {
	        Status newStatus = Status.CLOTURER;
	        
	        when(repo.findById("123")).thenReturn(Optional.of(assistance));
	
	        assistanceService.updateAssistance("123", newStatus);
	
	        assertEquals(newStatus, assistance.getStatus());
	        verify(repo, times(1)).save(assistance);
	    }
	    
	    @Test
	    public void testUpdateAssistance_AssistanceNotFound() {
	
	    	when(repo.findById("999")).thenReturn(Optional.empty());
	
	        assistanceService.updateAssistance("999", Status.ATTENTE);
	
	        verify(repo, times(0)).save(any(Assistance.class));
	    }
	    
	    @Test
	    public void testCreateAssistance() {
	        when(repo.save(any(Assistance.class))).thenReturn(assistance);
	        
	        assistanceService.createAssistance(1L, Status.ATTENTE, new Date(), "Test", TypeAssistance.AUTO, "John", "Doe", "john.doe@example.com", "password", "1234567890");
	
	        verify(repo, times(1)).save(any(Assistance.class));
	    }
	    
	    @Test
	    public void testCreateDepannage() {
	        Depannage depannage = new Depannage(1L, Status.TRAITEMENT, new Date(), "Test Depannage", TypeAssistance.AUTRE, "John", "Doe", "john.doe@example.com", "password", "1234567890", "Paris", "123 Rue Example");
	        
	        when(repo.save(any(Depannage.class))).thenReturn(depannage);
	        
	        assistanceService.createDepannage(1L, Status.TRAITEMENT, new Date(), "Test Depannage", TypeAssistance.AUTRE, "John", "Doe", "john.doe@example.com", "password", "1234567890", "Paris", "123 Rue Example");
	
	        verify(repo, times(1)).save(any(Depannage.class));
	    }
	    
	    @Test
	    public void testCreateAccident() {
	        Accident accident = new Accident(1L, Status.ATTENTE,new Date(), "Test Accident", TypeAssistance.ACCIDENT, "John", "Doe", "john.doe@example.com", "password", "1234567890", "Paris", "123 Rue Example", 3);
	        
	        when(repo.save(any(Accident.class))).thenReturn(accident);
	        
	        assistanceService.createAccident(1L, Status.ATTENTE, new Date(), "Test Accident", TypeAssistance.ACCIDENT, "John", "Doe", "john.doe@example.com", "password", "1234567890", "Paris", "123 Rue Example", 3);
	
	        verify(repo, times(1)).save(any(Accident.class));
	    }
	    
	    @Test
	    public void testCreateRemboursement() {
	        Remboursement remboursement = new Remboursement(1L, Status.ATTENTE,new Date(), "Test Remboursement", TypeAssistance.ACCIDENT, "John", "Doe", "john.doe@example.com", "password", "1234567890", 100.5f, "Medical");
	        
	        when(repo.save(any(Remboursement.class))).thenReturn(remboursement);
	        
	        assistanceService.createRemboursement(1L, Status.ATTENTE, new Date(), "Test Remboursement", TypeAssistance.ACCIDENT, "John", "Doe", "john.doe@example.com", "password", "1234567890", 100.5f, "Medical");
	
	        verify(repo, times(1)).save(any(Remboursement.class));
	    }
	    
	    @Test
	    public void testCreateMedical() {
	        Medical medical = new Medical(1L, Status.ATTENTE, new Date(), "Test Medical", TypeAssistance.AUTO, "John", "Doe", "john.doe@example.com", "password", "1234567890", "Treatment");
	        
	        when(repo.save(any(Medical.class))).thenReturn(medical);
	        
	        assistanceService.createMedical(1L, Status.ATTENTE, new Date(), "Test Medical", TypeAssistance.AUTO, "John", "Doe", "john.doe@example.com", "password", "1234567890", "Treatment");
	
	        verify(repo, times(1)).save(any(Medical.class));
	    }
	    
	    @Test
	    public void testGetAllAssistance() {
	        // Créer une liste d'assistances simulées
	        Assistance assistance1 = new Assistance(1L, Status.ATTENTE, new Date(), "Besoin d'aide 1", TypeAssistance.ACCIDENT, "Doe", "John", "john.doe@example.com", "password", "0123456789");
	        assistance1.setNumDossier("123");

	        Assistance assistance2 = new Assistance(2L, Status.CLOTURER, new Date(), "Besoin d'aide 2", TypeAssistance.AUTO, "Smith", "Jane", "jane.smith@example.com", "password", "9876543210");
	        assistance2.setNumDossier("124");

	        // Simuler le comportement du repository pour retourner une liste d'assistances
	        when(repo.findAll()).thenReturn(List.of(assistance1, assistance2));

	        // Appeler la méthode à tester
	        List<Assistance> result = assistanceService.getAllAssistance();

	        // Vérifier que la méthode findAll() du repository a été appelée une fois
	        verify(repo, times(1)).findAll();

	        // Vérifier que la liste retournée contient les bonnes assistances
	        assertEquals(2, result.size());
	        assertEquals("123", result.get(0).getNumDossier());
	        assertEquals("124", result.get(1).getNumDossier());
	    }

	    @Test
	    public void testGetMyAssistance() {
	    	// Créer une liste d'assistances simulées
	        Assistance assistance1 = new Assistance(1L, Status.ATTENTE, new Date(), "Besoin d'aide 1", TypeAssistance.ACCIDENT, "Doe", "John", "john.doe@example.com", "password", "0123456789");
	        assistance1.setNumDossier("123");
	        
	        // Simuler le comportement du repository pour retourner une liste d'assistances
	        when(repo.findByIdClient(1L)).thenReturn(List.of(assistance1));
	        
	        // Appeler la méthode à tester
	        List<Assistance> result = assistanceService.getMyAssistance(1L);
	    
	        // Vérifier que la méthode du repository a été appelée une fois
	        verify(repo, times(1)).findByIdClient(1L);
	        
	        assertEquals(1, result.size());
	        assertEquals("123", result.get(0).getNumDossier());
	    }
	    
	    @Test
	    public void testGetByNumDossier() {
	    	// Créer l'assistances simulées
	        Assistance assistance1 = new Assistance(1L, Status.ATTENTE, new Date(), "Besoin d'aide 1", TypeAssistance.ACCIDENT, "Doe", "John", "john.doe@example.com", "password", "0123456789");
	        assistance1.setNumDossier("123");
	        
	        // Simuler le comportement du repository pour retourner l'assistances
	        when(repo.findByNumDossier("123")).thenReturn(assistance1);
	        
	        // Appeler la méthode à tester
	        Assistance result = assistanceService.getByNumDossier("123");
	    
	        // Vérifier que la méthode du repository a été appelée une fois
	        verify(repo, times(1)).findByNumDossier("123");
	        
	        assertEquals("123", result.getNumDossier());
	    }
	    
	    @Test
	    public void testGetAssistanceDisponnible() {
	    	// Créer une liste d'assistances simulées
	        Assistance assistance1 = new Assistance(1L, Status.ATTENTE, new Date(), "Besoin d'aide 1", TypeAssistance.ACCIDENT, "Doe", "John", "john.doe@example.com", "password", "0123456789");
	        assistance1.setNumDossier("123");
	        
	        // Simuler le comportement du repository pour retourner une liste d'assistances
	        when(repo.findByGererFalse()).thenReturn(List.of(assistance1));
	        
	        // Appeler la méthode à tester
	        List<Assistance> result = assistanceService.getAssistanceDisponnible();
	    
	        // Vérifier que la méthode du repository a été appelée une fois
	        verify(repo, times(1)).findByGererFalse();
	        
	        assertEquals(1, result.size());
	        assertEquals("123", result.get(0).getNumDossier());
	    }
	}
