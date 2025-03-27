package com.example.devis.Enum.Options;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProfessionelleOptions {

	DOCUMENTS("Perte de documents",4.0),
	MATERIEL("Matériel pro couvert",8.0),
	JURIDIQUE("Assistance juridique à l’étranger",6.0);
	
	private String option;
	private double prix;

	private ProfessionelleOptions(String option, double prix) {
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
    public static ProfessionelleOptions fromString(String value) {
        for (ProfessionelleOptions options : ProfessionelleOptions.values()) {
            if (options.option.equalsIgnoreCase(value)) {
                return options;
            }
        }
        throw new IllegalArgumentException("Option inconnue : " + value);
    }
	
}
