package com.example.devis.request;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.DevisVehicule.Motorisation;
import com.example.devis.Enum.Options.VeloOptions;

import java.util.HashSet;
import java.util.Set;

class VeloRequestTest {

    @Test
    void testGetAndSetClientId() {
        VeloRequest request = new VeloRequest();
        request.setClientId(123L);
        assertEquals(123L, request.getClientId());
    }

    @Test
    void testGetAndSetType() {
        VeloRequest request = new VeloRequest();
        request.setType(TypeAssurance.VELO);
        assertEquals(TypeAssurance.VELO, request.getType());
    }

    @Test
    void testGetAndSetMotorisation() {
        VeloRequest request = new VeloRequest();
        request.setMotorisation(Motorisation.ELECTRIQUE);
        assertEquals(Motorisation.ELECTRIQUE, request.getMotorisation());
    }

    @Test
    void testGetAndSetOptions() {
        VeloRequest request = new VeloRequest();
        Set<VeloOptions> options = new HashSet<>();
        options.add(VeloOptions.ASSISTANCE_CREVAISON);
        request.setOptions(options);
        assertTrue(request.getOptions().contains(VeloOptions.ASSISTANCE_CREVAISON));
    }
}

