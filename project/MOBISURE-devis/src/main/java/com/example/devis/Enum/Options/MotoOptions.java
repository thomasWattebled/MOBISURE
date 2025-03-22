package com.example.devis.Enum.Options;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MotoOptions {

	ASSISTANCE_ZERO_KM("Assistance zéro km"),
	EQUIPEMENT_PROTEGER("Équipements protégés"),
	GARENTIE_TOUT_RISQUE("Garantie tous risques");
	
	private final String option;

	private MotoOptions(String option) {
		this.option = option;
	}

	@JsonValue
	public String getOption() {
		return option;
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
