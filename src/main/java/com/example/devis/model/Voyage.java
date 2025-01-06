package com.example.devis.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String destination;

    @Min(1)
    private int nombreVoyageurs;

    @NotNull
    private double coutBase;

    private String formule;

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getNombreVoyageurs() {
        return nombreVoyageurs;
    }

    public void setNombreVoyageurs(int nombreVoyageurs) {
        this.nombreVoyageurs = nombreVoyageurs;
    }

    public double getCoutBase() {
        return coutBase;
    }

    public void setCoutBase(double coutBase) {
        this.coutBase = coutBase;
    }

    public String getFormule() {
        return formule;
    }

    public void setFormule(String formule) {
        this.formule = formule;
    }
}
