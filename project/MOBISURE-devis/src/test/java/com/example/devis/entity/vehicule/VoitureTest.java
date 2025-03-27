package com.example.devis.entity.vehicule;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.devis.Enum.DureeAssurance;
import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.DevisVehicule.MarqueVoiture;
import com.example.devis.Enum.DevisVehicule.Motorisation;
import com.example.devis.Enum.DevisVehicule.UtilisationVehicule;
import com.example.devis.Enum.Options.VoitureOptions;

class VoitureTest {

    private Voiture voiture;
    private Set<VoitureOptions> options;

    @BeforeEach
    void setUp() {
        options = new HashSet<>();
        options.add(VoitureOptions.ASSISTANCE_ZERO_KM);

        voiture = new Voiture(1L, MarqueVoiture.RENAULT, Motorisation.THERMIQUE, 2022L,
                UtilisationVehicule.PERSONNEL, DureeAssurance.UN_AN, "Clio", "AB-123-CD", options);
    }

    @Test
    void testClientId() {
        assertEquals(1L, voiture.getClientId());
        voiture.setClientId(2L);
        assertEquals(2L, voiture.getClientId());
    }

    @Test
    void testTypeAssurance() {
        assertEquals(TypeAssurance.VOITURE, voiture.getType());
    }

    @Test
    void testMarque() {
        assertEquals(MarqueVoiture.RENAULT, voiture.getMarque());
        voiture.setMarque(MarqueVoiture.PEUGEOT);
        assertEquals(MarqueVoiture.PEUGEOT, voiture.getMarque());
    }

    @Test
    void testMotorisation() {
        assertEquals(Motorisation.THERMIQUE, voiture.getElectrique());
        voiture.setElectrique(Motorisation.ELECTRIQUE);
        assertEquals(Motorisation.ELECTRIQUE, voiture.getElectrique());
    }

    @Test
    void testFabrication() {
        assertEquals(2022L, voiture.getFabrication());
        voiture.setFabrication(2021L);
        assertEquals(2021L, voiture.getFabrication());
    }

    @Test
    void testUtilisation() {
        assertEquals(UtilisationVehicule.PERSONNEL, voiture.getUtilisation());
        voiture.setUtilisation(UtilisationVehicule.PROFESSIONNELLE);
        assertEquals(UtilisationVehicule.PROFESSIONNELLE, voiture.getUtilisation());
    }

    @Test
    void testDuree() {
        assertEquals(DureeAssurance.UN_AN, voiture.getDuree());
        voiture.setDuree(DureeAssurance.DEUX_SEMAINES);
        assertEquals(DureeAssurance.DEUX_SEMAINES, voiture.getDuree());
    }

    @Test
    void testModele() {
        assertEquals("Clio", voiture.getModele());
        voiture.setModele("Megane");
        assertEquals("Megane", voiture.getModele());
    }

    @Test
    void testPlaque() {
        assertEquals("AB-123-CD", voiture.getPlaque());
        voiture.setPlaque("XY-456-ZZ");
        assertEquals("XY-456-ZZ", voiture.getPlaque());
    }

    @Test
    void testOptions() {
        assertEquals(1, voiture.getOptions().size());
        assertTrue(voiture.getOptions().contains(VoitureOptions.ASSISTANCE_ZERO_KM));

        Set<VoitureOptions> newOptions = new HashSet<>();
        newOptions.add(VoitureOptions.BRIS_DE_GLACE);
        voiture.setOptions(newOptions);

        assertEquals(1, voiture.getOptions().size());
        assertTrue(voiture.getOptions().contains(VoitureOptions.BRIS_DE_GLACE));
    }
}