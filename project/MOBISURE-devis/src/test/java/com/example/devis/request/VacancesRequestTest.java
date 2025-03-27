package com.example.devis.request;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.DevisVoyage.NombreVoyageurs;
import com.example.devis.Enum.Options.VacanceOptions;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

class VacancesRequestTest {

    @Test
    void testGetAndSetClientId() {
        VacancesRequest request = new VacancesRequest();
        request.setClientId(123L);
        assertEquals(123L, request.getClientId());
    }

    @Test
    void testGetAndSetType() {
        VacancesRequest request = new VacancesRequest();
        request.setType(TypeAssurance.VACANCES);
        assertEquals(TypeAssurance.VACANCES, request.getType());
    }

    @Test
    void testGetAndSetPaysDepart() {
        VacancesRequest request = new VacancesRequest();
        request.setPaysDepart("France");
        assertEquals("France", request.getPaysDepart());
    }

    @Test
    void testGetAndSetPaysArrive() {
        VacancesRequest request = new VacancesRequest();
        request.setPaysArrive("Espagne");
        assertEquals("Espagne", request.getPaysArrive());
    }

    @Test
    void testGetAndSetVilleDepart() {
        VacancesRequest request = new VacancesRequest();
        request.setVilleDepart("Paris");
        assertEquals("Paris", request.getVilleDepart());
    }

    @Test
    void testGetAndSetVilleArrive() {
        VacancesRequest request = new VacancesRequest();
        request.setVilleArrive("Madrid");
        assertEquals("Madrid", request.getVilleArrive());
    }

    @Test
    void testGetAndSetDateDepart() {
        VacancesRequest request = new VacancesRequest();
        Date dateDepart = new Date();
        request.setDateDepart(dateDepart);
        assertEquals(dateDepart, request.getDateDepart());
    }

    @Test
    void testGetAndSetDateArrive() {
        VacancesRequest request = new VacancesRequest();
        Date dateArrive = new Date();
        request.setDateArrive(dateArrive);
        assertEquals(dateArrive, request.getDateArrive());
    }

    @Test
    void testGetAndSetTransport() {
        VacancesRequest request = new VacancesRequest();
        request.setTransport(2);
        assertEquals(2, request.getTransport());
    }

    @Test
    void testGetAndSetDistance() {
        VacancesRequest request = new VacancesRequest();
        request.setDistance(300.0);
        assertEquals(300.0, request.getDistance());
    }

    @Test
    void testGetAndSetCo2() {
        VacancesRequest request = new VacancesRequest();
        request.setCo2(25.5);
        assertEquals(25.5, request.getCo2());
    }

    @Test
    void testGetAndSetNbPersonnes() {
        VacancesRequest request = new VacancesRequest();
        request.setNbPersonnes(NombreVoyageurs.DEUX_A_TROIS);
        assertEquals(NombreVoyageurs.DEUX_A_TROIS, request.getNbPersonnes());
    }

    @Test
    void testGetAndSetOptions() {
        VacancesRequest request = new VacancesRequest();
        Set<VacanceOptions> options = new HashSet<>();
        options.add(VacanceOptions.ANNULATION);
        request.setOptions(options);
        assertTrue(request.getOptions().contains(VacanceOptions.ANNULATION));
    }
}
