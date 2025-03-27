package com.example.devis.Enum.DevisVehicule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class UtilisationVehiculeTest {

    @Test
    void testFromString_validValue() {
        assertThat(UtilisationVehicule.fromString("personnel")).isEqualTo(UtilisationVehicule.PERSONNEL);
        assertThat(UtilisationVehicule.fromString("professionnelle")).isEqualTo(UtilisationVehicule.PROFESSIONNELLE);
    }

    @Test
    void testFromString_invalidValue() {
        assertThrows(IllegalArgumentException.class, () -> UtilisationVehicule.fromString("invalid"));
    }

    @Test
    void testEnumValues() {
        assertThat(UtilisationVehicule.PERSONNEL).isEqualTo(UtilisationVehicule.valueOf("PERSONNEL"));
        assertThat(UtilisationVehicule.PROFESSIONNELLE).isEqualTo(UtilisationVehicule.valueOf("PROFESSIONNELLE"));
    }
}
