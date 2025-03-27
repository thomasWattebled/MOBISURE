package com.example.devis.entity.vehicule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.devis.Enum.DureeAssurance;
import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.DevisVehicule.MarqueMoto;
import com.example.devis.Enum.DevisVehicule.Motorisation;
import com.example.devis.Enum.DevisVehicule.UtilisationVehicule;
import com.example.devis.Enum.Options.MotoOptions;

public class MotoTest {

	private Moto moto;
    private Set<MotoOptions> options;

    @BeforeEach
    void setUp() {
        options = new HashSet<>();
        options.add(MotoOptions.ASSISTANCE_ZERO_KM);

        moto = new Moto(1L, MarqueMoto.YAMAHA, Motorisation.ELECTRIQUE, 2022L, 
                        UtilisationVehicule.PERSONNEL, DureeAssurance.UN_AN, 
                        "MT-07", "ABC-123", options);
    }
    
    @Test 
    void testConstructeurVide() {
    	Moto newMoto = new Moto();
    	assertEquals(TypeAssurance.MOTO,newMoto.getType());
    }

    @Test
    void testClientId() {
        assertEquals(1L, moto.getClientId());
        moto.setClientId(2L);
        assertEquals(2L, moto.getClientId());
    }

    @Test
    void testTypeAssurance() {
        assertEquals(TypeAssurance.MOTO, moto.getType());
    }

    @Test
    void testMarque() {
        assertEquals(MarqueMoto.YAMAHA, moto.getMarque());
        moto.setMarque(MarqueMoto.HONDA);
        assertEquals(MarqueMoto.HONDA, moto.getMarque());
    }

    @Test
    void testMotorisation() {
        assertEquals(Motorisation.ELECTRIQUE, moto.getElectrique());
        moto.setElectrique(Motorisation.THERMIQUE);
        assertEquals(Motorisation.THERMIQUE, moto.getElectrique());
    }

    @Test
    void testFabrication() {
        assertEquals(2022L, moto.getFabrication());
        moto.setFabrication(2020L);
        assertEquals(2020L, moto.getFabrication());
    }

    @Test
    void testUtilisationVehicule() {
        assertEquals(UtilisationVehicule.PERSONNEL, moto.getUtilisation());
        moto.setUtilisation(UtilisationVehicule.PROFESSIONNELLE);
        assertEquals(UtilisationVehicule.PROFESSIONNELLE, moto.getUtilisation());
    }

    @Test
    void testDureeAssurance() {
        assertEquals(DureeAssurance.UN_AN, moto.getDuree());
        moto.setDuree(DureeAssurance.DEUX_SEMAINES);
        assertEquals(DureeAssurance.DEUX_SEMAINES, moto.getDuree());
    }

    @Test
    void testModele() {
        assertEquals("MT-07", moto.getModele());
        moto.setModele("CBR600RR");
        assertEquals("CBR600RR", moto.getModele());
    }

    @Test
    void testPlaque() {
        assertEquals("ABC-123", moto.getPlaque());
        moto.setPlaque("XYZ-789");
        assertEquals("XYZ-789", moto.getPlaque());
    }

    @Test
    void testOptions() {
        assertEquals(1, moto.getOptions().size());
        assertTrue(moto.getOptions().contains(MotoOptions.ASSISTANCE_ZERO_KM));

        Set<MotoOptions> newOptions = new HashSet<>();
        newOptions.add(MotoOptions.EQUIPEMENT_PROTEGER);
        moto.setOptions(newOptions);

        assertEquals(1, moto.getOptions().size());
        assertTrue(moto.getOptions().contains(MotoOptions.EQUIPEMENT_PROTEGER));
    }
	
}
