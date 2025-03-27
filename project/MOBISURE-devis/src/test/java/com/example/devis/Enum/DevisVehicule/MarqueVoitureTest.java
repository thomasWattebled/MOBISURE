package com.example.devis.Enum.DevisVehicule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MarqueVoitureTest {

    @Test
    void testFromString_validValue() {
        assertThat(MarqueVoiture.fromString("bmw")).isEqualTo(MarqueVoiture.BMW);
        assertThat(MarqueVoiture.fromString("mercedes")).isEqualTo(MarqueVoiture.MERCEDES);
        assertThat(MarqueVoiture.fromString("ford")).isEqualTo(MarqueVoiture.FORD);
        assertThat(MarqueVoiture.fromString("audi")).isEqualTo(MarqueVoiture.AUDI);
        assertThat(MarqueVoiture.fromString("volkswagen")).isEqualTo(MarqueVoiture.VOLKSWAGEN);
        assertThat(MarqueVoiture.fromString("toyota")).isEqualTo(MarqueVoiture.TOYOTA);
        assertThat(MarqueVoiture.fromString("nissan")).isEqualTo(MarqueVoiture.NISSAN);
        assertThat(MarqueVoiture.fromString("honda")).isEqualTo(MarqueVoiture.HONDA);
        assertThat(MarqueVoiture.fromString("renault")).isEqualTo(MarqueVoiture.RENAULT);
        assertThat(MarqueVoiture.fromString("peugeot")).isEqualTo(MarqueVoiture.PEUGEOT);
    }

    @Test
    void testFromString_invalidValue() {
        assertThrows(IllegalArgumentException.class, () -> MarqueVoiture.fromString("invalid"));
    }

    @Test
    void testEnumValues() {
        assertThat(MarqueVoiture.BMW).isEqualTo(MarqueVoiture.valueOf("BMW"));
        assertThat(MarqueVoiture.MERCEDES).isEqualTo(MarqueVoiture.valueOf("MERCEDES"));
        assertThat(MarqueVoiture.FORD).isEqualTo(MarqueVoiture.valueOf("FORD"));
        assertThat(MarqueVoiture.AUDI).isEqualTo(MarqueVoiture.valueOf("AUDI"));
        assertThat(MarqueVoiture.VOLKSWAGEN).isEqualTo(MarqueVoiture.valueOf("VOLKSWAGEN"));
        assertThat(MarqueVoiture.TOYOTA).isEqualTo(MarqueVoiture.valueOf("TOYOTA"));
        assertThat(MarqueVoiture.NISSAN).isEqualTo(MarqueVoiture.valueOf("NISSAN"));
        assertThat(MarqueVoiture.HONDA).isEqualTo(MarqueVoiture.valueOf("HONDA"));
        assertThat(MarqueVoiture.RENAULT).isEqualTo(MarqueVoiture.valueOf("RENAULT"));
        assertThat(MarqueVoiture.PEUGEOT).isEqualTo(MarqueVoiture.valueOf("PEUGEOT"));
    }
}

