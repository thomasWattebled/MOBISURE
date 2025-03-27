package com.example.devis.Enum.DevisVehicule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MarqueMotoTest {

    @Test
    void testFromString_validValue() {
        assertThat(MarqueMoto.fromString("ducati")).isEqualTo(MarqueMoto.DUCATI);
        assertThat(MarqueMoto.fromString("harley")).isEqualTo(MarqueMoto.HARLEY);
        assertThat(MarqueMoto.fromString("indian")).isEqualTo(MarqueMoto.INDIAN);
        assertThat(MarqueMoto.fromString("triumph")).isEqualTo(MarqueMoto.TRIUMPH);
        assertThat(MarqueMoto.fromString("ktm")).isEqualTo(MarqueMoto.KTM);
        assertThat(MarqueMoto.fromString("yamaha")).isEqualTo(MarqueMoto.YAMAHA);
        assertThat(MarqueMoto.fromString("honda")).isEqualTo(MarqueMoto.HONDA);
        assertThat(MarqueMoto.fromString("kawasaki")).isEqualTo(MarqueMoto.KAWASAKI);
        assertThat(MarqueMoto.fromString("suzuki")).isEqualTo(MarqueMoto.SUZUKI);
    }

    @Test
    void testFromString_invalidValue() {
        assertThrows(IllegalArgumentException.class, () -> MarqueMoto.fromString("invalid"));
    }

    @Test
    void testEnumValues() {
        assertThat(MarqueMoto.DUCATI).isEqualTo(MarqueMoto.valueOf("DUCATI"));
        assertThat(MarqueMoto.HARLEY).isEqualTo(MarqueMoto.valueOf("HARLEY"));
        assertThat(MarqueMoto.INDIAN).isEqualTo(MarqueMoto.valueOf("INDIAN"));
        assertThat(MarqueMoto.TRIUMPH).isEqualTo(MarqueMoto.valueOf("TRIUMPH"));
        assertThat(MarqueMoto.KTM).isEqualTo(MarqueMoto.valueOf("KTM"));
        assertThat(MarqueMoto.YAMAHA).isEqualTo(MarqueMoto.valueOf("YAMAHA"));
        assertThat(MarqueMoto.HONDA).isEqualTo(MarqueMoto.valueOf("HONDA"));
        assertThat(MarqueMoto.KAWASAKI).isEqualTo(MarqueMoto.valueOf("KAWASAKI"));
        assertThat(MarqueMoto.SUZUKI).isEqualTo(MarqueMoto.valueOf("SUZUKI"));
    }
}

