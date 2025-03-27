package com.example.devis.Enum.Options;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VoitureOptions {

	ASSISTANCE_ZERO_KM("Assistance zéro km",6.0),
	VEHICULE_REMPLACEMENT("Véhicule de remplacement",12.0),
	BRIS_DE_GLACE("Bris de glace",7.0);
	
	private final String option;
	private double prix;

	private VoitureOptions(String option, double prix) {
		this.option = option;
		this.prix = prix;
	}
	
	@JsonValue
    public String getOption() {
        return option;
    }
	
	public double getPrix() {
		return this.prix;
	}
	
	@JsonCreator
    public static VoitureOptions fromString(String value) {
        for (VoitureOptions options : VoitureOptions.values()) {
            if (options.option.equalsIgnoreCase(value)) {
                return options;
            }
        }
        throw new IllegalArgumentException("Option inconnue : " + value);
    }
	
}
