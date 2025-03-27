package com.example.devis.Enum.Options;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MotoOptionsTest {

    @Test
    void testFromString_validValue() {
        assertThat(MotoOptions.fromString("Assistance zéro km")).isEqualTo(MotoOptions.ASSISTANCE_ZERO_KM);
        assertThat(MotoOptions.fromString("Équipements protégés")).isEqualTo(MotoOptions.EQUIPEMENT_PROTEGER);
        assertThat(MotoOptions.fromString("Garantie tous risques")).isEqualTo(MotoOptions.GARENTIE_TOUT_RISQUE);
    }

    @Test
    void testFromString_invalidValue() {
        assertThrows(IllegalArgumentException.class, () -> MotoOptions.fromString("Option invalide"));
    }

    @Test
    void testEnumValues() {
        assertThat(MotoOptions.ASSISTANCE_ZERO_KM).isEqualTo(MotoOptions.valueOf("ASSISTANCE_ZERO_KM"));
        assertThat(MotoOptions.EQUIPEMENT_PROTEGER).isEqualTo(MotoOptions.valueOf("EQUIPEMENT_PROTEGER"));
        assertThat(MotoOptions.GARENTIE_TOUT_RISQUE).isEqualTo(MotoOptions.valueOf("GARENTIE_TOUT_RISQUE"));
    }

    @Test
    void testGetOption() {
        assertThat(MotoOptions.ASSISTANCE_ZERO_KM.getOption()).isEqualTo("Assistance zéro km");
        assertThat(MotoOptions.EQUIPEMENT_PROTEGER.getOption()).isEqualTo("Équipements protégés");
        assertThat(MotoOptions.GARENTIE_TOUT_RISQUE.getOption()).isEqualTo("Garantie tous risques");
    }
}

