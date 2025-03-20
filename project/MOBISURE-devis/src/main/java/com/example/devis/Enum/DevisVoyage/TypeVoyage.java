package com.example.devis.Enum.DevisVoyage;

public enum TypeVoyage {
    VACANCES(50, 50),  // 50€ par 1000km et 50€ par kg CO2
    PROFESSIONNEL(40, 40);  // 40€ par 1000km et 40€ par kg CO2

    private final double coutDistance;
    private final double coutCO2;

    TypeVoyage(double coutDistance, double coutCO2) {
        this.coutDistance = coutDistance;
        this.coutCO2 = coutCO2;
    }

    public double getCoutDistance() {
        return coutDistance;
    }

    public double getCoutCO2() {
        return coutCO2;
    }
}
