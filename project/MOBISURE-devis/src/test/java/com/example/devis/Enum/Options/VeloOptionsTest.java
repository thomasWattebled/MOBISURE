package com.example.devis.Enum.Options;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class VeloOptionsTest {

    @Test
    void testFromString_validValue() {
        assertThat(VeloOptions.fromString("Protection contre le vandalisme")).isEqualTo(VeloOptions.PROTECTION_VANDALISME);
        assertThat(VeloOptions.fromString("Assistance crevaison")).isEqualTo(VeloOptions.ASSISTANCE_CREVAISON);
    }

    @Test
    void testFromString_invalidValue() {
        assertThrows(IllegalArgumentException.class, () -> VeloOptions.fromString("Option invalide"));
    }

    @Test
    void testEnumValues() {
        assertThat(VeloOptions.PROTECTION_VANDALISME).isEqualTo(VeloOptions.valueOf("PROTECTION_VANDALISME"));
        assertThat(VeloOptions.ASSISTANCE_CREVAISON).isEqualTo(VeloOptions.valueOf("ASSISTANCE_CREVAISON"));
    }

    @Test
    void testGetOption() {
        assertThat(VeloOptions.PROTECTION_VANDALISME.getOption()).isEqualTo("Protection contre le vandalisme");
        assertThat(VeloOptions.ASSISTANCE_CREVAISON.getOption()).isEqualTo("Assistance crevaison");
    }
}
