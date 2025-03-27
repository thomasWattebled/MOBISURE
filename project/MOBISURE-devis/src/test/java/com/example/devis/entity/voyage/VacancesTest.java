package com.example.devis.entity.voyage;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.devis.Enum.DevisVoyage.NombreVoyageurs;
import com.example.devis.Enum.Options.VacanceOptions;

class VacancesTest {

    private Vacances vacances;

    @BeforeEach
    void setUp() {
        vacances = new Vacances();
    }

    @Test
    void testSetPaysDepart() {
        vacances.setPaysDepart("France");
        assertThat(vacances.getPaysDepart()).isEqualTo("France");
    }

    @Test
    void testSetPaysArrive() {
        vacances.setPaysArrive("Espagne");
        assertThat(vacances.getPaysArrive()).isEqualTo("Espagne");
    }

    @Test
    void testSetVilleDepart() {
        vacances.setVilleDepart("Paris");
        assertThat(vacances.getVilleDepart()).isEqualTo("Paris");
    }

    @Test
    void testSetVilleArrive() {
        vacances.setVilleArrive("Barcelone");
        assertThat(vacances.getVilleArrive()).isEqualTo("Barcelone");
    }

    @Test
    void testSetDateDepart() {
        Date date = new Date();
        vacances.setDateDepart(date);
        assertThat(vacances.getDateDepart()).isEqualTo(date);
    }

    @Test
    void testSetDateArrive() {
        Date date = new Date();
        vacances.setDateArrive(date);
        assertThat(vacances.getDateArrive()).isEqualTo(date);
    }

    @Test
    void testSetTransport() {
        vacances.setTransport(2);
        assertThat(vacances.getTransport()).isEqualTo(2);
    }

    @Test
    void testSetDistance() {
        vacances.setDistance(1200.5);
        assertThat(vacances.getDistance()).isEqualTo(1200.5);
    }

    @Test
    void testSetCo2() {
        vacances.setCo2(300.75);
        assertThat(vacances.getCo2()).isEqualTo(300.75);
    }

    @Test
    void testSetNbPersonnes() {
        vacances.setNbPersonnes(NombreVoyageurs.DEUX_A_TROIS);
        assertThat(vacances.getNbPersonnes()).isEqualTo(NombreVoyageurs.DEUX_A_TROIS);
    }

    @Test
    void testSetOptions() {
        Set<VacanceOptions> options = new HashSet<>();
        vacances.setOptions(options);
        assertThat(vacances.getOptions()).isEqualTo(options);
    }

    @Test
    void testSetNbSemaine() {
        vacances.setNbSemaine(3);
        assertThat(vacances.getNbSemaine()).isEqualTo(3);
    }

    @Test
    void testCalculNombreDeSemaines() {
        Date dateDepart = new Date();
        Date dateArrive = new Date(dateDepart.getTime() + (14L * 24 * 60 * 60 * 1000));
        long semaines = vacances.getNombreDeSemaines(dateDepart, dateArrive);
        assertThat(semaines).isEqualTo(2);
    }

    @Test
    void testConstructeurAvecArguments() {
        Date dateDepart = new Date();
        Date dateArrive = new Date(dateDepart.getTime() + (21L * 24 * 60 * 60 * 1000));
        Set<VacanceOptions> options = new HashSet<>();

        Vacances vac = new Vacances(1L, "France", "Italie", "Paris", "Rome", dateDepart, dateArrive, 
                                    NombreVoyageurs.PLUS_DE_SIX, options, 1, 1500.0, 500.0);

        assertThat(vac.getClientId()).isEqualTo(1L);
        assertThat(vac.getPaysDepart()).isEqualTo("France");
        assertThat(vac.getPaysArrive()).isEqualTo("Italie");
        assertThat(vac.getVilleDepart()).isEqualTo("Paris");
        assertThat(vac.getVilleArrive()).isEqualTo("Rome");
        assertThat(vac.getDateDepart()).isEqualTo(dateDepart);
        assertThat(vac.getDateArrive()).isEqualTo(dateArrive);
        assertThat(vac.getNbPersonnes()).isEqualTo(NombreVoyageurs.PLUS_DE_SIX);
        assertThat(vac.getOptions()).isEqualTo(options);
        assertThat(vac.getTransport()).isEqualTo(1);
        assertThat(vac.getDistance()).isEqualTo(1500.0);
        assertThat(vac.getCo2()).isEqualTo(500.0);
        assertThat(vac.getNbSemaine()).isEqualTo(3);
    }

}