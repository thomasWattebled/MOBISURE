package com.example.devis.Enum.DevisVoyage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class NombreVoyageursTest {

    @Test
    void testFromString_validValue() {
        assertThat(NombreVoyageurs.fromString("une personne")).isEqualTo(NombreVoyageurs.UN);
        assertThat(NombreVoyageurs.fromString("deux à trois")).isEqualTo(NombreVoyageurs.DEUX_A_TROIS);
        assertThat(NombreVoyageurs.fromString("quatres à six")).isEqualTo(NombreVoyageurs.QUATRE_A_SIX);
        assertThat(NombreVoyageurs.fromString("plus de six")).isEqualTo(NombreVoyageurs.PLUS_DE_SIX);
    }

    @Test
    void testFromString_invalidValue() {
        assertThrows(IllegalArgumentException.class, () -> NombreVoyageurs.fromString("invalid"));
    }

    @Test
    void testEnumValues() {
        assertThat(NombreVoyageurs.UN).isEqualTo(NombreVoyageurs.valueOf("UN"));
        assertThat(NombreVoyageurs.DEUX_A_TROIS).isEqualTo(NombreVoyageurs.valueOf("DEUX_A_TROIS"));
        assertThat(NombreVoyageurs.QUATRE_A_SIX).isEqualTo(NombreVoyageurs.valueOf("QUATRE_A_SIX"));
        assertThat(NombreVoyageurs.PLUS_DE_SIX).isEqualTo(NombreVoyageurs.valueOf("PLUS_DE_SIX"));
    }

    @Test
    void testGetFacteur() {
        assertThat(NombreVoyageurs.UN.getFacteur()).isEqualTo("une personne");
        assertThat(NombreVoyageurs.DEUX_A_TROIS.getFacteur()).isEqualTo("deux à trois");
        assertThat(NombreVoyageurs.QUATRE_A_SIX.getFacteur()).isEqualTo("quatres à six");
        assertThat(NombreVoyageurs.PLUS_DE_SIX.getFacteur()).isEqualTo("plus de six");
    }
}
