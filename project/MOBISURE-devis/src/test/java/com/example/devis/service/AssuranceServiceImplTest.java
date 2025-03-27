package com.example.devis.service;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import com.example.devis.entity.Assurance;
import com.example.devis.entity.vehicule.Moto;
import com.example.devis.entity.vehicule.Velo;
import com.example.devis.entity.vehicule.Voiture;
import com.example.devis.entity.voyage.Professionnelle;
import com.example.devis.entity.voyage.Vacances;
import com.example.devis.Enum.DevisVehicule.MarqueMoto;
import com.example.devis.Enum.DevisVehicule.MarqueVoiture;
import com.example.devis.Enum.DevisVehicule.Motorisation;
import com.example.devis.Enum.DureeAssurance;
import com.example.devis.repository.AssuranceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AssuranceServiceImplTest {

    @InjectMocks
    private AssuranceServiceImpl assuranceService; // Service à tester

    @Mock
    private AssuranceRepository assuranceRepository; // Simulation du repository

    private Voiture voiture;
    private Moto moto;
    private Velo velo;
    private Vacances vac;
    private Professionnelle prof;
    
    @BeforeEach
    public void setUp() {
        voiture = new Voiture();
        moto = new Moto();
        velo = new Velo();
        vac = new Vacances();
        prof = new Professionnelle();
    }

    @Test
    public void testCreateVeloDevis() {
        // Exemple de test pour le vélo avec motorisation thermique
        Velo velo1 = new Velo();
        Velo velo2 = new Velo();
        
        velo1.setElectrique(Motorisation.ELECTRIQUE);
        velo2.setElectrique(Motorisation.THERMIQUE);

        double devisVelo1 = assuranceService.createVeloDevis(velo1);
        double devisVelo2 = assuranceService.createVeloDevis(velo2);

        assertEquals(150, devisVelo1);
        assertEquals(100, devisVelo2);
    }

    @Test
    public void testCreateContratVoiture() {
        // Simuler l'enregistrement de l'assurance Voiture
        when(assuranceRepository.save(voiture)).thenReturn(voiture);
        
        Voiture savedVoiture = assuranceService.createContratVoiture(voiture);

        assertNotNull(savedVoiture);
        verify(assuranceRepository, times(1)).save(voiture); // Vérifier l'appel au repository
    }
    
    @Test
    public void testCreateContratMoto() {
        when(assuranceRepository.save(moto)).thenReturn(moto);
        
        Moto savedMoto = assuranceService.createContratMoto(moto);

        assertNotNull(savedMoto);
        verify(assuranceRepository, times(1)).save(moto); // Vérifier l'appel au repository
    }
    
    @Test
    public void testCreateContratVacances() {
        when(assuranceRepository.save(vac)).thenReturn(vac);
        
        Vacances savedVac = assuranceService.createContratVacances(vac);

        assertNotNull(savedVac);
        verify(assuranceRepository, times(1)).save(vac); // Vérifier l'appel au repository
    }
    
    @Test
    public void testCreateContratProfessionnelle() {
        when(assuranceRepository.save(prof)).thenReturn(prof);
        
        Professionnelle savedProf = assuranceService.createContratProfessionnelle(prof);

        assertNotNull(savedProf);
        verify(assuranceRepository, times(1)).save(prof); // Vérifier l'appel au repository
    }
    
    @Test
    public void testCreateContratVelo() {
        when(assuranceRepository.save(velo)).thenReturn(velo);
        
        Velo savedVelo = assuranceService.createContratVelo(velo);

        assertNotNull(savedVelo);
        verify(assuranceRepository, times(1)).save(velo); // Vérifier l'appel au repository
    }

    @Test
    public void testGetAssuranceByNumDossier() throws Exception {
        // Simuler la recherche par numéro de dossier
        String numDossier = "12345";
        Assurance assuranceMock = new Assurance();
        assuranceMock.setNumDossier(numDossier);
        
        when(assuranceRepository.findByNumDossier(numDossier)).thenReturn(Optional.of(assuranceMock));
        
        Assurance foundAssurance = assuranceService.getAssuranceByNumDossier(numDossier);
        
        assertNotNull(foundAssurance);
        assertEquals(numDossier, foundAssurance.getNumDossier());
    }

    @Test
    public void testGetAssuranceByNumDossierNotFound() {
        // Simuler un cas où l'assurance n'est pas trouvée
        String numDossier = "99999";
        
        when(assuranceRepository.findByNumDossier(numDossier)).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(Exception.class, () -> {
            assuranceService.getAssuranceByNumDossier(numDossier);
        });

        assertEquals("Assurance non trouvée", exception.getMessage());
    }
    
    @Test 
    public void testGetAllAssurance() {
    	when(assuranceRepository.findAll()).thenReturn(List.of(voiture,velo));
    	List<Assurance> assurances = assuranceService.getAllAssurance();
    	
    	assertNotNull(assurances);
    	assertTrue(assurances.contains(voiture));
    	assertTrue(assurances.contains(velo));
    	assertEquals(2,assurances.size());
    }
    
    @Test
    public void getAssuranceByClientId() {
    	when(assuranceRepository.findByClientId(1L)).thenReturn(List.of(voiture));
    	List<Assurance> assurances = assuranceService.getAssuranceByClientId(1L);
    	assertNotNull(assurances);
    	assertTrue(assurances.contains(voiture));
    	assertEquals(1,assurances.size());
    }
    
    
    @Test
    public void testCreateVoitureDevis() {
        // Cas 1: Marque BMW, motorisation thermique, durée 1 an
        Voiture voiture1 = new Voiture();
        voiture1.setMarque(MarqueVoiture.BMW);
        voiture1.setElectrique(Motorisation.THERMIQUE);
        voiture1.setDuree(DureeAssurance.UN_AN);
        
        double devis1 = assuranceService.createVoitureDevis(voiture1);
        assertEquals(15 * 48 * 1.2, devis1, 0.001); // Calcul attendu : prix_total * multiplicateur

        // Cas 2: Marque VOLKSWAGEN, motorisation électrique, durée 2 semaines
        Voiture voiture2 = new Voiture();
        voiture2.setMarque(MarqueVoiture.VOLKSWAGEN);
        voiture2.setElectrique(Motorisation.ELECTRIQUE);
        voiture2.setDuree(DureeAssurance.DEUX_SEMAINES);
        
        double devis2 = assuranceService.createVoitureDevis(voiture2);
        assertEquals((15 * 2) * (1.1 * 0.85 *1.3), devis2, 0.001); // Calcul attendu pour ce cas

        // Cas 3: Marque RENAULT, motorisation thermique, durée 1 mois
        Voiture voiture3 = new Voiture();
        voiture3.setMarque(MarqueVoiture.RENAULT);
        voiture3.setElectrique(Motorisation.THERMIQUE);
        voiture3.setDuree(DureeAssurance.UN_MOIS);
        
        double devis3 = assuranceService.createVoitureDevis(voiture3);
        assertEquals((15 * 4) * (1 * 1.2), devis3, 0.001); // Calcul attendu

        // Cas 4: Marque TOYOTA, motorisation électrique, durée 6 mois
        Voiture voiture4 = new Voiture();
        voiture4.setMarque(MarqueVoiture.TOYOTA);
        voiture4.setElectrique(Motorisation.ELECTRIQUE);
        voiture4.setDuree(DureeAssurance.SIX_MOIS);
        
        double devis4 = assuranceService.createVoitureDevis(voiture4);
        assertEquals((15 * 24) * (1.1 * 1.1 * 0.85), devis4, 0.001); // Calcul attendu

        // Cas 5: Marque PEUGEOT, motorisation thermique, durée 1 semaine
        Voiture voiture5 = new Voiture();
        voiture5.setMarque(MarqueVoiture.PEUGEOT);
        voiture5.setElectrique(Motorisation.THERMIQUE);
        voiture5.setDuree(DureeAssurance.SEMAINE);
        
        double devis5 = assuranceService.createVoitureDevis(voiture5);
        assertEquals(15 * 1.4, devis5, 0.001); // Calcul attendu

        // Cas 6: Marque MERCEDES, motorisation électrique, durée 1 an
        Voiture voiture6 = new Voiture();
        voiture6.setMarque(MarqueVoiture.MERCEDES);
        voiture6.setElectrique(Motorisation.ELECTRIQUE);
        voiture6.setDuree(DureeAssurance.UN_AN);
        
        double devis6 = assuranceService.createVoitureDevis(voiture6);
        assertEquals(15 * 48 * 1.2 * 0.85, devis6, 0.001); // Calcul attendu pour Mercedes avec motorisation électrique
    }

    
    @Test
    public void testCreateMotoDevis_Ducati_Electrique_UnMois() {
        Moto moto = new Moto();
        moto.setMarque(MarqueMoto.DUCATI);
        moto.setElectrique(Motorisation.ELECTRIQUE);
        moto.setDuree(DureeAssurance.UN_MOIS);

        double devis = assuranceService.createMotoDevis(moto);
        assertEquals((10 * 4) * (1.2 * 1.2 * 0.85), devis, 0.001); // Vérification avec tolérance
    }

    @Test
    public void testCreateMotoDevis_Yamaha_Thermique_SixMois() {
        Moto moto = new Moto();
        moto.setMarque(MarqueMoto.YAMAHA);
        moto.setElectrique(Motorisation.THERMIQUE);
        moto.setDuree(DureeAssurance.SIX_MOIS);

        double devis = assuranceService.createMotoDevis(moto);
        assertEquals(10 * 24 * 1 * 1.1, devis, 0.001);
    }

    @Test
    public void testCreateMotoDevis_Kawasaki_Electrique_DeuxSemaines() {
        Moto moto = new Moto();
        moto.setMarque(MarqueMoto.KAWASAKI);
        moto.setElectrique(Motorisation.ELECTRIQUE);
        moto.setDuree(DureeAssurance.DEUX_SEMAINES);

        double devis = assuranceService.createMotoDevis(moto);
        assertEquals(10 * 2 * 1 * 1.3 * 0.85, devis, 0.001);
    }

    @Test
    public void testCreateMotoDevis_KTM_Thermique_UneSemaine() {
        Moto moto = new Moto();
        moto.setMarque(MarqueMoto.KTM);
        moto.setElectrique(Motorisation.THERMIQUE);
        moto.setDuree(DureeAssurance.SEMAINE);

        double devis = assuranceService.createMotoDevis(moto);
        assertEquals(10 * (1.1 * 1 * 1.4), devis, 0.001);
    }
}

