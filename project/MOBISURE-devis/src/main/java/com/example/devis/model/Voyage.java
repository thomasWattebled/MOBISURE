package com.example.devis.model;

import jakarta.persistence.*;
import lombok.Data;
import com.example.devis.Enum.MarqueVehicule;
import com.example.devis.Enum.Motorisation;

@Entity
@Data  // Lombok génère automatiquement les getters, setters, toString, equals, etc.
public class Voyage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MarqueVehicule marqueVehicule;

    @Enumerated(EnumType.STRING)
    private Motorisation motorisation;

    private int dureeSemaines;
    private int distanceKm;
    private int emissionCO2;
    private int nombreVoyageurs;
    private String typeVoyage;

    private double coutBase;

    // ✅ Ajout manuel du setter si nécessaire
    public void setCoutBase(double coutBase) {
        this.coutBase = coutBase;
    }

    // ✅ Constructeur si besoin (mais avec Lombok, ce n'est pas obligatoire)
    public Voyage(Long id, MarqueVehicule marqueVehicule, Motorisation motorisation, int dureeSemaines, int distanceKm, int emissionCO2, int nombreVoyageurs, String typeVoyage, double coutBase) {
        this.id = id;
        this.marqueVehicule = marqueVehicule;
        this.motorisation = motorisation;
        this.dureeSemaines = dureeSemaines;
        this.distanceKm = distanceKm;
        this.emissionCO2 = emissionCO2;
        this.nombreVoyageurs = nombreVoyageurs;
        this.typeVoyage = typeVoyage;
        this.coutBase = coutBase;
    }
    public Voyage() {
        // Constructeur vide nécessaire pour Hibernate
    }

}
