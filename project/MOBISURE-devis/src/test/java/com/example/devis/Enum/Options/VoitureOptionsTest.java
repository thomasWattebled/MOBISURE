package com.example.devis.Enum.Options;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class VoitureOptionsTest {

    @Test
    void testFromString_validValue() {
        assertThat(VoitureOptions.fromString("Assistance zéro km")).isEqualTo(VoitureOptions.ASSISTANCE_ZERO_KM);
        assertThat(VoitureOptions.fromString("Véhicule de remplacement")).isEqualTo(VoitureOptions.VEHICULE_REMPLACEMENT);
        assertThat(VoitureOptions.fromString("Bris de glace")).isEqualTo(VoitureOptions.BRIS_DE_GLACE);
    }

    @Test
    void testFromString_invalidValue() {
        assertThrows(IllegalArgumentException.class, () -> VoitureOptions.fromString("Option invalide"));
    }

    @Test
    void testEnumValues() {
        assertThat(VoitureOptions.ASSISTANCE_ZERO_KM).isEqualTo(VoitureOptions.valueOf("ASSISTANCE_ZERO_KM"));
        assertThat(VoitureOptions.VEHICULE_REMPLACEMENT).isEqualTo(VoitureOptions.valueOf("VEHICULE_REMPLACEMENT"));
        assertThat(VoitureOptions.BRIS_DE_GLACE).isEqualTo(VoitureOptions.valueOf("BRIS_DE_GLACE"));
    }

    @Test
    void testGetOption() {
        assertThat(VoitureOptions.ASSISTANCE_ZERO_KM.getOption()).isEqualTo("Assistance zéro km");
        assertThat(VoitureOptions.VEHICULE_REMPLACEMENT.getOption()).isEqualTo("Véhicule de remplacement");
        assertThat(VoitureOptions.BRIS_DE_GLACE.getOption()).isEqualTo("Bris de glace");
    }
}
