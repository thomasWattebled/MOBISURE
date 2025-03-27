package com.example.devis.Enum;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DureeAssuranceTest {

    @Test
    void testFromString_validValue() {
        assertThat(DureeAssurance.fromString("1 semaine")).isEqualTo(DureeAssurance.SEMAINE);
        assertThat(DureeAssurance.fromString("2 semaines")).isEqualTo(DureeAssurance.DEUX_SEMAINES);
        assertThat(DureeAssurance.fromString("1 mois")).isEqualTo(DureeAssurance.UN_MOIS);
        assertThat(DureeAssurance.fromString("6 mois")).isEqualTo(DureeAssurance.SIX_MOIS);
        assertThat(DureeAssurance.fromString("1 an")).isEqualTo(DureeAssurance.UN_AN);
    }

    @Test
    void testFromString_invalidValue() {
        assertThrows(IllegalArgumentException.class, () -> DureeAssurance.fromString("1 jour"));
    }

    @Test
    void testGetLabel() {
        assertThat(DureeAssurance.SEMAINE.getLabel()).isEqualTo("1 semaine");
        assertThat(DureeAssurance.DEUX_SEMAINES.getLabel()).isEqualTo("2 semaines");
        assertThat(DureeAssurance.UN_MOIS.getLabel()).isEqualTo("1 mois");
        assertThat(DureeAssurance.SIX_MOIS.getLabel()).isEqualTo("6 mois");
        assertThat(DureeAssurance.UN_AN.getLabel()).isEqualTo("1 an");
    }
}
