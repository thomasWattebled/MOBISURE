package com.example.devis.Enum;

public enum NombreVoyageurs {
    UN(1.0),
    DEUX_A_TROIS(1.05),
    QUATRE_A_SIX(1.1),
    PLUS_DE_SIX(1.25);

    private final double facteur;

    NombreVoyageurs(double facteur) {
        this.facteur = facteur;
    }

    public double getFacteur() {
        return facteur;
    }
}
