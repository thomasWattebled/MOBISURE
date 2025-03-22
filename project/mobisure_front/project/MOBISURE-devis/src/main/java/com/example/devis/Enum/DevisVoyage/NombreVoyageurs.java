package com.example.devis.Enum.DevisVoyage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum NombreVoyageurs {
    UN("une personne"),
    DEUX_A_TROIS("deux à trois"),
    QUATRE_A_SIX("quatres à six"),
    PLUS_DE_SIX("plus de six");

    private final String facteur;

    NombreVoyageurs(String facteur) {
        this.facteur = facteur;
    }

    @JsonValue
    public String getFacteur() {
        return facteur;
    }
    
    @JsonCreator
    public static NombreVoyageurs fromString(String value) {
        for (NombreVoyageurs nbPersonnes : NombreVoyageurs.values()) {
            if (nbPersonnes.facteur.equalsIgnoreCase(value)) {
                return nbPersonnes;
            }
        }
        throw new IllegalArgumentException("Nombre inconnue : " + value);
    }
    
}
