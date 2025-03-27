package com.example.devis.request;

import static org.assertj.core.api.Assertions.assertThat;
import com.example.devis.Enum.DevisVehicule.MarqueMoto;
import com.example.devis.Enum.DevisVehicule.Motorisation;
import com.example.devis.Enum.DevisVehicule.UtilisationVehicule;
import com.example.devis.Enum.DureeAssurance;
import com.example.devis.Enum.TypeAssurance;
import com.example.devis.Enum.Options.MotoOptions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class MotoRequestTest {

    @Test
    void testSetAndGetClientId() {
        MotoRequest motoRequest = new MotoRequest();
        motoRequest.setClientId(1L);
        assertThat(motoRequest.getClientId()).isEqualTo(1L);
    }

    @Test
    void testSetAndGetType() {
        MotoRequest motoRequest = new MotoRequest();
        motoRequest.setType(TypeAssurance.VOITURE);
        assertThat(motoRequest.getType()).isEqualTo(TypeAssurance.VOITURE);
    }

    @Test
    void testSetAndGetFabrication() {
        MotoRequest motoRequest = new MotoRequest();
        motoRequest.setFabrication(2021L);
        assertThat(motoRequest.getFabrication()).isEqualTo(2021L);
    }

    @Test
    void testSetAndGetUtilisation() {
        MotoRequest motoRequest = new MotoRequest();
        motoRequest.setUtilisation(UtilisationVehicule.PERSONNEL);
        assertThat(motoRequest.getUtilisation()).isEqualTo(UtilisationVehicule.PERSONNEL);
    }

    @Test
    void testSetAndGetDuree() {
        MotoRequest motoRequest = new MotoRequest();
        motoRequest.setDuree(DureeAssurance.DEUX_SEMAINES);
        assertThat(motoRequest.getDuree()).isEqualTo(DureeAssurance.DEUX_SEMAINES);
    }

    @Test
    void testSetAndGetModele() {
        MotoRequest motoRequest = new MotoRequest();
        motoRequest.setModele("Ducati Panigale");
        assertThat(motoRequest.getModele()).isEqualTo("Ducati Panigale");
    }

    @Test
    void testSetAndGetMarque() {
        MotoRequest motoRequest = new MotoRequest();
        motoRequest.setMarque(MarqueMoto.DUCATI);
        assertThat(motoRequest.getMarque()).isEqualTo(MarqueMoto.DUCATI);
    }

    @Test
    void testSetAndGetMotorisation() {
        MotoRequest motoRequest = new MotoRequest();
        motoRequest.setMotorisation(Motorisation.ELECTRIQUE);
        assertThat(motoRequest.getMotorisation()).isEqualTo(Motorisation.ELECTRIQUE);
    }

    @Test
    void testSetAndGetPlaque() {
        MotoRequest motoRequest = new MotoRequest();
        motoRequest.setPlaque("ABC-1234");
        assertThat(motoRequest.getPlaque()).isEqualTo("ABC-1234");
    }

    @Test
    void testSetAndGetOptions() {
        MotoRequest motoRequest = new MotoRequest();
        Set<MotoOptions> options = new HashSet<>();
        options.add(MotoOptions.ASSISTANCE_ZERO_KM);
        motoRequest.setOptions(options);
        assertThat(motoRequest.getOptions()).contains(MotoOptions.ASSISTANCE_ZERO_KM);
    }

}

