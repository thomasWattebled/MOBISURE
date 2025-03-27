package com.example.devis.Enum.DevisVehicule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MotorisationTest {

    @Test
    void testFromString_validValue() {
        assertThat(Motorisation.fromString("electrique")).isEqualTo(Motorisation.ELECTRIQUE);
        assertThat(Motorisation.fromString("thermique")).isEqualTo(Motorisation.THERMIQUE);
    }

    @Test
    void testFromString_invalidValue() {
        assertThrows(IllegalArgumentException.class, () -> Motorisation.fromString("invalid"));
    }

    @Test
    void testEnumValues() {
        assertThat(Motorisation.ELECTRIQUE).isEqualTo(Motorisation.valueOf("ELECTRIQUE"));
        assertThat(Motorisation.THERMIQUE).isEqualTo(Motorisation.valueOf("THERMIQUE"));
    }
}
