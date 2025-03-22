package mobisure.project.entity;

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
    private double coutBase; // Stores the calculated price

    private String formule;

    @Min(0)
    private double distance; // Distance traveled in km

    @Min(0)
    private double emissionCO2; // CO₂ emissions in kg

    @Min(1)
    private int dureeSemaines; // Travel duration in weeks

    private boolean professionnel; // Is it a business trip?

    // Getters and Setters
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getEmissionCO2() {
        return emissionCO2;
    }

    public void setEmissionCO2(double emissionCO2) {
        this.emissionCO2 = emissionCO2;
    }

    public int getDureeSemaines() {
        return dureeSemaines;
    }

    public void setDureeSemaines(int dureeSemaines) {
        this.dureeSemaines = dureeSemaines;
    }

    public boolean isProfessionnel() {
        return professionnel;
    }

    public void setProfessionnel(boolean professionnel) {
        this.professionnel = professionnel;
    }
}
