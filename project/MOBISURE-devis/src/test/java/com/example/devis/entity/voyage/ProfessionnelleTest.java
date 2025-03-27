package com.example.devis.entity.voyage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.devis.Enum.Options.ProfessionelleOptions;

class ProfessionnelleTest {

    private Professionnelle professionnelle;

    @BeforeEach
    void setUp() {
        professionnelle = new Professionnelle();
    }
    
    @Test
    void testConstructeurAvecArguments() {
        Date dateDepart = new Date();
        Date dateArrive = new Date();
        Set<ProfessionelleOptions> options = new HashSet<>();
        
        Professionnelle p = new Professionnelle(1L, "France", "Allemagne", "Paris", "Berlin", 
                                                "TechCorp", dateDepart, dateArrive, options, 
                                                2, 500.0, 120.5);

        assertThat(p.getClientId()).isEqualTo(1L);
        assertThat(p.getPaysDepart()).isEqualTo("France");
        assertThat(p.getPaysArrive()).isEqualTo("Allemagne");
        assertThat(p.getVilleDepart()).isEqualTo("Paris");
        assertThat(p.getVilleArrive()).isEqualTo("Berlin");
        assertThat(p.getEntreprise()).isEqualTo("TechCorp");
        assertThat(p.getDateDepart()).isEqualTo(dateDepart);
        assertThat(p.getDateArrive()).isEqualTo(dateArrive);
        assertThat(p.getOptions()).isEqualTo(options);
        assertThat(p.getTransport()).isEqualTo(2);
        assertThat(p.getDistance()).isEqualTo(500.0);
        assertThat(p.getCo2()).isEqualTo(120.5);
    }


    @Test
    void testEntrepriseGetterSetter() {
        professionnelle.setEntreprise("TechCorp");
        assertThat(professionnelle.getEntreprise()).isEqualTo("TechCorp");
    }

    @Test
    void testPaysDepartGetterSetter() {
        professionnelle.setPaysDepart("France");
        assertThat(professionnelle.getPaysDepart()).isEqualTo("France");
    }

    @Test
    void testPaysArriveGetterSetter() {
        professionnelle.setPaysArrive("Allemagne");
        assertThat(professionnelle.getPaysArrive()).isEqualTo("Allemagne");
    }

    @Test
    void testVilleDepartGetterSetter() {
        professionnelle.setVilleDepart("Paris");
        assertThat(professionnelle.getVilleDepart()).isEqualTo("Paris");
    }

    @Test
    void testVilleArriveGetterSetter() {
        professionnelle.setVilleArrive("Berlin");
        assertThat(professionnelle.getVilleArrive()).isEqualTo("Berlin");
    }

    @Test
    void testDateDepartGetterSetter() {
        Date date = new Date();
        professionnelle.setDateDepart(date);
        assertThat(professionnelle.getDateDepart()).isEqualTo(date);
    }

    @Test
    void testDateArriveGetterSetter() {
        Date date = new Date();
        professionnelle.setDateArrive(date);
        assertThat(professionnelle.getDateArrive()).isEqualTo(date);
    }

    @Test
    void testTransportGetterSetter() {
        professionnelle.setTransport(2);
        assertThat(professionnelle.getTransport()).isEqualTo(2);
    }

    @Test
    void testDistanceGetterSetter() {
        professionnelle.setDistance(500.0);
        assertThat(professionnelle.getDistance()).isEqualTo(500.0);
    }

    @Test
    void testCo2GetterSetter() {
        professionnelle.setCo2(120.5);
        assertThat(professionnelle.getCo2()).isEqualTo(120.5);
    }

    @Test
    void testNbSemaineGetterSetter() {
        professionnelle.setNbSemaine(4);
        assertThat(professionnelle.getNbSemaine()).isEqualTo(4);
    }

    @Test
    void testOptionsGetterSetter() {
        Set<ProfessionelleOptions> options = new HashSet<>();
        professionnelle.setOptions(options);
        assertThat(professionnelle.getOptions()).isEqualTo(options);
    }

}