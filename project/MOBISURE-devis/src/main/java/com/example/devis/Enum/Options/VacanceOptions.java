package com.example.devis.Enum.Options;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VacanceOptions {

	ANNULATION("Annulation toutes causes",10.0),
	BAGGAGE("Bagages assurés",5.0),
	FRAIS_MEDICAUX_ETRANGER("Frais médicaux à l’étranger",15.0);
	
	private String option;
	private double prix;

	private VacanceOptions(String option, double prix) {
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
    public static VacanceOptions fromString(String value) {
        for (VacanceOptions options : VacanceOptions.values()) {
            if (options.option.equalsIgnoreCase(value)) {
                return options;
            }
        }
        throw new IllegalArgumentException("Option inconnue : " + value);
    }
	
}
