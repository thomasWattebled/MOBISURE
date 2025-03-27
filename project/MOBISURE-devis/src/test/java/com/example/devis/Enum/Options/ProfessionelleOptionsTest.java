package com.example.devis.Enum.Options;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ProfessionelleOptionsTest {

    @Test
    void testFromString_validValue() {
        assertThat(ProfessionelleOptions.fromString("Perte de documents")).isEqualTo(ProfessionelleOptions.DOCUMENTS);
        assertThat(ProfessionelleOptions.fromString("Matériel pro couvert")).isEqualTo(ProfessionelleOptions.MATERIEL);
        assertThat(ProfessionelleOptions.fromString("Assistance juridique à l’étranger")).isEqualTo(ProfessionelleOptions.JURIDIQUE);
    }

    @Test
    void testFromString_invalidValue() {
        assertThrows(IllegalArgumentException.class, () -> ProfessionelleOptions.fromString("Option invalide"));
    }

    @Test
    void testEnumValues() {
        assertThat(ProfessionelleOptions.DOCUMENTS).isEqualTo(ProfessionelleOptions.valueOf("DOCUMENTS"));
        assertThat(ProfessionelleOptions.MATERIEL).isEqualTo(ProfessionelleOptions.valueOf("MATERIEL"));
        assertThat(ProfessionelleOptions.JURIDIQUE).isEqualTo(ProfessionelleOptions.valueOf("JURIDIQUE"));
    }

    @Test
    void testGetOption() {
        assertThat(ProfessionelleOptions.DOCUMENTS.getOption()).isEqualTo("Perte de documents");
        assertThat(ProfessionelleOptions.MATERIEL.getOption()).isEqualTo("Matériel pro couvert");
        assertThat(ProfessionelleOptions.JURIDIQUE.getOption()).isEqualTo("Assistance juridique à l’étranger");
    }
}
