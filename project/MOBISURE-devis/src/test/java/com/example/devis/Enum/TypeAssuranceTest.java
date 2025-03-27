package com.example.devis.Enum;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TypeAssuranceTest {

    @Test
    void testFromString_validValue() {
        assertThat(TypeAssurance.fromString("voiture")).isEqualTo(TypeAssurance.VOITURE);
        assertThat(TypeAssurance.fromString("moto")).isEqualTo(TypeAssurance.MOTO);
        assertThat(TypeAssurance.fromString("velo")).isEqualTo(TypeAssurance.VELO);
        assertThat(TypeAssurance.fromString("vacances")).isEqualTo(TypeAssurance.VACANCES);
        assertThat(TypeAssurance.fromString("professionnelle")).isEqualTo(TypeAssurance.PROFESSIONNELLE);
    }

    @Test
    void testFromString_invalidValue() {
        assertThrows(IllegalArgumentException.class, () -> TypeAssurance.fromString("invalid"));
    }

    @Test
    void testEnumValues() {
        assertThat(TypeAssurance.VOITURE).isEqualTo(TypeAssurance.valueOf("VOITURE"));
        assertThat(TypeAssurance.MOTO).isEqualTo(TypeAssurance.valueOf("MOTO"));
        assertThat(TypeAssurance.VELO).isEqualTo(TypeAssurance.valueOf("VELO"));
        assertThat(TypeAssurance.VACANCES).isEqualTo(TypeAssurance.valueOf("VACANCES"));
        assertThat(TypeAssurance.PROFESSIONNELLE).isEqualTo(TypeAssurance.valueOf("PROFESSIONNELLE"));
    }
    
}
