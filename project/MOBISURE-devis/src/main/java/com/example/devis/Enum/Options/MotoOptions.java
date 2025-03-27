package com.example.devis.Enum.Options;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MotoOptions {

	ASSISTANCE_ZERO_KM("Assistance zéro km",8.0),
	EQUIPEMENT_PROTEGER("Équipements protégés",5.0),
	GARENTIE_TOUT_RISQUE("Garantie tous risques",10.0);
	
	private final String option;
	private final double prix;

	private MotoOptions(String option,double prix) {
		this.option = option;
		this.prix = prix;
	}

	@JsonValue
	public String getOption() {
		return option;
	}
	
	public double getPrix() {
        return prix;
    }
	
	@JsonCreator
    public static MotoOptions fromString(String value) {
        for (MotoOptions options : MotoOptions.values()) {
            if (options.option.equalsIgnoreCase(value)) {
                return options;
            }
        }
        throw new IllegalArgumentException("Option inconnue : " + value);
    }
	
	
	
	
}
