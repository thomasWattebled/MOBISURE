package com.example.devis.request;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.devis.Enum.DureeAssurance;
import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.DevisVehicule.MarqueVoiture;
import com.example.devis.Enum.DevisVehicule.Motorisation;
import com.example.devis.Enum.DevisVehicule.UtilisationVehicule;
import com.example.devis.Enum.Options.VoitureOptions;

import java.util.HashSet;
import java.util.Set;

class VoitureRequestTest {

    @Test
    void testGetAndSetClientId() {
        VoitureRequest request = new VoitureRequest();
        request.setClientId(123L);
        assertEquals(123L, request.getClientId());
    }

    @Test
    void testGetAndSetType() {
        VoitureRequest request = new VoitureRequest();
        request.setType(TypeAssurance.VOITURE);
        assertEquals(TypeAssurance.VOITURE, request.getType());
    }

    @Test
    void testGetAndSetMotorisation() {
        VoitureRequest request = new VoitureRequest();
        request.setMotorisation(Motorisation.ELECTRIQUE);
        assertEquals(Motorisation.ELECTRIQUE, request.getMotorisation());
    }

    @Test
    void testGetAndSetFabrication() {
        VoitureRequest request = new VoitureRequest();
        request.setFabrication(2022L);
        assertEquals(2022L, request.getFabrication());
    }

    @Test
    void testGetAndSetUtilisation() {
        VoitureRequest request = new VoitureRequest();
        request.setUtilisation(UtilisationVehicule.PERSONNEL);
        assertEquals(UtilisationVehicule.PERSONNEL, request.getUtilisation());
    }

    @Test
    void testGetAndSetDuree() {
        VoitureRequest request = new VoitureRequest();
        request.setDuree(DureeAssurance.UN_AN);
        assertEquals(DureeAssurance.UN_AN, request.getDuree());
    }

    @Test
    void testGetAndSetModele() {
        VoitureRequest request = new VoitureRequest();
        request.setModele("ModeleX");
        assertEquals("ModeleX", request.getModele());
    }

    @Test
    void testGetAndSetMarque() {
        VoitureRequest request = new VoitureRequest();
        request.setMarque(MarqueVoiture.TOYOTA);
        assertEquals(MarqueVoiture.TOYOTA, request.getMarque());
    }

    @Test
    void testGetAndSetPlaque() {
        VoitureRequest request = new VoitureRequest();
        request.setPlaque("123-ABC");
        assertEquals("123-ABC", request.getPlaque());
    }

    @Test
    void testGetAndSetOptions() {
        VoitureRequest request = new VoitureRequest();
        Set<VoitureOptions> options = new HashSet<>();
        options.add(VoitureOptions.ASSISTANCE_ZERO_KM);
        request.setOptions(options);
        assertTrue(request.getOptions().contains(VoitureOptions.ASSISTANCE_ZERO_KM));
    }
}

