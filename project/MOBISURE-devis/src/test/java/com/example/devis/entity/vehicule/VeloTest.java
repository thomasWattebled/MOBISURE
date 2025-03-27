package com.example.devis.entity.vehicule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.DevisVehicule.Motorisation;
import com.example.devis.Enum.Options.VeloOptions;

public class VeloTest {

	private Velo velo;
    private Set<VeloOptions> options;

    @BeforeEach
    void setUp() {
        options = new HashSet<>();
        options.add(VeloOptions.ASSISTANCE_CREVAISON);

        velo = new Velo(1L, Motorisation.ELECTRIQUE, options);
    }

    @Test
    void testClientId() {
        assertEquals(1L, velo.getClientId());
        velo.setClientId(2L);
        assertEquals(2L, velo.getClientId());
    }

    @Test
    void testTypeAssurance() {
        assertEquals(TypeAssurance.VELO, velo.getType());
    }

    @Test
    void testMotorisation() {
        assertEquals(Motorisation.ELECTRIQUE, velo.getElectrique());
        velo.setElectrique(Motorisation.THERMIQUE);
        assertEquals(Motorisation.THERMIQUE, velo.getElectrique());
    }

    @Test
    void testOptions() {
        assertEquals(1, velo.getOptions().size());
        assertTrue(velo.getOptions().contains(VeloOptions.ASSISTANCE_CREVAISON));

        Set<VeloOptions> newOptions = new HashSet<>();
        newOptions.add(VeloOptions.PROTECTION_VANDALISME);
        velo.setOptions(newOptions);

        assertEquals(1, velo.getOptions().size());
        assertTrue(velo.getOptions().contains(VeloOptions.PROTECTION_VANDALISME));
    }
	
	
}
