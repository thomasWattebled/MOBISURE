package com.example.devis.Enum.Options;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class VacanceOptionsTest {

    @Test
    void testFromString_validValue() {
        assertThat(VacanceOptions.fromString("Annulation toutes causes")).isEqualTo(VacanceOptions.ANNULATION);
        assertThat(VacanceOptions.fromString("Bagages assurés")).isEqualTo(VacanceOptions.BAGGAGE);
        assertThat(VacanceOptions.fromString("Frais médicaux à l’étranger")).isEqualTo(VacanceOptions.FRAIS_MEDICAUX_ETRANGER);
    }

    @Test
    void testFromString_invalidValue() {
        assertThrows(IllegalArgumentException.class, () -> VacanceOptions.fromString("Option invalide"));
    }

    @Test
    void testEnumValues() {
        assertThat(VacanceOptions.ANNULATION).isEqualTo(VacanceOptions.valueOf("ANNULATION"));
        assertThat(VacanceOptions.BAGGAGE).isEqualTo(VacanceOptions.valueOf("BAGGAGE"));
        assertThat(VacanceOptions.FRAIS_MEDICAUX_ETRANGER).isEqualTo(VacanceOptions.valueOf("FRAIS_MEDICAUX_ETRANGER"));
    }

    @Test
    void testGetOption() {
        assertThat(VacanceOptions.ANNULATION.getOption()).isEqualTo("Annulation toutes causes");
        assertThat(VacanceOptions.BAGGAGE.getOption()).isEqualTo("Bagages assurés");
        assertThat(VacanceOptions.FRAIS_MEDICAUX_ETRANGER.getOption()).isEqualTo("Frais médicaux à l’étranger");
    }
}
