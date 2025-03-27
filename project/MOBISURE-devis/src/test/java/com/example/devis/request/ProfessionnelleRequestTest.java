package com.example.devis.request;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.Options.ProfessionelleOptions;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

class ProfessionnelleRequestTest {

    @Test
    void testGetAndSetClientId() {
        ProfessionnelleRequest request = new ProfessionnelleRequest();
        request.setClientId(123L);
        assertEquals(123L, request.getClientId());
    }

    @Test
    void testGetAndSetType() {
        ProfessionnelleRequest request = new ProfessionnelleRequest();
        request.setType(TypeAssurance.PROFESSIONNELLE);
        assertEquals(TypeAssurance.PROFESSIONNELLE, request.getType());
    }

    @Test
    void testGetAndSetEntreprise() {
        ProfessionnelleRequest request = new ProfessionnelleRequest();
        request.setEntreprise("Entreprise ABC");
        assertEquals("Entreprise ABC", request.getEntreprise());
    }

    @Test
    void testGetAndSetPaysDepart() {
        ProfessionnelleRequest request = new ProfessionnelleRequest();
        request.setPaysDepart("France");
        assertEquals("France", request.getPaysDepart());
    }

    @Test
    void testGetAndSetPaysArrive() {
        ProfessionnelleRequest request = new ProfessionnelleRequest();
        request.setPaysArrive("Allemagne");
        assertEquals("Allemagne", request.getPaysArrive());
    }

    @Test
    void testGetAndSetVilleDepart() {
        ProfessionnelleRequest request = new ProfessionnelleRequest();
        request.setVilleDepart("Paris");
        assertEquals("Paris", request.getVilleDepart());
    }

    @Test
    void testGetAndSetVilleArrive() {
        ProfessionnelleRequest request = new ProfessionnelleRequest();
        request.setVilleArrive("Berlin");
        assertEquals("Berlin", request.getVilleArrive());
    }

    @Test
    void testGetAndSetDateDepart() {
        ProfessionnelleRequest request = new ProfessionnelleRequest();
        Date dateDepart = new Date();
        request.setDateDepart(dateDepart);
        assertEquals(dateDepart, request.getDateDepart());
    }

    @Test
    void testGetAndSetDateArrive() {
        ProfessionnelleRequest request = new ProfessionnelleRequest();
        Date dateArrive = new Date();
        request.setDateArrive(dateArrive);
        assertEquals(dateArrive, request.getDateArrive());
    }

    @Test
    void testGetAndSetTransport() {
        ProfessionnelleRequest request = new ProfessionnelleRequest();
        request.setTransport(1);
        assertEquals(1, request.getTransport());
    }

    @Test
    void testGetAndSetDistance() {
        ProfessionnelleRequest request = new ProfessionnelleRequest();
        request.setDistance(100.0);
        assertEquals(100.0, request.getDistance());
    }

    @Test
    void testGetAndSetCo2() {
        ProfessionnelleRequest request = new ProfessionnelleRequest();
        request.setCo2(10.5);
        assertEquals(10.5, request.getCo2());
    }

    @Test
    void testGetAndSetOptions() {
        ProfessionnelleRequest request = new ProfessionnelleRequest();
        Set<ProfessionelleOptions> options = new HashSet<>();
        options.add(ProfessionelleOptions.DOCUMENTS);
        request.setOptions(options);
        assertTrue(request.getOptions().contains(ProfessionelleOptions.DOCUMENTS));
    }
}

